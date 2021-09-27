package co.store.infrastructure.repository.mapper;

import co.store.domain.model.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.store.domain.model.order.Order;
import co.store.domain.model.order.OrderSale;
import co.store.domain.model.order.OrderSeparate;
import co.store.infrastructure.repository.entity.order.OrderEntity;
import co.store.infrastructure.repository.entity.order.OrderSaleEntity;
import co.store.infrastructure.repository.entity.order.OrderSeparateEntity;


@Scope("singleton")
@Component
public class OrderMapper {

	@Autowired
	private ClientMapper clientMapper;
	
	public Order toDomain(OrderEntity orderEntity) {
		Order order = new OrderSale();
		
		if(orderEntity instanceof OrderSeparateEntity)
			order = new OrderSeparate();

		order.setClient(clientMapper.toDomain(orderEntity.getClient()));
		BeanUtils.copyProperties(orderEntity, order);
		return (OrderSale) order;
	}
	
	public OrderEntity toEntity(Order order, Long id) throws Exception {
		OrderEntity entity = new OrderSaleEntity();
		
		if(order instanceof OrderSeparate) 
			entity = new OrderSeparateEntity();

		BeanUtils.copyProperties(order, entity);
		if(id != null)
			entity.setId(id);

		return entity;
	}

	
}
