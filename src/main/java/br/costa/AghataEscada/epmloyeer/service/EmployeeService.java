package br.costa.AghataEscada.epmloyeer.service;

import br.costa.AghataEscada.config.PasswordConifg;
import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.EmployeeRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.PutEmployerRequestDto;
import br.costa.AghataEscada.epmloyeer.entity.dto.request.PutPassword;
import br.costa.AghataEscada.epmloyeer.entity.dto.response.EmployeeResponseDto;
import br.costa.AghataEscada.epmloyeer.entity.enumemployee.EmployeeEnum;
import br.costa.AghataEscada.epmloyeer.mapper.EmployeerMapper;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.epmloyeer.service.create.EmployeeCreate;
import br.costa.AghataEscada.epmloyeer.service.validate.EmployeeValidateService;
import br.costa.AghataEscada.exception.errorcase.BusinessException;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

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
                        employer.getEmployeId(),
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
        .orElseThrow(() -> new RessourceNotFoundException("employer not found"));

        return employerMapper.entetyToResponse(employee);
    }

    // delete employer for id
    public void deleteEmployeeById(UUID id){

       EmployeeEntity emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("employer not found"));

       employeeRepository.delete(emp);

    }
    // put new atributes in employer entity
    public EmployeeResponseDto updateEmployeeById(UUID id, PutEmployerRequestDto dto){
        EmployeeEntity emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("employer not found"));

        emp.setSector(dto.sector());
        emp.setPosition(dto.position());
        emp.setCltNumber(dto.cltNumber());
        emp.setName(dto.name());

        employeeRepository.save(emp);

        return employerMapper.putToEmployeeResponseDto(dto);
    }
    // patch for password reset
    public void passwordReset(UUID id, PutPassword dto){
        EmployeeEntity emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("employer not found"));

        if(!passwordEncoder.matches(dto.currentPassword(), emp.getPassword())){
            throw new BusinessException("wrong password");
        }

        emp.setPassword(passwordEncoder.encode(dto.newPassword()));

        employeeRepository.save(emp);
    }


}
