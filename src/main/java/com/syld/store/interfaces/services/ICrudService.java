package com.syld.store.interfaces.services;

public interface ICrudService<E, TypeId> {

    void save(E entity) throws Exception;
    void update(E entity) throws Exception;
    void remove(TypeId Id) throws Exception;

}
