package co.store.domain.service.useCase;

import co.store.infrastructure.repository.entity.product.ProductOrder;

public interface IProductOrderService {
	
	ProductOrder saveProductOrder(ProductOrder productOrder);

}
