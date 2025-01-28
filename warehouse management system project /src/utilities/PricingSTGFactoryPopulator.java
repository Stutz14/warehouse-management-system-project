package utilities;

import java.util.HashMap;

import model.factories.*;

public class PricingSTGFactoryPopulator {
	private static PricingSTGFactoryRepo repo;

	public static PricingSTGFactoryRepo populateRepo() {
		repo = PricingSTGFactoryRepo.getInstance();

		HashMap<Integer, IPricingSTGFactory> newRepoHash = new HashMap<Integer, IPricingSTGFactory>();
		newRepoHash.put(0, new PricingSTGSimpleFactory());
		newRepoHash.put(1, new PricingSTGAFactory());
		newRepoHash.put(2, new PricingSTGBFactory());
		newRepoHash.put(3, new PricingSTGCFactory());
		newRepoHash.put(4, new PricingSTGDFactory());
		newRepoHash.put(5, new PricingSTGEFactory());
		
		repo.setRepo(newRepoHash);
		return repo;
	}
}
