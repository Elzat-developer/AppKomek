package app.komek.appkomek.model.dto;

public record OrderDtos(
        String userFullName,
        String pharmacyName,
        String pharmacyAddress,
        String drugName,
        int count
) {
}
