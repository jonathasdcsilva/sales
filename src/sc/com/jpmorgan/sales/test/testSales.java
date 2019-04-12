package sc.com.jpmorgan.sales.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sc.com.jpmorgan.sales.dao.Database;
import sc.com.jpmorgan.sales.model.Message;
import sc.com.jpmorgan.sales.model.Product;
import sc.com.jpmorgan.sales.model.Sale;

public class testSales {

	@Test
	public void testProcessingMessageType1() {
		Message message = new Message(1,"Apple",10.0,1,"");
		Product product = new Product(message.getProduct(), message.getPrice());
		Sale sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		Assert.assertEquals("Apple", sales.get(0).getProduct().getName());
		
		Database.getInstance().clearAllSales();
	}

	@Test
	public void testProcessingMessageType2() {
		Message message = new Message(2,"Apple",20.0,10,"");
		Product product = new Product(message.getProduct(), message.getPrice());
		Sale sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		Assert.assertEquals(200.0, sales.get(0).getTotal(), 0.0000001);
		
		Database.getInstance().clearAllSales();
	}
	
	@Test
	public void testProcessingMessageType3Adding() {
		Message message = new Message(1,"Apple",10.0,1,"");
		Product product = new Product(message.getProduct(), message.getPrice());
		Sale sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(2,"Apple",20.0,10,"");
		product = new Product(message.getProduct(), message.getPrice());
		sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(3,"Apple",10.0,0,"ADD");
		Database.getInstance().updateSale(message.getOperation(), message.getPrice(), message.getProduct());
		
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		Assert.assertEquals(320.0, sales.get(0).getTotal(), 0.0000001);
		
		Database.getInstance().clearAllSales();
	}
	
	@Test
	public void testProcessingMessageType3Subtracting() {
		Message message = new Message(1,"Apple",10.0,1,"");
		Product product = new Product(message.getProduct(), message.getPrice());
		Sale sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(2,"Apple",20.0,10,"");
		product = new Product(message.getProduct(), message.getPrice());
		sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(3,"Apple",5.0,0,"SUBTRACT");
		Database.getInstance().updateSale(message.getOperation(), message.getPrice(), message.getProduct());
		
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		Assert.assertEquals(155.0, sales.get(0).getTotal(), 0.0000001);
		
		Database.getInstance().clearAllSales();
	}
	
	@Test
	public void testProcessingMessageType3Multiplying() {
		Message message = new Message(1,"Apple",10.0,1,"");
		Product product = new Product(message.getProduct(), message.getPrice());
		Sale sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(2,"Apple",20.0,10,"");
		product = new Product(message.getProduct(), message.getPrice());
		sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(3,"Apple",2.0,0,"MULTIPLY");
		Database.getInstance().updateSale(message.getOperation(), message.getPrice(), message.getProduct());
		
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		Assert.assertEquals(420.0, sales.get(0).getTotal(), 0.0000001);
		
		Database.getInstance().clearAllSales();
	}
	
	@Test
	public void testProcessingMessageType3Adding2Times() {
		Message message = new Message(1,"Apple",10.0,1,"");
		Product product = new Product(message.getProduct(), message.getPrice());
		Sale sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(2,"Apple",20.0,10,"");
		product = new Product(message.getProduct(), message.getPrice());
		sale = new Sale(product, message.getOccurrences());
		sale.setTotal(product.getPrice() * message.getOccurrences());
		Database.getInstance().addSale(sale);
		
		message = new Message(3,"Apple",10.0,0,"ADD");
		Database.getInstance().updateSale(message.getOperation(), message.getPrice(), message.getProduct());
		
		message = new Message(3,"Apple",10.0,0,"ADD");
		Database.getInstance().updateSale(message.getOperation(), message.getPrice(), message.getProduct());
		
		List<Sale> sales = Database.getInstance().getSalesSynthetic();
		Assert.assertEquals(430.0, sales.get(0).getTotal(), 0.0000001);
		
		Database.getInstance().clearAllSales();
	}
}
