package co.store.domain.model.order;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import co.store.domain.model.Client;
import co.store.domain.model.product.Product;

public abstract class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private List<Product> products = new ArrayList<>();
	private float totalCost;
	private float totalOriginalCost;
	private ZonedDateTime date = ZonedDateTime.now();
	private Client client;
	
	public Order() {
		super();
		setTotalCost();
		setTotalOriginalCost();
	}

	public Order(List<Product> products, Client client) {
		super();
		this.products = products;
		this.client = client;
		setTotalCost();
		setTotalOriginalCost();
	}
	
	

	public Long getId() {
		return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void setProduct(Product product) {
		this.products.add(product);
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost() {
		this.totalCost = (float) products.stream()
				.mapToDouble(prod ->
					((Product) prod).getCostSale() * prod.getUnits()
				).sum();
		System.out.println("tC: " + products.size());
	}
	
	public float getTotalOriginalCost() {
		return totalOriginalCost;
	}
	
	public void setTotalOriginalCost() {
		this.totalOriginalCost = (float) products.stream()
				.mapToDouble(prod ->
					((Product) prod).getOriginalCost() * prod.getUnits()
				).sum();
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
