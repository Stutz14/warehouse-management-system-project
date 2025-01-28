package viewer;

import model.IProductCache;
import model.Product;
import warehouseServerVisualizer.LastOrder;
import warehouseServerVisualizer.MainServerUI;

public class ModelObserver implements IObserver {
	private Product subject;
	
	@Override
	public void update(Product product, int prev) {
		int current = product.getCurrentStock();
		if (current > prev) {
			// product was re-stocked
			MainServerUI.getInstance().showRestock(product.getName(), current, prev);
		}else {
			// product was ordered
			// update last order portion of the ui
			LastOrder lastOrder = LastOrder.getInstance();
			// set the order information
			lastOrder.setDate(java.time.LocalDateTime.now());
			lastOrder.setQuantity(prev - product.getCurrentStock());
			lastOrder.setProductName(product.getName());
		}
		
		// Client.ui.update(subject.get()
		MainServerUI.getInstance().updateUI();
	}

	public Product getSubject() {
		return subject;
	}

	public void setSubject(Product subject) {
		this.subject = subject;
	}

}
