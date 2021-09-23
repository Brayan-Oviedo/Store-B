package co.store.infrastructure.repository.mapper;

import org.springframework.beans.BeanUtils;
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
	
	public Order toDomain(OrderEntity orderEntity) {
		Order order = new OrderSale();
		
		if(orderEntity instanceof OrderSeparateEntity)
			order = new OrderSeparate();
		
		BeanUtils.copyProperties(orderEntity, order);
		return (OrderSale) order;
	}
	
	public OrderEntity toEntityWithId(Order order) throws Exception {
		OrderEntity entity = new OrderSaleEntity();
		
		if(order instanceof OrderSeparate) 
			entity = new OrderSeparateEntity();
		
		BeanUtils.copyProperties(order, entity);
		entity.setId(order.getId());
			
		return entity;
	}
	
	public OrderEntity toEntityWithNewId(Order order) throws Exception {
		OrderEntity entity = new OrderSaleEntity();
		
		if(order instanceof OrderSeparate) 
			entity = new OrderSeparateEntity();
				
		BeanUtils.copyProperties(order, entity, "id");
		return entity;
	}
	
}
