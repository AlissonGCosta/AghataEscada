package br.costa.AghataEscada.productstorage.service;

import br.costa.AghataEscada.exception.errorcase.BusinessException;
import br.costa.AghataEscada.exception.errorcase.ConflictException;
import br.costa.AghataEscada.exception.errorcase.RessourceNotFoundException;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.repository.ProductStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateProduct {

    // calling repository
   private final ProductStorageRepository productStorageRepository;

    public void validate(ProductStorageRequestDto dto){



        if(!productStorageRepository.findByPart(dto.part()).isEmpty()){

            if(productStorageRepository.findByName(dto.name()).isEmpty()){
                throw new ConflictException("incorrect name");
            }

        }

        if(dto.quantity() < 0){
            throw new BusinessException("Invalid quantity");
        }

        if(dto.name().isEmpty()){
            throw new BusinessException("Invalid name");
        }


    }
}
