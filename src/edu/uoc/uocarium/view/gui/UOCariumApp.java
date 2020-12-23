package edu.uoc.uocarium.view.gui;

import java.io.IOException;

import edu.uoc.uocarium.controller.UOCariumController;
import edu.uoc.uocarium.model.ItemException;
import edu.uoc.uocarium.view.gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class UOCariumApp extends Application {

	private Region rootLayout;	
	
	@FXML
	private Pane tankPane;
	
	@Override
	public void start(Stage primaryStage) throws IOException, ItemException {		
		primaryStage.setTitle("UOCarium");
		primaryStage.setResizable(false);      
	    Controller.controller = new UOCariumController("./files/");	    		
	    primaryStage.setScene(createContent());	   
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Scene createContent() throws IOException {
		// Load root layout from fxml file.
		FXMLLoader loader = new FXMLLoader();         
	    loader.setLocation(UOCariumApp.class.getResource("MainView.fxml"));
	    rootLayout = (Region) loader.load();         
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);  
		return scene;
	}	
	 
}
