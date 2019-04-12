package sc.com.jpmorgan.sales.program;

import sc.com.jpmorgan.sales.resources.SalesProcessor;

public class Program {

	public static void main(String[] args) {
		
		SalesProcessor salesProcessor = new SalesProcessor();
		salesProcessor.processMessages();
		
	}
}
