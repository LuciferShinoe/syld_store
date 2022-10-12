package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Tag {

    @javax.persistence.Id
    private String Id;

    @Column(unique = true)
    private String tag_name;

    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @ManyToMany
    List<Product> products = new ArrayList<>();

    public void addProductToTag(Product product){this.products.add(product);}
}
