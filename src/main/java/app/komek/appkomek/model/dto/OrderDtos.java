package app.komek.appkomek.model.dto;

import java.time.LocalDateTime;

public record OrderDtos(
        String pharmacyName,
        String pharmacyAddress,
        String drugName,
        Integer count,
        Integer userId,
        String userName,
        String userSurName,
        String userLastName,
        LocalDateTime createData
) {
}
