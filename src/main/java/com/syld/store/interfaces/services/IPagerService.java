package com.syld.store.interfaces.services;

import org.springframework.data.domain.Page;

public interface IPagerService<E> {
    Page<E> getByPage(int page, int limit);
}
