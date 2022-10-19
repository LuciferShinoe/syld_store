package com.syld.store.dto;

import com.syld.store.entities.Color;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDto {
    String Id;

    @Size(min = 1)
    private String product_name;

    @Size(max = 5000)
    private String product_desc;

    @Size(max = 5000)
    private String product_detail;

    @Min(1)
    private float product_price;

    @Min(1)
    private int product_quantity;

    @Size(min = 1)
    private String slug;
    private int sale_off = 0;

//upload thumbnails

    List<MultipartFile> files = new ArrayList<>();
    //    tags
    private String group_tag;

    private String brand_id;

    List<String> colors = new ArrayList<>();

    private List<String> sizes = new ArrayList<>();

    private String category_id;

    private List<String> update_images = new ArrayList<>();
}
