package edu.uoc.uocarium.model;

public class MovableException extends Exception{
	
	public final static String MSG_ERR_SPEED_VALUE = "[ERROR] Speed cannot be 0 or negative!!",
							   MSG_ERR_THRESHOLD_VALUE = "[ERROR] Threshold reverse cannot be negative either greater than 1!!";
	
	public MovableException(){
		super();
	}
	
	public MovableException(String msg) {
		super(msg);
	}	
}