package model.factories;

import model.Product;
import model.pricing.PricingSTG;
import model.pricing.PricingSTGSimple;

public class PricingSTGSimpleFactory implements IPricingSTGFactory {

	@Override
	public PricingSTG makePricingSTG(Product product) {
		return new PricingSTGSimple(product);
	}

}
