package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Brand {

    @javax.persistence.Id
    private String Id;

    @Column(unique = true)
    private String brand_name;

    @Column
    private String brand_desc;

    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "brand")
    List<Product> productList = new ArrayList<>();

    public void addProductToBrand(Product product) {
        this.productList.add(product);
    }
}
