package co.store.infrastructure.repository.entity;

import java.util.List;

import javax.persistence.*;

import co.store.infrastructure.repository.entity.order.OrderEntity;

@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
	private String identification;
	
	@Column
	private String name;
	@Column
	private int phoneNumber;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderEntity> orders;

	public ClientEntity() { }
	
	public ClientEntity(String name, String identification, List<OrderEntity> orders, int phoneNumber) {
		super();
		this.name = name;
		this.identification = identification;
		this.orders = orders;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
