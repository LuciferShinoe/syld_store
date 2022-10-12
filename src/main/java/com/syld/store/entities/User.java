package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class User {

    @Id
    private String id;

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    @Size(min = 8, max = 16)
    private String password;

    @Column
    private boolean state = Boolean.TRUE;

    @Column
    private boolean verify = Boolean.FALSE;

    @Column
    private String address;

    @Column
    @Size(min = 10, max = 11)
    private String PhoneNumber;

    @OneToOne
    Cart cart = new Cart();



    @OneToOne
    Role role = new Role();
}
