/**
 * 
 */
package Tests_v2;



import static org.junit.Assert.assertTrue;
import TheGame.GameController;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Groupe12
 *
 */
public class testGameController {

	/**
	 * @throws java.lang.Exception
	 */
	
	@Before 
	public void before(){
		System.out.println("in before junit test");
	}
	
	/**
	 * Test method for {@link GameController#setGame()}.
	 */
	@Test
	public void testSetGame() {
		//fail("Not yet implemented"); // TODO
		GameController.playStatus=false;
		GameController.getInstance().setGame();
		assertTrue(GameController.playStatus);
	}

}
