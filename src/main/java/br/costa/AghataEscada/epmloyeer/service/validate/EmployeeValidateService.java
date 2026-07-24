package br.costa.AghataEscada.epmloyeer.service.validate;

import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeValidateService {

    private final EmployeeRepository employeeRepository;

    // simple validate
    public void validateEmployee(EmployeeRequestDto dto) {

        var employer = employeeRepository.findByCltNumber(dto.cltNumber());

        if (employer.get().getCltNumber().equals(dto.cltNumber())) {
            throw new ConflictException("CltNumber already exists " +  dto.cltNumber());
        }


    }
}
