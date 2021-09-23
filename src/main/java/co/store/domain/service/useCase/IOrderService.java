package co.store.domain.service.useCase;

import java.util.List;

import co.store.application.request.ProductRequest;
import co.store.application.request.order.OrderSaleRequest;
import co.store.application.request.order.OrderSeparateRequest;
import co.store.domain.model.order.Order;

public interface IOrderService {

	Order getOrderById(Long id) throws Exception;
	Long createOrderSale(OrderSaleRequest order) throws Exception;
	Long createoOrderSeparate(OrderSeparateRequest order) throws Exception;
	void deteleOrderById(Long id);
	void cancelOrderById(Long id) throws Exception;
	void validateUnitsToOrder(List<ProductRequest> products) throws Exception;
	void finalizeOrdenSeparate(Long id) throws Exception;
}
