package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
public class OrderEntity {
    @javax.persistence.Id
    private String Id;

    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    User user;

    @OneToOne
    Cart cart;

    @Column(unique = true)
    private String order_name;

    @Min(1)
    private float order_amount;

    private int order_state = 1;
    //1 chua xac nhan
    //2 dang giao hang
    //3 da nhan hang

}
