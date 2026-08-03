package br.costa.AghataEscada.managerstorage.service;

import br.costa.AghataEscada.managerstorage.dto.response.ManagerStorageResponseDto;
import br.costa.AghataEscada.managerstorage.entity.ManagerStorageEntity;
import br.costa.AghataEscada.managerstorage.entity.storageenum.ProductStorageManagerEnum;
import br.costa.AghataEscada.managerstorage.repository.ProductStorageManagerRepository;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStorageManagerService {

    // calling the repository
    private final ProductStorageManagerRepository managerRepository;

   //third repository
    private final ProductStorageRepository productStorageRepository;

    public void attManagerTable(){

   List<ProductStorageEntity> products =  productStorageRepository.findAll();

   List<ManagerStorageEntity> managerProducts = products.stream()
           .map(product -> ManagerStorageEntity.builder()
                   .nameProduct(product.getName())
                   .productPart(product.getPart())
                   .productQuantity((product.getQuantity()))
                   .status(ProductStorageManagerEnum.UNUSABLE)
                   .createdAt(LocalDate.now())
                   .updatedAt(LocalDate.now())
                   .build()

           )
           .toList();



    managerRepository.saveAll(managerProducts);
    }


    public List<ManagerStorageResponseDto> findAllProductsManager(){

        return managerRepository.findAll().stream()
                .map(pt -> new ManagerStorageResponseDto(
                        pt.getId(),
                        pt.getNameProduct(),
                        pt.getProductQuantity(),
                        pt.getProductPart(),
                        pt.getStatus(),
                        pt.getCreatedAt(),
                        pt.getUpdatedAt()
                ))
                .toList();
    }

}
