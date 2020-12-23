package edu.uoc.uocarium.model;


public class TankException extends Exception{

	public final static String MSG_ERR_HEIGHT_VALUE = "[ERROR] Height cannot be less than 0.1 cm.",
			MSG_ERR_LENGTH_VALUE = "[ERROR] Length cannot be less than 0.1 cm.",
			MSG_ERR_WIDTH_VALUE = "[ERROR] Width cannot be less than 0.1 cm.",
			MSG_ERR_PH_VALUE = "[ERROR] PH cannot be less than 0 or greater than 14",
			MSG_ERR_NAME_VALUE = "[ERROR] Name cannot be longer than 40 characters!!",
			MSG_ERR_ITEM_OBJECT = "[ERROR] Item must be an Item object!!",
			MSG_ERR_ITEM_REPEAT = "[ERROR] The item which you want to add is already in the tank!!",
			MSG_ERR_ITEM_NOT_FOUND = "[ERROR] The item does not exist in the tank";
	
	public TankException() {
		super();
	}
	
	public TankException(String msg) {
		super(msg);
	}	
}
