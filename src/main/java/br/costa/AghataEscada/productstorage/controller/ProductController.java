package br.costa.AghataEscada.productstorage.controller;

import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.entity.dto.response.ProductStorageResponsetDto;
import br.costa.AghataEscada.productstorage.service.ProdutcStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProdutcStorageService  produtcStorageService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductStorageResponsetDto createProduct(@PathVariable UUID id, @RequestBody ProductStorageRequestDto dto) {
        return produtcStorageService.addProductStorage(id, dto);
    }
}
