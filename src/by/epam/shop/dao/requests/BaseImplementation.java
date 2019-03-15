package by.epam.shop.dao.requests;

import by.epam.shop.dao.connection.ConnectionCompare;
import by.epam.shop.dao.connection.PoolConnection;
import by.epam.shop.exceptions.ConstantException;

import java.sql.SQLException;

abstract class BaseImplementation {

    protected ConnectionCompare connection;

    public void setConnection() throws ConstantException {
        try {
            this.connection = PoolConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConstantException(e);
        }
    }

    public void closeConnection() throws SQLException, ConstantException {
        PoolConnection.getInstance().freeConnection(this.connection);}
}
