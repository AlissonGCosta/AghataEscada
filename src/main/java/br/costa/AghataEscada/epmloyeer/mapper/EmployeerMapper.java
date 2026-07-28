package br.costa.AghataEscada.epmloyeer.mapper;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.PutEmployerRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeerMapper {

    private final EmployeeRepository  employeeRepository;


    // mapped the request into response
    public EmployeeResponseDto toEmployeeResponseDto(EmployeeRequestDto dto) {
        EmployeeEntity id = employeeRepository.findByCltNumber(dto.cltNumber())
                .orElseThrow(() -> new RessourceNotFoundException("employer not found"));

        return new EmployeeResponseDto(
                id.getEmployeId(),
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
    public EmployeeResponseDto putToEmployeeResponseDto(PutEmployerRequestDto dto) {
        EmployeeEntity id = employeeRepository.findByCltNumber(dto.cltNumber())
                .orElseThrow(() -> new RessourceNotFoundException("employer not found"));

        return new EmployeeResponseDto(
                id.getEmployeId(),
                dto.name(),
                dto.position(),
                dto.cltNumber(),
                dto.sector()

        );


    }
}
