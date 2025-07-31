package app.komek.appkomek.model.dto;

public record CreateDelivery(
        String firstName,
        String surName,
        String lastName,
        String email,
        String password,
        String phone,
        String photoUrl
) {
}
