package edu.uoc.uocarium.model;

public class KeeperException extends Exception{
	public static final String  MSG_FULL="[ERROR] A keeper cannot take care of more than 5  tanks";
	public static final String  MSG_FIRST_LETTER = "[ERROR] A Keeper's id must start with letter  'G'";
	public static final String  MSG_5_CHARACTERS = "[ERROR] A keeper's id  must have 5 characters!!";
	public KeeperException(String string) {
		super(string);
	}
	public KeeperException() {
		
	}

}
