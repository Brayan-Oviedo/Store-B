package co.store.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.store.domain.repository.IProductOrderRepository;
import co.store.domain.service.useCase.IProductOrderService;
import co.store.infrastructure.repository.entity.product.ProductOrder;

@Service
@Transactional
public class ProductOrderService implements IProductOrderService{
	
	@Autowired
	private IProductOrderRepository repository;

	@Override
	public ProductOrder saveProductOrder(ProductOrder productOrder) {
		return repository.saveProductOrder(productOrder);
	}

}
