package controller;

import java.util.HashMap;
import java.util.HashSet;

public class OrderManager implements IOrderManager {
	private static OrderManager instance = null;
	private HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

	private OrderManager OrderManager() {
		return OrderManager();
	}

	public  static OrderManager getInstance() {
		if (instance == null) {
			instance = new OrderManager();
		}
		return instance;
	}

	@Override
	public int makeOrder(String prodID, int amount) {
		Order newOrder = new Order(prodID, amount);
		this.orders.put(newOrder.getOrderId(), newOrder);
		return newOrder.getOrderId();
	}

	@Override
	public double executeOrder(int OrderId) {
		Order currentOrder = this.orders.get(OrderId);
		this.orders.remove(OrderId);
		return currentOrder.execute();

	}

	public HashMap<Integer, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer, Order> orders) {
		this.orders = orders;
	}

	@Override
	public int getProdQuanitity(String prodID) {
		ModelController controller = ModelController.getInstance();
		return controller.getProductInfo(prodID).getCurrentStock();
	}
}
