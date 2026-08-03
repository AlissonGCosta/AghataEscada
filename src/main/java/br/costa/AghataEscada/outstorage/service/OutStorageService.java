package br.costa.AghataEscada.outstorage.service;

import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.outstorage.entity.OutStorageEntity;
import br.costa.AghataEscada.outstorage.entity.dto.request.OutStorageRequestDto;
import br.costa.AghataEscada.outstorage.entity.dto.response.OutStorageResponseDto;
import br.costa.AghataEscada.outstorage.mapper.OutStorageMapper;
import br.costa.AghataEscada.outstorage.repository.OutStorageRepository;
import br.costa.AghataEscada.outstorage.service.create.OutStorageCreate;
import br.costa.AghataEscada.outstorage.service.validate.OutStorageValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutStorageService {

    private final OutStorageRepository outStorageRepository;
    private final OutStorageValidateService outStorageValidateService;
    private final OutStorageCreate outStorageCreate;
    private final OutStorageMapper outStorageMapper;

    public OutStorageResponseDto createOutStorage(UUID employeeId, OutStorageRequestDto dto) {
        outStorageValidateService.validate(dto);

        OutStorageEntity outStorage = outStorageCreate.create(employeeId, dto);
        OutStorageEntity savedOutStorage = outStorageRepository.save(outStorage);

        return outStorageMapper.toOutStorageResponseDto(savedOutStorage);
    }

    public List<OutStorageResponseDto> findAllOutStorage() {
        return outStorageRepository.findAll().stream()
                .map(outStorageMapper::toOutStorageResponseDto)
                .toList();
    }

    public OutStorageResponseDto findOutStorageById(UUID id) {
        OutStorageEntity outStorage = outStorageRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Out storage not found: " + id));

        return outStorageMapper.toOutStorageResponseDto(outStorage);
    }
}
