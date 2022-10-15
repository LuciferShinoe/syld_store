package com.syld.store.services.brand;

import com.syld.store.dto.BrandDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService extends ICrudService<BrandDto,String> {
    List<BrandDto> getAll();

    BrandDto getByBrand_desc(String desc);

    BrandDto getByName(String name);

    Page<BrandDto> getByPage(int page, int limit) throws Exception;

    BrandDto getBySlugName(String slug);

}
