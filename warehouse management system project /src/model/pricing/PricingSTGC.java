package model.pricing;

import model.Product;

public class PricingSTGC extends PricingSTG {

	public PricingSTGC(Product product) {
		super(product);
	}

	@Override
	public double getPrice(int quantity) {
		float unitPrice = this.getProduct().getUnitPrice();
		if (quantity <= 50) {
			return unitPrice * ((float) quantity);
		} else {
			return ((unitPrice) * 50.0) + (unitPrice * 0.95) * (((float) quantity) - 50.0);
		}
	}

}
