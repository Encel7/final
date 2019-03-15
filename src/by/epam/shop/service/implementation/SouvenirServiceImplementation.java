package by.epam.shop.service.implementation;

import by.epam.shop.dao.requests.SouvenirDAOImplementation;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.SouvenirService;

import java.sql.SQLException;
import java.util.List;

public class SouvenirServiceImplementation implements SouvenirService {
    @Override
    public List<Souvenir> takeAllSouvenir() throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.findAllTattoo();
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchSmallerThan(Integer maxSize) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.findSmallerThan(maxSize);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchCheaperThan(Integer maxPrice) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.cheaperThan(maxPrice);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchByColor(String color) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.findByColor(color);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchByAuthor(Integer authorID) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.findByAuthor(authorID);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public Souvenir searchByID(Integer identity) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.findByID(identity);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public Integer addSouvenir(Souvenir souvenir) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            return tattooDao.create(souvenir);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public void setDate(Souvenir souvenir) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            tattooDao.update(souvenir);
        } finally {
            tattooDao.closeConnection();
        }
    }

    @Override
    public void deleteByID(Integer identity) throws SQLException, ConstantException {
        SouvenirDAOImplementation tattooDao = new SouvenirDAOImplementation();
        try {
            tattooDao.setConnection();
            tattooDao.delete(identity);
        } finally {
            tattooDao.closeConnection();
        }
    }
}
