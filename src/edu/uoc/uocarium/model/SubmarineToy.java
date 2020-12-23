package edu.uoc.uocarium.model;

import java.util.Random;

public class SubmarineToy extends Item implements Movable{

	private double speed;
	private double thresholdReverse;
	private boolean facingRight;
	private int balanceMove = 0;//inicializamos aqui o en el constructor.
	private final int TURN_BALANCE_MOVE = 100; //inicializamos aqui o en el constructor
	
	public SubmarineToy(double xCoord, double yCoord, double length, double height, Tank tank) throws ItemException, MovableException{
		super(xCoord, yCoord, "./images/submarine/submarine.png", length, height, tank);
		setSpeed(1);
		setFacingRight(true);
		setThresholdReverse(0.0003);
	}

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) throws MovableException{
		if(speed<=0) throw new MovableException(MovableException.MSG_ERR_SPEED_VALUE);
		this.speed = speed;
	}

	@Override
	public boolean isFacingRight() {
		return facingRight;
	}	

	private void setFacingRight(boolean facingRight) {
    	this.facingRight = facingRight;    	
    }        

	@Override
    public void reverse(){
        facingRight = ! facingRight;
        setScaleX(facingRight?1:-1);
    }

    @Override
	public double getThresholdReverse() {
		return thresholdReverse;
	}

	@Override
	public void setThresholdReverse(double thresholdReverse) throws MovableException {
		if(thresholdReverse<0 || thresholdReverse>1) throw new MovableException(MovableException.MSG_ERR_THRESHOLD_VALUE);		
		this.thresholdReverse = thresholdReverse;
	}
	
	@Override
	public void moveLeft() {
		setLocation(getXCoord()-getSpeed(),getYCoord());		
	}

	@Override
	public void moveRight() {
		setLocation(getXCoord()+getSpeed(),getYCoord());		
	}

	@Override
	public void moveUp() {
		setLocation(getXCoord(),getYCoord()-getSpeed());		
	}

	@Override
	public void moveDown() {
		setLocation(getXCoord(),getYCoord()+getSpeed());		
	}

	@Override
	public Collision collideWithTank() {
		if(!isFacingRight() && getXCoord()-3<=0) {			
			return Collision.LEFT; //Choca por la izquierda
		}
		
		if(isFacingRight() && getXCoord()+60>=Movable.TANK_PANE_WIDTH) {
			return Collision.RIGHT;
		}
		
		if(getYCoord()-5<=0) {			
			return Collision.TOP;
		}
		
		if(getYCoord()+45>=Movable.TANK_PANE_HEIGHT) {
			return Collision.BOTTOM;
		}
		
		return Collision.NO_COLLISION;	
	}
	
	@Override
	public void update() {
		move();
	}
	
	private void move() {
		Collision collision = collideWithTank();
		float decision = (new Random()).nextFloat();
		if(balanceMove>0) {
			moveDown();
			balanceMove--;
		}else if(balanceMove<0) {
			moveUp();
			balanceMove++;
		}else {
			if(collision==Collision.NO_COLLISION) {//If the submarine doesn't collide with the tank			
				if(decision<getThresholdReverse()){ //Reverse because it wants...
					reverse();
				}else{
					decision = (new Random()).nextFloat();
					if(isFacingRight()) {
						moveRight();
					}else {
						moveLeft();
					}
									
					if(decision<0.48) {
						moveUp();				
					}else {
						moveDown();
					}
				}			
			}else if(collision==Collision.LEFT || collision == Collision.RIGHT) {
				reverse();
			}else if (collision==Collision.TOP) {
				balanceMove = TURN_BALANCE_MOVE;
			}else {
				balanceMove = -TURN_BALANCE_MOVE;
			}
		}	
	}
}