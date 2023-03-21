package com.epam.jmp.spring.core.dao;

import java.util.Collection;
import java.util.Optional;

/**
 * CRUDDao
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface CRUDDao<T> {

    Optional<T> get(long id);
    Collection<T> getAll();
    T save(T t);
    T update(T t);
    boolean delete(long id);
}
