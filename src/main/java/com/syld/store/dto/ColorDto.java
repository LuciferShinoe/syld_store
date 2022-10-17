package com.syld.store.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;



@Getter
@Setter
public class ColorDto {

    private String Id;

    private String name;

    private String colorCode;

    private boolean state = Boolean.TRUE;

    // Auto thời gian tạo
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    // Auto thời gian sửa
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

}
