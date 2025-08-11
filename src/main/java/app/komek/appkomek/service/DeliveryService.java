package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.OrderDtos;
import app.komek.appkomek.model.dto.OrderListDto;
import app.komek.appkomek.model.entity.Order;
import app.komek.appkomek.model.status.OrderStatus;
import app.komek.appkomek.repository.OrderRepo;
import app.komek.appkomek.repository.PharmacyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final OrderRepo orderRepo;
    private final MailSenderService mailSenderService;
    private final PharmacyRepo pharmacyRepo;

    public List<OrderListDto> getOrders(String orderStatus) {
        List<Order> orderList = orderRepo.findByOrderStatus(OrderStatus.valueOf(orderStatus));
        return toOrderDto(orderList);
    }

    private List<OrderListDto> toOrderDto(List<Order> orderList) {
        return orderList.stream()
                .map(this::toDto)
                .toList();
    }

    private OrderListDto toDto(Order order) {
        return new OrderListDto(
                order.getUser().getName(),
                order.getCreateOrder(),
                order.getOrderStatus()
        );
    }

    public OrderDtos getOrder(int orderId) {
        Order order = orderRepo.findById(orderId);
        return new OrderDtos(
                order.getPharmacy().getPharmacyName(),
                order.getPharmacy().getPharmacyAddress(),
                order.getDrugName(),
                order.getCount(),
                order.getUser().getName(),
                order.getUser().getSurName(),
                order.getUser().getLastName(),
                order.getCreateOrder()
        );
    }

    public void scanQrCode(String qrCode) {
        Order order = orderRepo.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(OrderStatus.READY);
        orderRepo.save(order);

        String message = String.format(
                "Пожалуйста, примите заказ от пользователя %s. " +
                        "Лекарство: %s, количество: %d",
                order.getUser().getName(),
                order.getDrugName(),
                order.getCount()
        );

        // Получаем email аптеки
        List<String> emails = pharmacyRepo.findEmailsByPharmacyId(order.getPharmacy().getId());

        if (emails.isEmpty()) {
            throw new RuntimeException("Pharmacy has no registered email to send notification");
        }

        // Отправляем на первый найденный email
        mailSenderService.send(
                emails.get(0),
                "Пришел заказ от пользователя",
                message
        );
    }

}
