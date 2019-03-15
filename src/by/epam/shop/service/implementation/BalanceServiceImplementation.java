package by.epam.shop.service.implementation;

import by.epam.shop.dao.requests.BalanceDAOImplementation;
import by.epam.shop.entity.Balance;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.BalanceService;

import java.sql.SQLException;

public class BalanceServiceImplementation implements BalanceService {
    @Override
    public Balance searchByOwner(Integer userID) throws SQLException, ConstantException {
        BalanceDAOImplementation balanceDAO = new BalanceDAOImplementation();
        try {
            balanceDAO.setConnection();
            return balanceDAO.findByOwner(userID);
        } finally {
            balanceDAO.closeConnection();
        }
    }

    @Override
    public Integer addBalance(Balance balance) throws SQLException, ConstantException  {
        BalanceDAOImplementation balanceDAO = new BalanceDAOImplementation();
        try {
            balanceDAO.setConnection();
            return balanceDAO.create(balance);
        } finally {
            balanceDAO.closeConnection();
        }
    }

    @Override
    public Balance searchByID(Integer identity) throws SQLException, ConstantException {
        BalanceDAOImplementation balanceDAO = new BalanceDAOImplementation();
        try {
            balanceDAO.setConnection();
            return balanceDAO.findByID(identity);
        } finally {
            balanceDAO.closeConnection();
        }
    }

    @Override
    public void setData(Balance balance) throws SQLException, ConstantException {
        BalanceDAOImplementation balanceDAO = new BalanceDAOImplementation();
        try {
            balanceDAO.setConnection();
            balanceDAO.update(balance);
        } finally {
            balanceDAO.closeConnection();
        }
    }

    @Override
    public void deleteBYID(Integer identity) throws SQLException, ConstantException {
        BalanceDAOImplementation balanceDAO = new BalanceDAOImplementation();
        try {
            balanceDAO.setConnection();
            balanceDAO.delete(identity);
        } finally {
            balanceDAO.closeConnection ();
        }
    }
}
