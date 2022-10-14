package com.syld.store.services.category;

import com.syld.store.dto.CategoryDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.data.domain.Page;

public interface CategoryService extends ICrudService<CategoryDto,String> {
    Page<CategoryDto> getByPage(int page,int limit) throws Exception;
}
