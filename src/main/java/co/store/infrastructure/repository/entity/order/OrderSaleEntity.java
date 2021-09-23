package co.store.infrastructure.repository.entity.order;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "orderSale")
public class OrderSaleEntity extends OrderEntity{

	public OrderSaleEntity() {
		super();
	}	
	
}
