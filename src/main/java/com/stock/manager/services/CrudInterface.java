package com.stock.manager.services;

import com.stock.manager.exceptions.ErroCrudException;

import java.util.List;

public interface CrudInterface<T> {

    public void create(T object) throws ErroCrudException;
    public void update(T object) throws ErroCrudException;
    public void updateQuantity(String id, int quantity) throws ErroCrudException;
    public void delete(String id) throws ErroCrudException;
    public List<T> read(int skip, int limit) throws ErroCrudException;
    public List<T> findByName(int skip, int limit, String name) throws ErroCrudException;

}
