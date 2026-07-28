package br.costa.AghataEscada.security.authController;

import br.costa.AghataEscada.security.dto.Request.LoginEmployerRequestDto;
import br.costa.AghataEscada.security.dto.Response.LoginEmployerResponseDto;
import br.costa.AghataEscada.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginEmployerResponseDto login(@RequestBody @Valid LoginEmployerRequestDto dto) {
        return authService.login(dto);
    }
}
