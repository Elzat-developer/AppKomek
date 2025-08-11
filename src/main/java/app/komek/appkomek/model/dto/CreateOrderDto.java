package app.komek.appkomek.model.dto;

public record CreateOrderDto(
        String pharmacyName,
        String drugName,
        Integer count
) {
}
