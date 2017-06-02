package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.*;
import asgn2Exceptions.CustomerException;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	DriverDeliveryCustomer driverCustomer;
	DroneDeliveryCustomer droneCustomer;
	PickUpCustomer pickupCustomer;
	
	DriverDeliveryCustomer testingDriver;
	DriverDeliveryCustomer testingDriver2;
	DroneDeliveryCustomer testingDrone;	
	
	@Before
	public void before() throws CustomerException {
		testingDriver = new DriverDeliveryCustomer("Steve", "0123456789", 6, 7);
		testingDriver2 = new DriverDeliveryCustomer("Albert", "0783129483", 3, 9);
		testingDrone = new DroneDeliveryCustomer("Steve", "0123456789", 6, 7);
	}
	
	//Testing a normal drone customer test
	@Test
	public void droneCustomerTest() throws CustomerException {
		droneCustomer = new DroneDeliveryCustomer("Steve", "0123456789", 0, 0);
	}
	
	//Testing a pick-up customer test
	@Test
	public void pickUpCustomerTest() throws CustomerException {
		pickupCustomer = new PickUpCustomer("Steve", "0123456789", 0, 0);
	}
	
	//Testing a normal driver customer test
	@Test
	public void driverCustomerTest() throws CustomerException {
		driverCustomer = new DriverDeliveryCustomer("Steve", "0123456789", 0, 0);
	}
	
	//Testing a name of ""
	@Test(expected = CustomerException.class)
	public void badNameTest() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("","0123456789", 0, 0);
	}
	
	//Testing a name that is over 20 characters
	@Test(expected = CustomerException.class)
	public void longNameTest() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("111111111111111111111","0123456789", 0, 0);
	}
	
	//Testing a name that is all whitespace
	@Test(expected = CustomerException.class)
	public void whiteSpaceNameTest() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("            ","0123456789", 0, 0);
	}
	
	//DRIVER DELIVERY DISTANCE TESTING
	//Testing an X location that is over 10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badX() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", 11, 0);
	}
	
	//Testing a Y location that is over 10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badY() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", 0, 11);
	}
	
	//Testing a X location that is over -10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badXNegative() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", -11, 1);
	}
	
	//Testing a Y location that is over -10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badYNegative() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", -1, -11);
	}
	
	//DRONE DELIVERY DISTANCE TESTING
	//Testing a X location that is over 10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badXDrone() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", 11, 0);
	}
	
	//Testing a Y location that is over 10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badYDrone() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", 1, 11);
	}
	
	//Testing a X location that is over -10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badXNegativeDrone() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", -11, 0);
	}
	
	//Testing a Y location that is over -10 in DriverDelivery
	@Test(expected = CustomerException.class)
	public void badYNegativeDrone() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", -1, -11);
	}
	
	//Testing a delivery distance that is too large
	@Test(expected = CustomerException.class)
	public void droneCustomerExceptionTest() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Jacob","0123456789", 20, 0);
	}
	
	//DRIVER DELIVERY TESTING BOUNDARY TESTING
	//Testing an X location that is over 10 in DriverDelivery
	@Test
	public void boundaryX() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", 10, 0);
	}
		
	//Testing a Y location that is over 10 in DriverDelivery
	@Test
	public void boundaryY() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", 0, 10);
	}
		
	//Testing a X location that is over -10 in DriverDelivery
	@Test
	public void boundaryNegativeX() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", -10, 1);
	}
		
	//Testing a Y location that is over -10 in DriverDelivery
	@Test
	public void boundaryNegativeY() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Max","0123456789", 0, -10);
	}
	
	
	//DRONE DELIVERY TESTING BOUNDARY TESTING
	//Testing a drone Customer with an X distance of 10
	@Test
	public void droneCustomerBoundaryX() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", 10, 0);
	}
	
	@Test
	public void droneCustomerBoundaryY() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", 0, 10);
	}
	
	@Test
	public void droneCustomerBoundaryNegativeX() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", -10, 0);
	}
	
	@Test
	public void droneCustomerBoundaryNegativeY() throws CustomerException{
		droneCustomer = new DroneDeliveryCustomer("Max","0123456789", 0, -10);
	}
	
	//Testing a phone number that doesnt start with 0
	@Test(expected = CustomerException.class)
	public void wrongNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Jacob","2123456789", 0, 0);
	}
	
	//Testing a phone number that is too short
	@Test(expected = CustomerException.class)
	public void tooShortNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Jacob","21234567", 0, 0);
	}
	
	//Testing another phone number that is too short
	@Test(expected = CustomerException.class)
	public void tooLongNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Jacob","034567891", 0, 0);
	}
	
	//TESTING CUSTOMER ABSTRACT METHODS
	//Testing getName
	@Test
	public void getNameTest() throws CustomerException {
		assertEquals("Steve", testingDriver.getName());
	}
	
	//Testing getMobileNumber
	@Test
	public void getMobileNumber() throws CustomerException {
		assertEquals("0123456789", testingDriver.getMobileNumber());
	}
	
	//Testing getX
	@Test
	public void getLocationXTest() throws CustomerException {
		assertEquals(6, testingDriver.getLocationX());
	}
	
	//Testing getY
	@Test
	public void getLocationYTest() throws CustomerException {
		assertEquals(7, testingDriver.getLocationY());
	}
	
	//TESTING ANOTHER INSTANCE TO TEST CUSTOMER ABSTRACT METHODS
	//Testing getName
	@Test
	public void getNameTest2() throws CustomerException {
		assertEquals("Albert", testingDriver2.getName());
	}
		
	//Testing getMobileNumber
	@Test
	public void getMobileNumber2() throws CustomerException {
		assertEquals("0783129483", testingDriver2.getMobileNumber());
	}
	
	//Testing getX
	@Test
	public void getLocationXTest2() throws CustomerException {
		assertEquals(3, testingDriver2.getLocationX());
	}
		
	//Testing getY
	@Test
	public void getLocationYTest2() throws CustomerException {
		assertEquals(9, testingDriver2.getLocationY());
	}
	
	
	//Testing getDeliveryDistance in driverDelivery
	//6 was the X value and 7 was the Y value, so the manhatten distance for that would be the absolute value of both added to each other, which results in 13
	@Test
	public void getDeliveryDistanceTest() throws CustomerException {
		assertEquals(13.0, testingDriver.getDeliveryDistance(), 0);
	}
	
	//Testing getDeliveryDistance with negative numbers to test if they are absolute, -5 plus 3 using absolute values is 5 + 3 which is 8
	@Test
	public void getDeliveryDistanceTest2() throws CustomerException {
		testingDriver = new DriverDeliveryCustomer("Jacob","0123456789", -5, 3);
		assertEquals(8.0, testingDriver.getDeliveryDistance(), 0);
	}
	
	//Testing getDeliveryDistance with two negative numbers
	@Test
	public void getDeliveryDistanceTest3() throws CustomerException {
		testingDriver = new DriverDeliveryCustomer("Jacob","0123456789", -5, -6);
		assertEquals(11.0, testingDriver.getDeliveryDistance(), 0);
	}
	
	//		testingDrone = new DroneDeliveryCustomer("Steve", "0123456789", 6, 7);

	//Testing getDeliveryDistance in drone delivery
	//The X for testingDrone is 6 and the Y is 7.
	//The Euclidean distance for the two is the square root of ((x1 - x2) + (y1 - y2))^2 with the absolute values for both
	//6^2 + 7^2 = 85
	// square root of 85 is 9.219544 but will round to 9.2 and set the buffer distance to 0.1
	@Test
	public void getDroneDeliveryDistanceTest() throws CustomerException {
		assertEquals(9.2, testingDrone.getDeliveryDistance(), 0.1);
	}
	
	//Testing another instance
	@Test
	public void getDroneDeliveryDistanceTest2() throws CustomerException {
		testingDrone = new DroneDeliveryCustomer("Steve", "0123456789", -4, -9);
		assertEquals(9.8, testingDrone.getDeliveryDistance(), 0.1);
	}
	
	//Testing pickup customer distance if it is returning 0 properly
	@Test
	public void getPickUpDistanceTest() throws CustomerException {
		pickupCustomer = new PickUpCustomer("Steve", "0123456789", -4, -9);
		assertEquals(0, pickupCustomer.getDeliveryDistance(), 0);
	}
}
