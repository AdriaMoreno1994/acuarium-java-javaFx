package edu.uoc.uocarium.model;

public class AnimalException extends ItemException{

	public final static String MSG_ERR_AGE_VALUE = "[ERROR] Animals' age cannot be a negative value!!";		
	public static final String MSG_ERR_ENERGY_RANGE_VALUE = "[ERROR] Animal's energy cannot negative either greater than 100!!";
	public static final String MSG_ERR_ENERGY_ZERO = "[ERROR] Animal's energy is zero. This value cannot be modified!!";
	public static final String MSG_WARNING_ENERGY_AUTOMATIC_VALUE = "[WARNING] Animal's energy has been assigned to ";

	
	public AnimalException() {
		super();
	}
	
	public AnimalException(String msg) {
		super(msg);
	}

}