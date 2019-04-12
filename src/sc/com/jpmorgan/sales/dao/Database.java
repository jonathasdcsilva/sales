package sc.com.jpmorgan.sales.dao;

import java.util.ArrayList;
import java.util.List;

import sc.com.jpmorgan.sales.model.Sale;

public class Database {
	
	private static Database INSTANCE;
	
	//These list are used to save the sales processed
	//The Analytical list is used to save all the sales processed
	//The Synthetic list is used to save the sales grouped by product
	//The adjustment list is used to save all the adjustment instructions
	private List<Sale> salesAnalytical = new ArrayList<Sale>();
	private List<Sale> salesSynthetic = new ArrayList<Sale>();
	private List<String> adjustment = new ArrayList<String>();
	
	private Database() {
	}
	
	//I created a singleton class to save the data in memory
	public static Database getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}
	
	public List<Sale> getSalesAnalytical() {
		return salesAnalytical;
	}
	
	public List<Sale> getSalesSynthetic() {
		return salesSynthetic;
	}
	
	public List<String> getAdjustment() {
		return adjustment;
	}
	
	public void addAdjustment(String adjustment) {
		this.adjustment.add(adjustment);
	}
	
	public void clearAllSales() {
		salesAnalytical = new ArrayList<Sale>();
		salesSynthetic = new ArrayList<Sale>();
		adjustment = new ArrayList<String>();
	}

	//Add the sale in the analytical list
	public void addSale(Sale sale) {
		this.salesAnalytical.add(sale);
		updateSalesSynthetic(sale);
	}
	
	//Add the sale in the synthetic list if it doesn't exist
	//or update the total and the amount of the product if it exist
	private void updateSalesSynthetic(Sale sale) {
		if(salesSynthetic.stream().noneMatch(c -> c.getProduct().getName().toLowerCase().equals(sale.getProduct().getName().toLowerCase()))) {
			salesSynthetic.add(sale);
		} else {
			for (Sale saleSynthetic : salesSynthetic) {
				if(saleSynthetic.getProduct().getName().toLowerCase().equals(sale.getProduct().getName().toLowerCase())) {
					saleSynthetic.setAmount(saleSynthetic.getAmount() + sale.getAmount());
					saleSynthetic.setTotal(saleSynthetic.getTotal() + sale.getTotal());
				}
			}
		}
	}

	//this method update the price according to the instruction in the message of type3
	public void updateSale(String operation, double price, String product) {
		
		for (Sale saleTemp : salesSynthetic) {
			if(saleTemp.getProduct().getName().toLowerCase().equals(product.toLowerCase())) {
				switch (operation.toUpperCase()) {
				case "ADD":
					saleTemp.setTotal(saleTemp.getTotal() + (saleTemp.getAmount() * price));
					break;
				case "SUBTRACT":
					saleTemp.setTotal(saleTemp.getTotal() - (saleTemp.getAmount() * price));
					break;
				case "MULTIPLY":
					saleTemp.setTotal(saleTemp.getTotal() * price);
					break;

				default:
					break;
				}
			}
		}
		
		String adjust = operation.toUpperCase() + " " + price + "p to the product " + product;
		addAdjustment(adjust);
	}
}
