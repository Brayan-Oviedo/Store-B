package co.store.infrastructure.repository.repo.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.store.infrastructure.repository.entity.product.ProductEntity;

@Repository
public interface ProductRepositoryDB extends JpaRepository<ProductEntity, Long> {

	Optional<ProductEntity> findByReference(String reference);
}
