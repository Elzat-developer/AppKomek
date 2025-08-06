package app.komek.appkomek.repository;

import app.komek.appkomek.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByPharmacyId(Integer pharmacyId);
    Order findById(int orderId);
}
