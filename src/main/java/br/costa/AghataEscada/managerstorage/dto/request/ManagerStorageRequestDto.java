package br.costa.AghataEscada.managerstorage.dto.request;

import org.springframework.validation.annotation.Validated;

@Validated
public record ManagerStorageRequestDto(
        String nameProduct,
        String productPart,
        Integer productQuantity
) {
}
