package app.komek.appkomek.model.dto;

public record PharmacyDto(
         String pharmacyName,
         String pharmacyAddress,
         String phone,
         String description,
         String location,
         String photoURL
) {
}
