package edu.uoc.uocarium.model;

public interface Movable {
	
	public final static int TANK_PANE_WIDTH = 404;
	public final static int TANK_PANE_HEIGHT = 346;
		
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveDown();	
	public Collision collideWithTank();	
	public void update();
	public double getSpeed();
	public void setSpeed(double speed) throws MovableException;
	public double getThresholdReverse();
	public void setThresholdReverse(double thresholdReverse) throws MovableException;	
	public boolean isFacingRight();	
	public void reverse();
}