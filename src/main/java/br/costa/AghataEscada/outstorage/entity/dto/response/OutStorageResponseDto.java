package br.costa.AghataEscada.outstorage.entity.dto.response;

import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;

import java.util.UUID;

public record OutStorageResponseDto(
        UUID productOutStorageId,
        String nameOutStorage,
        String partOutStorage,
        String quantityOutStorage,
        EmployeeResponseDto employee
) {
}
