package br.costa.AghataEscada.managerstorage.entity.mapper;

import br.costa.AghataEscada.managerstorage.dto.response.ManagerStorageResponseDto;
import br.costa.AghataEscada.managerstorage.entity.ManagerStorageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerMapper {

    public ManagerStorageResponseDto toResponseDto(ManagerStorageEntity ent){
        return new ManagerStorageResponseDto(
                ent.getId(),
                ent.getNameProduct(),
                ent.getProductQuantity(),
                ent.getProductPart(),
                ent.getStatus(),
                ent.getCreatedAt(),
                ent.getUpdatedAt()
        );
    }
}
