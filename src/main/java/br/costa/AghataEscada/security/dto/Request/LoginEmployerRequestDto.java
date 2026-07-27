package br.costa.AghataEscada.security.dto.Request;

import jakarta.validation.constraints.NotBlank;

public record LoginEmployerRequestDto(
        @NotBlank
        String cltNumber,
        @NotBlank
        String password
) {
}
