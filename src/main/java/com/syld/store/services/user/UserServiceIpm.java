package com.syld.store.services.user;

import com.syld.store.dto.RoleDto;
import com.syld.store.dto.UserClientDto;
import com.syld.store.entities.Role;
import com.syld.store.entities.User;
import com.syld.store.repositories.UserRepository;
import com.syld.store.services.role.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceIpm implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    @Override
    public void save(UserClientDto entity){
        try {
            User user = new ModelMapper().map(entity, User.class);
            user.setId(UUID.randomUUID().toString());
//            add role to user
            Role role = roleService.getByName(entity.getRole_name());
            if (role != null) {
                user.setRole(role);
            }
            if (Objects.equals(user.getPhoneNumber(),"")){
                user.setPhoneNumber(null);
            }
            if (Objects.equals(user.getAddress(),"")){
                user.setAddress(null);
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }


    @Override
    public void update(UserClientDto entity) {

    }
    @Override
    public void remove(String Id) {

    }

    @Override
    public UserClientDto findByEmail(String email) {
        return new ModelMapper().map(userRepository.findByEmail(email).orElse(null),UserClientDto.class);
    }
}














