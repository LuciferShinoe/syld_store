package com.syld.store.interfaces.controllers;

public interface ICrudController<E,TypeId> {
    void save(E entity);

    E save_entity(E entity);

    void update(E entity);

    E update_entity(E entity);

    void remove(TypeId Id);
}
