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

    private final PasswordConifg config;

    // creating a entity

    public EmployeeEntity createEmployee(EmployeeRequestDto dto) {

        EmployeeEntity employer = new EmployeeEntity();
        employer.setName(dto.name());
        employer.setPosition(dto.position());
        employer.setCltNumber(dto.cltNumber());
        employer.setSector(dto.sector());
        employer.setPassword(config.passwordEncoder().encode(dto.password()));
        employer.setEnumEmployee(EmployeeEnum.ROLE_USER);
        employer.setCreatedAT(LocalDate.now());
        employer.setUpdatedAT(LocalDate.now());
        return employer;
    }
}
