package model.factories;

import model.Product;
import model.pricing.PricingSTG;
import model.pricing.PricingSTGE;

public class PricingSTGEFactory implements IPricingSTGFactory {

	@Override
	public PricingSTG makePricingSTG(Product product) {
		return new PricingSTGE(product);
	}

}
