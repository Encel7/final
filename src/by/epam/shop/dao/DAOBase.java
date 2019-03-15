package by.epam.shop.dao;

import by.epam.shop.entity.BasicEntity;
import by.epam.shop.exceptions.ConstantException;

public interface DAOBase<Type extends BasicEntity> {

    Integer create(Type entity) throws ConstantException;

    Type findByID(Integer identity) throws ConstantException;

    void update(Type entity) throws ConstantException;

    void delete(Integer identity) throws ConstantException;
}
