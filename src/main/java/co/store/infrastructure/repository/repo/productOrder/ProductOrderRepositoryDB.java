package co.store.infrastructure.repository.repo.productOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.store.infrastructure.repository.entity.product.ProductOrder;

@Repository
public interface ProductOrderRepositoryDB extends JpaRepository<ProductOrder, Long> {
	

}
