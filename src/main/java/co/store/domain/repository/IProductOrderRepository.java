package co.store.domain.repository;

import co.store.infrastructure.repository.entity.product.ProductOrder;

public interface IProductOrderRepository {
	
	ProductOrder saveProductOrder(ProductOrder productOrder);

}
