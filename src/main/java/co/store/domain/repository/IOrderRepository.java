package co.store.domain.repository;

import co.store.infrastructure.repository.entity.order.OrderEntity;

public interface IOrderRepository {

	OrderEntity saveOrder(OrderEntity order);
	void deleteOrderById(Long id);
	OrderEntity getOrderById(Long id) throws Exception;
}
