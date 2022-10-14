package com.syld.store.services.user;

import com.syld.store.dto.ProductDto;
import com.syld.store.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void save(ProductDto productDto) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
    }
}
