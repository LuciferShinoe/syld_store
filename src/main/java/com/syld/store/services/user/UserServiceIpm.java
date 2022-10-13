package com.syld.store.services.user;

import com.syld.store.dto.UserClientDto;
import com.syld.store.entities.User;
import com.syld.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceIpm implements UserService{

    private final UserRepository userRepository;


    @Override
    public void save(UserClientDto entity) {
        try{
            User user = new ModelMapper().map(entity, User.class);
            user.setId(UUID.randomUUID().toString());
            userRepository.save(user);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public UserClientDto save_entity(UserClientDto entity) {
        try{
            User user = new ModelMapper().map(entity, User.class);
            user.setId(UUID.randomUUID().toString());

            return new ModelMapper().map(userRepository.save(user), UserClientDto.class);
        }catch (Exception e){
            throw  e;
        }
    }

    @Override
    public void update(UserClientDto entity) {

    }

    @Override
    public UserClientDto update_entity(UserClientDto entity) {
        return null;
    }

    @Override
    public void remove(String Id) {

    }
}














