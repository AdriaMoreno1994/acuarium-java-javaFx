package edu.uoc.uocarium.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import edu.uoc.uocarium.model.Animal;
import edu.uoc.uocarium.model.AnimalException;
import edu.uoc.uocarium.model.Corydoras;
import edu.uoc.uocarium.model.Danio;
import edu.uoc.uocarium.model.Fish;
import edu.uoc.uocarium.model.Food;
import edu.uoc.uocarium.model.Gender;
import edu.uoc.uocarium.model.Item;
import edu.uoc.uocarium.model.ItemException;
import edu.uoc.uocarium.model.Keeper;
import edu.uoc.uocarium.model.Movable;
import edu.uoc.uocarium.model.MovableException;
import edu.uoc.uocarium.model.SubmarineToy;
import edu.uoc.uocarium.model.Tank;
import edu.uoc.uocarium.model.TankException;
import edu.uoc.uocarium.model.Tetra;

public class UOCariumController {

	Database database;
	String tankSelected;
	
	public UOCariumController(String folderName) throws ItemException {
		database = new Database(folderName);
		tankSelected = (database.getTanks().size()!=0)? database.getTanks().get(0).getId():null;
	}
		
	public String getTankSelected() {
		return tankSelected;
	}
	
	public void setTankSelected(String tankSelected) {
		this.tankSelected = tankSelected;
	}

	public List<Tank> getTanks(){
		List<Tank> tanks = database.getTanks();
		//TODO
		return tanks.stream().sorted((p1,p2)-> p1.getId().compareTo(p2.getId())).collect(Collectors.toList());
	}
	
	public Tank getTank(String id) {
		return database.getTank(id);
	}
	
	public List<Item> getMovableItems(){
		//TODO
		 List<Item> itemMovable= new ArrayList<Item>();
		 List<Tank> tank2s = new ArrayList<Tank>(database.getTanks());
		 for(Tank tank:tank2s) {
			 for(int i=0; i<tank.getItems().size();i++) {
				 if(tank.getItems().get(i) instanceof Movable) {
					 itemMovable.add(tank.getItems().get(i));
					 
				 }
				 
			 }
			 
		 }
		 return itemMovable;
					
	}
	
	public List<Item> getItems(){		
		return database.getTank(getTankSelected()).getItems();
	}
	
	public void addFish() throws AnimalException, ItemException, MovableException, TankException{
	Random rand = new Random();
	int random = rand.nextInt(10);
	int xCoord = rand.nextInt(300);
	int yCoord = rand.nextInt(300);
	boolean bool = rand.nextBoolean();
	Gender gender ;
	if(bool == true) {
		gender= Gender.MALE;
		
	}else {
		gender=Gender.FEMALE;
	}
	if( random<3) {
		
		Danio danio = new Danio(xCoord, yCoord, gender, 0, 100, null);
		getTank(tankSelected).addItem(danio);
		
		
	}else if ( random>=3 && random<6 ) {
		Tetra tetra  = new Tetra(xCoord, yCoord, gender, 0, 100, null);
		getTank(tankSelected).addItem(tetra);
	}else {
		Corydoras corydora = new Corydoras(xCoord, yCoord, gender, 0, 100, null);
		getTank(tankSelected).addItem(corydora);
		
	}
	}
	
	public SubmarineToy getSubmarineToy() {
		
		Optional<Item> op = database.getTank(getTankSelected()).getItems().stream().filter(p -> p instanceof SubmarineToy).findFirst();
		
		return op.isEmpty()?null:(SubmarineToy) op.get();			
				
	}
	
	public boolean isSubmarineToy() {
		return getSubmarineToy() != null;
	}
	
	public void toggleSubmarineToy() throws ItemException, MovableException, TankException{
		SubmarineToy toy = new SubmarineToy(50, 50, 100, 50, null);
		for(int i = 0 ; i < getTank(tankSelected).getItems().size(); i++) {
			if(getTank(tankSelected).getItems().get(i) instanceof SubmarineToy) {
				getTank(tankSelected).getItems().remove(i);
				
				
			}
			
		}
		getTank(tankSelected).addItem(toy);
	}
	
	public String getTankInfo() {
		return getTank(getTankSelected()).toString();
	}
	
	public List<Item> removeDeadItems() throws TankException{
		List<Item> deads  = new ArrayList<Item>();
		for(Item item : getTank(tankSelected).getItems()) {
			if ( item instanceof Animal) {
				if(((Animal) item).isDead()) {
					deads.add(item);
					getTank(tankSelected).removeDeadItems();
				
				}
				
			}
		}
		
		
		return deads;
	}
	
	public void feed() throws Exception {
		new Food((new Random()).nextInt(Movable.TANK_PANE_WIDTH-10),0,1,1,5,getTank(getTankSelected()));	
		
	}
	
	public List<Keeper> getKeepers(){
		return database.getKeepers();
	}
}
