package co.store.application.request.order;


public class OrderSeparateRequest extends OrderRequest {

	private int bussinessDays;
	private double payReceived;
	
	public OrderSeparateRequest(int bussinessDays, double payReceived) {
		super();
		this.bussinessDays = bussinessDays;
		this.payReceived = payReceived;
	}

	public int getBussinessDays() {
		return bussinessDays;
	}

	public void setBussinessDays(int bussinessDays) {
		this.bussinessDays = bussinessDays;
	}

	public double getPayReceived() {
		return payReceived;
	}

	public void setPayReceived(double payReceived) {
		this.payReceived = payReceived;
	}

}
