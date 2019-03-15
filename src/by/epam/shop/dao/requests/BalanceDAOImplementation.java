package by.epam.shop.dao.requests;

import by.epam.shop.dao.BalanceDAO;
import by.epam.shop.entity.Balance;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.UserService;
import by.epam.shop.service.implementation.UserServiceImplementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BalanceDAOImplementation extends BaseImplementation implements BalanceDAO {

    private User returnOwner(Integer ownerID) throws ConstantException {
        UserService user = new UserServiceImplementation();
        try {
            return user.searchByID(ownerID);
        } catch (SQLException e) {
            throw new ConstantException(e);
        }
    }

    @Override
    public Balance findByOwner(Integer userID) throws ConstantException {
        String sql = "SELECT id, user_id, current_balance, overdruft FROM balance WHERE user_id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User owner;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                owner = returnOwner(resultSet.getInt(2));
                return new Balance(resultSet.getInt(1),
                        owner,
                        resultSet.getDouble(3),
                        resultSet.getDouble(4));
            } else {
                return null;
            }
        } catch (SQLException | ConstantException e) {
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
    public Integer create(Balance entity) throws ConstantException {
        String sql = "INSERT INTO balance (user_id, current_balance, overdruft) VALUES (?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getUser().getIdentity());
            statement.setDouble(2, entity.getCurrentBalance());
            statement.setDouble(3, entity.getOverdraft());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch(SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Balance findByID(Integer identity) throws ConstantException {
        String sql = "SELECT id, user_id, current_balance, overdruft FROM balance WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User owner;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,identity);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                owner = returnOwner(resultSet.getInt(2));
                return new Balance(resultSet.getInt(1),
                        owner,
                        resultSet.getDouble(3),
                        resultSet.getDouble(4));
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
    public void update(Balance balance) throws ConstantException {
        String sql = "UPDATE balance SET user_id=?, current_balance=?, overdruft=? WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, balance.getUser().getIdentity());
            statement.setDouble(2, balance.getCurrentBalance());
            statement.setDouble(3, balance.getOverdraft());
            statement.setInt(4, balance.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch(SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(Integer identity) throws ConstantException {
        String sql = "DELETE FROM balance WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new ConstantException(e);
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch(SQLException | NullPointerException e) {
            }
        }
    }
}
