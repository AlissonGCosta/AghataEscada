package br.costa.AghataEscada.epmloyeer.service.validate;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.ConflictException;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeValidateService {

    private final EmployeeRepository employeeRepository;

    // simple validate
    public void validateEmployee(EmployeeRequestDto dto) {



        if(employeeRepository.findByCltNumber(dto.cltNumber()).isPresent()) {
            throw new ConflictException("CltNumber already exists");
        }



    }
}
