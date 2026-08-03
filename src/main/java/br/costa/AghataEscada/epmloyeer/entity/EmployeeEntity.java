package br.costa.AghataEscada.epmloyeer.entity;

import br.costa.AghataEscada.epmloyeer.entity.enumemployee.EmployeeEnum;
import br.costa.AghataEscada.outstorage.entity.OutStorageEntity;
import br.costa.AghataEscada.productstorage.entity.ProductStorageEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID employeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false, unique = true)
    private String cltNumber;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeEnum roles;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate createdAT;

    @Column(nullable = false)
    private LocalDate updatedAT;

    @OneToMany(mappedBy = "employer")
    private List<ProductStorageEntity> productStorageEntity = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<OutStorageEntity> outStorageEntities = new ArrayList<>();

}
