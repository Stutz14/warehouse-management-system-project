package model;

import model.pricing.PricingSTG;
import utilities.PricingSTGFactoryPopulator;
import viewer.IObserver;
import viewer.ModelObserver;

public class Product {
	private String id;
	private String name;
	private int currentStock;
	private float unitPrice;
	private int maxStock;
	private int minStock;
	private int restockSchedule;
	private PricingSTG pricingSTG;
	private IObserver observer;

	public Product(String id, String name, int currentStock, float unitPrice, int maxStock, int minStock,
			int restockSchedule, int priceSTG) {
		this.id = id;
		this.name = name;
		this.currentStock = currentStock;
		this.unitPrice = unitPrice;
		this.maxStock = maxStock;
		this.minStock = minStock;
		this.restockSchedule = restockSchedule;
		this.pricingSTG = PricingSTGFactoryPopulator.populateRepo().getFacotory(priceSTG).makePricingSTG(this);
	}

	private void notifyViewer(int prevAmount) {
		if (this.observer == null) {
			this.observer = new ModelObserver();
		}
		this.observer.update(this, prevAmount);
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int newStock) {
		int oldStock = this.getCurrentStock();
		this.currentStock = newStock;
		notifyViewer(oldStock);
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getRestockSchedule() {
		return restockSchedule;
	}

	public void setRestockSchedule(int restockSchedule) {
		this.restockSchedule = restockSchedule;
	}

	public PricingSTG getPricingSTG() {
		return pricingSTG;
	}

	public void setPricingSTG(PricingSTG pricingSTG) {
		this.pricingSTG = pricingSTG;
	}
}
