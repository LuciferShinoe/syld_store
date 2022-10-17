package com.syld.store.services.product;

import com.syld.store.dto.ProductDto;
import com.syld.store.repositories.ProductRepository;
import com.syld.store.ultis.Uploader;
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
public class ProductServiceIpm implements ProductService{
    
    private final ProductRepository productRepository;
    final ModelMapper modelMapper = new ModelMapper();
    final Uploader uploader = new Uploader();
    @Override
    public void save(ProductDto entity) throws Exception {

        try{
            ProductDto productDto = new ModelMapper().map(entity, ProductDto.class);
            productDto.setId(UUID.randomUUID().toString());


        }catch (Exception e){

        }
    }

    @Override
    public void update(ProductDto entity) throws Exception {

    }

    @Override
    public void remove(String Id) throws Exception {

    }
}
