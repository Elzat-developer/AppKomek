package app.komek.appkomek.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pharmacy_name")
    private String pharmacyName;
    private String address;
    private String phone;
    private String description;
    private String location;
    @Column(name = "photo_url")
    private String photoURL;
    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}
