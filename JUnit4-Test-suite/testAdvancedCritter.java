/**
 * 
 */
package Tests_v2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TheGame.AdvancedCritter;
import TheGame.BasicCritter;
import TheGame.Critter;
import TheGame.CritterFactory;
import TheGame.IntermediateCritter; 

/**
 * @author Dap
 *
 */
public class testAdvancedCritter {

	@Before
	public void before(){
		System.out.println("unit testing advanced critter wave creation");
	}
	/**
	 * Test method for {@link TheGame.AdvancedCritter#spawnCriter(int)}.
	 */
	@Test
	public void testSpawnCriter() {
		int waveLevel=3;
		Critter critter = CritterFactory.createCritter(waveLevel);
		assertTrue(critter instanceof AdvancedCritter);
		assertFalse(critter instanceof BasicCritter);
		assertFalse(critter instanceof IntermediateCritter );
	}

}
