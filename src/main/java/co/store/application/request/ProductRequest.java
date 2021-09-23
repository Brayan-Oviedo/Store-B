package co.store.application.request;


public class ProductRequest {

	private String reference;
	private String description;
	private int units;
	
	public ProductRequest() {
		super();
	}
	
	public ProductRequest(String reference, String description, int units) {
		super();
		this.reference = reference;
		this.description = description;
		this.units = units;
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
