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
    @Column(name = "drug_name")
    private String drugName;
    private int count;
    @Column(name = "create_order")
    private LocalDateTime createOrder;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "delete_order")
    private LocalDateTime deleteOrder;
    @Column(name = "qr_code")
    private String qrCode;
    @ManyToMany
    @JoinTable(name = "drugs_orders", joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "drugs_id"))
    private List<Drug> drugs;
    @ManyToOne
    @JoinColumn(name = "pharmacy_id",referencedColumnName = "id")
    private Pharmacy pharmacy;
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;
}
