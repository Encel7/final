package by.epam.shop.dao;

import by.epam.shop.entity.Balance;
import by.epam.shop.exceptions.ConstantException;

public interface BalanceDAO extends DAOBase<Balance> {

    Balance findByOwner (Integer userID) throws ConstantException;
}
