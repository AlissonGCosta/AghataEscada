package br.costa.AghataEscada.productstorage.repository;

import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductStorageRepository extends JpaRepository<ProductStorageEntity, UUID> {
    Optional<ProductStorageEntity> findByPart(String part);
}
