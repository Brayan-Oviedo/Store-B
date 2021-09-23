package co.store.infrastructure.repository.entity.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String reference;
	
	@Column
	private String description;
	@Column
	private int units;
	@Column
	private String category; 
	@Column 
	private double originalCost;
	@Column
	private double costSale;
	@Column
	private boolean isSeparated;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.DETACH)
	private List<ProductOrder> productOrders;
	
	public ProductEntity( ) { }
	
	@Autowired
	public ProductEntity(String reference, String description, int units, String category, double originalCost,
			double costSale, boolean isSeparated) {
		this.reference = reference;
		this.description = description;
		this.units = units;
		this.category = category;
		this.originalCost = originalCost;
		this.costSale = costSale;
		this.isSeparated = isSeparated;
	}
	
	
	

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
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

	public boolean isSeparated() {
		return isSeparated;
	}

	public void setSeparated(boolean isSeparated) {
		this.isSeparated = isSeparated;
	}

	public List<ProductOrder> getOrder() {
		return productOrders;
	}

	public void setOrder(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	
	
}
