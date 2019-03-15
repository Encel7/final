package by.epam.shop.servlet;

import by.epam.shop.command.Command;
import by.epam.shop.command.factory.CommandFactory;
import by.epam.shop.dao.connection.PoolConnection;
import by.epam.shop.exceptions.ConstantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@MultipartConfig
public class MainServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger("name");
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;


    public void init() {
        try {
            PoolConnection.getInstance().init(DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT, DB_POOL_START_SIZE);
        } catch (ConstantException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        if (request.getParameter("command") != null) {
            LOG.info("Parameter " + request.getParameter("command"));
            Command command = commandFactory.createCommand((String) request.getParameter("command"));
            command.execute(request, response);
        } else {
            LOG.info("Attribute " + request.getAttribute("command"));
            Command command = commandFactory.createCommand((String) request.getAttribute("command"));
            command.execute(request, response);
        }
    }


    @Override
    public void destroy() {
        try {
            PoolConnection.getInstance().destroy();
        } catch (ConstantException e) {
            e.printStackTrace();
        }
    }
}
