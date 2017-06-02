package asgn2Pizzas;

import java.time.LocalTime;
import asgn2Pizzas.Pizza;
import asgn2Exceptions.PizzaException;

/**
 * A class that instantiates the subclasses of asgn2Pizzas.Pizza using the Factory Method pattern. 
 * The classes are instantiated from one of the three valid pizza codes outlined in
 * Section 5.3 of the Assignment Specification. Any other code will throw a PizzaException.      
 *  
 * @author Person A
 *
 */

public class PizzaFactory {	
	/**
	 * A method that uses the Factory Method pattern to produce an instance of one of the asgn2Pizzas.Pizza subclasses. 
	 * Subclasses are created using the pizzaCode. All valid pizza codes are listed in Section 5.3 of the Assignment Specification.
	 * A PizzaException should be thrown if an invalid pizza code is used as a parameter. 
	 * 
	 * @param pizzaCode - A code indicating the subclass of asgn2Pizzas.Pizza to instantiate. The valid codes are listed in Section 5.3 of the Assignment Specification. 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if the pizzaCode is not one of the three valid codes listed in Section 5.3 of the Assignment Specification. 
	 * @return A valid Pizza object using the specified parameters 
	 * */
	public static Pizza getPizza(String pizzaCode, int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException{		
		if (quantity > 10) {			
			throw new PizzaException("Too many pizzas ordered");
		}
		else if (quantity < 1){			
			throw new PizzaException("No pizzas ordered");
		}
		else if (orderTime == deliveryTime){			
			throw new PizzaException("Cannot deliver a pizza instantaneously");
		}
		else if (deliveryTime.isBefore(orderTime)){			
			throw new PizzaException("Cannot deliver pizza before it was ordered");
		}
		else if (((deliveryTime.getHour() - orderTime.getHour()) * 60) + deliveryTime.getMinute() < 10){
			throw new PizzaException("Must allow 10 minutes to cook pizza");
		}
		else if (deliveryTime.getHour() - orderTime.getHour() > 1){
			throw new PizzaException("Pizza has expired and must be thrown out");
		}
		else if (orderTime.getHour() < 19){
			throw new PizzaException("Kitchen is not yet open at this time");
		}
		else if (orderTime.getHour() >= 23){		
			throw new PizzaException("Kitchen is now closed");
		}
		
		if (pizzaCode.equals("PZM")){
			MargheritaPizza pizza = new MargheritaPizza(quantity, orderTime, deliveryTime);
			return pizza;
		}
		else if (pizzaCode.equals("PZV")){			
			VegetarianPizza pizza = new VegetarianPizza(quantity, orderTime, deliveryTime);
			return pizza;
		}
		else if (pizzaCode.equals("PZL")){			
			MeatLoversPizza pizza = new MeatLoversPizza(quantity, orderTime, deliveryTime);
			return pizza;
		}else
			throw new PizzaException("Not a valid pizza code");
		}
	}
