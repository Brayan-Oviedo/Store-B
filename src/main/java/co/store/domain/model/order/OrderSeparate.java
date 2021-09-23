package co.store.domain.model.order;

import java.time.ZonedDateTime;

import co.store.application.request.order.OrderSeparateRequest;

public class OrderSeparate extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bussinessDays;
	private ZonedDateTime dateOfExpiration;
	private double payReceived, payOwned;
	
	public OrderSeparate() {
	}
	
	public OrderSeparate(int bussinessDays, double payReceived) {
		super();
		this.bussinessDays = bussinessDays;
		dateOfExpiration = getDate().plusDays(bussinessDays);
		this.payReceived = payReceived;
		this.payOwned = getTotalCost() - payReceived;
	}

	
	public static OrderSeparate buildOf(OrderSeparateRequest order) {
		return new OrderSeparate(order.getBussinessDays(), order.getPayReceived());
	}
	
	public int getBussinessDays() {
		return bussinessDays;
	}

	public void setBussinessDays(int bussinessDays) {
		this.bussinessDays = bussinessDays;
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
