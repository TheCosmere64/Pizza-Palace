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
	
	@Before
	public void setup()
	{
		
	}
	
	@Test(expected = CustomerException.class)
	public void customerFactoryExceptionTest() throws CustomerException{
		
		CustomerFactory.getCustomer("DNA", "Jacob", "00000000", 2, -2);
	}
	
	@Test
	public void customerFactoryTest() throws CustomerException{
		
		CustomerFactory.getCustomer("DNC", "Jacob", "00000000", 2, -2);
	}
}
