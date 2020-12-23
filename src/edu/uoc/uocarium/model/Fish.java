package edu.uoc.uocarium.model;

import java.util.Random;

public abstract class Fish extends Animal{

	protected Color color;
	
	
	protected Fish(double xCoord, double yCoord, String spriteImage, double length, double height, Gender gender, int age, double speed, double requiredFoodQuantity, double thresholdReverse, Color color, int energy, Tank tank) throws AnimalException, ItemException, MovableException{
		super(xCoord, yCoord, spriteImage,length, height, gender, age, speed, requiredFoodQuantity,thresholdReverse, energy, tank);	
		setColor(color);
	}
			
	protected void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public void update() {
		swim();
		eat();
	}
    
	private void swim() {
		Collision collision = collideWithTank();
		float decision = (new Random()).nextFloat();
		
		if(getStatus() == AnimalStatus.DEAD) { //If the fish is dead, then it goes down, down and down... until it bumps into the floor.
			try {
				setSpeed(2);
			} catch (MovableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(collision!=Collision.BOTTOM) 
				moveDown();			
		}else {
		
			if(collision==Collision.NO_COLLISION) {//If the fish doesn't collide with the tank			
				if(decision<getThresholdReverse()){
					reverse();
				}else{
					decision = (new Random()).nextFloat();
					if(decision<0.8) {		
						if(isFacingRight()) {
							moveRight();
						}else {
							moveLeft();
						}
					}else if(decision<0.9) {
						moveUp();
					}else {
						moveDown();
					}
				}			
			}else if(collision==Collision.LEFT || collision == Collision.RIGHT) {
				reverse();
			}else if (collision==Collision.TOP) {
				moveDown();
			}else {
				moveUp();
			}
		}
	}
		
		
	/**
     *  Override String method.
     *  @return A String with fish's information.
     **/
	@Override
    public String toString(){
       return super.toString()+ " : "+getColor();
    }	

}
