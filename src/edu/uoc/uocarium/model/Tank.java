package edu.uoc.uocarium.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a Tank of UOCarium.
 * @author David Garcia Solorzano
 * @version 1.0
 */

public class Tank implements Comparable<Tank>{

	/**
	 * Tank's id, e.g. T1.
	 */
	private String id;
	
	/**
	 * Tank's name, e.g. Malaga.	
	 */	
	private String name;
	
	/**
	 * Tank's description, e.g. Acuario de Malaga.	
	 */
	private String description;
	/**
	 * Image source.
	 */	
	private String imageBackground;
	/**
	 * Tank's length.	
	 */
	private double length;
	/**
	 * Tank's height.	
	 */
	private double height;
	/**
	 * Tank's width.	
	 */
	private double width;
	/**
	 * Tank's temperature.	
	 */
	private double temperature;
	/**
	 * Tank's PH.		
	 */
	private int ph;

	/**
	 * items that are in the tank.
	 */
	private List<Item> items;
	
	/**
	 * Default constructor. It assigns the default value to all the instance's fields/attributes.
	 * @throws TankException When name is longer than 40 characters, length less than 0.1, width less than 0.1, height less than 0.1 or ph is not between 0 and 14.
	 */
	public Tank() throws TankException{
		this("D1","Default", "Tank Default", 50.25, 10.55, 100.232, "./", 15, 7);
	}
	
	/**
	 * Constructor with arguments.
	 * @param name Tank's  name.
	 * @param description Tank's description.
	 * @param length Tank's length.
	 * @param height Tank's height.
	 * @param width Tank's width.
	 * @param imageBackground Source where the tank's image is.
	 * @param temperature Tank's temperature.
	 * @param ph Tank's PH.
	 * @throws TankException When name is longer than 40 characters, length less than 0.1, width less than 0.1, height less than 0.1 or ph is not between 0 and 14.
	 */
	public Tank(String id, String name, String description, double length, double height, double width, String imageBackground, double temperature, int ph) throws TankException {
		setId(id);
		setName(name);
		setDescription(description);
		setLength(length);
		setHeight(height);
		setWidth(width);
		setImageBackground(imageBackground);
		setTemperature(temperature);
		setPh(ph);
		items = new ArrayList<Item>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * It is name's getter.
	 * @return Tank's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * It is name's setter.
	 * @param name Tanks's name.
	 * @throws TankException When param "name" is longer than 40 characters.
	 */
	public void setName(String name) throws TankException {
		if(name.length()>40) {
			throw new TankException(TankException.MSG_ERR_NAME_VALUE);
		}
		this.name = name;
	}
	
	
	/**
	 * It is description's getter.
	 * @return Tank's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * It is description's setter.
	 * @param description Tank's description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * It is length's getter. 
	 * @return Tank's width.
	 */
	public double getLength(){
		return length;
	}
	

	/**
	 * It is length's setter.
	 * @param length Tank's width.
	 * @throws TankException When param "length" is less than 0.1.
	 */

	public void setLength(double length) throws TankException{
		if(length < 0.1) {
			throw new TankException(TankException.MSG_ERR_LENGTH_VALUE);
			
		}
		this.length = length;
	}
	/**
	 * It is height's getter. 
	 * @return Tank's height.
	 */
	public double getHeight() {		
		return height;
	}

	/**
	 * It is height's setter.
	 * @param height Tank's height.
	 * @throws TankException When param "height" is less than 0.1.
	 */
	public void setHeight(double height) throws TankException {
		if(height < 0.1) {
			throw new TankException(TankException.MSG_ERR_HEIGHT_VALUE);
		}
		this.height = height;
	}
	
	/**
	 * It is width's getter. 
	 * @return Tank's width.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * It is width's setter.
	 * @param width Tank's width.
	 * @throws TankException When param "width" is less than 0.1.
	 */
	public void setWidth(double width) throws TankException {
		if(width<0.1) {
			throw new TankException(TankException.MSG_ERR_WIDTH_VALUE);
		}
		this.width = width;
	}
	
	/**
	 * It is imageBackground's getter. 
	 * @return Source where the team's image is.
	 */
	
	public String getImageBackground() {
		return imageBackground;
	}
	
	/**
	 * It is imageBackground's setter.  
	 * @param imageBackground Source where the tank's image is.
	 */
	public void setImageBackground(String imageBackground) {
		this.imageBackground = imageBackground;
	}
	
	/**
	 * It is temperature's getter. 
	 * @return temperature Tank's temperature.
	 */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	 * It is temperature setter. 
	 * @param temperature Tank's temperature.
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * It is ph's getter. 
	 * @return Tank's ph.
	 */
	public int getPh() {
		return ph;
	}

	/**
	 * It is ph's setter.
	 * @param ph Number of PH in the tank.
	 * @throws TankException When param "ph" is not between 0 and 14.
	 */
	public void setPh(int ph) throws TankException {
		if(ph<0 || ph>14) {
			throw new TankException(TankException.MSG_ERR_PH_VALUE);
		}
		this.ph = ph;
	}

	/**
	 * Item's getter
	 * @return items The List of the Item objects in the tank.
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/**
	 * Increases temperature 0.1�C 
	 */	
	public void warm() {
		setTemperature(getTemperature()+0.1);
	}
	
	/**
	 * Decreases temperature 0.1�C 
	 */
	public void cool() {
		setTemperature(getTemperature()-0.1);
	}
	
	/**
	 * Increases PH in one unit 
	 */
	public void increasePh() throws TankException {
		setPh(getPh()+1);
	}
	
	/**
	 * Decreases PH in one unit 
	 */	
	public void decreasePh() throws TankException {
		setPh(getPh()-1);
	}
	
	/**
	 * Calculates the volume of the tank.
	 * @return Tank's volume in cm^3.
	 */
	public double getVolume() {
		return getLength() * getHeight() * getWidth(); 
	}
	
	/**
	 * Calculates the maximum liters that the tank can have.
	 * @return Maximum water in liters that the tank is able to have according to its volume.
	 */
	public double getMaxLiters() {
		return getVolume()/1000;
	}
	
	/**
	 * Getter for the array "item".
	 * @param index The position of the item that you want to retrieve.
	 * @return The Item object in the position "index" of "items".
	 * @throws IndexOutOfBoundsException when the index is out of the range of the array "items".
	 */
	public Item getItem(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index >= getItems().size()) {
			throw new IndexOutOfBoundsException("[ERROR] The index is out of the range of the item list");
		}
		return items.get(index);
	}
	
