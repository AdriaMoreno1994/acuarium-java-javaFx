package edu.uoc.uocarium.model;

public class Corydoras extends Fish implements Movable {

	public Corydoras(double xCoord, double yCoord,  Gender gender,
			int age,  int energy,Tank tank) throws AnimalException, ItemException, MovableException {
		super(xCoord, yCoord, "./images/corydoras/corydoras", 64, 64, gender, age, 0.1	, 0.5, 0.001, Color.BLUE, energy, tank);
	
	}

	@Override
	public int getOxygenConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void breathe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return super.toString()+"\" : Corydoras\n";
	}
	


}
