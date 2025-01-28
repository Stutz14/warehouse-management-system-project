package model.pricing;

import model.Product;

public class PricingSTGB extends PricingSTG {

	public PricingSTGB(Product product) {
		super(product);
	}

	@Override
	public double getPrice(int quantity) {
		float unitPrice = this.getProduct().getUnitPrice();
		if (quantity <= 10) {
			return unitPrice * ((float) quantity);
		} else {
			return ((unitPrice) * 10.0) + (unitPrice * 0.9) * (((float) quantity) - 10.0);
		}
	}

}
