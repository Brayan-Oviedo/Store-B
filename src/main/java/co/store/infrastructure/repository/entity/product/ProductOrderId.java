package co.store.infrastructure.repository.entity.product;

import java.io.Serializable;

import javax.persistence.Column;

public class ProductOrderId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private Long productId;
	@Column
	private Long orderId;
	
	
	public ProductOrderId() {
		super();
	}


	public ProductOrderId(Long productId, Long orderId) {
		super();
		this.productId = productId;
		this.orderId = orderId;
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
	
	

}
