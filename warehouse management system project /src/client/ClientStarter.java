package client;

import loginServer.LoginWindow;
import server.ProductServer;

public class ClientStarter {

	public static void main(String[] args) {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.LoginWindow();

		ProductServer anHttpServer = new ProductServer();

		try {
			anHttpServer.startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}


