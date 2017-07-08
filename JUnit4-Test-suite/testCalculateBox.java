package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;

import TheGame.*;
import org.junit.*;

/**
 * Test the calculateBox method in map class.
 *
 */
public class testCalculateBox {
	DrawMap map;
	
	/**
	 * Test the method return right block.
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
		assertEquals(new Point(1,1),map.calculateBox(new Point(60,60)));
	}

}
