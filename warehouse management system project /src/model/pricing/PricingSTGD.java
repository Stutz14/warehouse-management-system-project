package model.pricing;

import model.Product;

public class PricingSTGD extends PricingSTG {

	public PricingSTGD(Product product) {
		super(product);
	}

	@Override
	public double getPrice(int quantity) {
		float unitPrice = this.getProduct().getUnitPrice();
		if (quantity <= 50) {
			return unitPrice * ((float) quantity);
		} else {
			if (quantity <= 200) {
				return ((unitPrice) * 50.0) + (unitPrice * 0.96) * (((float) quantity) - 50.0);
			}else {
				return ((unitPrice) * 50.0) + (unitPrice * 0.95) * (150) + (unitPrice * 0.94)*(((float) quantity) - 200.0);
			}
		}
	}
}
