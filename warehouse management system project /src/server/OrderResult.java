package server;

public class OrderResult {
	
	private int orderId;
	private String prodId;
	private int quantiy;
	double result;
	
	
	public OrderResult(int orderID, String prodId, int quantiy, double result) {
		this.orderId = orderId;
		this.prodId = prodId;
		this.quantiy = quantiy;
		this.result = result;
	}
	
	public String toString() {
		String output = "";
		if (this.result == -1) {
			output = "Order <" + this.orderId + "> Failed";
		}else {
			String formattedResult = String.format("%.2f", this.result);
			output = "Order <" + this.orderId+"> Success, total price = "+formattedResult;
		}
		
		return output;
		
	}
}
