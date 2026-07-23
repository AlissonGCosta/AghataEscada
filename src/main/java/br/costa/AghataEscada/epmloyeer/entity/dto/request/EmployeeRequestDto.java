package br.costa.AghataEscada.epmloyeer.entity.dto.request;

public record EmployeeRequestDto(
        String name,
        String position,
        String cltNumber,
        String sector,
        String password
) {
}
