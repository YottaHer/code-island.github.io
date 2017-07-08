
package Tests_v2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import TheGame.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Dap
 * Testing save game 
 */
public class testSaveGame {
	/**
	 * @before
	 */
	@Before 
	public void before(){
		System.out.println("in before junit test");
	}
	/**
	 * Test method for {@link DrawMap#saveGame()}.
	 * @throws IOException 
	 */
	
	@Test
	public void testSaveGame() throws IOException {
		
		Panels game= new Panels();
		game.saveGame();
		assertTrue(game.saveGameCompleted);
		File file = new File(game.map.namevar);
		assertTrue(file.exists());
		assertTrue(file.isFile());
		assertEquals(file.getName(),game.map.namevar);
		
			
	}

	

}
