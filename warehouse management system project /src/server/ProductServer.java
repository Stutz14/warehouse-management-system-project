package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.HashMap;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import controller.OrderManager;
import warehouseServerVisualizer.MainServerUI;

// The client calls http://localhost:8000/test1?p1=10&p2=20 (e.g. from a Web browser or a Java Client program)
// and gets back as a response "Hello World! P1 was: 10 and p2 was: 20"
// If the client calls http://http://localhost:8000/test2/?p3=1000
// it gets as a response "Hello New Brave World!  p3 was: 1000"

// Note that the server can respond by sending back a Json or XML string
// which is interpreted by the client appropriately as per the logic of the client

public class ProductServer {

	private static HashMap<Integer, OrderResult> pastOrderds;

	public void startServer() throws Exception {
		// stores past orders here
		this.pastOrderds = new HashMap<Integer, OrderResult>();
		MainServerUI.getInstance().startUI();
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/order", new OrderHandler());
		server.createContext("/orderResult", new OrderResultHandler());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
	}

	static class OrderHandler implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {

			System.out.println("call handled Part 1");
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());

			System.out.println(parms.get("p1") + " " + parms.get("p2"));

			// make a new order
			OrderManager orderManager = OrderManager.getInstance();
			int orderId = orderManager.makeOrder(parms.get("p1"), Integer.parseInt(parms.get("p2")));

			// response string
			String response = "Order number <" + orderId + "> placed for productID: " + parms.get("p1") + ", Quantity: "
					+ parms.get("p2");

			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();

			os.write(response.getBytes());
			os.flush();

			// execute order
			double orderResult = orderManager.executeOrder(orderId);
			OrderResult newOrderResult = new OrderResult(orderId, parms.get("p1"), Integer.parseInt(parms.get("p2")),
					orderResult);

			pastOrderds.put(orderId, newOrderResult);
			// store it in a db and then send response

			os.close();
			System.out.println("-------------------");
		}
	}

	static class OrderResultHandler implements HttpHandler {

		public void handle(HttpExchange exchange) throws IOException {

			System.out.println("call handled Part 2");
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());

			System.out.println(parms.get("p1"));

			// response string
			OrderResult result = pastOrderds.get(Integer.parseInt(parms.get("p1")));
			String response = result.toString();

			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();

			os.write(response.getBytes());
			os.flush();

			os.close();
			System.out.println("-------------------");
		}
	}

	public static Map<String, String> queryToMap(String query) {

		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
}