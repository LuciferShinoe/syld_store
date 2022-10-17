package com.syld.store.services.product;

import com.syld.store.dto.BrandDto;
import com.syld.store.dto.CategoryDto;
import com.syld.store.dto.ColorDto;
import com.syld.store.dto.ProductDto;
import com.syld.store.entities.*;
import com.syld.store.repositories.ColorRepository;
import com.syld.store.repositories.ProductImageRepository;
import com.syld.store.repositories.ProductRepository;
import com.syld.store.repositories.TagRepository;
import com.syld.store.services.brand.BrandService;
import com.syld.store.services.category.CategoryService;
import com.syld.store.services.color.ColorService;
import com.syld.store.ultis.Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceIpm implements ProductService {
    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private final BrandService brandService;
    final ModelMapper modelMapper = new ModelMapper();
    final Uploader uploader = new Uploader();
    private final ProductImageRepository productImageRepository;
    private final ColorService colorService;
    private final ColorRepository colorRepository;
    private final TagRepository tagRepository;

    @Override
    public void save(ProductDto entity) throws Exception {

        try {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            product.setId(UUID.randomUUID().toString());

//            process images

            for (MultipartFile file : entity.getFiles()) {
                String path = uploader.upload(file, entity.getProduct_name());
                ProductImage productImage = new ProductImage();
                productImage.setId(UUID.randomUUID().toString());
                productImage.setPath(path);
                productImageRepository.save(productImage);
                Optional<ProductImage> productImage_ = productImageRepository.findById(productImage.getId());
                if (productImage_.isPresent()) {
                    product.addImage(productImage_.get());
                }
            }
//            save images done
//             save colors
            for (String color : entity.getColors()) {
                ColorDto color_ = colorService.getColorCode(color);
                Color has_color = new Color();
                if (color_ != null) {
                    BeanUtils.copyProperties(color_, has_color);
                }

                if (color_ != null) {
                    product.addColors(has_color);
                } else {
                    Color new_color = new Color();
                    new_color.setColor_name("new_color_" + color);
                    new_color.setColor_code(color);
                    new_color.setId(UUID.randomUUID().toString());
                    colorRepository.save(new_color);
                    Color saved_color = modelMapper.map(colorService.getColorCode(color), Color.class);
                    if (saved_color != null) {
                        product.addColors(saved_color);
                    }
                }
            }
//end save colors
//            process tag
            String[] tags = entity.getGroup_tag().split(",");
            for (String tag : tags) {
                Tag has_tag = tagRepository.findByTagName(tag);
                if (has_tag != null) {
                    product.addTagToProduct(has_tag);
                } else {
                    Tag new_tag = new Tag();
                    new_tag.setId(UUID.randomUUID().toString());
                    new_tag.setTag_name(tag);
                    tagRepository.save(new_tag);
                    Tag saved_tag = tagRepository.findByTagName(tag);
                    product.addTagToProduct(saved_tag);
                }
            }

            Category category = categoryService.getById(entity.getCategory_id());
            if (category != null) {
                product.setCategory(category);
            }
            BrandDto brand = brandService.getById(entity.getBrand_id());
            if (brand != null) {
                product.setBrand(modelMapper.map(brand, Brand.class));
            }
            productRepository.save(product);
//            end process tag
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void update(ProductDto entity) throws Exception {

    }

    @Override
    public void remove(String Id) throws Exception {

    }
}
