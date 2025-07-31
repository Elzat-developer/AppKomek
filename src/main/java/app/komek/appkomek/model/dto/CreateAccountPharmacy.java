package app.komek.appkomek.model.dto;

public record CreateAccountPharmacy(
    String firstName,
    String surName,
    String lastName,
    String email,
    String password,
    String phone,
    String photoUrl
) {
}
