package com.syld.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListCategoryDto {
    private CategoryDto parent;
    private List<CategoryDto> children = new ArrayList<>();
}
