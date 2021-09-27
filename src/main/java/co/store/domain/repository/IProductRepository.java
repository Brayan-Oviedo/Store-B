package co.store.domain.repository;

import co.store.infrastructure.repository.entity.product.ProductEntity;

import java.util.List;

public interface IProductRepository {

	ProductEntity getProductByReference(String ref);
	ProductEntity getProductById(Long id);
	List<ProductEntity> getAllProducts();
	void saveProduct(ProductEntity product);
}
