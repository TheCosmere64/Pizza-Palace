package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.text.DefaultCaret;

import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;
import java.time.LocalTime;
import asgn2Exceptions.*;

import java.awt.*;
import javax.swing.*;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	
	private PizzaRestaurant restaurant = new PizzaRestaurant();
	private Pizza pizzaMethod;
	private JFrame GUIframe;
	private JButton logButton = new JButton();
	private JLabel lblTitle;
	private JLabel descLabel;
	private JPanel titlePanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JPanel operationPanel = new JPanel();
	private JFileChooser logFile = new JFileChooser(new File("logs"));
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) throws LogHandlerException, PizzaException, CustomerException{
		super(title);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logFile.setDialogTitle("do it");
		logFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		this.setLayout(new GridLayout(0,1));
		lblTitle = createLabel("Pizza Palace Order Reciever");
		descLabel = createLabel("Import Order Log File");
		logButton = createButton("Import Log File");
		storeLogs(logButton);
		this.setSize(700, 600);
		titlePanel.add(lblTitle);
		operationPanel.add(descLabel);
		operationPanel.add(logButton);
		this.add(titlePanel);
		this.add(operationPanel);		
	}
	
	private void secondPage() throws PizzaException, CustomerException{
		operationPanel.removeAll();
		this.remove(operationPanel);
		logFile.showOpenDialog(null);
    	try {
			restaurant.processLog(String.valueOf(logFile.getSelectedFile().getAbsolutePath()));		
		} catch (CustomerException | PizzaException | LogHandlerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object[][] custData = new Object[restaurant.getNumCustomerOrders() + 1][10];
		Object[][] pizzaData = new Object[restaurant.getNumPizzaOrders() + 1][10];
		Object[] column = new Object[]{"", "", "", "", ""};
		
		for (int index = 0; index < restaurant.getNumCustomerOrders(); index++){
			custData[index + 1][0] = restaurant.getCustomerByIndex(index).getName()	;
			custData[index + 1][1] = restaurant.getCustomerByIndex(index).getMobileNumber();
			custData[index + 1][2] = restaurant.getCustomerByIndex(index).getCustomerType();
			custData[index + 1][3] = restaurant.getCustomerByIndex(index).getLocationX() + " , " + restaurant.getCustomerByIndex(index).getLocationY();
			custData[index + 1][4] = restaurant.getCustomerByIndex(index).getDeliveryDistance();
		}
		for (int index = 0; index < restaurant.getNumPizzaOrders(); index++){
			restaurant.getPizzaByIndex(index).calculateCostPerPizza();
			pizzaData[index + 1][0] = restaurant.getPizzaByIndex(index).getPizzaType();
			pizzaData[index + 1][1] = restaurant.getPizzaByIndex(index).getQuantity();
			pizzaData[index + 1][2] = restaurant.getPizzaByIndex(index).getOrderPrice();
			pizzaData[index + 1][3] = restaurant.getPizzaByIndex(index).getOrderCost();
			pizzaData[index + 1][4] = restaurant.getPizzaByIndex(index).getOrderProfit();
		}
			
		custData[0][0] = "Customer Name";
		custData[0][1] = "Mobile Number";
		custData[0][2] = "Customer Type";
		custData[0][3] = "X,Y position";
		custData[0][4] = "Delivery Distance";
		pizzaData[0][0] = "Pizza Type";
		pizzaData[0][1] = "Quantity";
		pizzaData[0][2] = "Order Price";
		pizzaData[0][3] = "Order Cost";
		pizzaData[0][4] = "Order Profit";
		JTable custTable = new JTable(custData, column);
		JTable pizzaTable = new JTable(pizzaData, column);
		lblTitle.setText("Pizza Palace Order(s) Details");
		operationPanel.add(custTable);
		tablePanel.add(pizzaTable);
		this.add(operationPanel);
		this.add(tablePanel);
	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		return button;	
	} 
	
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		return label;	
	} 

	private void storeLogs(JButton button) throws PizzaException, CustomerException{
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        		 
	        		try {
						secondPage();
					} catch (PizzaException | CustomerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	         }          
	     });
	}
	
	@Override
	public void run() {
		// TO DO
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
