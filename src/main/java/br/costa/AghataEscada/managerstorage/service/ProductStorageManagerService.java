package br.costa.AghataEscada.managerstorage.service;

import br.costa.AghataEscada.exception.errorcase.ConflictException;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.managerstorage.dto.request.ManagerStorageRequestDto;
import br.costa.AghataEscada.managerstorage.dto.response.ManagerStorageResponseDto;
import br.costa.AghataEscada.managerstorage.entity.ManagerStorageEntity;
import br.costa.AghataEscada.managerstorage.entity.mapper.ManagerMapper;
import br.costa.AghataEscada.managerstorage.entity.storageenum.ProductStorageManagerEnum;
import br.costa.AghataEscada.managerstorage.repository.ProductStorageManagerRepository;
import br.costa.AghataEscada.managerstorage.repository.ProductSummary;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductStorageManagerService {

    // calling the repository
    private final ProductStorageManagerRepository managerRepository;

   //third repository
    private final ProductStorageRepository productStorageRepository;

    // calling the utilities
    private final ManagerMapper managerMapper;

    // method for add in manager table
    public void attManagerTable(){

       List<ProductSummary> products =  productStorageRepository.findProductSummary();

       Map<String, ManagerStorageEntity> existingProducts =
               managerRepository.findAll().stream()
                       .collect(Collectors.toMap(
                               ManagerStorageEntity::getProductPart,
                               Function.identity()
                       ));

       List<ManagerStorageEntity> managerProducts = products.stream()
               .map(product -> {
                   ManagerStorageEntity manager =
                           existingProducts.getOrDefault(
                                   product.getPart(),
                                   new ManagerStorageEntity()
                           );
                   manager.setNameProduct(product.getName());
                   manager.setProductPart(product.getPart());
                   manager.setProductQuantity(
                           Math.toIntExact(product.getQuantity())
                   );
                   manager.setStatus(ProductStorageManagerEnum.USABLE);

                   if(manager.getCreatedAt() == null){
                       manager.setCreatedAt(LocalDate.now());
                   }
                   manager.setUpdatedAt(LocalDate.now());

                   return manager;
               })
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

    public ManagerStorageResponseDto findManagerProductById(UUID id){

        ManagerStorageEntity product = managerRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("product not found"));


        return managerMapper.toResponseDto(product);

    }

    // method for put atributes
    public ManagerStorageResponseDto putManagerProduct(UUID id, ManagerStorageRequestDto dto ){
        ManagerStorageEntity product = managerRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("product not found"));

        if(dto.nameProduct() == product.getNameProduct()){
            throw new ConflictException("product name already exists");
        }

        if(dto.productPart() == product.getProductPart()){
            throw new ConflictException("product part already exists");
        }

        product.setNameProduct(dto.nameProduct());
        product.setProductPart(dto.productPart());
        product.setProductQuantity(dto.productQuantity());


        managerRepository.save(product);

        return managerMapper.toResponseDto(product);
    }

}
