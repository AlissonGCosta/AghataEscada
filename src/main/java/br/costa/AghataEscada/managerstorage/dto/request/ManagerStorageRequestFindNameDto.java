package br.costa.AghataEscada.managerstorage.dto.request;

import org.springframework.validation.annotation.Validated;

@Validated
public record ManagerStorageRequestFindNameDto(
        String nameProduct
) {
}
