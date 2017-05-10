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
	

	//margherita(“PZM”), vegetarian (“PZV”) or meat lovers (“PZL”).
	@Test
	public void getPizzaMargherita() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZM", 1, time, time);
	}
	
	@Test
	public void getPizzaVegetarian() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZV", 1, time, time);
	}
	
	@Test
	public void getPizzaMeatLovers() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZL", 1, time, time);
	}
	//Testing pizzas that don't have valid codes meant to throw pizzaException
	
	@Test(expected = PizzaException.class)
	public void getPizzaBadCode1() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("ZZZ", 1, time, time);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaBadCode2() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZZ", 1, time, time);
	}
	
	@Test(expected = PizzaException.class)
	public void getPizzaBadCode3() throws PizzaException {
		LocalTime time = LocalTime.of(19, 0);
		PizzaFactory.getPizza("PZQ", 1, time, time);
	}
	
	
	
}
