package viewer;

import model.IProductCache;
import model.Product;

public interface IObserver {
	public void update(Product subject, int prev);
}
