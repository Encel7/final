package by.epam.shop.service;

import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> takeAllUsers() throws SQLException, ConstantException;

    List<User> searchByPersonal(String login, String password) throws SQLException, ConstantException;

    List<User> searchByRole(String role) throws SQLException, ConstantException;

    User searchByID(Integer identity) throws SQLException, ConstantException;

    Integer addUser(User user) throws SQLException, ConstantException;

    void setData(User user) throws SQLException, ConstantException;

    void deleteByID(Integer identity) throws SQLException, ConstantException;
}
