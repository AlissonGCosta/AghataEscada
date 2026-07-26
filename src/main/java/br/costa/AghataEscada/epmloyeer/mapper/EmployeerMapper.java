package br.costa.AghataEscada.epmloyeer.mapper;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeerMapper {

    private final EmployeeRepository  employeeRepository;


    // mapped the request into response
    public EmployeeResponseDto toEmployeeResponseDto(EmployeeRequestDto dto) {
        UUID id = employeeRepository.findByCltNumber(dto.cltNumber()).get().getEmployeId();

        return new EmployeeResponseDto(
                id,
                dto.name(),
                dto.position(),
                dto.cltNumber(),
                dto.sector()

        );


    }

    // method fpr entity to response
    public EmployeeResponseDto entetyToResponse(EmployeeEntity entity){

        return new EmployeeResponseDto(
                entity.getEmployeId(),
                entity.getName(),
                entity.getPosition(),
                entity.getCltNumber(),
                entity.getSector()
        );
    }
}
