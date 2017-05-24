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
	private JPanel operationPanel = new JPanel();
	private JFileChooser logFile = new JFileChooser(new File("logs"));
	private ArrayList<LocalTime> orderTime = new ArrayList<LocalTime>();
	private ArrayList<LocalTime> deliveryTime = new ArrayList<LocalTime>();
	private ArrayList<String> custName = new ArrayList<String>();
	private ArrayList<Integer> mobile = new ArrayList<Integer>();
	private ArrayList<String> acquireCode = new ArrayList<String>();
	private ArrayList<Integer> x_pos = new ArrayList<Integer>();
	private ArrayList<Integer> y_pos = new ArrayList<Integer>();
	private ArrayList<String> pizzaCode = new ArrayList<String>();
	private ArrayList<Integer> quantity = new ArrayList<Integer>();
	
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
	
	private void secondPage(){
		operationPanel.removeAll();
		Object[][] data = new Object[custName.size() + 1][9];
		Object[] column = new Object[]{"","","","","","","","",""};
		for (int i = 1; i <= custName.size(); i++){
			
			data[i][0] = orderTime.get(i - 1);
			data[i][1] = deliveryTime.get(i - 1);
			data[i][2] = custName.get(i - 1);
			data[i][3] = mobile.get(i - 1);
			data[i][4] = acquireCode.get(i - 1);
			data[i][5] = x_pos.get(i - 1);
			data[i][6] = y_pos.get(i - 1);
			data[i][7] = pizzaCode.get(i - 1);
			data[i][8] = quantity.get(i - 1);
			
		}	
		data[0][0] = "Order Time";
		data[0][1] = "Delivery Time";
		data[0][2] = "Customer Name";
		data[0][3] = "Mobile Number";
		data[0][4] = "Method Code";
		data[0][5] = "X Position";
		data[0][6] = "Y Position";
		data[0][7] = "Pizza Code";
		data[0][8] = "Quantity";
		JTable nameTable = new JTable(data, column);
		for (int i = 0; i < mobile.size(); i++){
			
			
		}
		lblTitle.setText("Pizza Palace Order(s) Details");
		operationPanel.add(nameTable);
		this.add(operationPanel);

	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		return button;	
	} 
	
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		return label;	
	} 

	private void storeLogs(JButton button){
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        		 
	        	logFile.showOpenDialog(null);
	        	try {
					restaurant.processLog(String.valueOf(logFile.getSelectedFile().getAbsolutePath()));
				} catch (CustomerException | PizzaException | LogHandlerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	secondPage();	
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
