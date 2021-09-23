package co.store.infrastructure.repository.repo.cash;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.store.infrastructure.repository.entity.CashEntity;

@Repository
public interface CashRepositoryDB extends JpaRepository<CashEntity, String> {

	Optional<CashEntity> findByName(String name);
}
