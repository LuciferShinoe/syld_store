package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
public class Cart {

    @javax.persistence.Id
    private String Id;

    @OneToOne(mappedBy = "cart")
    User user = new User();

}