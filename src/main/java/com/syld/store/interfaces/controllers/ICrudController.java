package com.syld.store.interfaces.controllers;

public interface ICrudController<E, TypeId> {
    String save(E entity);

    String update(E entity);

    String remove(TypeId Id);
}
