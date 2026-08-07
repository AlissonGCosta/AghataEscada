package br.costa.AghataEscada.productstorage.controller;

import br.costa.AghataEscada.productstorage.entity.dto.request.ProductStorageRequestDto;
import br.costa.AghataEscada.productstorage.entity.dto.response.ProductStorageResponsetDto;
import br.costa.AghataEscada.productstorage.service.ProdutcStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProdutcStorageService  produtcStorageService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductStorageResponsetDto createProduct(@PathVariable UUID id, @RequestBody @Valid ProductStorageRequestDto dto) {
        return produtcStorageService.addProductStorage(id, dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductStorageResponsetDto> findAllProductStorage() {
        return produtcStorageService.findAllProductStorage();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductStorageResponsetDto findProductStorageById(@PathVariable UUID id) {
        return produtcStorageService.findProductStorageById(id);
    }


}
