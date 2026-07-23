package br.costa.AghataEscada.epmloyeer.service;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import br.costa.AghataEscada.epmloyeer.mapper.EmployeerMapper;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.epmloyeer.service.create.EmployeeCreate;
import br.costa.AghataEscada.epmloyeer.service.validate.EmployeeValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeValidateService employeeValidateService;
    private final EmployeeCreate  employeeCreate;
    private final EmployeerMapper employerMapper;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

        // validate Employee
        employeeValidateService.validateEmployee(dto);

        // create entities
        var entities =  employeeCreate.createEmployee(dto);

        // save in repository
        employeeRepository.save(entities);

        // return a response
        return employerMapper.toEmployeeResponseDto(dto);
     }
}
