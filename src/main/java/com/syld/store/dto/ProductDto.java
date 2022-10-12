package com.syld.store.dto;

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

    private String product_name;

    @Size(max = 5000)
    private String product_desc;

    @Size(max = 5000)
    private String product_detail;

    @Min(1)
    private float product_price;

    @Min(1)
    private int product_quantity;

    private int sale_off = 0;

    List<MultipartFile> files = new ArrayList<>();

    private String brand_id;

    private String category_id;

}
