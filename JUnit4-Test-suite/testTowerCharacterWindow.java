package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 *Test testTowerCharacterWindow in Tower class. 
 *
 */
public class testTowerCharacterWindow {
	DrawMap map;
	
	/**
	 * Test if the focusedID is right.
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
		map.map[0][0]=10;
		map.map[0][1]=11;
		map.map[0][2]=12;
		map.t=new Tower[3];
		map.t[0]=new Tower(new Point(0,1),0,11);
		map.t[1]=new Tower(new Point(0,0),1,10);
		map.t[2]=new Tower(new Point(0,2),2,12);
		map.towerCount=3;
		map.towerCharacterWindow(new Point(20,20));
		assertEquals(1,map.focusedTowerID);
	}
	

}
