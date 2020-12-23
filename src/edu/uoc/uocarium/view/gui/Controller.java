package edu.uoc.uocarium.view.gui;

import edu.uoc.uocarium.controller.UOCariumController;
import javafx.animation.AnimationTimer;

/**
 * This class is the parent of all the controllers.
 * 
 * @author David García Solórzano
 * @version 1.0
 */
public class Controller {

	// Reference to the main application.
	public static UOCariumController controller;
	protected static AnimationTimer timer = null;
	
	/**
	 * Default constructor.
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param uocariumApp Object related to the main app.
     */
    public static void setController(UOCariumController c) {
        controller = c;     
    }

}
