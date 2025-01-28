package model.pricing;

import model.Product;

public class PricingSTGE extends PricingSTG {

	public PricingSTGE(Product product) {
		super(product);
	}

	@Override
	public double getPrice(int quanity) {
		return quanity * this.getProduct().getUnitPrice() * 0.6;
	}

}
