package model.factories;

import model.Product;
import model.pricing.PricingSTG;
import model.pricing.PricingSTGC;

public class PricingSTGCFactory implements IPricingSTGFactory {

	@Override
	public PricingSTG makePricingSTG(Product product) {
		return new PricingSTGC( product);
	}

}
