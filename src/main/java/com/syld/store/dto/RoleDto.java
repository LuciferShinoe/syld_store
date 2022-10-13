package com.syld.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    public RoleDto() {
    }

    public RoleDto(String id, String name) {
        this.role_name = name;
        this.id = id;
    };

    private String id;

    private String role_name;

}
