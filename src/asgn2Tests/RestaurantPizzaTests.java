package asgn2Tests;

import java.time.LocalTime;

import org.junit.Before;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;

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
	
	MeatLoversPizza meatPizza;
	VegetarianPizza vegiePizza;
	MargheritaPizza margPizza;
	
	//JUST PIZZA OBJECTS
	@Before
	public void instantiatePizzaObjects() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		meatPizza = new MeatLoversPizza(1, order, delivery);
		vegiePizza = new VegetarianPizza(1, order, delivery);
		margPizza = new MargheritaPizza(1, order, delivery);
		
	}
	
	@Test
	
	
}
