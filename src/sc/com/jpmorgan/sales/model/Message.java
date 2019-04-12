package sc.com.jpmorgan.sales.model;

public class Message {

	private String product;
	private int type;
	private String operation = "";
	private int occurrences = 1;
	private double price = 0.0;
	
	public Message(int type, String product, double price, int occurrences, String operation) {
		this.type = type;
		this.product = product;
		this.occurrences = occurrences;
		this.operation = operation;
		this.price = price;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProducts(String product) {
		this.product = product;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public int getOccurrences() {
		return occurrences;
	}
	
	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
