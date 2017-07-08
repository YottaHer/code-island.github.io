/**
 * 
 */
package Tests_v2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import TheGame.*;

/**
 * @author Dap
 *
 */
public class testIntermediateCritter {
	
	@Before
	public void before(){
		System.out.println("unit testing Intermediate Critter wave creation");
		
	}
	
	@Test
	public void spawnCriter() {
		int waveLevel=2;
		Critter critter = CritterFactory.createCritter(waveLevel);
		assertTrue(critter instanceof IntermediateCritter);
		assertFalse(critter instanceof BasicCritter);
		assertFalse(critter instanceof AdvancedCritter);
	}
	

}
