package Tests_v2;


import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test the removeTower method in DrawMap class. 
 */
public class testRemoveTower {
	DrawMap map;
	
	/**
	 * Test if a tower is removed.
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
		map.map=new int[3][3];
		map.t=new Tower[3];
		map.t[0]=new Tower(new Point(0,0),0,10);
		map.t[1]=new Tower(new Point(1,0),1,11);
		map.t[2]=new Tower(new Point(1,1),2,12);
		map.towerCount=3;
		map.removeTower(1);
		assertEquals(map.t[1].location,new Point(1,1));
		assertEquals(map.towerCount,2);
	}
	

}
