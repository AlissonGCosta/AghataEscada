package br.costa.AghataEscada.productstorage.service;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.ConflictException;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.entity.productenum.ProductStatus;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateProduct {

    private final EmployeeRepository employeeRepository;
    private final ProductStorageRepository productStorageRepository;

    public ProductStorageEntity create(UUID id, ProductStorageRequestDto dto) {

        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Employee not found" + id));



       ProductStorageEntity product = new ProductStorageEntity();
       product.setName(dto.name());
       product.setPart(dto.part());
       product.setQuantity(dto.quantity());
       product.setStatus(ProductStatus.USABLE);
       product.setCreatedAt(LocalDateTime.now());
       product.setUpdatedAt(LocalDateTime.now());
       product.setEmployer(employee);

       return product;
    }
}
