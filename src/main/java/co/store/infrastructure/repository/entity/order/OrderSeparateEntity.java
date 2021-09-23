package co.store.infrastructure.repository.entity.order;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "orderSeparate")
public class OrderSeparateEntity extends OrderEntity {

	@Column
	private ZonedDateTime dateOfExpiration;
	@Column
	private double payReceived;
	@Column
	private double payOwned;
	

	public OrderSeparateEntity() {
		super();
	}

	@Autowired
	public OrderSeparateEntity(ZonedDateTime dateOfExpiration, double payReceived, double payOwned) {
		super();
		this.dateOfExpiration = dateOfExpiration;
		this.payReceived = payReceived;
		this.payOwned = payOwned;
	}
	

	public ZonedDateTime getDateOfExpiration() {
		return dateOfExpiration;
	}

	public void setDateOfExpiration(ZonedDateTime dateOfExpiration) {
		this.dateOfExpiration = dateOfExpiration;
	}

	public double getPayReceived() {
		return payReceived;
	}

	public void setPayReceived(double payReceived) {
		this.payReceived = payReceived;
	}

	public double getPayOwned() {
		return payOwned;
	}

	public void setPayOwned(double payOwned) {
		this.payOwned = payOwned;
	}
}
