package warehouseServerVisualizer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.OrderManager;

public class AvailableProductList {
	private static AvailableProductList instance = null;

	
	private HashMap<String, Integer> availableProductList = new HashMap<String, Integer>();

	public HashMap<String, Integer> getAvailableProductList() {
		return availableProductList;
	}

	public static AvailableProductList getInstance() {
		if (instance == null)
			instance = new AvailableProductList();
		

		return instance;
	}

	private AvailableProductList() {
		//findAvailableProductsAndQuantities();
	}

	public HashMap<String, Integer> findAvailableProductsAndQuantities() {
		
		// Here we query the Product DB and we get the product names or the product IDs

		availableProductList.clear();
		
		
		
		// Here we query the Product DB and we get the product names or the product IDs
		String[] prod_list = new String[]{"P1","P2","P3","P4","P5","P6","P7","P8","P9"};
		//String[] prod_list = new String[]{"P1","P2","P3","P4","P5"};
		
		for (String prodId : prod_list) 
		{ 
		    int quantity  = OrderManager.getInstance().getProdQuanitity(prodId);
		    availableProductList.put(prodId, quantity);
		}
		return availableProductList;

	}



}
