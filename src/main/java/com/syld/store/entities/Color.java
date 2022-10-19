package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE colors SET state = false WHERE id=?")
public class Color {

    @javax.persistence.Id
    private String Id;
    private String color_name;
    private String color_code;
    private boolean state = Boolean.TRUE;
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());
}
