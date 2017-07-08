package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test checkTowerPosition in DrawMap class.
 *
 */
public class testCheckTowerPosition {
	DrawMap map;
	
	/**
	 * Test if the method can judge if a tower can be put at some place.
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
		map.map=new int[3][3];
		map.map[0][0]=0;
		assertFalse(map.checkTowerPositionValidity(new Point(0,0),10));
		map.map[0][1]=1;
		assertTrue(map.checkTowerPositionValidity(new Point(0,1),10));
	}

}
