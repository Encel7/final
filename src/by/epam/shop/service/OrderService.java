package by.epam.shop.service;

import by.epam.shop.entity.Order;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    List<Souvenir> takeAllSouvenir(Integer orderID) throws SQLException, ConstantException;

    Integer calculateTotalPrice(Integer orderID) throws SQLException, ConstantException;

    List<Order> searchByUsers(Integer userID) throws SQLException, ConstantException;

    Order searchActiveOrder(Integer userID) throws SQLException, ConstantException;

    List<Order> searchInactiveOrder(Integer userID) throws SQLException, ConstantException;

    Integer addOrder(Order order) throws SQLException, ConstantException;

    Integer addSouvenirOrder(Integer orderID, Integer tattooID) throws SQLException, ConstantException;

    Order searchByID(Integer identity) throws SQLException, ConstantException;

    void setData(Order order) throws SQLException, ConstantException;

    void deleteByID(Integer identity) throws SQLException, ConstantException;

    void deleteSouvenir(Integer orderID, Integer tattooID) throws SQLException, ConstantException;
}
