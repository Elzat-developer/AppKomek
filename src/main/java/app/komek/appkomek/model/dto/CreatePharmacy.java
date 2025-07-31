package app.komek.appkomek.model.dto;

public record CreatePharmacy(
       String pharmacyName,
       String pharmacyAddress,
       String pharmacyLocation,
       String pharmacyPhone,
       String pharmacyDescription,
       String pharmacyPhotoUrl
) {
}
