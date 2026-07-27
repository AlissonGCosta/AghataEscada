package br.costa.AghataEscada.security.dto.Response;

public record LoginEmployerResponseDto(
        String name,
        String cltNumber,
        String token
) {
}
