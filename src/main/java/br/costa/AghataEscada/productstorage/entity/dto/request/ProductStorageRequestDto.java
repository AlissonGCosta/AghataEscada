package br.costa.AghataEscada.productstorage.entity.dto.request;

import org.springframework.validation.annotation.Validated;

@Validated
public record ProductStorageRequestDto(
        String name,
        String part,
        Integer quantity

) {
}
