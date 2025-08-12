package app.komek.appkomek.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "drug_name")
    private String drugName;
    private int count;
    @Column(name = "reserve_count")
    private int reserveCount;
    @Column(name = "create_data")
    private LocalDateTime createData;
    @Column(name = "delete_data")
    private LocalDateTime deleteData;
    @OneToMany(mappedBy = "warehouse",cascade = CascadeType.ALL)
    private List<Drug> drugs = new ArrayList<>();

}
