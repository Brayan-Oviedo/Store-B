package co.store.infrastructure.repository.repo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import co.store.domain.repository.IProductRepository;
import co.store.infrastructure.repository.entity.product.ProductEntity;

import java.util.List;

@Component
@Primary
public class ProductRepository implements IProductRepository{
	
	@Autowired
	private ProductRepositoryDB repo;
	

	@Override
	public ProductEntity getProductByReference(String ref) {
		return repo.findByReference(ref).orElse(null);
	}

	@Override
	public void saveProduct(ProductEntity product) {
		repo.save(product);
	}

	@Override
	public ProductEntity getProductById(Long id) {
		return repo.findById(id).orElse(null);
	}


	@Override
	public List<ProductEntity> getAllProducts() {
		return repo.findAll();
	}
}
