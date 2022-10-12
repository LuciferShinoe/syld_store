package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Product {

    @javax.persistence.Id
    private String Id;

    @Column(unique = true)
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

    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    Brand brand;

    @ManyToOne
    Category category;

    @OneToMany(mappedBy = "product")
    List<ProductCart> productCartList = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    List<Tag> tags = new ArrayList<>();

    public void addTagToProduct(Tag tag) {
        this.tags.add(tag);
    }
}