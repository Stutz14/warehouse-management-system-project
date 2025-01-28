package controller;

import model.IProductCache;
import model.ProductCacheModel;
import model.Product;

public class ModelController implements IProdController {

	private static ModelController instance = null;
	private IProductCache model;

	private ModelController ModelController() {
		return ModelController();
	}
	

	public static ModelController getInstance() {
		if (instance == null) {
			instance = new ModelController();
			instance.model = ProductCacheModel.getInstance();
		}
		return instance;
	}

	public Product getProductInfo(String prodId) {
		return this.model.getProduct(prodId);
	}

	public void updateProductInfo(Product product) {
		model.updateProduct(product.getId(), product.getCurrentStock());

	}

}
