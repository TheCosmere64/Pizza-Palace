package asgn2Tests;

import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Person A
 */

public class LogHandlerCustomerTests {
	
	
	//POPULATECUSTOMERDATASET TESTS
	//Testing a normal case with three different order codes and different order times and delivery times
	@Test
	public void normalTest() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\20170101.txt");
	}
	
	//Testing a wrong format for the delivery time. 19::00 was used as opposed to 19:00:00
	@Test(expected = CustomerException.class)
	public void wrongOrderTime() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongOrderTimeTest.txt");
	}
	
	//Testing a wrong format for the delivery time. 19:10 was used as opposed to 19:10:00
	@Test(expected = CustomerException.class)
	public void wrongDeliveryTime() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongDeliveryTimeTest.txt");
	}
	
	//Testing a phone number that contains letters
	@Test(expected = CustomerException.class)
	public void wrongNumber() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongPhoneNumber.txt");
	}
	
	//Testing a phone number that contains letters and numbers
	@Test(expected = CustomerException.class)
	public void wrongNumber2() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongPhoneNumber2.txt");
	}
	
	//Testing a customer code that isn't appliable
	@Test(expected = CustomerException.class)
	public void wrongCode() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongCustomerCode.txt");
	}
	
	//Testing an X location that isn't an int, put 'x' as the x location
	@Test(expected = CustomerException.class)
	public void wrongX() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongX.txt");
	}
	
	//Testing a Y location that isn't an int, put 'y' as the y location
	@Test(expected = CustomerException.class)
	public void wrongY() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("logs\\wrongY.txt");
	}
	
	
	
	//CREATECUSTOMER TESTS
	//Normal test
	@Test
	public void createCustomerTest() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:10:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Normal test with a different name, mobile number, order time, delviery code
	@Test
	public void createCustomerTest2() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10:00,John Stamos,0386501483,PUC,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Test with an order time in the wrong format 20:00 instead of 20:00:00
	@Test(expected = CustomerException.class)
	public void createCustomerWrongOrderTime() throws LogHandlerException, CustomerException {
		String line = "20:00:0,20:10:00,John Stamos,0386501483,PUC,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Test with an delivery time in the wrong format 20:20:: instead of 20:00:00
	@Test(expected = CustomerException.class)
	public void createCustomerWrongDeliveryTime() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10::,John Stamos,0386501483,PUC,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Testing a mobile number with letters in it
	@Test(expected = CustomerException.class)
	public void createCustomerWrongMobileNumber() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10::,John Stamos,038650148ete3,PUC,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Testing an incorrect order code
	@Test(expected = CustomerException.class)
	public void createCustomerWrongOrderCode() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10:00,John Stamos,0386501483,DED,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Testing an incorrect order code
	@Test(expected = CustomerException.class)
	public void createCustomerWrongOrderCode2() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10:00,John Stamos,0386501483,YOU,5,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Testing an incorrect X coordinate a value of 't' instead of an int
	@Test(expected = CustomerException.class)
	public void createCustomerWrongX() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10:00,John Stamos,0386501483,PUC,t,5,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	//Testing an incorrect Y coordinate a value of 'z' instead of an int
	@Test(expected = CustomerException.class)
	public void createCustomerWrongY() throws LogHandlerException, CustomerException {
		String line = "20:00:00,20:10:00,John Stamos,0386501483,PUC,5,z,PZV,2";
		LogHandler.createCustomer(line);
	}
	
	
	
}
