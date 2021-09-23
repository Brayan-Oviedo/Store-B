package co.store.infrastructure.repository.repo.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.store.infrastructure.repository.entity.ClientEntity;

@Repository
public interface ClientRepositoryDB extends JpaRepository<ClientEntity, Long> {

	Optional<ClientEntity> findByIdentification(String identification);
	void deleteByIdentification(String identification);
}
