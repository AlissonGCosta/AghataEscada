package br.costa.AghataEscada.managerstorage.entity;

import br.costa.AghataEscada.managerstorage.entity.storageenum.ProductStorageManagerEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "storage_manager",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"product_name", "product_part"}
        )
    )
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerStorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_name", nullable = false)
    private String nameProduct;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @Column(name = "product_part", nullable = false)
    private String productPart;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStorageManagerEnum status;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;


}
