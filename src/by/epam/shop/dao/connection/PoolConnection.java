package by.epam.shop.dao.connection;

import by.epam.shop.exceptions.ConstantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public class PoolConnection {
    private static final String USER_NAME = "root7";
    private static final String USER_PASSWORD = "root";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/salon_db?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private int maxSize;
    private int checkConnectionTimeout;

    private static final Logger LOG = LogManager.getLogger("name");

    private BlockingQueue<ConnectionCompare> freeConnections = new LinkedBlockingQueue<>();
    private Set<ConnectionCompare> usedConnections = new ConcurrentSkipListSet<>();

    private static PoolConnection instance = new PoolConnection();

    public static PoolConnection getInstance() {
        return instance;
    }

    public synchronized ConnectionCompare getConnection() throws ClassNotFoundException, SQLException, ConstantException {
        ConnectionCompare connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    LOG.error("The limit of number of database connections is exceeded");
                    throw new ConstantException();
                }
            } catch (InterruptedException | SQLException e) {
                LOG.error("It is impossible to connect to a database");
                throw new ConstantException(e);
            }
        }
        usedConnections.add(connection);
        return connection;
    }

    public synchronized void freeConnection(ConnectionCompare connection) throws ConstantException {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
            }
        } catch (SQLException e1) {
            LOG.error("It is impossible to return database connection into pool", e1);
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new ConstantException(e2);
            }
        } catch (InterruptedException e) {
            throw new ConstantException(e);
        }
    }

    public synchronized void init(int newMaxSize, int newCheckConnectionTimeout, int startSize) throws ConstantException {
        try {
            destroy();
            Class.forName(DB_DRIVER);
            this.maxSize = newMaxSize;
            this.checkConnectionTimeout = newCheckConnectionTimeout;
            for (int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {

            LOG.error("It is impossible to initialize connection pool", e);
            throw new ConstantException(e);
        }
    }

    private ConnectionCompare createConnection() throws SQLException {
        return new ConnectionCompare(DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASSWORD));
    }

    public synchronized void destroy() throws ConstantException {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (ConnectionCompare connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                throw new ConstantException(e);
            }
        }
        usedConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
