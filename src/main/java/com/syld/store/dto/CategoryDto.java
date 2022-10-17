package com.syld.store.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CategoryDto {
    private String id;
    private String category_thumbnail;
    private String category_name;
    private String category_slug;
    private boolean state = Boolean.TRUE;
    private String parent_id = "parent";
    MultipartFile file;
}
