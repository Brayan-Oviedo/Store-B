package co.store.domain.model.product;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import co.store.application.request.ProductRequest;

public class Product extends ProductRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String category;
	private double originalCost;
	private double costSale;
	private boolean isSeparated = false;
	
	public Product() {
		super();
	}
	                                   
	
	public Product(String reference, String description, int units, String category, double originalCost, double costSale) {
		super(reference, description, units);
		this.category = category;
		this.originalCost = originalCost;
		this.costSale = costSale;
	}
	
	public static Product buildOf(ProductRequest productRequest) {
		Product product = new Product();
		BeanUtils.copyProperties(productRequest, product, "description");
		return product;
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(double originalCost) {
		this.originalCost = originalCost;
	}

	public double getCostSale() {
		return costSale;
	}

	public void setCostSale(double costSale) {
		this.costSale = costSale;
	}

	public boolean isSeperated() {
		return isSeparated;
	}

	public void setSeperated(boolean isSeperated) {
		this.isSeparated = isSeperated;
	}




}
