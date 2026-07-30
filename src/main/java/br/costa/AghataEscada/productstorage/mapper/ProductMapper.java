package br.costa.AghataEscada.productstorage.mapper;

import br.costa.AghataEscada.epmloyeer.mapper.EmployeerMapper;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.entity.dto.response.ProductStorageResponsetDto;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductStorageRepository productStorageRepository;
    private final EmployeerMapper mapper;

    public ProductStorageResponsetDto toProductStorageResponseDto(ProductStorageRequestDto dto){

        ProductStorageEntity product = productStorageRepository.findByPart(dto.part())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductStorageResponsetDto(
                product.getId(),
                dto.name(),
                dto.part(),
                dto.quantity(),
                product.getStatus(),
                mapper.entetyToResponse(product.getEmployer())
        );
    }
}
