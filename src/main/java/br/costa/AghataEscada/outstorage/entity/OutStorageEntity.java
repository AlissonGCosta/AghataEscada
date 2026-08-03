package br.costa.AghataEscada.outstorage.entity;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "out_storage")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutStorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_out_storage_id")
    private UUID productOutStorageId;

    @Column(name = "name_out_storage", nullable = false)
    private String nameOutStorage;

    @Column(name = "part_out_storage", nullable = false)
    private String partOutStorage;

    @Column(name = "quantity_out_storage", nullable = false)
    private String quantityOutStorage;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
