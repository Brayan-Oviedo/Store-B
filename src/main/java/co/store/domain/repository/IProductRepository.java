package co.store.domain.repository;

import co.store.infrastructure.repository.entity.product.ProductEntity;

public interface IProductRepository {

	ProductEntity getProductByReference(String ref);
	ProductEntity getProductById(Long id);
	void saveProduct(ProductEntity product);
}
