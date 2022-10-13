package com.syld.store.services.role;

import com.syld.store.dto.RoleDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends ICrudService<RoleDto,String> { }
