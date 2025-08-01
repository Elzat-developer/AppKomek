package app.komek.appkomek.model.dto;

public record OrderDtos(
        String pharmacyName,
        String pharmacyAddress,
        String drugName,
        Integer count,
        Integer userId,
        String userName,
        String userSurName,
        String userLastName
) {
}
