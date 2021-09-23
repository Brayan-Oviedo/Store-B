package co.store.infrastructure.repository.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import co.store.infrastructure.repository.entity.order.OrderEntity;

@Entity
@Table(name = "productOrder")
@IdClass(ProductOrderId.class)
public class ProductOrder {
	
	@Id
	private Long productId;
	
	@Id
	private Long orderId;
	
	@Column
	private int units;
	
	@ManyToOne()
	@JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
	private ProductEntity product;
	
	@ManyToOne()
	@JoinColumn(name = "orderId", referencedColumnName = "id", insertable = false, updatable = false)
	private OrderEntity order;
	
	public ProductOrder() {
	}
	
	public ProductOrder(Long productId, Long orderId, int units) {
		super();
		this.productId = productId;
		this.orderId = orderId;
		this.units = units;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}
	

}
