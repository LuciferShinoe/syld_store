package com.syld.store.services.category;

import com.syld.store.dto.CategoryDto;
import com.syld.store.dto.ListCategoryDto;
import com.syld.store.entities.Category;
import com.syld.store.repositories.CategoryRepository;
import com.syld.store.ultis.SlugGenerator;
import com.syld.store.ultis.Uploader;
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
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceIpm implements CategoryService {
    private final CategoryRepository categoryRepository;
    final ModelMapper modelMapper = new ModelMapper();

    final Uploader uploader = new Uploader();

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> list = categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();

        for (CategoryDto categoryDto : list) {
            if (!Objects.equals(categoryDto.getParent_id(), "parent")) {
                Optional<Category> category = categoryRepository.findById(categoryDto.getParent_id());
                if (category.isPresent()){
                    categoryDto.setParent(category.get());
                }
            }else {
                categoryDto.setParent(null);
            }
        }

        return list;
    }

    final String default_thumbnail = "/assets/admin/img/products/vender-upload-preview.jpg";

    @Override
    public void save(CategoryDto entity) throws Exception {
        try {
//           save  file to local
            String filePath = uploader.upload(entity.getFile(), entity.getCategory_name());
//            end
            Category category = this.modelMapper.map(entity, Category.class);
            category.setId(UUID.randomUUID().toString());
            if (filePath != null) {
                category.setCategory_thumbnail(filePath);
            } else {
                category.setCategory_thumbnail(default_thumbnail);
            }
            category.setCategory_slug(SlugGenerator.toSlug(entity.getCategory_slug()));
            categoryRepository.save(category);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(CategoryDto entity) throws Exception {
        try {
            Category category = categoryRepository.findById(entity.getId()).orElse(null);
            if (category != null) {
                String old_path = category.getCategory_thumbnail();
                if (Objects.equals(category.getId(), entity.getParent_id())) {
                    category.setParent_id("parent");
                }
                BeanUtils.copyProperties(entity, category);
                if (!Objects.equals(entity.getFile().getOriginalFilename(), "")) {
                    String path = uploader.upload(entity.getFile(), entity.getCategory_name());
                    uploader.remove(category.getCategory_thumbnail());
                    category.setCategory_thumbnail(path);
                } else {
                    category.setCategory_thumbnail(old_path);
                }
                categoryRepository.save(category);
            } else {
                throw new Exception("No Category Found");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void remove(String Id) throws Exception {
        try {
            Category category = categoryRepository.findById(Id).orElse(null);
            if (category != null) {
                categoryRepository.delete(category);
            } else {
                throw new Exception("No Category Found");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<CategoryDto> getByPage(int page, int limit) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page - 1, limit);
            Page<Category> categoryDtos = categoryRepository.findAll(pageable);
            return new PageImpl<>(categoryDtos.getContent().stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CategoryDto> getListCategory() throws Exception {
        try {
            List<Category> listParent = categoryRepository.findParent("parent");
            return listParent.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public CategoryDto getBySlugName(String slug) {
        Optional<Category> category = categoryRepository.findBySlug(slug);
        if (category.isPresent()) {
            return modelMapper.map(category, CategoryDto.class);
        }
        return null;
    }

    @Override
    public CategoryDto getByName(String name) {
        Category category = categoryRepository.findByCategory_name(name);
        if (category != null) {
            return modelMapper.map(category, CategoryDto.class);
        }
        return null;
    }

    @Override
    public CategoryDto getParent(String parent_id) {
        try {
            return modelMapper.map(categoryRepository.findParentCategory(parent_id, "parent"), CategoryDto.class);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ListCategoryDto> getByParentList() {
        List<ListCategoryDto> categoryDtoList = new ArrayList<>();

        try {
            List<Category> parents = categoryRepository.findParent("parent");

            for (Category category : parents) {
                List<Category> categoryList = categoryRepository.findByParent(category.getId());
                ListCategoryDto categoryDto = new ListCategoryDto();
                categoryDto.setParent(modelMapper.map(category, CategoryDto.class));
                categoryDto.setChildren(categoryList.stream().map(child -> modelMapper.map(child, CategoryDto.class)).toList());
                categoryDtoList.add(categoryDto);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return categoryDtoList;
    }

    @Override
    public Category getById(String category_id) {
        Optional<Category> category = categoryRepository.findById(category_id);
        return category.orElse(null);
    }
}
