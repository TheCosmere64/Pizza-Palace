package asgn2Wizards;


import javax.swing.SwingUtilities;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2GUIs.PizzaGUI;

/**
 * This class is the ‘entry point’ to the rest of the system and provides a public static void main method. 
 * At the moment, this just calls the asgn2GUIs.PizzaGUI class. You can probably leave the class as it is,
 *  however, you must make sure that it is the one and only entry point to the rest of the system. 
 *  
 *  @author Person A and Person B
 */

public class PizzaWizard {

    /**
     * Creates an instance of PizzaWizard
     */
	public PizzaWizard() {
	}

	/**
	 * The entry point to the rest of the system. 
	 * @param args Command Line Arguments
	 * @throws CustomerException 
	 * @throws PizzaException 
	 * @throws LogHandlerException 
	 */
	public static void main(String[] args) throws LogHandlerException, PizzaException, CustomerException {
		SwingUtilities.invokeLater(new PizzaGUI("Pizza Place"));		
		
	}

}
