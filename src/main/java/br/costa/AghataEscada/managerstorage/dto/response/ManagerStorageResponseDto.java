package br.costa.AghataEscada.managerstorage.dto.response;

import br.costa.AghataEscada.managerstorage.entity.storageenum.ProductStorageManagerEnum;

import java.time.LocalDate;
import java.util.UUID;

public record ManagerStorageResponseDto(
        UUID id,
        String nameProduct,
        Integer productQuantity,
        String productPart,
        ProductStorageManagerEnum status,
        LocalDate createdAt,
        LocalDate updatedAt

) {
}
