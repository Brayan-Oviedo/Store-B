package co.store.domain.service.useCase;

import java.util.List;

import co.store.application.request.ProductRequest;
import co.store.application.request.order.OrderSaleRequest;
import co.store.application.request.order.OrderSeparateRequest;
import co.store.domain.model.order.Order;
import co.store.domain.model.order.OrderSale;
import co.store.domain.model.order.OrderSeparate;

public interface IOrderService {

	Order getOrderById(Long id) throws Exception;
	OrderSale createOrderSale(OrderSaleRequest order) throws Exception;
	OrderSeparate createoOrderSeparate(OrderSeparateRequest order) throws Exception;
	void deteleOrderById(Long id);
	void cancelOrderById(Long id) throws Exception;
	void validateUnitsToOrder(List<ProductRequest> products) throws Exception;
	void finalizeOrdenSeparate(Long id) throws Exception;
}
