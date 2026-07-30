package br.costa.AghataEscada.productstorage.entity.dto.response;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import br.costa.AghataEscada.productstorage.entity.productenum.ProductStatus;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public record ProductStorageResponsetDto(
        UUID id,
        String name,
        String part,
        Integer quantity,
        ProductStatus status,
        EmployeeResponseDto employee

) {
}
