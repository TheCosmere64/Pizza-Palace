package asgn2Tests;

import org.junit.*;
import asgn2Exceptions.CustomerException;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *fuk u
 */
public class CustomerTests {
	
	DriverDeliveryCustomer driverCustomer;
	DroneDeliveryCustomer droneCustomer;
	PickUpCustomer pickupCustomer;
	
	@Before
	public void setup()throws CustomerException{
		
		driverCustomer = new DriverDeliveryCustomer(null, null, 0, 0);
		droneCustomer = new DroneDeliveryCustomer(null, null, 0, 0);
		pickupCustomer = new PickUpCustomer(null, null, 0, 0);
	}
	
	@Test
	public void droneCustomerTest() throws CustomerException{
		
		driverCustomer = new DriverDeliveryCustomer("Jacob","00000000", 2, -2);
	}
	
	@Test(expected = CustomerException.class)
	public void droneCustomerExceptionTest() throws CustomerException{
		
		driverCustomer = new DriverDeliveryCustomer("Jacob","00000000", 25, 50);
	}
	
	@Test
	public void driverCustomerTest() throws CustomerException{
		
		driverCustomer = new DriverDeliveryCustomer("Jacob","00000000", 2, -2);
	}
	
	@Test(expected = CustomerException.class)
	public void driverCustomerExceptionTest()throws CustomerException{
		
		driverCustomer = new DriverDeliveryCustomer("Jacob", "00000000", 30, 4);
	}
	
	@Test
	public void pickupCustomerTest() throws CustomerException{
		
		pickupCustomer = new PickUpCustomer("Jacob","00000000", 2, -2);
	}
	
	@Test(expected = CustomerException.class)
	public void pickupExceptionTest()throws CustomerException{
		
		pickupCustomer = new PickUpCustomer("Jacob", "00000000", 17, 32);
	}
}
