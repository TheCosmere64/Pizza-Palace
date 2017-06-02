package asgn2Customers;

import asgn2Exceptions.CustomerException;

/** A class that represents a customer that has chosen to have their pizza delivered by a driver. 
 * This class extends the abstract Customer class and calculates the delivery distance as the Manhattan  
 * Distance between the customer and the restaurant. A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.
 * 
 * @author Person B
 *
 */
public class DriverDeliveryCustomer extends Customer {

	/**
	 *  This class represents a customer of the Pizza Palace restaurant that has chosen to have their pizza delivered by 
	 *  a driver.  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment
	 *  Specification are violated. 
     *
     *  <P>PRE: TRUE
 	 *  <P>POST: All field values are set
	 *  
	 * @param name - The Customer's name 
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param locationY -  The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @throws CustomerException if supplied parameters are invalid
	 * 
	 */
	
	private int customerLocationX;
	private int customerLocationY;
	
	public DriverDeliveryCustomer(String name, String mobileNumber, int locationX, int locationY) throws CustomerException {
		
		super(name, mobileNumber, locationX, locationY, "Driver Delivery");
		
		int firstDigit = Integer.parseInt(mobileNumber.substring(0, 1));
		int lengthNumber = Integer.valueOf(mobileNumber).toString().length();
		
		if (name.length() < 1 || name.length() > 20) {
			throw new CustomerException("The name must be between 1 and 20 characters long");
		} else if (name.trim().length() < 1) {
			throw new CustomerException("The name cannot be all whitespace");			
		} else if (locationX > 10 || locationX < -10 || locationY > 10 || locationY < -10) {
			throw new CustomerException("The location can't be greater than 10 blocks north, south, east or west from the restuarant");
		} else if (firstDigit != 0) {
			throw new CustomerException("The first digit of the phone number needs to be a 0");
		} else if (lengthNumber != 9) {
			throw new CustomerException("The length of the phoneNumber needs to be equal to 10");
		} 
		
		customerLocationX = locationX;
		customerLocationY = locationY;
	}
	
	/**
	 * Returns the Manhattan Distance between the instance of DriverDeliveryCustomer and the restaurant. Overrides  
	 * getDeliveryDistance() in Customer.
	 * 
	 * @return The distance between the restaurant and the customer in Manhattan distance.
	 */
	@Override
	public double getDeliveryDistance() {	
		return Math.abs(customerLocationX) + Math.abs(customerLocationY);
	}

}