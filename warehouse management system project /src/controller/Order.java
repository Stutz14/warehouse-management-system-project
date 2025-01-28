package controller;

import model.Product;

public class Order {

	private Product product;
	private int quantity;
	private int orderId;
	private static int orderCounter = 0;
	private static ModelController modelController = ModelController.getInstance();

	public Order(String productID, int quantity) {
		// System.out.println("New Order Made");
		this.quantity = quantity;
		this.product = modelController.getProductInfo(productID);
		this.orderId = orderCounter;
		orderCounter += 1;
	}

	public double execute() {

		int maxStock = this.product.getMaxStock();

		if (maxStock >= this.quantity) {
			// re-stock before doing the order if needed
			while (this.product.getCurrentStock() < this.quantity) {
				this.product.setCurrentStock(this.product.getCurrentStock() + this.product.getRestockSchedule());
				modelController.updateProductInfo(this.product);
			}
			// do the order
			this.product.setCurrentStock(this.product.getCurrentStock() - this.quantity);
			modelController.updateProductInfo(this.product);
			// re-stock after the order if needed
			if (this.product.getCurrentStock() < this.product.getMinStock()) {
				while (this.product.getCurrentStock() < this.product.getMaxStock()) {
					this.product.setCurrentStock(this.product.getCurrentStock() + this.product.getRestockSchedule());
					modelController.updateProductInfo(this.product);
				}
			}

			return this.product.getPricingSTG().getPrice(this.quantity);
			
		} else {
			return -1;
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
