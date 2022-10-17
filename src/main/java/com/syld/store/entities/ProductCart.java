package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
public class ProductCart {

    @javax.persistence.Id
    private String Id;

    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @OneToOne
    Cart cart;

    @ManyToOne
    Product product;
}
