package edu.uoc.uocarium.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.uoc.uocarium.model.Corydoras;
import edu.uoc.uocarium.model.Danio;
import edu.uoc.uocarium.model.AnimalException;
import edu.uoc.uocarium.model.Gender;
import edu.uoc.uocarium.model.Item;
import edu.uoc.uocarium.model.ItemException;
import edu.uoc.uocarium.model.Keeper;
import edu.uoc.uocarium.model.KeeperException;
import edu.uoc.uocarium.model.Kelp;
import edu.uoc.uocarium.model.Movable;
import edu.uoc.uocarium.model.MovableException;
import edu.uoc.uocarium.model.Snail;
import edu.uoc.uocarium.model.Tank;
import edu.uoc.uocarium.model.TankException;
import edu.uoc.uocarium.model.Tetra;

/**
 * This class represents the inventory of the program. It loads and manages uocarium's data.
 * 
 * @author David García Solórzano
 * @version 1.0
 *  
 */
public class Database   {

	private HashMap<String,Tank> tanks;
	private ArrayList<Keeper> keepers;
	
	/**
	 * Constructor with arguments.
	 * @param folderName Name of the folder where the data are.
	 * @throws ItemException 
	 */
	public Database(String folderName) throws ItemException{
		tanks = new HashMap<String,Tank>();
		keepers = new ArrayList<Keeper>();
		loadTanks(folderName);
		loadItems(folderName);		
		loadKeepers(folderName);
	}
				
	/**
	 * Manages the reading of the file and stores tanks in the inventory as a list.
	 * @param folderName Name of the folder where the data are.
	 */
	private void loadTanks(String folderName){		
		List<String> list = new ArrayList<String>();
		
		try (Stream<String> stream = Files.lines(Paths.get(folderName,"tanks.txt"),StandardCharsets.ISO_8859_1)) {
			//Convert it into a List
			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String tank : list){
			String[] elements = tank.split("\\*");
								
			try{
				tanks.put(elements[0], new Tank(elements[0], //id
						elements[1],//name
						elements[2],//description
						Double.valueOf(elements[3]),//length
						Double.valueOf(elements[4]),//height
						Double.valueOf(elements[5]),//width
						elements[6], //imageBackground
						Double.valueOf(elements[7]), //temperature
						Integer.valueOf(elements[8]) //ph
						)						
					);
			}catch(TankException e) {
				e.printStackTrace();
			}
		}			
	}
	
	/**
	 * Returns the whole tanks list.
	 * @return List with the tanks
	 */
	public List<Tank> getTanks() {
		return new ArrayList<Tank>(tanks.values());
	}

	/**
	 * Manages the reading of the file and stores fish in the inventory as a list for each tank.
	 * @param folderName Name of the folder where the data are.
	 * @throws ItemException 
	 */
	private void loadItems(String folderName) throws ItemException{		
		List<String> list = new ArrayList<String>();
						
		try (Stream<String> stream = Files.lines(Paths.get(folderName,"items.txt"),StandardCharsets.ISO_8859_1)) {
			//Convert it into a List
			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String fish : list){
			String[] elements = fish.split("\\*");
							
			try{
				createItem(elements,tanks.get(elements[0]));				
			}catch(AnimalException | MovableException e) {
				e.printStackTrace();
			}
		}	
	}
	
	private Item createItem(String[] elements, Tank tank) throws AnimalException, MovableException, ItemException{
		
		switch(elements[1].toLowerCase()) {
			case "danio":
						return new Danio(Double.valueOf(elements[2]),Double.valueOf(elements[3]), Gender.valueOf(elements[4]), Integer.valueOf(elements[5]), Integer.valueOf(elements[6]), tank);						
			case "corydoras":
						return new Corydoras(Double.valueOf(elements[2]),Double.valueOf(elements[3]), Gender.valueOf(elements[4]), Integer.valueOf(elements[5]), Integer.valueOf(elements[6]), tank);
			case "tetra":
						return new Tetra(Double.valueOf(elements[2]),Double.valueOf(elements[3]), Gender.valueOf(elements[4]), Integer.valueOf(elements[5]), Integer.valueOf(elements[6]), tank);
			case "snail":
						return new Snail(Double.valueOf(elements[2]), Double.valueOf(elements[3]),Double.valueOf(elements[4]), Gender.valueOf(elements[5]), Integer.valueOf(elements[6]), Integer.valueOf(elements[7]),tank);
			case "kelp":
				 
			return new Kelp(Double.valueOf(elements[2]),tank.getHeight(),Double.valueOf(elements[4]),Double.valueOf(elements[4]),tank);						
			default:
						return null;		
		}
	}
	
	/**
	 * Returns a list with all the items of the database.
	 * @return List with all the items of the database.
	 */
	public List<Item> getItems() {
		return tanks.values().stream().map((Tank t) -> t.getItems()).flatMap(s -> s.stream()).collect(Collectors.toList());
	}
		
	/**
	 * Returns the Tank object whose id meets the parameter id.
	 * @param id Tank's id.
	 * @return Tank object which is required.
	 */
	public Tank getTank(String id) {
		return tanks.get(id);
	}
	
	
	public void loadKeepers(String folderName) {
		List<String> list = new ArrayList<String>();
		
		try (Stream<String> stream = Files.lines(Paths.get(folderName,"keepers.txt"),StandardCharsets.ISO_8859_1)) {
			//Convert it into a List
			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String item : list){
			String[] elements = item.split("\\*");							
			try{
				Keeper keeper = new Keeper(elements[0],elements[1],elements[2]);
				String[] keeperTanks = elements[3].split("/");
				for(var tank : keeperTanks) {
					keeper.addTank(tanks.get(tank));
				}
				keepers.add(keeper);
			}catch(KeeperException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public List<Keeper> getKeepers(){
		return keepers;
	}

	
}