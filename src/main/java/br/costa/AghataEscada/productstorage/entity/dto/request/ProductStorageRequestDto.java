package br.costa.AghataEscada.productstorage.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record ProductStorageRequestDto(
       @NotBlank String name,
        @NotBlank String part,
       @NotNull @Positive Integer quantity

) {
}
