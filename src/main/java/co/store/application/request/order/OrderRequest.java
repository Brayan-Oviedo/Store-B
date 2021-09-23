package co.store.application.request.order;

import java.util.ArrayList;
import java.util.List;

import co.store.application.request.ProductRequest;
import co.store.domain.model.Client;

public abstract class OrderRequest { 
	
	private List<ProductRequest> products = new ArrayList<>();
	private Client client;
	
	public OrderRequest() {
		super();
	}

	public OrderRequest(List<ProductRequest> products, Client client) {
		super();
		this.products = products;
		this.client = client;
	}
	
	

	public List<ProductRequest> getProducts() {
		return products;
	}

	public void setProducts(List<ProductRequest> products) {
		this.products = products;
	}
	
	public void setProduct(ProductRequest product) {
		this.products.add(product);
	}
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}
