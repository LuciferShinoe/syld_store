package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Size {

    @javax.persistence.Id
    private String Id;

    private String size_name;

    @ManyToOne
    Product product;
}
