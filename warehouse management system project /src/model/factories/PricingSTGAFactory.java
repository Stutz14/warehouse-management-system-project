package model.factories;

import model.Product;
import model.pricing.PricingSTG;
import model.pricing.PricingSTGA;

public class PricingSTGAFactory implements IPricingSTGFactory {

	@Override
	public PricingSTG makePricingSTG(Product product) {
		return new PricingSTGA(product);
	}

}
