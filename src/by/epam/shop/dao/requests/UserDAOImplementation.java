package by.epam.shop.dao.requests;

import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAOImplementation extends BaseImplementation implements UserDAO {
    @Override
    public List<User> takeAllUsers() throws ConstantException {
        String sql = "SELECT id, role, first_name, last_name, login, password, discount FROM user";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        Role role;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(2) == 0) {
                    role = Role.USER;
                } else {
                    role = Role.ADMINISTRATOR;
                }
                users.add(new User(resultSet.getInt(1),
                        role,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7) / 100.0));
            }
            return users;
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
    public List<User> findByPersonal(String login, String password) throws ConstantException {
        String sql = "SELECT id, role, first_name, last_name, login, password, discount FROM user WHERE login=? AND password=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        Role role;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(2) == 0) {
                    role = Role.USER;
                } else {
                    role = Role.ADMINISTRATOR;
                }
                users.add(new User(resultSet.getInt(1),
                        role,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        (resultSet.getInt(7) / 100.0)));
            }
            return users;
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
    public List<User> findByRole(String role) throws ConstantException {
        int r;
        Role roleNew;
        if (Objects.equals(role, "admin")) {
            r = 1;
            roleNew = Role.ADMINISTRATOR;
        } else {
            r = 0;
            roleNew = Role.USER;
        }
        String sql = "SELECT id, role, first_name, last_name, login, password, discount FROM user WHERE role=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, r);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1),
                        roleNew,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7) / 100.0));
            }
            return users;
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
    public Integer create(User user) throws ConstantException {
        String sql = "INSERT INTO user (role, first_name, last_name, login, password, discount) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int r;
            if (user.getRole() == Role.ADMINISTRATOR) {
                r = 1;
            } else {
                r = 0;
            }
            statement.setInt(1, r);
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setInt(6, (int) (user.getDiscount() * 100));
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
    public User findByID(Integer identity) throws ConstantException {
        String sql = "SELECT id, role, first_name, last_name, login, password, discount FROM user WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt(2) == 0) {
                    role = Role.USER;
                } else {
                    role = Role.ADMINISTRATOR;
                }
                return new User(resultSet.getInt(1),
                        role,
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7) / 100.0);
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
            } catch (SQLException e) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void update(User user) throws ConstantException {
        String sql = "UPDATE user SET role=?, first_name=?, last_name=?, login=?, password=?, discount=? WHERE id=?";
        PreparedStatement statement = null;
        try {
            int r;
            if (user.getRole() == Role.ADMINISTRATOR) {
                r = 1;
            } else {
                r = 0;
            }
            statement = connection.prepareStatement(sql);
            statement.setInt(1, r);
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setInt(6, (int) (user.getDiscount() * 100));
            statement.setInt(7, user.getIdentity());
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
        String sql = "DELETE FROM user WHERE id=?";
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
