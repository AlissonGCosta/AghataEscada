package br.costa.AghataEscada.outstorage.repository;

import br.costa.AghataEscada.outstorage.entity.OutStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutStorageRepository extends JpaRepository<OutStorageEntity, UUID> {
}
