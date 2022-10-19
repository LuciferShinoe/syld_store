package com.syld.store.services.product;

import com.syld.store.dto.*;
import com.syld.store.entities.*;
import com.syld.store.repositories.*;
import com.syld.store.services.brand.BrandService;
import com.syld.store.services.category.CategoryService;
import com.syld.store.services.color.ColorService;
import com.syld.store.services.size.SizeService;
import com.syld.store.ultis.Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;


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
    private final SizeRepository sizeRepository;

    private void processColor(List<String> colors){

    }

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
//                SAVE COLOR

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
            //                SAVE SIZES
            for (String size : entity.getSizes()) {
                Size size_ = sizeRepository.getByName(size);
                if (size_ != null) {
                    product.addSizeToProduct(size_);
                }
            }
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
        try {
            Optional<Product> product = productRepository.findById(entity.getId());
            if (product.isPresent()){
                BeanUtils.copyProperties(entity,product.get());
//            update image
                if (entity.getFiles().size() > 0 && !Objects.equals(entity.getFiles().get(0).getOriginalFilename(), "")){
                    for (String file_in : entity.getUpdate_images()){
                        if (!Objects.equals(file_in, "")){
                            String [] data = file_in.split("--");
                            for (MultipartFile file:entity.getFiles()){
                                if (Objects.equals(file.getOriginalFilename(), data[1])){
                                    String path = uploader.upload(file,entity.getProduct_name());
                                    Optional<ProductImage> productImage = productImageRepository.findById(data[0]);
                                    if (productImage.isPresent()){
                                        uploader.remove(productImage.get().getPath());
                                        productImage.get().setPath(path);
                                        productImageRepository.save(productImage.get());
                                        break;
                                    }
                                };
                            }
                        }
                    }
                }

                List<Color> colors = new ArrayList<>();
                for (String color : entity.getColors()) {
                    ColorDto color_ = colorService.getColorCode(color);
                    Color has_color = new Color();
//                SAVE COLOR
                    if (color_!=null){
                        has_color = modelMapper.map(color_,Color.class);
                        has_color.setId(color_.getId());
                    }

                    if (color_ != null) {
                        colors.add(has_color);
                    } else {
                        Color new_color = new Color();
                        new_color.setColor_name("new_color_" + color);
                        new_color.setColor_code(color);
                        new_color.setId(UUID.randomUUID().toString());
                        colorRepository.save(new_color);
                        Color saved_color = modelMapper.map(colorService.getColorCode(color), Color.class);
                        if (saved_color != null) {
                            colors.add(saved_color);
                        }
                    }
                }

                Set<Color> colorSet = new HashSet<>(colors);
                product.get().setColors(colorSet);
//end save colors
                //                SAVE SIZES
                List<Size> sizes = new ArrayList<>();
                for (String size : entity.getSizes()) {
                    Size size_ = sizeRepository.getByName(size);
                    if (size_ != null) {
                        sizes.add(size_);
                    }
                }

                Set<Size> sizesSet = new HashSet<>(sizes);
                product.get().setSizes(sizesSet);


                String[] tags = entity.getGroup_tag().split(",");
                List<Tag> tagsL= new ArrayList<>();
                for (String tag : tags) {
                    Tag has_tag = tagRepository.findByTagName(tag);
                    if (has_tag != null) {
                        tagsL.add(has_tag);
                    } else {
                        Tag new_tag = new Tag();
                        new_tag.setId(UUID.randomUUID().toString());
                        new_tag.setTag_name(tag);
                        tagRepository.save(new_tag);
                        Tag saved_tag = tagRepository.findByTagName(tag);
                        tagsL.add(saved_tag);
                    }
                }
                Set<Tag> tagSet = new HashSet<>(tagsL);
                product.get().setTags(tagSet);

                Category category = categoryService.getById(entity.getCategory_id());
                if (category != null) {
                    product.get().setCategory(category);
                }

                BrandDto brand = brandService.getById(entity.getBrand_id());
                if (brand != null) {
                    product.get().setBrand(modelMapper.map(brand, Brand.class));
                }
                productRepository.save(product.get());
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }

    }

    @Override
    public void remove(String Id) throws Exception {

    }

    @Override
    public ProductDto findByName(String product_name) {
        Optional<Product> product = productRepository.findByName(product_name);
        if (product.isPresent()) {
            return modelMapper.map(product, ProductDto.class);
        } else {
            return null;
        }
    }

    @Override
    public ProductDto findBySlug(String slug) {
        Optional<Product> product = productRepository.findBySlug(slug);
        if (product.isPresent()) {
            return modelMapper.map(product, ProductDto.class);
        } else {
            return null;
        }
    }

    @Override
    public List<ProductViewDto> getAll() {
        List<ProductViewDto> list = new ArrayList<>();
        try {
            List<Product> products = productRepository.findAll();
            list = products.stream().map(product -> modelMapper.map(product, ProductViewDto.class)).toList();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return list;
    }

    @Override
    public ProductViewDto getById(String id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()){
                ProductViewDto productViewDto = modelMapper.map(product,ProductViewDto.class);
                productViewDto.convertData();
                return productViewDto;
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }
}
