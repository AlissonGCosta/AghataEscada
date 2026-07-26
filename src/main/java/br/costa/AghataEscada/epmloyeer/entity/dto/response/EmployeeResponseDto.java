package br.costa.AghataEscada.epmloyeer.entity.dto.response;

import java.util.UUID;

public record EmployeeResponseDto(
        UUID id,
        String name,
        String position,
        String cltNumber,
        String sector

) {

}
