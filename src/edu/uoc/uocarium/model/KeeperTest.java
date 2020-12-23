package edu.uoc.uocarium.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class KeeperTest {
	
	Keeper keeper;
	@Test
	void testSetIdWrongFirstLetter() throws  KeeperException{
		try {
		Keeper keeper = new Keeper("a", "B", "c");
		keeper.setId("A");
		
		fail(); 
		}catch(KeeperException ex){
			assertEquals(KeeperException.MSG_FIRST_LETTER, ex.getMessage());
			
			
			
		}
		
		
	}
	@Test
	void testSetId5Character()throws KeeperException{
		try {
			Keeper keeper = new Keeper("a", "B", "c");
			keeper.setId("G234");
			fail();
		}catch(KeeperException ex ) {
			assertEquals(KeeperException.MSG_5_CHARACTERS, ex.getMessage());
		}
		
	}
	@Test 
	void testSetIdNull()throws NullPointerException, KeeperException{
		assertThrows(NullPointerException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				Keeper keeper = new Keeper("a", "B", "c");
				keeper.setId(null);
				
			}
		});
	}

	@Test
	void testAddTank() throws KeeperException, TankException {
		Keeper keeper = new Keeper("a", "B", "c");
		Tank tank1 = new Tank();
		Tank tank2 = new Tank();
		Tank tank3 = new Tank();
		Tank tank4 = new Tank();
		Tank tank5 = new Tank();
		Tank tank6 = new Tank();
		try {
			keeper.addTank(tank1);
			assertEquals(tank1, keeper.getTanks()[0]);
			keeper.addTank(tank2);
			assertEquals(tank2, keeper.getTanks()[1]);
			keeper.addTank(tank3);
			assertEquals(tank3, keeper.getTanks()[2]);
			keeper.addTank(tank4);
			assertEquals(tank4, keeper.getTanks()[3]);
			keeper.addTank(tank5);
			assertEquals(tank5, keeper.getTanks()[4]);
			keeper.addTank(tank6);
			assertEquals(tank2, keeper.getTanks()[5]);
			
			fail();
		}catch(KeeperException ex) {
			assertEquals(KeeperException.MSG_FULL, ex.getMessage());
		}
		
		
	}

}
