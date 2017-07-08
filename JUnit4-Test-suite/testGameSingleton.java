/**
 * 
 */
package Tests_v2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TheGame.GameController;

/**
 * @author Dap
 *
 */

public class testGameSingleton {
	
	@Before 
	public void before(){
		System.out.println("in before junit test");
	}

	/**
	 * Test method for {@link GameController#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		GameController obj1 = GameController.getInstance();
		GameController obj2 = GameController.getInstance();
		assertSame(obj1,obj2);
	}

}
