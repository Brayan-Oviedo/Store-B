package co.store.infrastructure.repository.repo.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import co.store.domain.exception.OrderException;
import co.store.domain.repository.IOrderRepository;
import co.store.infrastructure.repository.entity.order.OrderEntity;

@Component
@Primary
public class OrderRepository implements IOrderRepository{
	
	@Autowired
	private OrderRepositoryDB repo;

	@Override
	public Long saveOrder(OrderEntity order) {
		OrderEntity entity = repo.save(order);
		return entity.getId();
	}

	@Override
	public void deleteOrderById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public OrderEntity getOrderById(Long id) throws Exception {
		return repo.findById(id).orElseThrow(() -> new OrderException(""));
	}

}
