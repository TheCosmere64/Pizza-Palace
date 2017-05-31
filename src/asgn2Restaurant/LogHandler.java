package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException {
		String line = null;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (BufferedReader theFile = new BufferedReader(new FileReader(filename))) {
			while ((line = theFile.readLine()) != null) {
				String[] variables = line.split(",");
				if (!variables[0].matches("\\d{2}:\\d{2}:\\d{2}") || !variables[1].matches("\\d{2}:\\d{2}:\\d{2}")) {
					throw new CustomerException("The customers order time or delivery time contains semantic errors");
				} else if (variables[2] == "" || !variables[3].matches("([0-9])+")) {
					throw new CustomerException("The customers mobile or name contains semantic errors");
				} else if (!variables[4].equals("PUC") && !variables[4].equals("DNC") && !variables[4].equals("DVC")) {
					throw new CustomerException("The delivery type contains semantic errors");
				} else if (!variables[5].matches("(-?[0-9])+") || !variables[6].matches("(-?[0-9])+")) {
					throw new CustomerException("The customer's X or Y location contains semantic errors");
				} else {
					customers.add(createCustomer(line));
				}
			}
		}  
		catch (IOException e) {
			throw new LogHandlerException("There is a problem parsing the line from the file");
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
		String line = null;
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		try (BufferedReader theFile = new BufferedReader(new FileReader(filename))) {
			while ((line = theFile.readLine()) != null) {
				String[] variables = line.split(",");
				if (!variables[0].matches("\\d{2}:\\d{2}:\\d{2}") || !variables[1].matches("\\d{2}:\\d{2}:\\d{2}")) {
					throw new PizzaException("The customers order time or delivery time contains semantic errors");
				} else if (!variables[7].equals("PZM") && !variables[7].equals("PZV") && !variables[7].equals("PZL")) {
					throw new PizzaException("The pizza code contains semantic errors");
				} else if (!variables[8].matches("([0-9])+")) {
					throw new PizzaException("The pizza quantity is contains semantic errors");
				} else {
					pizzas.add(createPizza(line));
				}
			}
		} catch (IOException e) {
			throw new LogHandlerException("There is a problem parsing the line from the file");
		}
		return pizzas;
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
		String[] variables = line.split(",");		
		if (!variables[0].matches("\\d{2}:\\d{2}:\\d{2}") || !variables[1].matches("\\d{2}:\\d{2}:\\d{2}")) {
			throw new CustomerException("The customers order time or delivery time contains semantic errors");
		} else if (variables[2] == "" || !variables[3].matches("([0-9])+")) {
			throw new CustomerException("The customers mobile or name contains semantic errors");
		} else if (!variables[4].equals("PUC") && !variables[4].equals("DNC") && !variables[4].equals("DVC")) {
			throw new CustomerException("The delivery type contains semantic errors");
		} else if (!variables[5].matches("(-?[0-9])+") || !variables[6].matches("(-?[0-9])+")) {
			throw new CustomerException("The customer's X or Y location contains semantic errors");
		} else {
			String customerName = variables[2];
			String customerMobile = variables[3];
			String customerCode = variables[4];
			int locationX = Integer.parseInt(variables[5]);
			int locationY = Integer.parseInt(variables[6]);
			customer = CustomerFactory.getCustomer(customerCode, customerName, customerMobile, locationX, locationY);
			return customer;
		}
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
		String[] variables = line.split(",");
		if (!variables[0].matches("\\d{2}:\\d{2}:\\d{2}") || !variables[1].matches("\\d{2}:\\d{2}:\\d{2}")) {
			throw new PizzaException("The customers order time or delivery time contains semantic errors");
		} else if (!variables[7].equals("PZM") && !variables[7].equals("PZV") && !variables[7].equals("PZL")) {
			throw new PizzaException("The pizza code contains semantic errors");
		} else if (!variables[8].matches("([0-9])+")) {
			throw new PizzaException("The pizza quantity contains semantic errors");
		} else {
			String pizzaCode = variables[7];
			int quantity = Integer.parseInt(variables[8]);
			LocalTime orderTime = (LocalTime.parse(variables[0]));
			orderTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
			LocalTime deliveryTime = LocalTime.parse(variables[1]);
			deliveryTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
			pizza = PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);	
			return pizza;
		}
	}

}