package com.syld.store.repositories;

import com.syld.store.dto.UserClientDto;

import java.util.List;

public interface UserRepositoryClient {
    List<UserClientDto> findAllClient();
}
