package app.komek.appkomek.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String photoURL;
    @ManyToOne
    @JoinColumn(name = "warehouses_id",referencedColumnName = "id")
    private Warehouse warehouse;
    @ManyToMany
    @JoinTable(name = "drugs_orders", joinColumns = @JoinColumn(name = "drugs_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> orders;
}
