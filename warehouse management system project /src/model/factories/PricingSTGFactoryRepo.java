package model.factories;

import java.util.HashMap;

public class PricingSTGFactoryRepo {

	private static PricingSTGFactoryRepo instance = null;
	private HashMap<Integer, IPricingSTGFactory> repo;

	private PricingSTGFactoryRepo PricingSTGFactoryRepo() {
		return new PricingSTGFactoryRepo();
	}

	public static PricingSTGFactoryRepo getInstance() {
		if (instance == null) {
			instance = new PricingSTGFactoryRepo();
		}
		return instance;
	}

	public HashMap<Integer, IPricingSTGFactory> getRepo() {
		return repo;
	}

	public void setRepo(HashMap<Integer, IPricingSTGFactory> repo) {
		this.repo = repo;
	}

	public IPricingSTGFactory getFacotory(int selection) {
		return this.repo.get(selection);
	}

}
