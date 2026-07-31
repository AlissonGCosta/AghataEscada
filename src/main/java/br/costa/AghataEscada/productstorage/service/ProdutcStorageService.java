package br.costa.AghataEscada.productstorage.service;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import br.costa.AghataEscada.epmloyeer.mapper.EmployeerMapper;
import br.costa.AghataEscada.epmloyeer.repository.EmployeeRepository;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.entity.dto.response.ProductStorageResponsetDto;
import br.costa.AghataEscada.productstorage.mapper.ProductMapper;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutcStorageService {

    // calling the repository
    private final ProductStorageRepository productStorageRepository;
    private final EmployeeRepository employeeRepository;

    // calling the validators and creators
    private final ValidateProduct validateProduct;
    private final CreateProduct createProduct;

    // calling the mapper
    private final ProductMapper productMapper;
    private final EmployeerMapper employerMapper;

    // method for creating a adiction in db
    public ProductStorageResponsetDto addProductStorage(UUID id, ProductStorageRequestDto dto) {

        // calling the validator
        validateProduct.validate(dto);

        //calling the creator and saving
        productStorageRepository.save(createProduct.create(id, dto));

        return productMapper.toProductStorageResponseDto(dto);

    }

    // method find all
    public List<ProductStorageResponsetDto> findAllProductStorage() {

            return productStorageRepository.findAll().stream()
                    .map( pt -> new ProductStorageResponsetDto(
                            pt.getId(),
                            pt.getName(),
                            pt.getPart(),
                            pt.getQuantity(),
                            pt.getStatus(),
                            employerMapper.entetyToResponse(pt.getEmployer())
                    ))
                    .toList();

    }

    // method find by id
    public ProductStorageResponsetDto findProductStorageById(UUID id) {
        ProductStorageEntity product =  productStorageRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Product not found"));

        return productMapper.toProductStorageResponseDto(product);
    }
}
