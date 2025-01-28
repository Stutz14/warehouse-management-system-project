package warehouseServerVisualizer;

import java.util.Date;
import java.util.HashMap;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class LastOrder {
	public static String getProductName() {
		return productName;
	}

	public static void setProductName(String productName) {
		LastOrder.productName = productName;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static void setQuantity(int quantity) {
		LastOrder.quantity = quantity;
	}

	public static LocalDateTime getDate() {
		return date;
	}

	public static void setDate(LocalDateTime date) {
		LastOrder.date = date;
	}

	private static LastOrder instance = null;

	private static String productName = "";
	private static int quantity = 0;
	private static LocalDateTime date;
	

	public static LastOrder getInstance() {
		if (instance == null)
			instance = new LastOrder();

		return instance;
	}

	private LastOrder() {
		//findAvailableProductsAndQuantities();
	}

//	public LastOrder findLastOrder() {
//		productName = "Product1";
//		quantity = 22;
//		date = java.time.LocalDateTime.now();
//		
//		return this;
//
//	}

}
