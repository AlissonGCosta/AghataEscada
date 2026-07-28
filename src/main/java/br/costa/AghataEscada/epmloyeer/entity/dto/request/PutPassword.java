package br.costa.AghataEscada.epmloyeer.entity.dto.request;

public record PutPassword(
        String currentPassword,
        String newPassword
) {
}
