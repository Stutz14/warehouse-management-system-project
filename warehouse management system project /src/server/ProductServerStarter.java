package server;


public class ProductServerStarter {
	public static void main(String[] args) {
		ProductServer anHttpServer = new ProductServer();
		
		try {
			anHttpServer.startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
