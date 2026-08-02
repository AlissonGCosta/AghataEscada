package br.costa.AghataEscada.managerstorage.repository;


import br.costa.AghataEscada.managerstorage.entity.ManagerStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductStorageManagerRepository extends JpaRepository<ManagerStorageEntity, UUID> {
}
