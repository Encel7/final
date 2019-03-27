package by.epam.shop.service.implementation;

import by.epam.shop.dao.requests.OrderDAOImplementation;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImplementation implements OrderService {
    @Override
    public List<Souvenir> takeAllSouvenirs(Integer orderID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.findAllSouvenirs(orderID);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public Integer calculateTotalPrice(Integer orderID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.countTotalPrice(orderID);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public List<Order> searchByUsers(Integer userID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.findByUser(userID);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public Order searchActiveOrder(Integer userID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.findActiveOrder(userID);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public List<Order> searchInactiveOrder(Integer userID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.findInactiveOrder(userID);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public Integer addOrder(Order order) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.create(order);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public Integer addSouvenirOrder(Integer orderID, Integer souvenirID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.addSouvenir(orderID, souvenirID);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public Order searchByID(Integer identity) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            return orderDAO.findByID(identity);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public void setData(Order order) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            orderDAO.update(order);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public void deleteByID(Integer identity) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            orderDAO.delete(identity);
        } finally {
            orderDAO.closeConnection();
        }
    }

    @Override
    public void deleteSouvenir(Integer orderID, Integer souvenirID) throws SQLException, ConstantException {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        try {
            orderDAO.setConnection();
            orderDAO.deleteSouvenir(orderID, souvenirID);
        } finally {
            orderDAO.closeConnection();
        }
    }
}
