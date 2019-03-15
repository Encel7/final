package by.epam.shop.dao;

import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;

import java.util.List;

public interface UserDAO extends DAOBase<User> {

    List<User> takeAllUsers() throws ConstantException;

    List<User> findByPersonal (String login, String password) throws ConstantException;

    List<User> findByRole (String role) throws ConstantException;
}
