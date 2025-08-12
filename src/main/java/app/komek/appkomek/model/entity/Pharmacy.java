package app.komek.appkomek.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pharmacies")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pharmacy_name")
    private String pharmacyName;
    @Column(name = "pharmacy_address")
    private String pharmacyAddress;
    private String phone;
    private String description;
    private String location;
    @Column(name = "photo_url")
    private String photoURL;
    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "pharmacy",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "pharmacy",cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
}
