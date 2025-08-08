package app.komek.appkomek.model.entity;

import app.komek.appkomek.model.role.Authorities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    @Email
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Authorities authorities;
    @Column(name = "photo_url")
    private String photoURL;
    private String phone;
    @Column(name = "password_temporary")
    private boolean passwordTemporary;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authorities.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
