package app.komek.appkomek.repository;

import app.komek.appkomek.model.entity.Order;
import app.komek.appkomek.model.status.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByPharmacyId(Integer pharmacyId);
    Order findById(int orderId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
    Optional<Order> findByQrCode(String qrCode);

    List<Order> findByUserId(Integer userId);

    List<Order> findByDeleteOrderBefore(LocalDateTime dateTime);
}
