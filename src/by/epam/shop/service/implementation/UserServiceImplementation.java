package by.epam.shop.service.implementation;

import by.epam.shop.dao.requests.UserDAOImplementation;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImplementation implements UserService {
    @Override
    public List<User> takeAllUsers() throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            return userDao.takeAllUsers();
        } finally {
            userDao.closeConnection();
        }
    }

    @Override
    public List<User> searchByPersonal(String login, String password) throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            return userDao.findByPersonal(login, password);
        } finally {
            userDao.closeConnection();
        }
    }

    @Override
    public List<User> searchByRole(String role) throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            return userDao.findByRole(role);
        } finally {
            userDao.closeConnection();
        }
    }

    @Override
    public User searchByID(Integer identity) throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            return userDao.findByID(identity);
        } finally {
            userDao.closeConnection();
        }
    }

    @Override
    public Integer addUser(User user) throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            return userDao.create(user);
        } finally {
            userDao.closeConnection();
        }
    }

    @Override
    public void setData(User user) throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            userDao.update(user);
        } finally {
            userDao.closeConnection();
        }
    }

    @Override
    public void deleteByID(Integer identity) throws SQLException, ConstantException {
        UserDAOImplementation userDao = new UserDAOImplementation();
        try {
            userDao.setConnection();
            userDao.delete(identity);
        } finally {
            userDao.closeConnection();
        }
    }
}
