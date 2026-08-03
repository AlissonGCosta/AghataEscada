package br.costa.AghataEscada.outstorage.controller;

import br.costa.AghataEscada.outstorage.entity.dto.request.OutStorageRequestDto;
import br.costa.AghataEscada.outstorage.entity.dto.response.OutStorageResponseDto;
import br.costa.AghataEscada.outstorage.service.OutStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/out-storage")
@RequiredArgsConstructor
@Validated
public class OutStorageController {

    private final OutStorageService outStorageService;

    @PostMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OutStorageResponseDto createOutStorage(
            @PathVariable UUID employeeId,
            @RequestBody @Valid OutStorageRequestDto dto
    ) {
        return outStorageService.createOutStorage(employeeId, dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OutStorageResponseDto> findAllOutStorage() {
        return outStorageService.findAllOutStorage();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OutStorageResponseDto findOutStorageById(@PathVariable UUID id) {
        return outStorageService.findOutStorageById(id);
    }
}
