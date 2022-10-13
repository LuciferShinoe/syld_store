package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    @Size(min = 8)
    private String password;

    @Column
    private boolean state = Boolean.TRUE;

    @Column
    private boolean verify = Boolean.FALSE;

    @Nullable
    @Column
    private String address;

    @Column
    @Nullable
    @Size(min = 10, max = 11)
    private String PhoneNumber;

    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    @Nullable
    @OneToOne(mappedBy = "user")
    Card card;

    @OneToOne
    Role role;

    @OneToMany(mappedBy = "admin")
    List<Bill> bills = new ArrayList<>();

}
