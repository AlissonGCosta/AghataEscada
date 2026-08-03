package br.costa.AghataEscada.outstorage.mapper;

import br.costa.AghataEscada.epmloyeer.mapper.EmployeerMapper;
import br.costa.AghataEscada.outstorage.entity.OutStorageEntity;
import br.costa.AghataEscada.outstorage.entity.dto.response.OutStorageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutStorageMapper {

    private final EmployeerMapper employeerMapper;

    public OutStorageResponseDto toOutStorageResponseDto(OutStorageEntity outStorage) {
        return new OutStorageResponseDto(
                outStorage.getProductOutStorageId(),
                outStorage.getNameOutStorage(),
                outStorage.getPartOutStorage(),
                outStorage.getQuantityOutStorage(),
                employeerMapper.entetyToResponse(outStorage.getEmployee())
        );
    }
}
