package model.pricing;

import model.Product;

public class PricingSTGSimple extends PricingSTG {

	public PricingSTGSimple(Product product) {
		super(product);
	}

	public double getPrice(int quanity) {
		return this.getProduct().getUnitPrice() * ((float) quanity);
	}
}
