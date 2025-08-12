package app.komek.appkomek.service;

import app.komek.appkomek.model.entity.Order;
import app.komek.appkomek.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCleanupService {

    private final OrderRepo orderRepo;

    @Scheduled(cron = "0 0 2 * * ?") // каждый день в 2:00 ночи
    public void deleteOldOrders() {
        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);
        List<Order> oldOrders = orderRepo.findByDeleteOrderBefore(oneYearAgo);

        if (!oldOrders.isEmpty()) {
            orderRepo.deleteAll(oldOrders);
            System.out.println("Удалено старых заказов: " + oldOrders.size());
        }
    }
}

