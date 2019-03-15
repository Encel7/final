package by.epam.shop.service;

import by.epam.shop.entity.Balance;
import by.epam.shop.exceptions.ConstantException;

import java.sql.SQLException;

public interface BalanceService {

    Balance searchByOwner(Integer userID) throws SQLException, ConstantException;

    Integer addBalance(Balance balance) throws SQLException, ConstantException;

    Balance searchByID(Integer identity) throws SQLException, ConstantException;

    void setData(Balance balance) throws SQLException, ConstantException;

    void deleteBYID(Integer identity) throws SQLException, ConstantException;
}
