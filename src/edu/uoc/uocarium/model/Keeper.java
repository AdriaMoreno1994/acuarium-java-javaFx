package edu.uoc.uocarium.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keeper  {
	private String id;
	private String name;
	private String surname;
	private String reference;
	private Tank tanks[] ;
	
	
	
	public Keeper(String id, String name, String surname)throws KeeperException {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		setReference(reference);
		tanks =   new Tank[5];
	}



	public String getId() {
		return id;
	}



	public void setId(String id) throws NullPointerException, KeeperException {
		if(id==null) {
			throw new NullPointerException();
			
			
		}else {
			if(id.startsWith("G")==false){
				throw new KeeperException(KeeperException.MSG_FIRST_LETTER);
				
				
			}else {
				if(id.length()!=5) {
					throw new KeeperException(KeeperException.MSG_5_CHARACTERS);
					
				}else {
					this.id = id;
				}
			}
		}
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getReference() {
		return reference;
	}



	public void setReference(String reference) {
		this.reference = reference;
	}



	public  Tank[] getTanks() {
		return tanks;
	}



	
	public void setTanks(Tank[] tanks) {
		this.tanks = tanks;
	}



	public void addTank (Tank tank)throws KeeperException {

			
			if( tanks[4]!=null ) {
				throw new KeeperException(KeeperException.MSG_FULL);
				
			}
			for(int j  =0; j<tanks.length; j++) {
				if(tanks[j]==tank) {
					
				}else {
					if(tanks[j]==null) {
						tanks[j]=tank;
						break;
					
				}
				
			
			}
					}
				}
		
		
	


	@Override
	public String toString() {
		StringBuilder sb =new StringBuilder();
		sb.append("["+getId()+"]");
		sb.append(" "+getName());
		sb.append(" ,"+getSurname());
		//for(Tank tank:tanks) {
			//sb.append(tank.getName()+"\n");
		//}
		return sb.toString();
	}
	
	
}




