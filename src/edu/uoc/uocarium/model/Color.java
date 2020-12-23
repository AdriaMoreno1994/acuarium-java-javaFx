package edu.uoc.uocarium.model;

public enum Color {
	YELLOW("#FFFF00"), 
	RED("#FF0000"), 
	GREEN("#00FF00"), 
	BLUE("#0000FF"), 
	GRAY("#888888"), 
	WHITE("#FFFFFF"), 
	BLACK("#000000"), 
	ORANGE("#FF8300"), 
	BRONZE("#CD7F32"), 
	NOT_DEFINED("#");
	
	 String hexCode;

	 Color(String hexCode) {
	    this.hexCode = hexCode;
	 }
	 
	 public String getHexCode() {
	        return this.hexCode;
	 }
	 
	 public Color getColor(String hexCode) {
	    	 
		for (Color c : Color.values()) {
			 if (c.getHexCode().equals(hexCode)){
				 return c;
			 }
		}
		
	    return NOT_DEFINED;
	 }
}