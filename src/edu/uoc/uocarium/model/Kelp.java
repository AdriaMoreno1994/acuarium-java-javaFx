package edu.uoc.uocarium.model;

public class Kelp extends Item{

	private int growStep = 50;
	
	public Kelp(double xCoord, double yCoord, double length, double height, Tank tank) throws ItemException {
		super(xCoord, yCoord, "./images/kelp/kelp_baby.png", length, height, tank);		
	}

	@Override
	public String toString() {
		return super.toString()+"\" :Kelp";
	}
}
