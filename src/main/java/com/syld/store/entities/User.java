package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String password;


    @OneToOne
    @JoinColumn(name = "user_id")
    Role role = new Role();
}
