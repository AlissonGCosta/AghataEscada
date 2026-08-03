package br.costa.AghataEscada.outstorage.service.validate;

import br.costa.AghataEscada.exception.errorcase.BusinessException;
import br.costa.AghataEscada.outstorage.entity.dto.request.OutStorageRequestDto;
import org.springframework.stereotype.Component;

@Component
public class OutStorageValidateService {

    public void validate(OutStorageRequestDto dto) {
        if (dto == null) {
            throw new BusinessException("Out storage data is required");
        }

        if (dto.nameOutStorage() == null || dto.nameOutStorage().isBlank()) {
            throw new BusinessException("Invalid name");
        }

        if (dto.partOutStorage() == null || dto.partOutStorage().isBlank()) {
            throw new BusinessException("Invalid part");
        }

        if (dto.quantityOutStorage() == null || dto.quantityOutStorage().isBlank()) {
            throw new BusinessException("Invalid quantity");
        }
    }
}
