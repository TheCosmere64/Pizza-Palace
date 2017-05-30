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
	private JButton displayDistanceButton;
	private JButton displayProfitButton;
	private JButton resetButton;
	private JLabel lblTitle;
	private JLabel custDescLabel;
	private JLabel pizzaDescLabel;
	private JButton custTableButton;
	private JButton pizzaTableButton;
	private JLabel errorDesc;
	private JPanel titlePanel = new JPanel();
	private JPanel operationPanel = new JPanel();
	private JPanel calculationPanel = new JPanel();
	private JPanel resetPanel = new JPanel();
	private JPanel errorPanel = new JPanel();
	private JTextField totalProfit;
	private JTextField totalDistance;
	JScrollPane pizzaScroll = new JScrollPane();
	JScrollPane custScroll = new JScrollPane();
	JTable custTable;
	JTable pizzaTable;
	DefaultTableModel custModel;
	DefaultTableModel pizzaModel;
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
		lblTitle = createLabel("Pizza Palace Order Processor");
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.RED);
		logFile.setDialogTitle("Select log file to read");
		logFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		this.setSize(800, 700);
		titlePanel.add(lblTitle);
		logButton = createButton("Select Log File");
		logReadActivity(logButton);
		operationPanel.add(logButton);
		this.add(titlePanel);
		this.add(operationPanel);
	}
	
	private void customerTableDisplay(JButton button) throws PizzaException, CustomerException, LogHandlerException{
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e){	        		 
					remove(operationPanel);
					remove(calculationPanel);
					remove(resetPanel);
					add(custScroll);
					add(operationPanel);
					add(calculationPanel);
					add(resetPanel);
					operationPanel.remove(custTableButton);
					operationPanel.remove(custDescLabel);
					revalidate();	
	         }         
	     });
	}
	
	private void pizzaTableDisplay(JButton button) throws PizzaException, CustomerException, LogHandlerException{
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e){	        		 
					remove(operationPanel);
					remove(calculationPanel);
					remove(resetPanel);
					add(pizzaScroll);
					add(operationPanel);
					add(calculationPanel);
					add(resetPanel);
					operationPanel.remove(pizzaTableButton);
					operationPanel.remove(pizzaDescLabel);
					revalidate();	
	         }         
	     });
	}
	
	private void logReadActivity(JButton button) throws PizzaException, CustomerException, LogHandlerException{
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e){	        		 
				try {
					loadLog();
				} catch (PizzaException | CustomerException | LogHandlerException e1) {
					
				}	
	         }         
	     });
	}
	
	private void resetPage(JButton button){
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
				restaurant.resetDetails();
				operationPanel.removeAll();
				calculationPanel.removeAll();
				errorPanel.removeAll();
				if(pizzaScroll.isDisplayable()){
					remove(pizzaScroll);
				}
				if(custScroll.isDisplayable()){
					remove(custScroll);
				}
				resetPanel.removeAll();
				remove(calculationPanel);
				remove(resetPanel);
				operationPanel.add(logButton);
				repaint();
				revalidate();     		
	         }         
	     });
	}
	
	private void totalDistanceButton(JButton button){		
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {        	 
	        	calculationPanel.remove(displayDistanceButton);
	     		totalDistance = new JTextField();
	     		totalDistance.setText(String.valueOf(restaurant.getTotalDeliveryDistance()));
	     		JLabel distanceDesc = createLabel("Total Distance Travelled");
	     		calculationPanel.add(distanceDesc);
	     		calculationPanel.add(totalDistance);
	     		add(calculationPanel);
	     		add(resetPanel);
	     		repaint();
	     		revalidate();
	         }
		});
	}
	
	private void totalProfitButton(JButton button){		
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {        	 
	        	calculationPanel.remove(displayProfitButton);
	     		totalProfit = new JTextField();
	     		totalProfit.setText(String.valueOf(restaurant.getTotalProfit()));
	     		JLabel profitDesc = createLabel("Total Profit Made");
	     		calculationPanel.add(profitDesc);
	     		calculationPanel.add(totalProfit);
	     		add(calculationPanel);
	     		add(resetPanel);
	     		repaint();
	     		revalidate();
	         }
		});
	}
	
	private void loadLog() throws PizzaException, CustomerException, LogHandlerException{
		logFile.showOpenDialog(null);
		repaint();
		try{
			restaurant.processLog(String.valueOf(logFile.getSelectedFile().getAbsolutePath()));
			operationPanel.removeAll();	
			lblTitle.setText("Pizza Palace Order(s) Details");
			custDescLabel = createLabel("Display Customer Table");
			custTableButton = createButton("Get Customer Info");
			pizzaDescLabel = createLabel("Display Pizza Table");
			pizzaTableButton = createButton("Get Pizza Info");
			customerTableDisplay(custTableButton);
			pizzaTableDisplay(pizzaTableButton);
			operationPanel.add(custDescLabel);
			operationPanel.add(custTableButton);
			operationPanel.add(pizzaDescLabel);
			operationPanel.add(pizzaTableButton);
			displayDistanceButton = createButton("Total Distance Travelled");
			displayProfitButton = createButton("Total Profit Made");
			totalDistanceButton(displayDistanceButton);
			totalProfitButton(displayProfitButton);
			calculationPanel.add(displayDistanceButton);
			calculationPanel.add(displayProfitButton);
			errorDesc = createLabel("All operations have been completed successfully");
			add(calculationPanel);	
			pizzaTableSetup();
			customerTableSetup();
		}catch(Exception e){
			operationPanel.removeAll();
			if(e.getClass().getSimpleName().equals("PizzaException") || e.getClass().getSimpleName().equals("CustomerException")){				
				errorDesc = createLabel(e.getMessage());
			}
			else if(e.getClass().getSimpleName().equals("LogHandlerException")){				
				errorDesc = createLabel("There was an error reading the log file");
			}
			else{				
				errorDesc = createLabel(e.getClass().getSimpleName());
			}
		}
		errorPanel.add(errorDesc);
 		resetButton = createButton("Reset all values");
 		resetPanel.add(resetButton);
 		resetPage(resetButton);
 		add(resetPanel);
 		add(errorPanel);
		revalidate();
	}
	
	private void customerTableSetup() throws PizzaException, CustomerException, LogHandlerException{
		repaint();
		String [] custHeader = {"Customer Name","Mobile Number","Customer Type","X,Y position","Delivery Distance"};
		Object[][] custData = new Object[restaurant.getNumCustomerOrders()][5];
			
			for (int index = 0; index < restaurant.getNumCustomerOrders(); index++){
				custData[index][0] = restaurant.getCustomerByIndex(index).getName()	;
				custData[index][1] = restaurant.getCustomerByIndex(index).getMobileNumber();
				custData[index][2] = restaurant.getCustomerByIndex(index).getCustomerType();
				custData[index][3] = restaurant.getCustomerByIndex(index).getLocationX() + " , " + restaurant.getCustomerByIndex(index).getLocationY();
				custData[index][4] = restaurant.getCustomerByIndex(index).getDeliveryDistance();
			}
			custModel = new DefaultTableModel(custData,custHeader);
			custTable = new JTable(custModel);
			custData[0][0] = "Customer Name";
			custData[0][1] = "Mobile Number";
			custData[0][2] = "Customer Type";
			custData[0][3] = "X,Y position";
			custData[0][4] = "Delivery Distance";
			custScroll = new JScrollPane(custTable);
	}
	
	private void pizzaTableSetup() throws PizzaException, CustomerException, LogHandlerException{	
		repaint();
		String [] pizzaHeader = {"Pizza Type","Quantity","Order Price","Order Cost","Order Profit"};	
		Object[][] pizzaData = new Object[restaurant.getNumPizzaOrders()][5];
		
			for (int index = 0; index < restaurant.getNumPizzaOrders(); index++){
				restaurant.getPizzaByIndex(index).calculateCostPerPizza();
				pizzaData[index][0] = restaurant.getPizzaByIndex(index).getPizzaType();
				pizzaData[index][1] = restaurant.getPizzaByIndex(index).getQuantity();
				pizzaData[index][2] = restaurant.getPizzaByIndex(index).getOrderPrice();
				pizzaData[index][3] = restaurant.getPizzaByIndex(index).getOrderCost();
				pizzaData[index][4] = restaurant.getPizzaByIndex(index).getOrderProfit();
			}
			pizzaModel = new DefaultTableModel(pizzaData,pizzaHeader);
			pizzaTable = new JTable(pizzaModel);
			pizzaData[0][0] = "Pizza Type";
			pizzaData[0][1] = "Quantity";
			pizzaData[0][2] = "Order Price";
			pizzaData[0][3] = "Order Cost";
			pizzaData[0][4] = "Order Profit";
			pizzaScroll = new JScrollPane(pizzaTable);
			
	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		return button;	
	} 
	
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		return label;	
	}
	
	@Override
	public void run() {
		// TO DO
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	

}
