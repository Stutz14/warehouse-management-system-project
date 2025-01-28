package model.factories;

import model.Product;
import model.pricing.PricingSTG;
import model.pricing.PricingSTGD;

public class PricingSTGDFactory implements IPricingSTGFactory {

	@Override
	public PricingSTG makePricingSTG(Product product) {
		return new PricingSTGD(product);
	}

}
