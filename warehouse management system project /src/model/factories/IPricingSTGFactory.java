package model.factories;

import model.Product;
import model.pricing.PricingSTG;

public interface IPricingSTGFactory {
	public PricingSTG makePricingSTG(Product product );
}
