package hibernate.dao;

import hibernate.model.Product;

import java.util.List;

public interface IDAO<T> {

    List<T> findAll();

    T create(T entity);

    int update(T entity);

    void delete(T entity);
}
