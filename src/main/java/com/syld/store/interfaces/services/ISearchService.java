package com.syld.store.interfaces.services;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService<E, TypeId> {

    Page<E> searchByKeyword(String key);

    List<E> searchByKeyword_(String key);
}
