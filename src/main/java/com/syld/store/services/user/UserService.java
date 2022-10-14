package com.syld.store.services.user;

import com.syld.store.dto.UserClientDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public interface UserService extends ICrudService<UserClientDto,String> {
    UserClientDto findByEmail(String email);
}