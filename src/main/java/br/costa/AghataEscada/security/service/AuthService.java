package br.costa.AghataEscada.security.service;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.security.dto.Request.LoginEmployerRequestDto;
import br.costa.AghataEscada.security.dto.Response.LoginEmployerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // process of login
    public LoginEmployerResponseDto login(LoginEmployerRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.cltNumber(),
                        dto.password()
                )
        );

        EmployeeEntity emp = employeeRepository.findByCltNumber(dto.cltNumber())
                .orElseThrow(() -> new RessourceNotFoundException("employee not found"));

        String tokens = jwtService.generateToken(emp);

        return new  LoginEmployerResponseDto(
                emp.getName(),
                emp.getCltNumber(),
                tokens
        );

    }


}
