package co.store.infrastructure.repository.entity.order;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import co.store.infrastructure.repository.entity.ClientEntity;
import co.store.infrastructure.repository.entity.product.ProductOrder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name = "order")
public abstract class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	private List<ProductOrder> productOrders = new ArrayList<>();
	
	@Column
	private float totalCost;
	@Column
	private float totalOriginalCost;
	@Column
	private ZonedDateTime date;
	
	@ManyToOne()
	@JoinColumn(name = "client_id")
	private ClientEntity client;
	
	public OrderEntity() {
		super();
	}

	@Autowired
	public OrderEntity(List<ProductOrder> products, float totalCost, float totalOriginalCost, ClientEntity client) {
		super();
		this.productOrders = products;
		this.totalCost = totalCost;
		this.client = client;
		this.totalOriginalCost = totalOriginalCost;
	}
	
	

	public Long getId() {
		return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	
	public List<ProductOrder> getProducts() {
		return productOrders;
	}

	public void setProducts(List<ProductOrder> products) {
		this.productOrders = products;
	}
	
	public void addProduct(ProductOrder product) {
		this.productOrders.add(product);
	}

	public float getTotalCost() {
		return totalCost;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}

	public float getTotalOriginalCost() {
		return totalOriginalCost;
	}

	public void setTotalOriginalCost(float totalOriginalCost) {
		this.totalOriginalCost = totalOriginalCost;
	}
}
