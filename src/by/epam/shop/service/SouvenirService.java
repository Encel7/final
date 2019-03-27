package by.epam.shop.service;

import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;

import java.sql.SQLException;
import java.util.List;

public interface SouvenirService {

    List<Souvenir> takeAllSouvenirs() throws SQLException, ConstantException;

    List<Souvenir> searchSmallerThan(Integer maxSize) throws SQLException, ConstantException;

    List<Souvenir> searchCheaperThan(Integer maxPrice) throws SQLException, ConstantException;

    List<Souvenir> searchByColor(String color) throws SQLException, ConstantException;

    List<Souvenir> searchByAuthor(Integer authorID) throws SQLException, ConstantException;

    Souvenir searchByID(Integer identity) throws SQLException, ConstantException;

    Integer addSouvenir(Souvenir souvenir) throws SQLException, ConstantException;

    void setDate(Souvenir souvenir) throws SQLException, ConstantException;

    void deleteByID(Integer identity) throws SQLException, ConstantException;
}
