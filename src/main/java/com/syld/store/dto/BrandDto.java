package com.syld.store.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BrandDto {

    private String id;
    private String brand_name;
    private String brand_desc;
    private String brand_slug;
    private boolean state = Boolean.TRUE;
    private String brand_logo;

    MultipartFile file;

}
