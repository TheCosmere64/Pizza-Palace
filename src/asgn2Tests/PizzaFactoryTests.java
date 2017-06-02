package asgn2Tests;

import java.time.LocalTime;

import org.junit.*;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.PizzaFactory;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
public class PizzaFactoryTests {
	

	@Test
	public void getPizzaMargherita() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZM", 1, orderTime, deliveryTime);
	}
	
	@Test
	public void getPizzaVegetarian() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZV", 1, orderTime, deliveryTime);
	}
	
	@Test
	public void getPizzaMeatLovers() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZL", 1, orderTime, deliveryTime);
	}
	
	//Testing with pizzas that have the same order time as delivery time
	@Test(expected = PizzaException.class)
	public void getPizzaMargheritaSameTime() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZM", 1, time, time);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaVegetarianSameTime() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZV", 1, time, time);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaMeatLoversSameTime() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZL", 1, time, time);
	}
	
	//Testing a delivery time that is less than the order time
	
	@Test(expected = PizzaException.class)
	public void getPizzaMargheritaLessThan() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 20);
		LocalTime deliveryTime = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZM", 1, orderTime, deliveryTime);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaVegetarianLessThan() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 20);
		LocalTime deliveryTime = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZV", 1, orderTime, deliveryTime);
	}
	
	//Testing an order where the pizza needs to be thrown out
	
	@Test(expected = PizzaException.class)
	public void pizzaThrownOut() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 20);
		LocalTime deliveryTime = LocalTime.of(21, 50);
		PizzaFactory.getPizza("PZV", 1, orderTime, deliveryTime);
	}
	
	@Test(expected = PizzaException.class)
	public void pizzaThrownOut2() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(21, 0);
		PizzaFactory.getPizza("PZV", 1, orderTime, deliveryTime);
	}
	
	
	//Testing an order of 0 pizzas
	
	@Test(expected = PizzaException.class)
	public void noPizzaOrder() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZL", 0, orderTime, deliveryTime);
	}
	
	@Test(expected = PizzaException.class)
	public void noPizzaOrder2() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZV", 0, orderTime, deliveryTime);
	}
	
	//Testing an order of 10 or more pizzas
	
	@Test(expected = PizzaException.class)
	public void moreThanTenOrder() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZL", 11, orderTime, deliveryTime);
	}
	
	@Test(expected = PizzaException.class)
	public void moreThanTenOrder2() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZV", 15, orderTime, deliveryTime);
	}
	
	//Tesing an order of 10 pizzas
	@Test
	public void boundaryPizza() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZL", 10, orderTime, deliveryTime);
	}
	
	//Testing pizzas that don't have valid codes meant to throw pizzaException
	
	@Test(expected = PizzaException.class)
	public void getPizzaBadCode1() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("ZZZ", 1, orderTime, deliveryTime);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaBadCode2() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZZ", 1, orderTime, deliveryTime);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaBadCode3() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		LocalTime deliveryTime = LocalTime.of(19, 20);
		PizzaFactory.getPizza("PZQ", 1, orderTime, deliveryTime);
	}
}
