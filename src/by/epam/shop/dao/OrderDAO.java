package by.epam.shop.dao;

import by.epam.shop.entity.Order;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;

import java.util.List;

public interface OrderDAO extends DAOBase<Order> {

    List<Souvenir> findAllSouvenir(Integer orderID) throws ConstantException;

    Integer countTotalPrice (Integer orderID) throws ConstantException;

    Integer addSouvenir(Integer orderID, Integer tattooID) throws ConstantException;

    List<Order> findByUser (Integer userID) throws ConstantException;

    void deleteSouvenir(Integer orderID, Integer tattooID) throws ConstantException;

    Order findActiveOrder(Integer userID) throws ConstantException;

    List<Order> findInactiveOrder(Integer userID) throws ConstantException;

}
