package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class ProdDatabaseReader implements IDBReader {

	private static ProdDatabaseReader instance = null;
	private Connection databaseConnection;
	private final String TABLE_NAME = "prod_db";

//	private static 

	private ProdDatabaseReader ProdDatabaseReader() {
		return new ProdDatabaseReader();
	}

	public static ProdDatabaseReader getInstance() {
		if (instance == null) {
			instance = new ProdDatabaseReader();
		}
		return instance;
	}

	private ProdDatabaseReader() {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:test.db");
		try {
			this.databaseConnection = ds.getConnection();

		} catch (SQLException e) {
			System.out.println("Failed to establish connection...");
		}
	}

	@Override
	public String[] readProdDB(String prodId) {
		String[] output = null;
		try {
			Statement stmt = databaseConnection.createStatement();
			String readQuery = "select * from " + TABLE_NAME + " WHERE id = \"" + prodId + "\"";

			ResultSet result = stmt.executeQuery(readQuery);
			if (result.next()) {
				String id = result.getString("id");
				String name = result.getString("name");
				String currentStock = result.getString("currentStock");
				String unitPrice = result.getString("unitPrice");
				String maxStock = result.getString("maxStock");
				String minStock = result.getString("minStock");
				String restockSchedule = result.getString("restockSchedule");
				String pricingSTG = result.getString("pricingSTG");

				output = new String[] { id, name, currentStock, unitPrice, maxStock, minStock, restockSchedule,
						pricingSTG };
			}
		} catch (SQLException e) {
			System.out.println("Failed to read productID = " + prodId);
			e.printStackTrace();
		}
		return output;
	}

	@Override
	public boolean deleteProdDB(String prodId) {
		return false;
	}

	@Override
	public boolean updateProdDB(String prodId, int quantity) {
		try {
			Statement stmt = databaseConnection.createStatement();
			String updateQuery = "UPDATE " + TABLE_NAME + " SET currentStock = " + quantity + " WHERE id = '" + prodId
					+ "'";

		} catch (SQLException e) {
			System.out.println("Failed to update product with id: " +prodId);
			e.printStackTrace();
		}
		
		// for now do nothing
		return false;
	}

	@Override
	public boolean addProdDB(Product product) {
		return false;
	}

}
