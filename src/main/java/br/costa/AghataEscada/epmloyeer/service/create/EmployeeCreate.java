package br.costa.AghataEscada.epmloyeer.service.create;

import br.costa.AghataEscada.config.PasswordConifg;
import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.enumemployee.EmployeeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmployeeCreate {

    private final PasswordConifg conifg;

    public EmployeeEntity createEmployee(EmployeeRequestDto dto) {

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(dto.name());
        employee.setPosition(dto.position());
        employee.setCltNumber(dto.cltNumber());
        employee.setPassword(conifg.passwordEncoder().encode(dto.password()));
        employee.setEnumEmployee(EmployeeEnum.ROLE_USER);
        employee.setCreatedAT(LocalDate.now());
        employee.setUpdatedAT(LocalDate.now());


        return employee;
    }
}
