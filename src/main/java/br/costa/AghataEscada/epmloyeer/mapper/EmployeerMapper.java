package br.costa.AghataEscada.epmloyeer.mapper;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeerMapper {


    // mapped the request into response
    public EmployeeResponseDto toEmployeeResponseDto(EmployeeRequestDto dto) {

        return new EmployeeResponseDto(
                dto.name(),
                dto.position(),
                dto.cltNumber(),
                dto.sector()

        );


    }

    // method fpr entity tu response
    public EmployeeResponseDto entetyToResponse(EmployeeEntity entity){

        return new EmployeeResponseDto(
                entity.getName(),
                entity.getPosition(),
                entity.getCltNumber(),
                entity.getSector()
        );
    }
}
