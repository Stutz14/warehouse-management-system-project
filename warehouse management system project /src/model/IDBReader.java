package model;

public interface IDBReader {
	
	
	public String[] readProdDB(String prodId);
	public boolean deleteProdDB(String prodId);
	public boolean updateProdDB(String prodId, int quantity );
	public boolean addProdDB(Product product);
	
	
}
