package by.epam.shop.dao.requests;

import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.SouvenirService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.implementation.SouvenirServiceImplementation;
import by.epam.shop.service.implementation.UserServiceImplementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImplementation extends BaseImplementation implements OrderDAO {

    private User returnUser(Integer userID) throws ConstantException {
        UserService user = new UserServiceImplementation();
        try {
            return user.searchByID(userID);
        } catch (SQLException e) {
            throw new ConstantException(e);
        }
    }

    @Override
    public List<Souvenir> findAllSouvenirs(Integer orderID) throws ConstantException {
        String sql = "SELECT souvenir_id FROM souvenir_order WHERE order_id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SouvenirService souvenirService = new SouvenirServiceImplementation();
        List<Integer> integers = new ArrayList<>();
        List<Souvenir> souvenirs = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                integers.add(resultSet.getInt("souvenir_id"));
            }

            for (Integer i : integers) {
                souvenirs.add(souvenirService.searchByID(i));
            }

            return souvenirs;
        } catch (SQLException e) {
            throw new ConstantException(e);
        }
    }

    @Override
    public Integer countTotalPrice(Integer orderID) throws ConstantException {
        String sql = "SELECT SUM(souvenir.price) as summ FROM souvenir JOIN souvenir_order ON souvenir.id = souvenir_order.souvenir_id JOIN user_order ON user_order.id = souvenir_order.order_id WHERE user_order.id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("summ");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Integer addSouvenir(Integer orderID, Integer souvenirID) throws ConstantException {

        String sql = "INSERT INTO souvenir_order (order_id, souvenir_id) VALUES (?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderID);
            statement.setInt(2, souvenirID);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Order> findByUser(Integer userID) throws ConstantException {
        String sql = "SELECT id, paid, total_price, date, user_id FROM user_order WHERE user_id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        User user;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = returnUser(resultSet.getInt(5));
                orders.add(new Order(resultSet.getInt(1),
                        resultSet.getBoolean(2),
                        resultSet.getDouble(3),
                        resultSet.getDate(4),
                        user));
            }
            return orders;
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void deleteSouvenir(Integer orderID, Integer souvenirID) throws ConstantException {
        String sql = "DELETE FROM souvenir_order WHERE order_id=? AND souvenir_id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderID);
            statement.setInt(2,souvenirID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Order findActiveOrder(Integer userID) throws ConstantException {
        String sql = "SELECT id, paid, total_price, date, user_id FROM user_order WHERE user_id=? AND paid=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setBoolean(2, false);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = returnUser(resultSet.getInt(5));
                return new Order(resultSet.getInt(1),
                        resultSet.getBoolean(2),
                        resultSet.getDouble(3),
                        resultSet.getDate(4),
                        user);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Order> findInactiveOrder(Integer userID) throws ConstantException {
        String sql = "SELECT id, paid, total_price, date, user_id FROM user_order WHERE user_id=? AND paid=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user;
        List<Order> orders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setBoolean(2, true);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = returnUser(resultSet.getInt(5));
                orders.add(new Order(resultSet.getInt(1),
                        resultSet.getBoolean(2),
                        resultSet.getDouble(3),
                        resultSet.getDate(4),
                        user));
            }
            return orders;
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }


    @Override
    public Integer create(Order order) throws ConstantException {
        String sql = "INSERT INTO user_order (paid, total_price, date, user_id) VALUES (?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, order.isPaid());
            statement.setDouble(2, order.getTotalPrice());
            statement.setDate(3, order.getDate());
            statement.setInt(4, order.getUser().getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Order findByID(Integer identity) throws ConstantException {
        String sql = "SELECT id, paid, total_price, date, user_id FROM user_order WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        User user;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = returnUser(resultSet.getInt(5));
                return new Order(resultSet.getInt(1),
                        resultSet.getBoolean(2),
                        resultSet.getDouble(3),
                        resultSet.getDate(4),
                        user);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void update(Order order) throws ConstantException {
        String sql = "UPDATE user_order SET paid=?, total_price=?, date=?, user_id=? WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, order.isPaid());
            statement.setDouble(2, order.getTotalPrice());
            statement.setDate(3, order.getDate());
            statement.setInt(4, order.getUser().getIdentity());
            statement.setInt(5, order.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(Integer identity) throws ConstantException {
        String sql = "DELETE FROM user_order WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
