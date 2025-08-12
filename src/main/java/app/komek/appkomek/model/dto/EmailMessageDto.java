package app.komek.appkomek.model.dto;

public record EmailMessageDto(
        String to,
        String subject,
        String text
) {}
