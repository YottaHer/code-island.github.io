package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;

import org.junit.*;

public class testCheckTowerPresence {
	DrawMap map;
	@Test
	public void test()
	{
		try {
			map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.map=new int[2][2];
		map.map[0][0]=12;
		assertTrue(map.checkTowerPresence(new Point(0,0)));
	}

}
