/**
 * 
 */
package Tests_v2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import TheGame.*;
/**
 * @author Dap
 *
 */
public class testLoadGame {
	
	@Before 
	public void before(){
		System.out.println("in before junit test");
	}
	/**
	 * Test method for {@link TheGame.DrawMap#loadGame(TheGame.Screen)}.
	 * @throws IOException 
	 */
	
	
	@Test
	public void testLoadGame() throws IOException {
		Panels game= new Panels();
		game.loadGame(new Screen());
		assertTrue(game.loadGameCompleted);
		File file = new File(game.map.namevar);
		assertTrue(file.exists());
		assertTrue(file.isFile());
		assertTrue(file.canRead());
		System.out.println(game.map.namevar);
	}

}
