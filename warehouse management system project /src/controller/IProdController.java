package controller;

import model.Product;

public interface IProdController {

	public Product getProductInfo(String id);

	public void updateProductInfo(Product product);
	
}
