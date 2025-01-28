package model.pricing;

import model.Product;

public class PricingSTGA extends PricingSTG {

	public PricingSTGA(Product product) {
		super(product);
	}




	@Override
	public double getPrice(int quantity) {
		float unitPrice = this.getProduct().getUnitPrice();
		if (quantity <= 20) {
			return unitPrice * ((float) quantity);
		} else {
			return (unitPrice*0.9) *  quantity;
		}
		
	}

}
