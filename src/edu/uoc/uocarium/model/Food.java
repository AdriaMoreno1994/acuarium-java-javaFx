package edu.uoc.uocarium.model;

public class Food extends Item implements Movable{

	private double speed;
	private boolean eaten;
	private int energy;
	
	public Food(double xCoord, double yCoord, double length, double height, int energy, Tank tank)
			throws AnimalException, ItemException, MovableException, Exception {
		super(xCoord, yCoord, "./images/food/seed.png", length, height, tank);
		setSpeed(1);
		this.eaten = false; //podria haberse inicializado en la declaracion
		setEnergy(energy);
	}
	
	@Override
	public double getSpeed() {		
		return speed;
	}	

	@Override
	public void setSpeed(double speed) throws MovableException {
		if(speed<=0) throw new MovableException(MovableException.MSG_ERR_SPEED_VALUE);
		this.speed = speed;		
	}
	
	public boolean isEaten() {
		return eaten;
	}
	
	public void eaten() {
		this.eaten = true;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	private void setEnergy(int energy) throws Exception{
		if(energy<1 || energy>10) {
			throw new Exception("[ERROR] Food cannot be less than 1 either greater than 10!!");
		}
		
		this.energy = energy;
	}

	@Override
	public void moveLeft() {
		//Nothing...		
	}

	@Override
	public void moveRight() {
		//Nothing...		
	}

	@Override
	public void moveUp() {
		//Nothing...		
	}

	@Override
	public void moveDown() {
		setLocation(getXCoord(),getYCoord()+getSpeed());		
	}

	@Override
	public Collision collideWithTank() {
		if(getYCoord()+20>=Movable.TANK_PANE_HEIGHT) {
			return Collision.BOTTOM;
		}
		return Collision.NO_COLLISION;	
	}

	@Override
	public void update() {		
		sink();		
	}

	private void sink() {
		//If the food doesn't bump into the floor.
		if(collideWithTank()!=Collision.BOTTOM) {			
				moveDown();			
		}
	}
	
	@Override
	public double getThresholdReverse() {
		//Nothing...		
		return 0;
	}

	@Override
	public void setThresholdReverse(double thresholdReverse) throws MovableException {
		//Nothing...		
	}

	@Override
	public boolean isFacingRight() {
		return false;
	}

	@Override
	public void reverse() {
		//Nothing...		
	}	
}
