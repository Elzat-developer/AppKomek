package app.komek.appkomek.model.dto;

public record ChangePasswordDto(
        String email,
        String oldPassword,
        String newPassword
) {
}
