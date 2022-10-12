package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Card {

    @javax.persistence.Id
    private String Id;

    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @Column(unique = true)
    private String card_number;

    @Column(unique = true)
    private String card_brand;

    @Column(unique = true)
    private String brand_thumbnail;
    @OneToOne
    User user;
}
