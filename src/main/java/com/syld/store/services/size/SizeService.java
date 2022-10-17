package com.syld.store.services.size;

import com.syld.store.dto.SizeDto;
import com.syld.store.interfaces.services.ICrudService;

import java.util.List;

public interface SizeService extends ICrudService<SizeDto,String> {

    List<SizeDto> getAll();

    SizeDto getById(String id);

    SizeDto getByName(String name);


}
