package co.store.domain.model.order;

import org.springframework.beans.BeanUtils;

import co.store.application.request.order.OrderSaleRequest;

public class OrderSale extends Order {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public OrderSale() {
		super();
	}

	public OrderSale(Long id) {
		super(id);
	}
	
	
	public static OrderSale buildOf(OrderSaleRequest orderSale) {
		OrderSale order = new OrderSale();
		BeanUtils.copyProperties(orderSale, order);
		return order;
	}
	
}
