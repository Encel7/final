package by.epam.shop.dao.requests;

import by.epam.shop.dao.SouvenirDAO;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.UserService;
import by.epam.shop.service.implementation.UserServiceImplementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SouvenirDAOImplementation extends BaseImplementation implements SouvenirDAO {

    private User returnAuthor(Integer authorID) throws SQLException, ClassNotFoundException, ConstantException {
        UserService user = new UserServiceImplementation();
        return user.searchByID(authorID);
    }

    @Override
    public List<Souvenir> findAllSouvenir() throws ConstantException {
        String sql = "SELECT id, size, color, image, price, author_id FROM souvenir";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Souvenir> souvenirs = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = returnAuthor(resultSet.getInt(6));
                souvenirs.add(new Souvenir(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        user));
            }
            return souvenirs;
        } catch (SQLException | ClassNotFoundException | ConstantException e) {
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
    public List<Souvenir> findSmallerThan(Integer maxSize) throws ConstantException {
        String sql = "SELECT id, size, color, image, price, author_id FROM souvenir WHERE size<?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Souvenir> souvenirs = new ArrayList<>();
        User author;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,maxSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                author = returnAuthor(resultSet.getInt(6));
                souvenirs.add(new Souvenir(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        author));
            }
            return souvenirs;
        } catch (SQLException | ClassNotFoundException | ConstantException e) {
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
    public List<Souvenir> cheaperThan(Integer maxPrice) throws ConstantException {
        String sql = "SELECT id, size, color, image, price, author_id FROM souvenir WHERE price<?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Souvenir> souvenirs = new ArrayList<>();
        User author;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,maxPrice);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                author = returnAuthor(resultSet.getInt(6));
                souvenirs.add(new Souvenir(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        author));
            }
            return souvenirs;
        } catch (SQLException e) {
            throw new ConstantException(e);
        } catch (ClassNotFoundException e) {
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
    public List<Souvenir> findByColor(String color) throws ConstantException {
        String sql = "SELECT id, size, color, image, price, author_id FROM souvenir WHERE color=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Souvenir> souvenirs = new ArrayList<>();
        User author;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,color);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                author = returnAuthor(resultSet.getInt(6));
                souvenirs.add(new Souvenir(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        author));
            }
            return souvenirs;
        } catch (SQLException | ClassNotFoundException e) {
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
    public List<Souvenir> findByAuthor(Integer authorID) throws ConstantException {
        String sql = "SELECT id, size, color, image, price, author_id FROM souvenir WHERE author_id<?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Souvenir> souvenirs = new ArrayList<>();
        User author;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,authorID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                author = returnAuthor(resultSet.getInt(6));
                souvenirs.add(new Souvenir(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        author));
            }
            return souvenirs;
        } catch (SQLException | ClassNotFoundException e) {
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
    public Integer create(Souvenir souvenir) throws ConstantException {
        String sql = "INSERT INTO souvenir (size, color, image, price, author_id) VALUES (?,?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, souvenir.getSize());
            statement.setString(2, souvenir.getColor());
            statement.setString(3, souvenir.getImage());
            statement.setInt(4, souvenir.getPrice());
            statement.setInt(5, souvenir.getAuthor().getIdentity());
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
    public Souvenir findByID(Integer identity) throws ConstantException {
        String sql = "SELECT id, size, color, image, price, author_id FROM souvenir WHERE id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Souvenir> souvenirs = new ArrayList<>();
        User author;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,identity);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                author = returnAuthor(resultSet.getInt(6));
                return new Souvenir(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        author);
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
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
    public void update(Souvenir souvenir) throws ConstantException {
        String sql = "UPDATE souvenir SET size=?, color=?, image=?, price=?, author_id=? WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, souvenir.getSize());
            statement.setString(2, souvenir.getColor());
            statement.setString(3, souvenir.getImage());
            statement.setInt(4, souvenir.getPrice());
            statement.setInt(5, souvenir.getAuthor().getIdentity());
            statement.setInt(6, souvenir.getIdentity());
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
        String sql = "DELETE FROM souvenir WHERE id=?";
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
