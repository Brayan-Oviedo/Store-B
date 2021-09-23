package co.store.domain.model;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class Cash implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private double minorCash;
	private double majorCash;
	private double minimumAmount;
	
	public Cash() {
		this.name = "Cash";
	}
	
	@Autowired
	public Cash(double minorCash, double majorCash, double minimumAmount) {
		super();
		this.name = "Cash";
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

	public void setMinorCash(double cash) {
		if( cash > 0 )
			this.minorCash += cash;
		else
			this.minorCash += cash;
	}

	public double getMajorCash() {
		return majorCash;
	}

	public void setMajorCash(double cash) {
		if( cash > 0 )
			this.majorCash += cash;
		else
			this.majorCash += cash;
	}

	public double getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

}
