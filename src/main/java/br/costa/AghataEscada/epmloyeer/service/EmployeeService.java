package br.costa.AghataEscada.epmloyeer.service;

import br.costa.AghataEscada.config.PasswordConifg;
import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import br.costa.AghataEscada.epmloyeer.entity.enumemployee.EmployeeEnum;
import br.costa.AghataEscada.epmloyeer.mapper.EmployeerMapper;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.epmloyeer.service.create.EmployeeCreate;
import br.costa.AghataEscada.epmloyeer.service.validate.EmployeeValidateService;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeValidateService employeeValidateService;
    private final EmployeeCreate  employeeCreate;
    private final EmployeerMapper employerMapper;
    private final PasswordConifg config;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

        // validate Employee
        employeeValidateService.validateEmployee(dto);

        // create entities
        EmployeeEntity employer = employeeCreate.createEmployee(dto);

        // save in repository
        employeeRepository.save(employer);

        // return a response
        return employerMapper.toEmployeeResponseDto(dto);
    }

    // find all employers method
    public List<EmployeeResponseDto> findAllEmployer(){

        return  employeeRepository.findAll().stream().map(
                (employer -> new EmployeeResponseDto(
                        employer.getName(),
                        employer.getPosition(),
                        employer.getCltNumber(),
                        employer.getSector()

                ))

        ).toList();
    }

    // find a unique employer method

    public EmployeeResponseDto findEmployeeById(UUID id){
        EmployeeEntity employee =  employeeRepository.findById(id)
        .orElseThrow(() -> new RessourceNotFoundException("employeer not found"));

        return employerMapper.entetyToResponse(employee);
    }
}
