package co.store.infrastructure.repository.entity.order;

import co.store.infrastructure.repository.entity.product.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "orderSale")
public class OrderSaleEntity extends OrderEntity{

	public OrderSaleEntity() {
		super();
	}

	
}
