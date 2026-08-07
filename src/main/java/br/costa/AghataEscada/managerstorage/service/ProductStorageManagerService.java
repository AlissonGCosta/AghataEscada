package br.costa.AghataEscada.managerstorage.service;

import br.costa.AghataEscada.exception.errorcase.ConflictException;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.managerstorage.dto.request.ManagerStorageRequestDto;
import br.costa.AghataEscada.managerstorage.dto.request.ManagerStorageRequestFindNameDto;
import br.costa.AghataEscada.managerstorage.dto.response.ManagerStorageResponseDto;
import br.costa.AghataEscada.managerstorage.entity.ManagerStorageEntity;
import br.costa.AghataEscada.managerstorage.entity.mapper.ManagerMapper;
import br.costa.AghataEscada.managerstorage.entity.storageenum.ProductStorageManagerEnum;
import br.costa.AghataEscada.managerstorage.repository.ProductStorageManagerRepository;
import br.costa.AghataEscada.managerstorage.repository.ProductSummary;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void attManagerTable(String name, String part, Integer quantity) {
        ManagerStorageEntity managerEnt =  managerRepository.findByNameProductAndProductPart(name, part)
                .orElse(null);

        if(managerEnt == null) {
            managerEnt = new ManagerStorageEntity();
            managerEnt.setNameProduct(name);
            managerEnt.setProductPart(part);
            managerEnt.setProductQuantity(quantity);
            managerEnt.setStatus(ProductStorageManagerEnum.USABLE);
            managerEnt.setCreatedAt(LocalDate.now());
        }else {
            managerEnt.setProductQuantity(managerEnt.getProductQuantity() + quantity);
        }

        managerEnt.setUpdatedAt(LocalDate.now());
        managerRepository.save(managerEnt);


    }

    // method find all
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
    // method findById
    public ManagerStorageResponseDto findManagerProductById(UUID id){

        ManagerStorageEntity product = managerRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("product not found"));


        return managerMapper.toResponseDto(product);

    }

    // method findBy name
    public List<ManagerStorageResponseDto> findManagerStorageByName(ManagerStorageRequestFindNameDto dto){

        if(managerRepository.findManagerStorageByNameProduct(dto.nameProduct()).isEmpty()){
            throw new RessourceNotFoundException("product not found");
        }

        return managerRepository.findManagerStorageByNameProduct(dto.nameProduct()).stream()
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
