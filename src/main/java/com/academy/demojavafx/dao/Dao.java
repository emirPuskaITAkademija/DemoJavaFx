package com.academy.demojavafx.dao;

import java.util.List;

/**
 * <ol>CRUD template
 * <li>Create</li>
 * <li>Retrieve</li>
 * <li>Update</li>
 * <li>Delete</li>
 * </ol>
 * @param <E>
 */
public interface Dao<E>{

    void save(E entity) ;

    List<E> getAll() ;

    E get(Integer primaryKey) ;

    void update(E entity);

    void delete(E entity);
}
