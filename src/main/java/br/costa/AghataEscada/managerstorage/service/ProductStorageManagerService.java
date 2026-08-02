package br.costa.AghataEscada.managerstorage.service;

import br.costa.AghataEscada.managerstorage.repository.ProductStorageManagerRepository;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStorageManagerService {

    // calling the repository
    private final ProductStorageManagerRepository managerRepository;

    public void createProductStorageManager(ProductStorageRequestDto dto){

    }
}
