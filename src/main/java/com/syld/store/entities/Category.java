package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE category SET state = true WHERE id=?")
public class Category {

    @javax.persistence.Id
    private String Id;


    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @Column(unique = true)
    private String category_name;

    @OneToMany(mappedBy = "category")
    List<Product> productList = new ArrayList<>();

    public void addProductToCategory(Product product) {
        this.productList.add(product);
    }

}
