//In Brazil we use br.com... as package name. I used "sc" as Scotland.
package sc.com.jpmorgan.sales.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sc.com.jpmorgan.sales.dao.Database;
import sc.com.jpmorgan.sales.model.Message;
import sc.com.jpmorgan.sales.model.Product;
import sc.com.jpmorgan.sales.model.Sale;
import sc.com.jpmorgan.sales.report.Report;

public class SalesProcessor {
	
	private static final int TOTAL_MESSAGE_REPORT_SALES = 10;
	private static final int TOTAL_MESSAGE_REPORT_ADJUSTMENT = 50;
	private Report report = new Report();
	
	public SalesProcessor() {
		
	}

	//This method is responsible for process all the messages
	//This method is also responsible for log the sales and the adjustments performed 
	public void processMessages() {
		
		int countMessagesReport = 1;
		int countMessagesAdjustments = 1;
		
		List<Message> messages = readMessages();
		
		for (Message message : messages) {
			
			processSale(message);
						
			if(countMessagesReport == TOTAL_MESSAGE_REPORT_SALES) {
				countMessagesReport = 0;
				report.reportSales();
			}
			
			if(countMessagesAdjustments == TOTAL_MESSAGE_REPORT_ADJUSTMENT) {
				report.reportAdjustments();
				break;
			}
			
			countMessagesAdjustments++;
			countMessagesReport++;
		}
		
	}

	//This method is responsible for store/update each sale according to the message type
	private void processSale(Message message) {
		if(message.getType() == 1 || message.getType() == 2) {
			
			Product product = new Product(message.getProduct(), message.getPrice());
			Sale sale = new Sale(product, message.getOccurrences());
			sale.setTotal(product.getPrice() * message.getOccurrences());
			Database.getInstance().addSale(sale);
			
		} else if(message.getType() == 3) {
			Database.getInstance().updateSale(message.getOperation(), message.getPrice(), message.getProduct());
		}
	}

	//This method is responsible for returning a list with all the messages read of a Json file
	private List<Message> readMessages() {
		
		List<Message> messages = new ArrayList<Message>();
		JsonParser jsonParser = new JsonParser();
		String jsonString = readJson();

		JsonArray arrayFromString = jsonParser.parse(jsonString).getAsJsonArray();

        for (int i = 0; i < arrayFromString.size(); i++) {

             JsonElement jsonElement = arrayFromString.get(i);
             JsonObject objectMessage = jsonElement.getAsJsonObject();
             
             int type = objectMessage.get("type").getAsInt();
             String product = objectMessage.get("product").getAsString();
             double value = objectMessage.get("value").getAsDouble();
             int amount = objectMessage.get("amount").getAsInt();
             String operation = objectMessage.get("operation").getAsString();
             
             Message message = new Message(type, product, value, amount, operation);
             messages.add(message);
        }    

        return messages;
	}

	//This method is responsible for reading the Json file and returning it as a String
	private String readJson() {
		StringBuilder sb = new StringBuilder();
		try {
			
			String path = "C:\\messages.json";
			FileReader fr;
		
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line;
		
			while((line = br.readLine()) != null) {
				sb = sb.append(line);
			}
		
			br.close();
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
}
