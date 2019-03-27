package by.epam.shop.service.implementation;

import by.epam.shop.dao.requests.SouvenirDAOImplementation;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.SouvenirService;

import java.sql.SQLException;
import java.util.List;

public class SouvenirServiceImplementation implements SouvenirService {
    @Override
    public List<Souvenir> takeAllSouvenirs() throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.findAllSouvenir();
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchSmallerThan(Integer maxSize) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.findSmallerThan(maxSize);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchCheaperThan(Integer maxPrice) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.cheaperThan(maxPrice);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchByColor(String color) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.findByColor(color);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public List<Souvenir> searchByAuthor(Integer authorID) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.findByAuthor(authorID);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public Souvenir searchByID(Integer identity) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.findByID(identity);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public Integer addSouvenir(Souvenir souvenir) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            return souvenirDao.create(souvenir);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public void setDate(Souvenir souvenir) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            souvenirDao.update(souvenir);
        } finally {
            souvenirDao.closeConnection();
        }
    }

    @Override
    public void deleteByID(Integer identity) throws SQLException, ConstantException {
        SouvenirDAOImplementation souvenirDao = new SouvenirDAOImplementation();
        try {
            souvenirDao.setConnection();
            souvenirDao.delete(identity);
        } finally {
            souvenirDao.closeConnection();
        }
    }
}
