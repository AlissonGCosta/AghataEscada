package br.costa.AghataEscada.outstorage.entity.dto.request;

import jakarta.validation.constraints.NotBlank;

public record OutStorageRequestDto(
        @NotBlank(message = "name is required")
        String nameOutStorage,
        @NotBlank(message = "part is required")
        String partOutStorage,
        @NotBlank(message = "quantity is required")
        String quantityOutStorage
) {
}
