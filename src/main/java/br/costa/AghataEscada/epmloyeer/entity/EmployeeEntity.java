package br.costa.AghataEscada.epmloyeer.entity;

import br.costa.AghataEscada.epmloyeer.entity.enumemployee.EmployeeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private EmployeeEnum enumEmployee;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate createdAT;

    @Column(nullable = false)
    private LocalDate updatedAT;

}
