package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Person A
 */
public class RestaurantCustomerTests {
	
	PizzaRestaurant pizzaRes; 
	PizzaRestaurant largerRes;
	//JUST PIZZA OBJECTS
	@Before
	public void instantiatePizzaObjects() throws PizzaException, LogHandlerException, CustomerException {
		pizzaRes = new PizzaRestaurant();
		largerRes = new PizzaRestaurant();
		pizzaRes.processLog("logs\\20170101.txt");
		largerRes.processLog("logs\\20170103.txt");
	}
	
	//Testing getting a customer by index 
	@Test
	public void getCustomerNormal() throws CustomerException {
		pizzaRes.getCustomerByIndex(0);
	}
		
	//Testing a normal case
	@Test
	public void getCustomerLargerNormal() throws CustomerException {
		largerRes.getCustomerByIndex(50);
	}
	
	//Testing getting a customer by index (there is only 3 customer objects contained in this logfile
	@Test
	public void getCustomerBoundary() throws CustomerException {
		pizzaRes.getCustomerByIndex(2);
	}
	
	//There is one hundred custoemr objects in this file, testing the bounds
	@Test
	public void getCustomerLargerBoundary() throws CustomerException {
		largerRes.getCustomerByIndex(99);
	}
	
	//Testing the amount of customer objects
	@Test
	public void getCustomers() throws CustomerException {
		assertEquals(3, pizzaRes.getNumCustomerOrders());
	}
	
	@Test
	public void getCustomersLargerRestaurant() throws CustomerException {
		assertEquals(100, largerRes.getNumCustomerOrders());
	}
	
	//Testing the total delivery distance, using a forloop to iterate through the customer objects to get the total delivery distance and comparing it to what the method found
	@Test
	public void totalDeliveryDistance() throws CustomerException {
		double totalDistance = 0;
		for (int i = 0; i < largerRes.getNumCustomerOrders(); i++) {
			totalDistance += largerRes.getCustomerByIndex(i).getDeliveryDistance();
		}
		assertEquals(totalDistance, largerRes.getTotalDeliveryDistance(), 1);
	}
	
	//Testing to see if it resets the details properly
	@Test
	public void resetTest() throws CustomerException {
		largerRes.resetDetails();
	}
	
	//Testing if it will return a customer object even after the details have been reset
	@Test(expected = CustomerException.class)
	public void resetTestProper() throws CustomerException {
		largerRes.resetDetails();
		largerRes.getCustomerByIndex(0);
	}
	
}
