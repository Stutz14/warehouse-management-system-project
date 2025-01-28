package client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

import loginServer.LoginWindow;

public class DBcreate {

	private static void deleteTable(String tableName, Statement stmt) {
		String delQuery = "DROP TABLE IF EXISTS " + tableName;
		try {
			stmt.execute(delQuery);
			System.out.println("Table " + tableName + " deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to delete " + tableName);
		}
	}

	private static void createTable(String tableName, Statement stmt) {
		String createQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (id TEXT PRIMARY KEY, "
				+ "name TEXT NOT NULL, " + "currentStock INT NOT NULL, " + "unitPrice FLOAT NOT NULL, "
				+ "maxStock INT NOT NULL, " + "minStock INT NOT NULL, " + "restockSchedule INT NOT NULL, "
				+ "pricingSTG INT NOT NULL )";
		try {
			stmt.executeUpdate(createQuery);
			System.out.println("Table " + tableName + " created");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create " + tableName);
		}
	}
	
	private static void createUserTable(String tableName, Statement stmt) {
	    String createQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
	            + "username TEXT PRIMARY KEY, " + "password TEXT NOT NULL)";
	    try {
	        stmt.executeUpdate(createQuery);
	        System.out.println("Table " + tableName + " created");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Failed to create " + tableName);
	    }
	}

	private static void fillTable(String tableName, Statement stmt) {
		String fillQuery = "INSERT INTO " + tableName + " VALUES "
				+ "( \"P1\", \"Product 1\", 200, 60.00, 1000, 50, 100, 0 ), "
				+ "( \"P2\", \"Product 2\", 330, 20.00, 2000, 50, 300, 2), "
				+ "( \"P3\", \"Product 3\", 90, 150.00, 1000, 50, 100, 1 ),"
				+ "( \"P4\", \"Product 4\", 900, 300.00, 2200, 50, 200, 4 ), "
				+ "( \"P5\", \"Product 5\", 3300, 16.00, 9000, 800, 800, 5 ), "
				+ "( \"P6\", \"Product 6\", 200, 60.25, 600, 50, 100, 4 ), "
				+ "( \"P7\", \"Product 7\", 400, 40.00, 1300, 50, 100, 3 ), "
				+ "( \"P8\", \"Product 8\", 50, 22.50, 3000, 50, 500, 5 ), "
				+ "( \"P9\", \"Product 9\", 510, 79.50, 1200, 50, 200, 2)";
		try {
			stmt.executeUpdate(fillQuery);
			System.out.println("Table " + tableName + " filled");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to fill " + tableName);
		}
	}
	
	private static void fillUserTable(String tableName, Statement stmt) {
	    String fillQuery = "INSERT INTO " + tableName + " VALUES "
	            + "( 'user1', 'password1' ), "
	            + "( 'user2', 'password2' ), "
	            + "( 'user3', 'password3' )";
	    try {
	        stmt.executeUpdate(fillQuery);
	        System.out.println("Table " + tableName + " filled");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Failed to fill " + tableName);
	    }
	}

	public static void main(String[] args) {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:test.db");

    try (Connection conn = ds.getConnection();
         Statement stmt = conn.createStatement()) {

        String prodTableName = "prod_db";
        deleteTable(prodTableName, stmt);
        createTable(prodTableName, stmt);
        fillTable(prodTableName, stmt);

        String userTableName = "user_db";
        deleteTable(userTableName, stmt);
        createUserTable(userTableName, stmt);
        fillUserTable(userTableName, stmt);

        String sq = "SELECT * FROM " + prodTableName;
        ResultSet rs = stmt.executeQuery(sq);
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            Float unitPrice = rs.getFloat("unitPrice");
            System.out.println("Product Result: ID = " + id + ", NAME = " + name + ", PRICE  = " + unitPrice);
        }



    } catch (SQLException e) {
        System.out.println("Something went wrong...");
        e.printStackTrace();
    }
}

}
