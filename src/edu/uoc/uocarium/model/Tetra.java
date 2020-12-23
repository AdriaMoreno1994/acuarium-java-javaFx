package edu.uoc.uocarium.model;

public class Tetra extends Fish implements Movable {

	public Tetra(double xCoord, double yCoord,  Gender gender,
			int age,  int energy,Tank tank) throws AnimalException, ItemException, MovableException {
		super(xCoord, yCoord, "./images/tetra/tetra", 64, 64, gender, age, 0.5	, 0.3, 0.002, Color.RED, energy, tank);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getOxygenConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return super.toString()+"\" : Tetra\n";
	}

	@Override
	public void breathe() {
		// TODO Auto-generated method stub
		
	}
	
}
