package com.syld.store.services.brand;

import com.syld.store.dto.BrandDto;
import com.syld.store.interfaces.services.ICrudService;

import java.util.List;

public interface BrandService extends ICrudService<BrandDto,String> {
    List<BrandDto> getAll();

    BrandDto getByName(String name);

    BrandDto getBySlugName(String slug);

    BrandDto getListBrand() throws Exception;
}
