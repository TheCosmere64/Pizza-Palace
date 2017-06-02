package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	//Order has to be placed after 7pm
	//Order has to be before 11pm
	//At least one pizza
	//Maximum number of pizzas is 10
	
	//Meat lovers variable to test pizza abstract class
	MeatLoversPizza meatLoversPizza;
	VegetarianPizza vegieLoversPizza;
	MargheritaPizza margLoversPizza;
	
	MargheritaPizza margPizza;
	MeatLoversPizza meatPizza;
	VegetarianPizza vegiePizza;
	
	//MARGHERITA PIZZA TESTING
	
	//NEED TO ADD ABSTRACT CLASS PIZZA TESTING UNSURE HOW
	@Before
	public void instantiateMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		meatLoversPizza = new MeatLoversPizza(7, order, delivery);
		vegieLoversPizza = new VegetarianPizza(4, order, delivery);
		margLoversPizza = new MargheritaPizza(6, order, delivery);
	}
	
	
	//Testing order time of 7pm and delivery time of 7:20pm with 1 pizza
	@Test
	public void normalTimeTestMargherita() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		margPizza = new MargheritaPizza(1, order, delivery);
	}
	
	//Testing order time of 10pm and delivery time of 10:20pm with 5 pizzas
	@Test
	public void normalTimeTest2Margherita() throws PizzaException {
		LocalTime order = LocalTime.of(22, 0);
		LocalTime delivery = LocalTime.of(22, 20);
		margPizza = new MargheritaPizza(5, order, delivery);
	}
	
	//Testing order time of 10:59pm and delivery time of 11:20pm with 1 pizza
	@Test
	public void borderLineTimeTestMargherita() throws PizzaException {
		LocalTime order = LocalTime.of(22, 59);
		LocalTime delivery = LocalTime.of(23, 20);
		margPizza = new MargheritaPizza(1, order, delivery);
	}
	
	//Testing an order of 10 pizzas
	@Test
	public void tenPizzaTestMargherita() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		margPizza = new MargheritaPizza(10, order, delivery);
	}
	
	//Testing an order greater than 10 pizzas
	@Test(expected = PizzaException.class)
	public void tooManyPizzasTestMargherita() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		margPizza = new MargheritaPizza(11, order, delivery);
	}
	
	//Testing an order far greater than 10 pizzas
	@Test(expected = PizzaException.class)
	public void farTooManyPizzasTestMargherita() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		margPizza = new MargheritaPizza(100, order, delivery);
	}
	
	//Testing an invalid order time of 11pm
	@Test(expected = PizzaException.class)
	public void invalidOrderTimeMargherita() throws PizzaException {
		LocalTime order = LocalTime.of(23, 0);
		LocalTime delivery = LocalTime.of(23, 20);
		margPizza = new MargheritaPizza(1, order, delivery);
	}
	
	//Testing an invalid order time of 6:59pm
	@Test(expected = PizzaException.class)
	public void invalidOrderTime2Margherita() throws PizzaException {
		LocalTime order = LocalTime.of(18, 59);
		LocalTime delivery = LocalTime.of(19, 20);
		margPizza = new MargheritaPizza(1, order, delivery);
	}
	
	//Testing an extreme order time of 4am
	@Test(expected = PizzaException.class)
	public void invalidOrderTime3Margherita() throws PizzaException {
		LocalTime order = LocalTime.of(4, 0);
		LocalTime delivery = LocalTime.of(4, 20);
		margPizza = new MargheritaPizza(1, order, delivery);
	}
	
	//MEAT LOVERS PIZZA TESTING
	
	//Testing order time of 7pm and delivery time of 7:20pm with 1 pizza
	@Test
	public void normalTimeTestMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		meatPizza = new MeatLoversPizza(1, order, delivery);
	}
		
	//Testing order time of 10pm and delivery time of 10:20pm with 5 pizzas
	@Test
	public void normalTimeTest2MeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(22, 0);
		LocalTime delivery = LocalTime.of(22, 20);
		meatPizza = new MeatLoversPizza(5, order, delivery);
	}
		
	//Testing order time of 10:59pm and delivery time of 11:20pm with 1 pizza
	@Test
	public void borderLineTimeTestMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(22, 59);
		LocalTime delivery = LocalTime.of(23, 20);
		meatPizza = new MeatLoversPizza(1, order, delivery);
	}
		
	//Testing an order of 10 pizzas
	@Test
	public void tenPizzaTestMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		meatPizza = new MeatLoversPizza(10, order, delivery);
	}
	
	//Testing an order greater than 10 pizzas
	@Test(expected = PizzaException.class)
	public void tooManyPizzasTestMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		meatPizza = new MeatLoversPizza(11, order, delivery);
	}
		
	//Testing an order far greater than 10 pizzas
	@Test(expected = PizzaException.class)
	public void farTooManyPizzasTestMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		meatPizza = new MeatLoversPizza(100, order, delivery);
	}
		
	//Testing an invalid order time of 11pm
	@Test(expected = PizzaException.class)
	public void invalidOrderTimeMeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(23, 0);
		LocalTime delivery = LocalTime.of(23, 20);
		meatPizza = new MeatLoversPizza(1, order, delivery);
	}
		
	//Testing an invalid order time of 6:59pm
	@Test(expected = PizzaException.class)
	public void invalidOrderTime2MeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(18, 59);
		LocalTime delivery = LocalTime.of(19, 20);
		meatPizza = new MeatLoversPizza(1, order, delivery);
	}
		
	//Testing an extreme order time of 4am
	@Test(expected = PizzaException.class)
	public void invalidOrderTime3MeatLovers() throws PizzaException {
		LocalTime order = LocalTime.of(4, 0);
		LocalTime delivery = LocalTime.of(4, 20);
		meatPizza = new MeatLoversPizza(1, order, delivery);
	}
	
	
	//VEGETARIAN PIZZA TESTING
	
	//Testing order time of 7pm and delivery time of 7:20pm with 1 pizza
	@Test
	public void normalTimeTestVegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		vegiePizza = new VegetarianPizza(1, order, delivery);
	}
			
	//Testing order time of 10pm and delivery time of 10:20pm with 5 pizzas
	@Test
	public void normalTimeTest2Vegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(22, 0);
		LocalTime delivery = LocalTime.of(22, 20);
		vegiePizza = new VegetarianPizza(5, order, delivery);
	}
		
	//Testing order time of 10:59pm and delivery time of 11:20pm with 1 pizza
	@Test
	public void borderLineTimeTestVegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(22, 59);
		LocalTime delivery = LocalTime.of(23, 20);
		vegiePizza = new VegetarianPizza(1, order, delivery);
	}
		
	//Testing an order of 10 pizzas
	@Test
	public void tenPizzaTestVegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		vegiePizza = new VegetarianPizza(10, order, delivery);
	}
	
	//Testing an order greater than 10 pizzas
	@Test(expected = PizzaException.class)
	public void tooManyPizzasTestVegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		vegiePizza = new VegetarianPizza(11, order, delivery);
	}
		
	//Testing an order far greater than 10 pizzas
	@Test(expected = PizzaException.class)
	public void farTooManyPizzasTestVegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(19, 0);
		LocalTime delivery = LocalTime.of(19, 20);
		vegiePizza = new VegetarianPizza(100, order, delivery);
	}
		
	//Testing an invalid order time of 11pm
	@Test(expected = PizzaException.class)
	public void invalidOrderTimeVegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(23, 0);
		LocalTime delivery = LocalTime.of(23, 20);
		vegiePizza = new VegetarianPizza(1, order, delivery);
	}
	
	//Testing an invalid order time of 6:59pm
	@Test(expected = PizzaException.class)
	public void invalidOrderTime2Vegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(18, 59);
		LocalTime delivery = LocalTime.of(19, 20);
		vegiePizza = new VegetarianPizza(1, order, delivery);
	}
		
	//Testing an extreme order time of 4am
	@Test(expected = PizzaException.class)
	public void invalidOrderTime3Vegetarian() throws PizzaException {
		LocalTime order = LocalTime.of(4, 0);
		LocalTime delivery = LocalTime.of(4, 20);
		vegiePizza = new VegetarianPizza(1, order, delivery);
	}
	
	//		meatLoversPizza = new MeatLoversPizza(1, order, delivery);

	//TESTING THE ABSTRACT CLASS PIZZA
	//Testing the cost per pizza of a meatlovers
	//Tomato + Cheese + Bacon + Pepperoni + Salami = 0.5 + 1 + 1.5 + 1 + 1 = 5
	@Test
	public void costPerPizzaTest() throws PizzaException {
		meatLoversPizza.calculateCostPerPizza();
		assertEquals(5, meatLoversPizza.getCostPerPizza(), 0);
	}
	
	//Testing the cost per pizza of a vegetarian
	//Tomato, Cheese, Eggplant, Mushroom, Capsicum  = 0.5 + 1 + 0.8 + 2 + 1.2 = 5.5
	@Test
	public void costPerPizzaTest2() throws PizzaException {
		vegieLoversPizza.calculateCostPerPizza();
		assertEquals(5.5, vegieLoversPizza.getCostPerPizza(), 0);
	}
	
	//Testing the cost per pizza of a vegetarian
	//Tomato, Cheese = 0.5 + 1 = 1.5
	@Test
	public void costPerPizzaTest3() throws PizzaException {
		margLoversPizza.calculateCostPerPizza();
		assertEquals(1.5, margLoversPizza.getCostPerPizza(), 0);
	}
	
	//Testing the price of a pizza which is meant to be 12
	@Test
	public void priceOfPizzaTest() throws PizzaException {
		meatLoversPizza.calculateCostPerPizza();
		assertEquals(12, meatLoversPizza.getPricePerPizza(), 0);
	}
	
	//Testing the order cost of the pizzas
	@Test
	public void OrderCostTest() throws PizzaException {
		meatLoversPizza.calculateCostPerPizza();
		assertEquals(meatLoversPizza.getQuantity() * meatLoversPizza.getCostPerPizza(), meatLoversPizza.getOrderCost(), 0);
		// amount of pizzas is 7 cost to make is 5. 7 * 5 = 35
		assertEquals(35, meatLoversPizza.getOrderCost(), 0);
	}
	
	//Testing the order price of the pizzas
	@Test
	public void OrderPriceTest() throws PizzaException {
		meatLoversPizza.calculateCostPerPizza();
		assertEquals(meatLoversPizza.getQuantity() * meatLoversPizza.getPricePerPizza(), meatLoversPizza.getOrderPrice(), 0);
		// amount of pizzas is 7 cost to its sold to the customer at 12. 7 * 12 = 84
		assertEquals(84, meatLoversPizza.getOrderPrice(), 0);
	}
	
	//Testing the getQuantity method
	@Test
	public void getQuantityTest() throws PizzaException {
		assertEquals(7, meatLoversPizza.getQuantity());
	}
	
	//Testing the order profit for a meatlovers pizza
	//The order price is 84 and the order cost is 35 so 84-35 is 49
	@Test
	public void getProfit() throws PizzaException {
		meatLoversPizza.calculateCostPerPizza();
		assertEquals(84, meatLoversPizza.getOrderPrice(), 0);
		assertEquals(35, meatLoversPizza.getOrderCost(), 0);
		assertEquals(49, meatLoversPizza.getOrderProfit(), 0);
	}
	
	//Testing if the pizza type is returning properly
	@Test
	public void pizzaType() throws PizzaException {
		assertEquals("Meatlovers", meatLoversPizza.getPizzaType());
	}
}

