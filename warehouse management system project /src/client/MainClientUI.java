/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainClientUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JComboBox<String> productList;
	private static JComboBox<String> quantityList;
	private static String theProduct;
	private static String theQuantity;
	private JTextArea orderDetails;
	private static String quantityReport = null;
	private static String timeReport = null;

	private static MainClientUI instance;

	public static MainClientUI getInstance() {
		if (instance == null)
			instance = new MainClientUI();

		return instance;
	}

	private MainClientUI() {
		// Set window title
		super("Product Ordering Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set top bar
		JLabel step1 = new JLabel("Step1 Choose Product");
		JLabel step2 = new JLabel("Step2 Choose Quantity");

		JLabel chooseProductLabel = new JLabel(": ");
		Vector<String> productNames = new Vector<String>();
		productList = new JComboBox<String>(productNames);
		productNames.add("P1");
		productNames.add("P2");
		productNames.add("P3");
		productNames.add("P4");
		productNames.add("P5");
		productNames.add("P6");
		productNames.add("P7");
		productNames.add("P8");
		productNames.sort(null);

		// JButton addProduct = new JButton("Choose");
		// addProduct.setActionCommand("addProduct");
		// addProduct.addActionListener(this);

		JLabel qty = new JLabel(": ");
		// JLabel to = new JLabel("To");
		Vector<String> quantity = new Vector<String>();
		for (int i = 0; i <= 1000; i = i + 50) {
			quantity.add("" + i);
		}
		quantity.remove(0);
		quantity.add(0, "1");
		quantityList = new JComboBox<String>(quantity);
		JButton addQuantity = new JButton("Choose");
		addQuantity.setActionCommand("addQuantity");
		addQuantity.addActionListener(this);

		// quantityList.setActionCommand("addQuantity");

		JPanel north = new JPanel();
		north.add(step1);
		north.add(chooseProductLabel);
		north.add(productList);
		// north.add(addProduct);
		north.add(step2);
		north.add(qty);
		north.add(quantityList);
		north.add(addQuantity);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		// createCharts(west);

		JLabel orderDetailsLabel = new JLabel("Order Details: ");
		orderDetails = new JTextArea(30, 60);
		JScrollPane orderDetailsScrollPane = new JScrollPane(orderDetails);
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(orderDetailsLabel);
		east.add(orderDetailsScrollPane);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.WEST);
	}

	private void createCharts(JPanel west) {
		createReport(west);

	}

	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = "Product " + theProduct + "\n" + "Quantity " + theQuantity + "\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

	public static void main(String[] args) {

		JFrame frame = MainClientUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

	}
	public static void startClientUI() {
		JFrame frame = MainClientUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

	}
	

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.print(command);
		

		if ("addQuantity".equals(command)) {

			// selectedList.add(cryptoList.getSelectedItem().toString());

			theQuantity = quantityList.getSelectedItem().toString();
			theProduct = productList.getSelectedItem().toString();

			quantityReport = "Quantity : " + theQuantity + "\n";
			timeReport = "Client Time Stamp : " + java.time.LocalDateTime.now().toString() + "\n";

			orderDetails.setText(quantityReport + timeReport + "\n");
			
			String orderId = "0";
			
			try {
				String urlString = String.format("http://localhost:8000/order?p1=%s&p2=%s", theProduct, theQuantity);

				URL url = new URL(urlString);

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");

				int responseCode = connection.getResponseCode();
				System.out.println("Response Code: " + responseCode);

				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line;
					StringBuilder response = new StringBuilder();

					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					orderDetails.setText(response.toString() + timeReport + "\n");

					reader.close();

					System.out.println("Response Content:\n" + response.toString());
				} else {
					System.out.println("HTTP request failed with response code: " + responseCode);
				}

				connection.disconnect();

			} catch (IOException execption) {
				System.out.println("Something went wrong with the API call.");
			}
			;
			
			try {
				String urlString = String.format("http://localhost:8000/orderResult?p1=%s", orderId);

				URL url = new URL(urlString);

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");

				int responseCode = connection.getResponseCode();
				System.out.println("Response Code: " + responseCode);

				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line;
					StringBuilder response = new StringBuilder();

					while ((line = reader.readLine()) != null) {
						response.append(line);
					}

					reader.close();

					System.out.println("Order status:\n" + response.toString());
				} else {
					System.out.println("HTTP request failed with response code: " + responseCode);
				}

				connection.disconnect();
				
			} catch (IOException execption) {
				System.out.println("Something went wrong with the API call.");
			}
			;
		}
	}
	// TODO Auto-generated method stub

}