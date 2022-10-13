package com.syld.store.services.role;

import com.syld.store.dto.RoleDto;
import com.syld.store.entities.Role;
import com.syld.store.repositories.RoleRepository;
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

public class RoleServiceIpm implements RoleService{

    private final RoleRepository roleRepository;
    @Override
    public void save(RoleDto entity) throws Exception{
        try{
            Role role = new ModelMapper().map(entity, Role.class);
            role.setId(UUID.randomUUID().toString());
            roleRepository.save(role);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public RoleDto save_entity(RoleDto entity) throws Exception{
    // chuyển lỗi cho controller
        try{
            Role role = new ModelMapper().map(entity, Role.class);
            role.setId(UUID.randomUUID().toString());

            return new ModelMapper().map(roleRepository.save(role),RoleDto.class);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void update(RoleDto entity) {

    }

    @Override
    public RoleDto update_entity(RoleDto entity) {
        return null;
    }

    @Override
    public void remove(String Id) {

    }
}
