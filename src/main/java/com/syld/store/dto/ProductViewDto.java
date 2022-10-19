package com.syld.store.dto;

import com.syld.store.entities.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductViewDto {
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

    private Category category;
    private Brand brand;
    private Set<Color> colors;
    private Set<com.syld.store.entities.Size> sizes;
    private Set<ProductImage> thumbnails;
    private Set<Tag> tags;
    private boolean state = Boolean.TRUE;

    private List<Color> colors_con;
    private List<com.syld.store.entities.Size> sizes_con;
    private List<ProductImage> images_con;
    private List<Tag> tags_con;

    public void convertData(){
        this.colors_con = new ArrayList<>(this.colors);
        this.sizes_con = new ArrayList<>(this.sizes);
        this.tags_con = new ArrayList<>(this.tags);
        this.images_con = new ArrayList<>(this.thumbnails);
    }

    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());
}
