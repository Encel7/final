package by.epam.shop.dao;

import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;

import java.util.List;

public interface SouvenirDAO extends DAOBase<Souvenir> {

    List<Souvenir> findAllSouvenir() throws ConstantException;

    List<Souvenir> findSmallerThan (Integer maxSize) throws ConstantException;

    List<Souvenir> cheaperThan (Integer maxPrice) throws ConstantException;

    List<Souvenir> findByColor (String color) throws ConstantException;

    List<Souvenir> findByAuthor (Integer authorID) throws ConstantException;
}
