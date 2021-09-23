package co.store.infrastructure.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.store.domain.model.product.Product;
import co.store.infrastructure.repository.entity.product.ProductEntity;

@Scope("singleton")
@Component
public class ProductOrderMapper {

	public Product toDomain(ProductEntity productEntity) {
		Product product = new Product();
		BeanUtils.copyProperties(productEntity, product);
		return product;
	}
	
	public ProductEntity toEntityWithId(Product product) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(product, productEntity);
		return productEntity;
	}
	
	public ProductEntity toEntityWithNewId(Product product) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(product, productEntity, "id");
		return productEntity;
	}
}