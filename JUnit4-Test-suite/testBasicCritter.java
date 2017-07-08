/**
 * 
 */
package Tests_v2;

import static org.junit.Assert.*;

import org.junit.Test;

import TheGame.*;

/**
 * @author SOEN6441-group12
 *
 */
public class testBasicCritter {

	/**
	 * Test method for {@link TheGame.CritterFactory#spawnCriter(int)}.
	 */
	@Test
	public void testSpawnCriter() {
		int waveLevel=1;
		Critter critter = CritterFactory.createCritter(waveLevel);
		assertTrue(critter instanceof BasicCritter );
		assertFalse(critter instanceof AdvancedCritter);
		assertFalse(critter instanceof IntermediateCritter );
	}

}
