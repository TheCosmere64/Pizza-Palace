package asgn2Pizzas;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import asgn2Exceptions.PizzaException;

/**
 * 
 *  A class that represents a meat lovers pizza made at the Pizza Palace restaurant. 
 *  The meat lovers pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author PersonA
 *
 */
public class MeatLoversPizza extends Pizza {

	private List<PizzaTopping> toppings = new ArrayList<PizzaTopping>();
	
	/**
	 * 
	 *  This class represents a meat lovers pizza made at the  Pizza Palace restaurant. The meat lovers pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
 	 * <P> PRE: TRUE
 	 * <P> POST: All field values including the cost per pizza are set
     *
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 *
	 */
	public MeatLoversPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		
		super(quantity, orderTime, deliveryTime, "Meatlovers", 12);
		
		if (quantity > 10) {			
			throw new PizzaException("Too many pizzas ordered");
		}
		else if (quantity < 1){			
			throw new PizzaException("No pizzas ordered");
		}
		else if (orderTime.equals(deliveryTime)){			
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

		pizzaToppings.add(PizzaTopping.CHEESE);
		pizzaToppings.add(PizzaTopping.TOMATO);
		pizzaToppings.add(PizzaTopping.BACON);
		pizzaToppings.add(PizzaTopping.PEPPERONI);
		pizzaToppings.add(PizzaTopping.SALAMI);
		
		pizzaPrice = 12;
	}
}
