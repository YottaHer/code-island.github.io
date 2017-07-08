/**
 * 
 */
package Tests_v2;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import TheGame.*;

/**
 * @author Soen6441 group12
 *
 */
public class testCritterFactory {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link TheGame.CritterFactory#spawnCriter(int)}.
	 * testing if a critter object is created when no wave level is selected
	 */
	@Test
	public void testCreateCritter() {
		int waveLevel=0;
		Critter critter = CritterFactory.createCritter(waveLevel);
		assertNull(critter);
	}

}
