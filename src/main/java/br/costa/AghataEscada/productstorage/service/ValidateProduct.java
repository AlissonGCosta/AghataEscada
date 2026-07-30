package br.costa.AghataEscada.productstorage.service;

import br.costa.AghataEscada.exception.errorcase.BusinessException;
import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ValidateProduct {

    public void validate(ProductStorageRequestDto dto){


        if(dto.quantity() < 0){
            throw new BusinessException("Invalid quantity");
        }

        if(dto.name().isEmpty()){
            throw new BusinessException("Invalid name");
        }


    }
}
