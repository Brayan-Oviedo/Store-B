package co.store.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "cash")
public class CashEntity {

	@Id
	private String name;
	
	@Column
	private double minorCash;
	@Column
	private double majorCash;
	@Column
	private double minimumAmount;
	
	public CashEntity() { }
	
	@Autowired
	public CashEntity(String name, double minorCash, double majorCash, double minimumAmount) {
		super();
		this.name = name;
		this.minorCash = minorCash;
		this.majorCash = majorCash;
		this.minimumAmount = minimumAmount;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMinorCash() {
		return minorCash;
	}

	public void setMinorCash(double minorCash) {
		this.minorCash = minorCash;
	}

	public double getMajorCash() {
		return majorCash;
	}

	public void setMajorCash(double majorCash) {
		this.majorCash = majorCash;
	}

	public double getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
}
