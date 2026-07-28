package br.costa.AghataEscada.epmloyeer.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record PutEmployerRequestDto(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "position is required")
        String position,
        @NotBlank(message = "Clt Number is required")
        String cltNumber,
        @NotBlank(message = "sector is required")
        String sector
) {
}
