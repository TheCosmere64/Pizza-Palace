package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Person A and Person B
 *
 */
public class LogHandler {
	


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		String line = "";
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			BufferedReader theFile = new BufferedReader(new FileReader(filename));
			while ((line = theFile.readLine()) != null) {
				customers.add(createCustomer(line));
			}
			theFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
        	e.printStackTrace();
		}
		return customers;
	}

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		String line = "";
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		try {
			BufferedReader theFile = new BufferedReader(new FileReader(filename));
			while ((line = theFile.readLine()) != null) {
				System.out.println(line);
				pizzaArray.add(createPizza(line));
			}
			theFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
        	e.printStackTrace();
		}
		return pizzaArray;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		Customer customer;
		String customerName = null;
		String customerMobile = null;
		String customerCode = null;
		int locationX = 0;
		int locationY = 0;
		try{
		System.out.println(line);
			String[] variables = line.split(",");
			customerName = variables[2];
			customerMobile = variables[3];
			customerCode = variables[4];
			locationX = Integer.parseInt(variables[5]);
			locationY = Integer.parseInt(variables[6]);
		}catch (Exception e){
			throw new LogHandlerException("Error parsing the line from the log file");
		}
		
		customer = CustomerFactory.getCustomer(customerCode, customerName, customerMobile, locationX, locationY);
		return customer;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		Pizza pizza;
		String pizzaCode = null;
		int quantity = 0;
		LocalTime orderTime = LocalTime.MIN;
		LocalTime deliveryTime = LocalTime.MIN;
		try {									
			String[] variable = line.split(",");		
			quantity = Integer.parseInt(variable[8]);
			orderTime = (LocalTime.parse(variable[0]));
			orderTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
			deliveryTime = LocalTime.parse(variable[1]);
			deliveryTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
			pizzaCode = variable[7];			
		} catch (Exception e) {
			throw new LogHandlerException("Error parsing the line from the log file");
		}
		System.out.println(orderTime.toString());
		System.out.println(deliveryTime.toString());
		pizza = PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
		return pizza;
	}

}
