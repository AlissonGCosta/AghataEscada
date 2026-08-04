package br.costa.AghataEscada.productstorage.repository;

import br.costa.AghataEscada.managerstorage.repository.ProductSummary;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductStorageRepository extends JpaRepository<ProductStorageEntity, UUID> {
    List<ProductStorageEntity> findByPart(String part);
    List<ProductStorageEntity> findByName(String name);
    List<ProductStorageEntity> findAllByNameContainingIgnoreCase(String name);

    @Query("""
            SELECT
             product.name AS name,
             product.part AS part,
             SUM(product.quantity) AS quantity
             FROM ProductStorageEntity product
             GROUP BY product.name, product.part
""")
    List<ProductSummary> findProductSummary();
}
