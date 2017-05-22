package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;
import java.time.LocalTime;

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
	
	
	private PizzaRestaurant restaurant;
	private Pizza pizzaMethod;
	private JFrame GUIframe;
	private JButton btnOne;
	private JLabel lblOne;
	private JLabel lblTwo;
	private JPanel pnlOne;
	private JPanel pnlTwo;
	private JFileChooser logFile;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	private String custName;
	private int mobile;
	private String getCode;
	private int x_pos;
	private int y_pos;
	private String pizzaCode;
	private int quantity;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		restaurant = new PizzaRestaurant();
		pnlOne = new JPanel();
		pnlTwo = new JPanel();
		logFile = new JFileChooser();
		logFile.setCurrentDirectory(new java.io.File("C:/Users/narut/Desktop/Log Files"));
		logFile.setDialogTitle("do it");
		logFile.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		this.setLayout(new GridLayout(0,1));
		lblOne = createLabel("Pizza Palace Order Form");
		lblTwo = createLabel("Import Order Log File");
		btnOne = createButton("Import Log File");
		setActivity(btnOne);
		this.setSize(500, 400);
		pnlOne.add(lblOne);
		pnlTwo.add(lblTwo);
		pnlTwo.add(btnOne);
		this.add(pnlOne);
		this.add(pnlTwo);
	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		return button;	
	} 
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		return label;	
	} 

	private void setActivity(JButton button){
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if (e.getSource() == btnOne) { 	        		 
	        		 Charset charset = Charset.forName("US-ASCII");
	        		 logFile.showOpenDialog(null);
	        		 try (BufferedReader reader = Files.newBufferedReader(logFile.getSelectedFile().toPath(), charset)) {
	        			 		StringBuilder lineMo = new StringBuilder();
	        			 		int line = 0;
	        			 		int ref = 0;
	        			 		while ((line = reader.read()) != -1){
	        			 			if (line == '\n'){
	        			 				lineMo.append(',');
	        			 			}
	        			 			lineMo.append((char)line);
	        			 			ref++;
	        			 		}
	        			 		for (int i = 0; i < lineMo.length() / 9; i++){
	        			 			
	        			 				int change = 9 * i;
	        			 				StringBuilder time1 = new StringBuilder();
	        			 				time1.append(lineMo.toString().split(",")[change]);
	        			 				orderTime.plusHours(Integer.parseInt(time1.toString().split(":")[change]));
	        			 				orderTime.plusMinutes(Integer.parseInt(time1.toString().split(":")[1 + change]));
	        			 				orderTime.plusSeconds(Integer.parseInt(time1.toString().split(":")[2 + change]));
	        			 				StringBuilder time2 = new StringBuilder();
	        			 				time2.append(lineMo.toString().split(",")[1 + change]);
	        			 				deliveryTime.plusHours(Integer.parseInt(time1.toString().split(":")[change]));
	        			 				deliveryTime.plusMinutes(Integer.parseInt(time1.toString().split(":")[1 + change]));
	        			 				deliveryTime.plusSeconds(Integer.parseInt(time1.toString().split(":")[2 + change]));
	        			 				custName = lineMo.toString().split(",")[2 + change];
		        			 			mobile = Integer.parseInt(lineMo.toString().split(",")[3 + change]);
		        			 			getCode = lineMo.toString().split(",")[4 + change];
		        			 			x_pos = Integer.parseInt(lineMo.toString().split(",")[5 + change]);
		        			 			y_pos = Integer.parseInt(lineMo.toString().split(",")[6 + change]);
		        			 			pizzaCode = lineMo.toString().split(",")[7 + change];
		        			 			quantity = Integer.parseInt(lineMo.toString().split(",")[8 + change]);
	        			 		}
	        			 		lblOne.setText(lineMo.toString().split(",")[8]);
	        			} catch (IOException x) {
	        			    System.err.format("IOException: %s%n", x);
	        			}	
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