	/**
	 * Returns the number of items in the tank.
	 * @return Number of no-null objects in "item" array.
	 */
	public int getNumberItems() {
		return items.size();		
	}
	
	/**
	 * Empty the tank and returns the items.
	 * @return No-null items in the tank before emptying it.
	 */
	public List<Item> empty() {
		List<Item> itemsToReturn = new ArrayList<Item>(); //Tambien se puede hacer new ArrayList<>();
		//itemsToReturn podria ser declarada como ArrayList<Item> itemsToReturn
		
		itemsToReturn.addAll(getItems());
		
		for(Item item : itemsToReturn) {
			item.setTank(null);
		}
		
		getItems().clear();
		
		return itemsToReturn;
		
		/*
		 * FORMA 2: Con iterator
		 * 
		 * 
		ArrayList<Item> itemsToReturn;
		
		if(getNumberItems()==0) {
			return null;
		}else {
			itemsToReturn = new ArrayList<Item>();	//Tambien se puede hacer new ArrayList<>();		
		}
		
		Iterator<Item> itr = getItems().iterator();
		
		while(itr.hasNext()) {
			Item item = itr.next();
			itemsToReturn.add(item);
			itr.remove();
		}
		
		for(Item item : itemsToReturn) {
			item.setTank(null);
		}
				
		return itemsToReturn;	
		 * 
		 * 
		 */				
	}
	
	/**
	 * Inserts a new item in the tank.
	 * @param item Element which must be added to the tank.
	 * @throws TankException When the item which must be added to the tank is already in the tank or the item is null.
	 */	
	public void addItem(Item item) throws TankException{
		if(item == null) {
			throw new TankException(TankException.MSG_ERR_ITEM_OBJECT);
		}
		
		if(getItems().contains(item)) {
			throw new TankException(TankException.MSG_ERR_ITEM_REPEAT);
		}
					
		
		if(item.getTank()!=null) {
			//Si el item ya tiene un tanque, le quitamos al tanque el item
			item.getTank().removeItem(item);			
		}
		
		items.add(item);
		item.setTank(this);
	}
	
	/**
	 * 
	 * @param index Position in which the item to remove is
	 * @return The removed item
	 * @throws IndexOutOfBoundsException When the index is out of the range of the array "item".
	 */
	public Item removeItem(int index) throws IndexOutOfBoundsException{
		Item item = items.remove(index);
		item.setTank(null);
		return item;		
	}
	
	/**
	 * 
	 * @param item Element which we want to remove from the tank.
	 * @throws TankException When the item does not exist
	 */
	public void removeItem(Item item) throws TankException{
		if(!items.remove(item)) {
		 throw new TankException(TankException.MSG_ERR_ITEM_NOT_FOUND);
		}
		item.setTank(null);
	}
	
	/**
	 * Remove all the dead items from the tank.
	 */
	public void removeDeadItems() {
		List<Item> deadItems = new ArrayList<Item>();
		
		//Guardamos en una lista auxiliar los items a borrar
		for(Item item : getItems()) {
			if(item instanceof Animal && ((Animal)item).getStatus() == AnimalStatus.DEAD) {
				deadItems.add(item);
			}
		}
		
		//Borramos los items con "removeItem"
		for(var deadItem : deadItems) {
			try {
				removeItem(deadItem);
			} catch (TankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		/*
		 * El siguiente codigo parece correcto, pero no lo es:
		 * 
		 * 
		 * for(int i = 0; i < getNumberItems(); i++) {
			if(getItem(i) instanceof Animal) {
				if(((Animal)getItem(i)).getStatus() == AnimalStatus.DEAD) {
					removeItem(i);
				}
			}
		}
		
		* puesto que si hay dos items DEAD seguidos, no los borra correctamente
		* debido a que el indice del item en el array se modifica en tiempo de ejecucion
		 */
	}
	
	public List<Item> getFood(){
		return getItems().stream().filter(Food.class::isInstance).collect(Collectors.toList());
	}	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(getName());
	
		str.append("\n"+getDescription());
		str.append("\nLength: "+getLength());
		str.append("\nHeight: "+getHeight());
		str.append("\nTemperature: "+getTemperature());
		str.append("\nPH: "+getPh());		
		str.append("\nItems: ");
		
		for(Item item : getItems()) { 
			if(item != null) str.append("\n\t"+item);
		}
		
		return str.toString();
	}

	@Override
	public int compareTo(Tank o) {
		if(o instanceof Tank) {
			return this.id.compareTo(o.id);
		}
		// TODO Auto-generated method stub
		return 0;
	}
}