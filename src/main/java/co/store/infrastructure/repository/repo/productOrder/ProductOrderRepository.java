package co.store.infrastructure.repository.repo.productOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import co.store.domain.repository.IProductOrderRepository;
import co.store.infrastructure.repository.entity.product.ProductOrder;

@Component
@Primary
public class ProductOrderRepository implements IProductOrderRepository{

	@Autowired
	private ProductOrderRepositoryDB repo;

	@Override
	public ProductOrder saveProductOrder(ProductOrder productOrder) {
		return repo.save(productOrder);
	}
	
	
}
