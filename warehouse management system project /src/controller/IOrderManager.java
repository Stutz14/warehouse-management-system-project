package controller;

public interface IOrderManager {

	public int makeOrder(String prodID, int amount);

	public double executeOrder(int OrderId);
	
	public int getProdQuanitity(String prodID);
}
