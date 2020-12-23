package edu.uoc.uocarium.view.gui;

//import java.awt.Button;
//import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//import com.sun.glass.events.MouseEvent;

import edu.uoc.uocarium.model.Item;
import edu.uoc.uocarium.model.ItemException;
import edu.uoc.uocarium.model.Animal;
import edu.uoc.uocarium.model.AnimalException;
import edu.uoc.uocarium.model.AnimalStatus;
import edu.uoc.uocarium.model.Fish;
import edu.uoc.uocarium.model.Food;
import edu.uoc.uocarium.model.Movable;
import edu.uoc.uocarium.model.MovableException;
import edu.uoc.uocarium.model.Tank;
import edu.uoc.uocarium.model.TankException;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;


public class MainController{
	
	@FXML
	private ComboBox<String> tankDropdown;
	
	@FXML
	private Pane tankFrame;
	@FXML
	private Pane tankPane, tankInfo;
	
	@FXML
	private Label temperatureLabel;
	
	@FXML
	private Slider temperatureSlider;
	//@FXML
	//private Button removeDeadItems;
	
	/**
	 * Default constructor.
	 */
	public MainController(){
		super();		
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
	 * @throws IOException 
     */
    @FXML
    private void initialize() throws IOException {    	
    	initDropdown(Controller.controller.getTanks());
    	populateTank(); 		
    }
    
    /**
     * Populate and bind action to the dropdown.
     * @param tanks List with the tanks available in the aquarium.
     */
    private void initDropdown(List<Tank> tanks) throws IOException {    	
    	//We populate the dropdown
    	tanks.stream().forEach(t -> tankDropdown.getItems().add(t.getId()));
    	
    	//We choose the fist option
    	tankDropdown.getSelectionModel().selectFirst();
    	Controller.controller.setTankSelected(tankDropdown.getValue());
		
    	//We bind a method when any action fires (when dropdown changes).
    	tankDropdown.setOnAction(e -> {
    		Controller.controller.setTankSelected(tankDropdown.getValue());
    		populateTank();  		
    	});
    }
      
    
    private void populateTank() {
		if(Controller.timer!=null) {
		   Controller.timer.stop();
		   Controller.timer = null;    	   
		}
    	
		Tank tank = Controller.controller.getTank(Controller.controller.getTankSelected());
    	    	
    	ObservableList<Node> nodeList =  FXCollections.observableArrayList();
        
    	nodeList.addAll(tank.getItems());             
        tankPane.getChildren().clear();
        tankPane.getChildren().addAll(nodeList);
    	
        //Item's info when clicking on it.
        nodeList.stream().forEach(n -> { 
        	n.setOnMouseReleased(ev -> {        		
				tankInfo.getChildren().clear();		
				tankInfo.getChildren().add(new Label(n.toString()));					
			});
        	        	
        	n.setStyle("-fx-cursor:HAND;");
        });
    	   	
    
        temperatureLabel.setText((int)tank.getTemperature()+" ºC");
                      
        temperatureSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
        	tank.setTemperature(newValue.intValue());
        	temperatureLabel.setText(newValue.intValue()+" ºC");
        });
        
        tankPane.setBackground(  		
        		new Background(new BackgroundImage(new Image("file:"+tank.getImageBackground(),Movable.TANK_PANE_WIDTH,Movable.TANK_PANE_HEIGHT,false,true),
        		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        		          BackgroundSize.DEFAULT))); 
                
        Controller.timer = new AnimationTimer() {
            public void handle(long now) {
         	   try {
				updateTank();
			} catch (TankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        };
       
        Controller.timer.start(); 
    }
    
	private void updateTank() throws TankException {		
		
		Iterator<Item> itr = Controller.controller.getMovableItems().iterator();
		
		while(itr.hasNext()) {
			Item item = itr.next();
			if(item instanceof Food && ((Food) item).isEaten()) { //Food is eaten
				Controller.controller.getTank(Controller.controller.getTankSelected()).removeItem(item);				
				itr.remove();
				populateTank();
			}else {
				((Movable) item).update();			
			}
		}		
		
	}    
    
    @FXML
    private void handleAddFish() throws MovableException, TankException, ItemException, AnimalException {    	
    	Controller.controller.addFish();
    	populateTank();
    }
    
    @FXML
    private void handleToggleSubmarineToy() throws MovableException, ItemException, TankException {    	
    	
    	if(Controller.controller.isSubmarineToy()) {
    		for(var node : tankPane.getChildren()) {    			
    			if(node.getId().equals(Controller.controller.getSubmarineToy().getId())){    				
    				tankPane.getChildren().remove(node);
    				break;
    			};
    			
    		}
    	}    	
    	   	
    	Controller.controller.toggleSubmarineToy();    
    	populateTank();
    }
    
    @FXML
    private void handleFeed() throws Exception {    	
    	Controller.controller.feed();
    	populateTank();
    } 
    
   @FXML
   private void removeDeadItems() throws TankException {
	  Controller.controller.getTank(Controller.controller.getTankSelected()).removeDeadItems();
	  populateTank();
	 
	 
 
}
}
