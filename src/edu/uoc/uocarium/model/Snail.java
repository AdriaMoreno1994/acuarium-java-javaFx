package edu.uoc.uocarium.model;

import java.util.Random;

public class Snail extends Animal{
		
	public Snail(double xCoord, double length, double height, Gender gender, int age, int energy, Tank tank) throws AnimalException, ItemException, MovableException{
		super(xCoord, Movable.TANK_PANE_HEIGHT-30, "./images/snail/snail", length, height, gender, age, 0.01, 0.1,0.00000003, energy, tank);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"\" :Snail\n";
	}

	@Override
	public void moveUp() {
		//We override in order to avoid that the snail moves up
	}

	@Override
	public void moveDown() {
		//We override in order to avoid that the snail moves down		
	}
	
	//@Override
	public void update() {
		crawl();
		eat();
	}	
	
	private void crawl() {
		
		if(collideWithTank()==Collision.LEFT || collideWithTank() == Collision.RIGHT) {
			reverse();
		}
			
		//If the snail doesn't collide with the tank
			
		float decision = (new Random()).nextFloat();
		
		if(decision<0.00000003){
			reverse();
		}
		
		if(isFacingRight()) {
			moveRight();
		}else {
			moveLeft();
		}			
	}
	
	@Override
	public int getOxygenConsumption() {
		return 1;
	}

	@Override
	public void breathe() {
		//TODO
	}	
}
