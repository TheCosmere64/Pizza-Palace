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
import javax.swing.table.DefaultTableModel;


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
	private JButton logButton;
	private JButton calcDispButton;
	private JButton resetButton;
	private JLabel lblTitle;
	private JLabel descLabel;
	private JLabel errorDesc;
	private JPanel titlePanel = new JPanel();
	private JPanel operationPanel = new JPanel();
	private JPanel calculationPanel = new JPanel();
	private JPanel resetPanel = new JPanel();
	private JTextField totalProfit;
	private JTextField totalDistance;
	private JFileChooser logFile = new JFileChooser(new File("logs"));
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) throws LogHandlerException, PizzaException, CustomerException{
		super(title);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(0,1));
		Font titleFont = new Font("Serif", Font.BOLD, 25);
		lblTitle = createLabel("Pizza Palace Order Reciever");
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.RED);
		descLabel = createLabel("Import Order Log File");
		logButton = createButton("Import Log File");
		logFile.setDialogTitle("Select log file to read");
		logFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		storeLogs(logButton);
		this.setSize(800, 700);
		titlePanel.add(lblTitle);
		operationPanel.add(descLabel);
		operationPanel.add(logButton);
		this.add(titlePanel);
		this.add(operationPanel);
	}
	
	private void secondPage() throws PizzaException, CustomerException, LogHandlerException{
		repaint();
		operationPanel.removeAll();
		this.remove(operationPanel);
		logFile.showOpenDialog(null);
		String [] custHeader = {"Customer Name","Mobile Number","Customer Type","X,Y position","Delivery Distance"};
		String [] pizzaHeader = {"Pizza Type","Quantity","Order Price","Order Cost","Order Profit"};
		if(!restaurant.processLog(String.valueOf(logFile.getSelectedFile().getAbsolutePath()))){
			
			Object[][] custData = new Object[1][5];
			Object[][] pizzaData = new Object[1][5];
			DefaultTableModel custModel = new DefaultTableModel(custData,custHeader);
			DefaultTableModel pizzaModel = new DefaultTableModel(pizzaData,pizzaHeader);
			JTable custTable = new JTable(custModel);
			JTable pizzaTable = new JTable(pizzaModel);
			JScrollPane custScroll = new JScrollPane(custTable);
			JScrollPane pizzaScroll = new JScrollPane(pizzaTable);
			lblTitle.setText("Pizza Palace Order(s) Details");
			this.add(custScroll);
			this.add(pizzaScroll);		
		}
		else{
			
			Object[][] custData = new Object[restaurant.getNumCustomerOrders()][5];
			Object[][] pizzaData = new Object[restaurant.getNumPizzaOrders()][5];
		
			for (int index = 0; index < restaurant.getNumCustomerOrders(); index++){
				custData[index][0] = restaurant.getCustomerByIndex(index).getName()	;
				custData[index][1] = restaurant.getCustomerByIndex(index).getMobileNumber();
				custData[index][2] = restaurant.getCustomerByIndex(index).getCustomerType();
				custData[index][3] = restaurant.getCustomerByIndex(index).getLocationX() + " , " + restaurant.getCustomerByIndex(index).getLocationY();
				custData[index][4] = restaurant.getCustomerByIndex(index).getDeliveryDistance();
			}
			for (int index = 0; index < restaurant.getNumPizzaOrders(); index++){
				restaurant.getPizzaByIndex(index).calculateCostPerPizza();
				pizzaData[index][0] = restaurant.getPizzaByIndex(index).getPizzaType();
				pizzaData[index][1] = restaurant.getPizzaByIndex(index).getQuantity();
				pizzaData[index][2] = restaurant.getPizzaByIndex(index).getOrderPrice();
				pizzaData[index][3] = restaurant.getPizzaByIndex(index).getOrderCost();
				pizzaData[index][4] = restaurant.getPizzaByIndex(index).getOrderProfit();
			}
			DefaultTableModel custModel = new DefaultTableModel(custData,custHeader);
			DefaultTableModel pizzaModel = new DefaultTableModel(pizzaData,pizzaHeader);
			JTable custTable = new JTable(custModel);
			JTable pizzaTable = new JTable(pizzaModel);
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
			JScrollPane custScroll = new JScrollPane(custTable);
			JScrollPane pizzaScroll = new JScrollPane(pizzaTable);
			lblTitle.setText("Pizza Palace Order(s) Details");
			this.add(custScroll);
			this.add(pizzaScroll);
		}
		
		this.add(operationPanel);
		calcDispButton = createButton("Show Calculations");
		dispCalcButton(calcDispButton);
		calculationPanel.add(calcDispButton);
		this.add(calculationPanel);
	}
	
	private void thirdScreen(){	
		repaint();
		calculationPanel.removeAll();
		this.remove(calculationPanel);
		lblTitle.setText("Pizza Palace Order(s) Details ");
		totalProfit = new JTextField();
		totalDistance = new JTextField();
		totalProfit.setText(String.valueOf(restaurant.getTotalProfit()));
		totalDistance.setText(String.valueOf(restaurant.getTotalDeliveryDistance()));
		JLabel profitDesc = createLabel("Total Profit Made");
		JLabel distanceDesc = createLabel("Total Distance Travelled");
		resetButton = createButton("Reset all values");
		calculationPanel.add(profitDesc);
		calculationPanel.add(totalProfit);
		calculationPanel.add(distanceDesc);
		calculationPanel.add(totalDistance);
		resetPanel.add(resetButton);
		resetActivity(resetButton);
		this.add(calculationPanel);
		this.add(resetPanel);
	}
	
	private void resetPage() throws PizzaException, CustomerException{
		restaurant.resetDetails();
		operationPanel.removeAll();
		calculationPanel.removeAll();
		resetPanel.removeAll();
		this.remove(calculationPanel);
		this.remove(resetPanel);
		lblTitle.setText("Pizza Palace Order Reciever");
		operationPanel.add(logButton);		
		repaint();
	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		return button;	
	} 
	
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		return label;	
	} 

	private void storeLogs(JButton button) throws PizzaException, CustomerException, LogHandlerException{
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e){	        		 
				try {
					secondPage();
				} catch (PizzaException | CustomerException | LogHandlerException e1) {
					
				}	
	         }         
	     });
	}
	
	private void resetActivity(JButton button){
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        		 
					try {
						resetPage();
					} catch (PizzaException | CustomerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	     		
	         }         
	     });
	}
	
	private void dispCalcButton(JButton button){		
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {        	 
	        	 thirdScreen();
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
