package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test the loseHealth method in Mob class.
 *
 */
public class testLoseCoin {
	DrawMap map;
	Mob mob=new Mob(map);
	
	/**
	 * Test if the currency decrease.
	 */
	@Test
	public void test()
	{
		try {
			map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.currency=50;
		mob.map=map;
		mob.loseCoin();
		assertEquals(40,map.currency);
	}

}
