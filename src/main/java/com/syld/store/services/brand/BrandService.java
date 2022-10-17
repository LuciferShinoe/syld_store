package com.syld.store.services.brand;

import com.syld.store.dto.BrandDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService extends ICrudService<BrandDto, String> {
    List<BrandDto> getAll();

    BrandDto getByBrand_desc(String desc);

    BrandDto getByName(String name);

    BrandDto getByNameNotSame(String name, String id);


    Page<BrandDto> getByPage(int page, int limit) throws Exception;

    BrandDto getBySlugName(String slug);

    BrandDto getBySlugNameNotSame(String slug, String id);

    List<BrandDto> getListBrand() throws Exception;

    BrandDto getById(String id);
}
