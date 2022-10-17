package com.syld.store.services.brand;

import com.syld.store.dto.BrandDto;
import com.syld.store.entities.Brand;
import com.syld.store.repositories.BrandRepository;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BrandServiceIpm implements BrandService{

    private final BrandRepository brandRepository;
    final Uploader uploader = new Uploader();

    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, BrandDto.class)).toList();
    }
    final String brand_logo = "/asset/admin/img/products/vender-upload-preview.jpg";

    // gọi mô tả của brand
    @Override
    public BrandDto getByBrand_desc(String desc){
        Brand brand = brandRepository.findByBrand_desc(desc);
        if(brand != null){
            return modelMapper.map(brand, BrandDto.class);
        }
        return null;
    }
    @Override
    public Page<BrandDto> getByPage(int page, int limit) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page - 1, limit);
            Page<Brand> brandDto = brandRepository.findAll(pageable);
            return new PageImpl<>(brandDto.getContent().stream().map(brand -> modelMapper.map(brand, BrandDto.class)).toList());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
    @Override
    public BrandDto getById(String id) {
        Optional<Brand> brand = brandRepository.findById(id);

        if(brand != null) {
            return modelMapper.map(brand, BrandDto.class);
        }
        return null;
    }

// gọi tên của brand
    @Override
    public BrandDto getByName(String name) {
        Brand brand = brandRepository.findByBrand_name(name);
        if(brand != null){
            return modelMapper.map(brand, BrandDto.class);
        }
        return null;
    }

    // phần chuyển đổi chữ hoa và dạng slug k dấu
    @Override
    public BrandDto getBySlugName(String slug) {
        Optional<Brand> brand = brandRepository.findBySlug(slug);
        if(brand.isPresent()){
            return modelMapper.map(brand, BrandDto.class);
        }
        return null;
    }

    @Override
    public List<BrandDto> getListBrand() throws Exception {

        return null;
    }


    // Phần thêm mới brand
    @Override
    public void save(BrandDto entity) throws Exception {

        try{
            String filePath = uploader.upload(entity.getFile(), entity.getBrand_name());

            Brand brand = this.modelMapper.map(entity, Brand.class);
            brand.setId(UUID.randomUUID().toString());
            if(filePath != null) {
                brand.setBrand_logo(filePath);
            }else {
                brand.setBrand_logo(brand_logo);
            }
            brand.setBrand_slug(SlugGenerator.toSlug(entity.getBrand_slug()));
            brandRepository.save(brand);
        }catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

// phần sửa thông tin của brand
    @Override
    public void update(BrandDto entity) throws Exception {

        try{
            Brand brand = brandRepository.findById(entity.getId()).orElse(null);
            if(brand != null) {
                String old_path = brand.getBrand_logo();
                BeanUtils.copyProperties(entity, brand);
                if(!Objects.equals(entity.getFile().getOriginalFilename(), "")) {
                String path = uploader.upload(entity.getFile(), entity.getBrand_name());
                uploader.remove(brand.getBrand_logo());
                brand.setBrand_logo(path);
            } else {
                brand.setBrand_logo(old_path);
            }
            brandRepository.save(brand);
        } else {
                throw new Exception("no brand found !");
            }

        }catch (Exception e){
            log.info(e.getMessage());
        }


    }

    // phần xóa brand
    @Override
    public void remove(String Id) throws Exception {

        try{
            Brand brand = brandRepository.findById(Id).orElse(null);
            if( brand != null) {
                brandRepository.delete(brand);
            }else {
                throw new Exception("No Brand Found");
            }
        }catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}
