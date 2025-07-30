package app.komek.appkomek.model.entity;

import app.komek.appkomek.model.status.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_full_name")
    private String userFullName;
    @Column(name = "pharmacy_name")
    private String pharmacyName;
    @Column(name = "pharmacy_address")
    private String pharmacyAddress;
    @Column(name = "create_order")
    private LocalDateTime createOrder;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "delete_order")
    private LocalDateTime deleteOrder;
    @ManyToMany
    @JoinTable(name = "drugs_orders", joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "drugs_id"))
    private List<Drug> drugs;
}
