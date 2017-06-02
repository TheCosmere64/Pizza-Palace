package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * 
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {

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
	
	//Testing getting a pizza by index 
	@Test
	public void getPizzaNormal() throws PizzaException {
		pizzaRes.getPizzaByIndex(0);
	}
		
	//Testing a normal case
	@Test
	public void getPizzaLargerNormal() throws PizzaException {
		largerRes.getPizzaByIndex(50);
	}
	
	//Testing getting a pizza by index (there is only 3 pizza objects contained in this logfile
	@Test
	public void getPizzaBoundary() throws PizzaException {
		pizzaRes.getPizzaByIndex(2);
	}
	
	//There is one hundred pizza objects in this file, testing the bounds
	@Test
	public void getPizzaLargerBoundary() throws PizzaException {
		largerRes.getPizzaByIndex(99);
	}
	
	//Testing the amount of pizza objects
	@Test
	public void getCustomers() throws PizzaException {
		assertEquals(3, pizzaRes.getNumPizzaOrders());
	}
	
	@Test
	public void getCustomersLargerRestaurant() throws PizzaException {
		assertEquals(100, largerRes.getNumPizzaOrders());
	}
	
	//Testing the total order profit using a forloop to iterate through the pizza objects to get the total delivery distance and comparing it to what the method found
	@Test
	public void totalDeliveryDistance() throws PizzaException {
		double totalDistance = 0;
		for (int i = 0; i < largerRes.getNumPizzaOrders(); i++) {
			totalDistance += largerRes.getPizzaByIndex(i).getOrderProfit();
		}
		assertEquals(totalDistance, largerRes.getTotalProfit(), 1);
	}
	
	//Testing to see if it resets the details properly
	@Test
	public void resetTest() throws PizzaException {
		largerRes.resetDetails();
	}
	
	//Testing if it will return a pizza object even after the details have been reset
	@Test(expected = PizzaException.class)
	public void resetTestProper() throws PizzaException {
		largerRes.resetDetails();
		largerRes.getPizzaByIndex(0);
	}
}
