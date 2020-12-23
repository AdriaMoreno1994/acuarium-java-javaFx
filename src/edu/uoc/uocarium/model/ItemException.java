package edu.uoc.uocarium.model;

public class ItemException extends Exception{
	
		public static final String MSG_ERR_LENGTH_VALUE = "[ERROR]: Item's length cannot negative either 0!!";
		public static final String MSG_ERR_HEIGHT_VALUE = "[ERROR]: Item's height cannot negative either 0!!";		
				
	   public ItemException() {
		super();
	   }
		
	   public ItemException(String msg) {
		super(msg);
	   }
}
