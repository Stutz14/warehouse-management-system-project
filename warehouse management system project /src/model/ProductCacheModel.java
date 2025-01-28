package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class ProductCacheModel implements IProductCache {

	private static ProductCacheModel instance = null;
	private HashMap<String, Product> cache = new HashMap<String, Product>();
	Deque<String> cacheQue = new ArrayDeque<String>(10);

	private IDBReader dbReader;
	private static int num_objects = 0;

	private ProductCacheModel ProductCacheModel() {
		return new ProductCacheModel();
	}

	public static ProductCacheModel getInstance() {
		if (instance == null) {
			instance = new ProductCacheModel();
			instance.setDbReader(ProdDatabaseReader.getInstance());
		}
		return instance;
	}

	@Override
	public Product getProduct(String prodId) {
		if (cache.containsKey(prodId)) {
			return cache.get(prodId);
		} else {
			String[] prodInfo = dbReader.readProdDB(prodId);
			// make a new product

			String newID = prodInfo[0];
			String newName = prodInfo[1];
			int newCurrentStoc = Integer.parseInt(prodInfo[2]);
			float newUnitPrice = Float.parseFloat(prodInfo[3]);
			int newMaxStock = Integer.parseInt(prodInfo[4]);
			int newMinStock = Integer.parseInt(prodInfo[5]);
			int newRestockSchedule = Integer.parseInt(prodInfo[6]);
			int newPricingSTG = Integer.parseInt(prodInfo[7]);

			Product newProduct = new Product(newID, newName, newCurrentStoc, newUnitPrice, newMaxStock, newMinStock,
					newRestockSchedule, newPricingSTG);

			if (num_objects <= 10) {
				cache.put(newProduct.getId(), newProduct);
				cacheQue.add(newProduct.getId());
				num_objects += 1;
			} else {
				cache.put(newProduct.getId(), newProduct);
				cacheQue.add(newProduct.getId());
				cache.remove(cacheQue.pollFirst());
			}
			return newProduct;
		}
	}

	@Override
	public boolean updateProduct(String prodId, int quantity) {
		return dbReader.updateProdDB(prodId, quantity);
	}

	public IDBReader getDbReader() {
		return dbReader;
	}

	public void setDbReader(IDBReader dbReader) {
		this.dbReader = dbReader;
	}

	public int getNum_objects() {
		return num_objects;
	}

	public void setNum_objects(int num_objects) {
		this.num_objects = num_objects;
	}

}
