package edu.uoc.uocarium.model;

import java.util.Random;

public abstract class Animal extends Item implements Movable {

	private Gender gender;
	private int age;
	private boolean facingRight; // whether animal is facing right or left
	private double speed;
	private double requiredFoodQuantity;
	private double thresholdReverse;
	private AnimalStatus status;
	private int energy = 100;

	protected Animal(double xCoord, double yCoord, String spriteImage, double length, double height, Gender gender,
			int age, double speed, double requiredFoodQuantity, double thresholdReverse, int energy, Tank tank)
			throws AnimalException, ItemException, MovableException {
		super(xCoord, yCoord, spriteImage, length, height, tank);
		setGender(gender);
		setAge(age);
		setSpeed(speed);
		setRequiredFoodQuantity(requiredFoodQuantity);
		setFacingRight(true);
		setThresholdReverse(thresholdReverse);
		setEnergy(energy);
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws AnimalException {
		if (age < 0)
			throw new AnimalException(AnimalException.MSG_ERR_AGE_VALUE);
		this.age = age;
	}

	public double getRequiredFoodQuantity() {
		return requiredFoodQuantity;
	}

	public void setRequiredFoodQuantity(double requiredFoodQuantity) {
		this.requiredFoodQuantity = requiredFoodQuantity;
	}

	@Override
	public double getThresholdReverse() {
		return thresholdReverse;
	}

	@Override
	public void setThresholdReverse(double thresholdReverse) throws MovableException {
		if (thresholdReverse < 0 || thresholdReverse > 1)
			throw new MovableException(MovableException.MSG_ERR_THRESHOLD_VALUE);
		this.thresholdReverse = thresholdReverse;
	}

	@Override
	/**
	 * Determines whether the fish is facing right.
	 * 
	 * @return <code>true</code> if fish is facing right; <code>false</code>
	 *         otherwise
	 **/
	public boolean isFacingRight() {
		return facingRight;
	}

	private void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	/**
	 * Reverses direction.
	 **/
	public void reverse() {
		facingRight = !facingRight;
		setScaleX(facingRight ? 1 : -1);
	}

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) throws MovableException {
		if (speed <= 0)
			throw new MovableException(MovableException.MSG_ERR_SPEED_VALUE);
		this.speed = speed;
	}

	public AnimalStatus getStatus() {
		return status;
	}

	private void setStatus(AnimalStatus status) {
		this.status = status;
		String[] aux = getSpriteImage().split("_");
		
		if(status==AnimalStatus.DEAD) {
			
			setSpriteImage(aux[0]+"_dead.png");
			
		}else {
			setSpriteImage(aux[0]+"_healthy.png");
		
		}
		
		
		
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) throws AnimalException {
		if (energy < 0 || energy > 100)
			throw new AnimalException(AnimalException.MSG_ERR_ENERGY_RANGE_VALUE);

		if (this.energy != 0) {
			this.energy = energy;
		} else {
			throw new AnimalException(AnimalException.MSG_ERR_ENERGY_ZERO);
		}

		if (getEnergy() == 0) {
			setStatus(AnimalStatus.DEAD);
		} else if (getEnergy() < 25) {
			setStatus(AnimalStatus.SICK);
		} else {
			setStatus(AnimalStatus.HEALTHY);
		}
	}

	public void incEnergy(int energy) throws AnimalException {
		try {
			if (energy < 0)
				throw new Exception("[ERROR] Incorrect value!!"); // Este error no estaba especificado en el enunciado y
																	// no hacia falta codificarlo/controlarlo
			setEnergy(getEnergy() + energy);
		} catch (Exception e) {
			try {
				setEnergy(100);
			} catch (Exception e2) {
			}
			throw new AnimalException(AnimalException.MSG_WARNING_ENERGY_AUTOMATIC_VALUE + 100);
		}
	}

	public void decEnergy(int energy) throws AnimalException {
		try {
			if (energy < 0)
				throw new Exception("[ERROR] Incorrect value!!"); // Este error no estaba especificado en el enunciado y
																	// no hacia falta codificarlo/controlarlo
			setEnergy(getEnergy() - energy);
		} catch (Exception e) {
			try {
				setEnergy(0);
			} catch (Exception e2) {
			}
			throw new AnimalException(AnimalException.MSG_WARNING_ENERGY_AUTOMATIC_VALUE + 0);
		}
	}

	public boolean isDead() {
		return (getEnergy() == 0); // TAMBIEN: return getStatus()==ItemStatus.DEAD;
	}

	@Override
	public void moveLeft() {
		setLocation(getXCoord() - getSpeed(), getYCoord());
	}

	@Override
	public void moveRight() {
		setLocation(getXCoord() + getSpeed(), getYCoord());
	}

	@Override
	public void moveUp() {
		setLocation(getXCoord(), getYCoord() - getSpeed());
	}

	@Override
	public void moveDown() {
		setLocation(getXCoord(), getYCoord() + getSpeed());
	}

	@Override
	public Collision collideWithTank() {
		if (!isFacingRight() && getXCoord() - 3 <= 0) {
			return Collision.LEFT; // Choca por la izquierda
		}

		if (isFacingRight() && getXCoord() + 60 >= Movable.TANK_PANE_WIDTH) {
			return Collision.RIGHT;
		}

		if (getYCoord() - 5 <= 0) {
			return Collision.TOP;
		}

		if (getYCoord() + 45 >= Movable.TANK_PANE_HEIGHT) {
			return Collision.BOTTOM;
		}

		return Collision.NO_COLLISION;
	}

	public void eat() {
		if (getStatus() != AnimalStatus.DEAD) {
			if(getTank().getFood().isEmpty()==false) {
			for (Item f : getTank().getFood()) {
				Food food = (Food) f;

				if (food.getXCoord() >= this.getXCoord() - 5 && food.getXCoord() <= this.getXCoord() + getLength()
						&& food.getYCoord() <= this.getYCoord() + getHeight() * 0.75
						&& food.getYCoord() >= this.getYCoord()) {
					int energy = food.getEnergy();
					try {
						this.incEnergy(energy);
					} catch (ItemException e) {
					}
					food.eaten();
				}//si  descomentem  aquest tros de codi  l'algoritme eat  funcionara de manera que  
				// si el peix no reb menjar  es genera un numero aleatori  si aquest numero es inferior a 1 se li restara 1 de energia 
				// l'he utilitzat per  restar energia  fins que els peixos morisin  aixi saber si el boto remove dead items estava ben codificat
				//else {
				//	Random random= new Random();
				//	int rnd= random.nextInt(100);
				//	if(rnd<1) {
				//	try {
				//		this.decEnergy(1);
				//	} catch (AnimalException e) {
						// TODO Auto-generated catch block
				//		e.printStackTrace();
				//	}
					}
				}
					
						
					
				}//al descomentar aquest codi  se li resta energia de la mateixa manera   el else anterior per  el mateix motiu
			//}else {
			//	Random random= new Random();
			//	int rnd= random.nextInt(100);
			//	if(rnd<1) {
			//	try {
			//		this.decEnergy(5);
			//	} catch (AnimalException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}
			//	}
		//	}
		//	}
		}
	

	/**
	 * Override String method.
	 * 
	 * @return A String with animal's information.
	 **/
	@Override
	public String toString() {
		return "(" + getXCoord() + ", " + getYCoord() + ") " + (isFacingRight() ? "R" : "L") + " " + getGender() + " E="
				+ getEnergy();
	}

	public abstract int getOxygenConsumption();

	public abstract void breathe();
}
