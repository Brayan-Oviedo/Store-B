package co.store.application.request.order;

import co.store.application.request.ProductRequest;
import co.store.domain.model.Client;

import java.util.List;

public class OrderSaleRequest extends OrderRequest {

	public OrderSaleRequest() { }

	public OrderSaleRequest(List<ProductRequest> products, Client client) {
		super(products, client);
	}

}
