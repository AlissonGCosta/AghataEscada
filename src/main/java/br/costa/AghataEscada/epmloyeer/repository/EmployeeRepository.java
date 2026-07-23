package br.costa.AghataEscada.epmloyeer.repository;

import br.costa.AghataEscada.epmloyeer.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
}
