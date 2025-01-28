package model.pricing;

import model.Product;

public abstract class PricingSTG {
	private Product product;

	public PricingSTG(Product product) {
		this.product = product;
	}

	public abstract double getPrice(int quantity);

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
