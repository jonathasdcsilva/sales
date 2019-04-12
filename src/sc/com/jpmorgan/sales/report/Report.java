package sc.com.jpmorgan.sales.report;

import java.util.List;

import sc.com.jpmorgan.sales.dao.Database;
import sc.com.jpmorgan.sales.model.Sale;

public class Report {

	public Report() {
		
	}
	
	//The log printed after every 10th message processed
	public void reportSales() {
		System.out.println("========================");
		System.out.println("Report sales");
		System.out.println("========================");
		System.out.println();
		System.out.println("Products");
		System.out.println();
		printProduct();
		System.out.println();
	}

	//This method prints the amount of sales and their total value of each product
	private void printProduct() {
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		
		sales.stream().forEach(s -> System.out.println(
									s.getAmount() + 
									" sales of " + 
									s.getProduct().getName() +
									" totalizing " + 
									s.getTotal() + "p"));
	}

	//The log printed after 50 messages processed
	public void reportAdjustments() {
		System.out.println("========================");
		System.out.println("Sorry! The application has stopped right now. Try again later.");
		System.out.println("========================");
		System.out.println("Report of all sales adjustments");
		System.out.println("========================");
		System.out.println();
		System.out.println("Adjustments");
		System.out.println();
		printAdjustments();
	}
	
	//This method prints all the adjustments performed
	private void printAdjustments() {
		List<String> adjustments = Database.getInstance().getAdjustment();
		adjustments.stream().forEach(s -> System.out.println(s));
	}
}
