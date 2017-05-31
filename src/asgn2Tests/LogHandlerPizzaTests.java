package asgn2Tests;

import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	//POPULATEPIZZADATASET
	//Testing a normal case logfile 1
	@Test
	public void pizzaTest() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset("logs\\20170101.txt");
	}
	
	//Testing a normal case logfile 2
	@Test
	public void pizzaTest2() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset("logs\\20170102.txt");
	}
	
	//Testing a normal case logfile 3
	@Test
	public void pizzaTest3() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset("logs\\20170103.txt");
	}
	
	//Testing a wrong format for the delivery time. 19::00 was used as opposed to 19:00:00
	@Test(expected = PizzaException.class)
	public void wrongOrderTime() throws LogHandlerException, PizzaException {
		LogHandler.populatePizzaDataset("logs\\wrongOrderTimeTest.txt");
	}
		
	//Testing a wrong format for the delivery time. 19:10 was used as opposed to 19:10:00
	@Test(expected = PizzaException.class)
	public void wrongDeliveryTime() throws LogHandlerException, PizzaException {
		LogHandler.populatePizzaDataset("logs\\wrongDeliveryTimeTest.txt");
	}
	
	//Testing a wrong pizza code
	@Test(expected = PizzaException.class)
	public void wrongPizzaCode() throws LogHandlerException, PizzaException {
		LogHandler.populatePizzaDataset("logs\\wrongPizzaCodeTest.txt");
	}
	
	//Testing another wrong pizza code
	@Test(expected = PizzaException.class)
	public void wrongPizzaCode2() throws LogHandlerException, PizzaException {
		LogHandler.populatePizzaDataset("logs\\wrongPizzaCodeTest2.txt");
	}
	
	//Testing a negative amount of pizzas
	@Test(expected = PizzaException.class)
	public void negativeAmountOfPizzas() throws LogHandlerException, PizzaException {
		LogHandler.populatePizzaDataset("logs\\negativeAmountOfPizzas.txt");
	}
	
	//Testing not an int for the amount of pizzas
	@Test(expected = PizzaException.class)
	public void wrongAmountPizzas() throws LogHandlerException, PizzaException {
		LogHandler.populatePizzaDataset("logs\\wrongAmountOfPizzas.txt");
	}
	
	//CREATE PIZZA TESTS
	//Testing a normal case
	@Test
	public void createPizzaNormal() throws LogHandlerException, PizzaException {
		String line = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		LogHandler.createPizza(line);
	}
	
	//Testing another normal case with different values for each field
	@Test
	public void createPizzaNormal2() throws LogHandlerException, PizzaException {
		String line = "21:00:00,21:25:00,Steely Johnson,0981267321,PUC,3,4,PZL,5";
		LogHandler.createPizza(line);
	}
	
	//Testing an order time in the wrong format
	@Test(expected = PizzaException.class)
	public void createPizzaWrongOrderTime() throws LogHandlerException, PizzaException {
		String line = "21::00,21:25:00,Steely Johnson,0981267321,PUC,3,4,PZL,5";
		LogHandler.createPizza(line);
	}
	
	//Testing a delivery time in the wrong format
	@Test(expected = PizzaException.class)
	public void createPizzaWrongDeliveryTime() throws LogHandlerException, PizzaException {
		String line = "21::00,21:,Steely Johnson,0981267321,PUC,3,4,PZL,5";
		LogHandler.createPizza(line);
	}
	
	//Testing an incorrect pizza code
	@Test(expected = PizzaException.class)
	public void createPizzaWrongPizzaCode() throws LogHandlerException, PizzaException {
		String line = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,DDD,1";
		LogHandler.createPizza(line);
	}
	
	//Testing another incorrect pizza code
	@Test(expected = PizzaException.class)
	public void createPizzaWrongPizzaCode2() throws LogHandlerException, PizzaException {
		String line = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,YOUYOUY,1";
		LogHandler.createPizza(line);
	}
	
	//Testing a pizza quantity that is negative
	@Test(expected = PizzaException.class)
	public void createPizzaWrongQuantity() throws LogHandlerException, PizzaException {
		String line = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,-1";
		LogHandler.createPizza(line);
	}
	
}
