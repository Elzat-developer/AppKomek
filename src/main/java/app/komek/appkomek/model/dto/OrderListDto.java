package app.komek.appkomek.model.dto;

import app.komek.appkomek.model.status.OrderStatus;

import java.time.LocalDateTime;

public record OrderListDto(
        String userName,
        LocalDateTime createOrder,
        OrderStatus orderStatus
) {
}
