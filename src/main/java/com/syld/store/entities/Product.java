package com.syld.store.entities;

import com.syld.store.dto.ColorDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@SQLDelete(sql = "update product set state = false where id = ?")
public class Product {

    @javax.persistence.Id
    private String id;

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
    private String slug;


    private boolean state = Boolean.TRUE;


    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    Brand brand;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    Set<Color> colors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    Set<com.syld.store.entities.Size> sizes = new HashSet<>();
    @ManyToOne
    Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<ProductImage> thumbnails = new HashSet<>();

    @OneToMany(mappedBy = "product")
    Set<ProductCart> productCartList = new HashSet<>();

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    Set<Tag> tags = new HashSet<>();

    public void addImage(ProductImage productImage) {
        this.thumbnails.add(productImage);
    }

    public void addColors(Color color) {
        this.colors.add(color);
    }

    public void addTagToProduct(Tag tag) {
        this.tags.add(tag);
    }
    public void addSizeToProduct(com.syld.store.entities.Size size){this.sizes.add(size);}
}