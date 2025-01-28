package model.factories;

import model.Product;
import model.pricing.PricingSTG;
import model.pricing.PricingSTGB;

public class PricingSTGBFactory implements IPricingSTGFactory{
	
	public PricingSTG makePricingSTG(Product product) {
		return new PricingSTGB( product);
	}
}
