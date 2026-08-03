package br.costa.AghataEscada.outstorage.service.create;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.outstorage.entity.OutStorageEntity;
import br.costa.AghataEscada.outstorage.entity.dto.request.OutStorageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OutStorageCreate {

    private final EmployeeRepository employeeRepository;

    public OutStorageEntity create(UUID employeeId, OutStorageRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RessourceNotFoundException("Employee not found: " + employeeId));

        return OutStorageEntity.builder()
                .nameOutStorage(dto.nameOutStorage())
                .partOutStorage(dto.partOutStorage())
                .quantityOutStorage(dto.quantityOutStorage())
                .employee(employee)
                .build();
    }
}
