package edu.uoc.uocarium.view.cmd;


import javafx.application.Application;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.uoc.uocarium.controller.UOCariumController;
import edu.uoc.uocarium.model.ItemException;
import edu.uoc.uocarium.model.Keeper;
import edu.uoc.uocarium.model.Tank;

/**
* CmdApp function of the UOCarium Software JAVA Practice
* 
* @author David García Solórzano
* @version 1.0
*/
public class CmdApp extends Application{
	

	private static UOCariumController controller;
	private Scanner in;
	private final String MSG_CONTINUE = "\n\tPress any character and enter to continue...";
		
	/**
	 * Default constructor. It creates an instance of Game/Controller.
	 * @throws ItemException 
	 */
	public CmdApp() throws ItemException {
		// not necessary if extending Object.
		
		super();
		controller = new UOCariumController("./files/");
		in = new Scanner(System.in);
	}
	
	/**
	 * Manages the actions performed in the menu. 
	 */	
	public void manageMenu(){
		
			
		do{
			System.out.println("\n**********************Welcome to UOCarium*****************************");
			System.out.println("\n\t1. See Tanks.");
			System.out.println("\n\t2. See Keepers.");
			System.out.println("\n\t3. Add fish.");
			System.out.println("\n\t4. Exit.");			
			System.out.println("*********************************************************************");
			
			try{
				int choice = in.nextInt();
			
				if(choice==1){
					seeTanks();				
				}else if(choice==2){
					seeKeepers();
				}else if(choice==3) {
					addFish();
				}else if(choice==4) {
					System.out.println("Bye!!");
					System.exit(0);;
				}else{
					System.err.println("[ERROR] Your option is incorrect!! Try again!!");
				}			
				
			}catch(InputMismatchException e) {
				System.err.println("[ERROR] You must type a number!!!");
				in.next();// To avoid infinite loop
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}while(true);		
	}
	
	private void seeTanks() {
		List<Tank> list = controller.getTanks();
		int index = 0;
		
		for(Tank item : list) {			
			System.out.println("Tank number "+index+"\n"+item+"\n____________________\n");
			index++;
		}
		
		System.out.println("Total: "+list.size());
		
		System.out.println(MSG_CONTINUE);
		in.next();
	}
	
	
	private void seeKeepers() {
		List<Keeper> list = controller.getKeepers();
				
		for(Keeper keeper : list) {			
			System.out.println(keeper+"\n____________________\n");
		}
		
		System.out.println("Total: "+list.size());
		
		System.out.println(MSG_CONTINUE);
		in.next();
	}
	
	private void addFish() {
		List<Tank> list = controller.getTanks();
		int index = 0;
		
		do{
			System.out.println("Choose one tank:");
		
			for(Tank item : list) {			
				System.out.println("Tank number "+index+": "+item.getName()+"\n");
				index++;
			}
			
			System.out.println("Write tank's number (e.g. 0): ");
		
			try {
				int choice = in.nextInt();			
				controller.setTankSelected(list.get(choice).getId());			
				controller.addFish();
				System.out.println("Fish added correctly in tank number "+choice+"!!");				
				break;
			}catch(InputMismatchException e) {
				System.err.println("[ERROR] You must type a number!!!");
				in.next();// To avoid infinite loop
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}while(true);
		
		System.out.println(MSG_CONTINUE);
		in.next();
	}
	
	
	/**
	 * Program entry point.
	 * 
	 * @param args arguments to the program (they are not needed).
	 */
	public static void main(String[] args) {
	
		    
	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		
		do {
			manageMenu();
		}while(true);
	}
}
