package model;

public interface IProductCache {

	public Product getProduct(String prodId);

	public boolean updateProduct(String prodId, int quantity);
}
