package br.costa.AghataEscada.managerstorage.controller;

import br.costa.AghataEscada.managerstorage.dto.request.ManagerStorageRequestDto;
import br.costa.AghataEscada.managerstorage.dto.request.ManagerStorageRequestFindNameDto;
import br.costa.AghataEscada.managerstorage.dto.response.ManagerStorageResponseDto;
import br.costa.AghataEscada.managerstorage.service.ProductStorageManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/manager")
@RequiredArgsConstructor
public class ManagerStoragerController {

    private final ProductStorageManagerService managerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerStorageResponseDto> findAllProductsManager() {
        return managerService.findAllProductsManager();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerStorageResponseDto findById(@PathVariable UUID id) {
        return managerService.findManagerProductById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerStorageResponseDto> findByName(@RequestBody @Valid ManagerStorageRequestFindNameDto dto) {
        return managerService.findManagerStorageByName(dto);
    }
}


