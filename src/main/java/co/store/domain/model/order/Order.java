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
		calculateTotalCost();
		calculateTotalOriginalCost();
	}

	public Order(Long id) {
		this.id = id;
		calculateTotalCost();
		calculateTotalOriginalCost();
	}

	public Order(List<Product> products, Client client) {
		super();
		this.products = products;
		this.client = client;
		calculateTotalCost();
		calculateTotalOriginalCost();
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
	
	public void addProduct(Product product) {
		this.products.add(product);
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void calculateTotalCost() {
		for (Product product: products) {
			totalCost += product.getCostSale() * product.getUnits();
		}
	}
	
	public float getTotalOriginalCost() {
		return totalOriginalCost;
	}
	
	public void calculateTotalOriginalCost() {
		for (Product product: products) {
			totalOriginalCost += product.getOriginalCost() * product.getUnits();
		}
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public void setTotalOriginalCost(float totalOriginalCost) {
		this.totalOriginalCost = totalOriginalCost;
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
