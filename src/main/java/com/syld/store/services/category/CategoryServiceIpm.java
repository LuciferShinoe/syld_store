package com.syld.store.services.category;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.syld.store.dto.CategoryDto;
import com.syld.store.entities.Category;
import com.syld.store.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceIpm implements CategoryService{
    private final CategoryRepository categoryRepository;
    final ModelMapper modelMapper = new ModelMapper();
    @Override
    public void save(CategoryDto entity) throws Exception {
        try {
            Category category = this.modelMapper.map(entity,Category.class);
            category.setId(UUID.randomUUID().toString());
            categoryRepository.save(category);
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(CategoryDto entity) throws Exception {
        try {
            Category category = categoryRepository.findById(entity.getId()).orElse(null);
            if (category!= null){
                BeanUtils.copyProperties(entity,category);
                categoryRepository.save(category);
            }else {
                throw new Exception("No Category Found");
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void remove(String Id) throws Exception {
        try {
            Category category = categoryRepository.findById(Id).orElse(null);
            if (category!= null){
                categoryRepository.delete(category);
            }else {
                throw new Exception("No Category Found");
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<CategoryDto> getByPage(int page, int limit) throws Exception{
        try {
            Pageable pageable = PageRequest.of(page - 1,limit);
            Page<Category> categoryDtos = categoryRepository.findAll(pageable);
            return new PageImpl<>(categoryDtos.getContent().stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList());
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }
}
