package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE size SET state = 0 where id=?")
public class Size {

    @javax.persistence.Id
    private String Id;

    @Column(unique = true)
    private String size_name;

    private boolean state = Boolean.TRUE;
}
