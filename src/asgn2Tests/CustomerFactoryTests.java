package asgn2Tests;

import org.junit.*;
import asgn2Exceptions.CustomerException;
import asgn2Customers.CustomerFactory;
/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
	
	//Testing a normal case with DNC as the customerCode
	@Test
	public void DroneDeliveryTest() throws CustomerException{
		CustomerFactory.getCustomer("DNC", "Jacob", "0123456789", 2, -2);
	}
	
	//Testing another normal case with PUC as the customerCode
	@Test
	public void PickUpOrderTest() throws CustomerException{
		CustomerFactory.getCustomer("PUC", "Steve", "0123456789", 2, -2);
	}
	
	//Testing another normal case with DVC as the customerCode
	@Test
	public void DriverDeliveryTest() throws CustomerException{
		CustomerFactory.getCustomer("DVC", "Albert John", "0123456789", 2, -2);
	}

	//Testing an incorrect customerCode
	@Test(expected = CustomerException.class)
	public void CustomerFactoryWrongCode() throws CustomerException{
		CustomerFactory.getCustomer("DNA", "Yousef", "0123456789", 2, -2);
	}
	
	//Testing another incorrect customerCode
	@Test(expected = CustomerException.class)
	public void CustomerFactoryWrongCode2() throws CustomerException{
		CustomerFactory.getCustomer("DED", "Jacob", "0123456789", 2, -2);
	}
		
	
	
}
