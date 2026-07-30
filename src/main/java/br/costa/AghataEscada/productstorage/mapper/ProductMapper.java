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

      return  productStorageRepository.findAll().stream().reduce((first, second) -> second).map(
                pt -> new ProductStorageResponsetDto(
                        pt.getId(),
                        dto.name(),
                        dto.part(),
                        dto.quantity(),
                        pt.getStatus(),
                        mapper.entetyToResponse(pt.getEmployer())
                )
        )
              .orElseThrow(() -> new RuntimeException("Product not found"));


    }
}
