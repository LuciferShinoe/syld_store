package com.syld.store.services.category;

import com.syld.store.dto.CategoryDto;
import com.syld.store.dto.ListCategoryDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService extends ICrudService<CategoryDto,String> {

    List<CategoryDto> getAll();
    Page<CategoryDto> getByPage(int page,int limit) throws Exception;
    List<CategoryDto> getListCategory() throws Exception;

    CategoryDto getBySlugName(String slug);
    CategoryDto getByName(String name);

    CategoryDto getParent(String parent_id);

    List<ListCategoryDto> getByParentList();
}
