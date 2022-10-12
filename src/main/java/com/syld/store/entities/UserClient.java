package com.syld.store.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "syld_shop", catalog = "")
@Getter
@Setter
public class UserClient implements UserDetails {
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
    @Size(min = 8, max = 16)
    private String RePassword;

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

    private Long Role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
