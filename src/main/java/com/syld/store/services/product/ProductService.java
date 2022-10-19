package com.syld.store.services.product;

import com.syld.store.dto.ProductDto;
import com.syld.store.dto.ProductViewDto;
import com.syld.store.interfaces.services.ICrudService;

import java.util.List;

public interface ProductService extends ICrudService<ProductDto,String> {


    ProductDto findByName(String product_name);
    ProductDto findBySlug(String slug);

    List<ProductViewDto> getAll();

    ProductViewDto getById(String slug);
}
