package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test the calculateGridPosition in Panels class.
 *
 */
public class testCalculateGridPositon {
	Panels panel;
	
	/**
	 * Test the method can get right coordinate of the grid.
	 */
	@Test
	public void test()
	{
		try {
			panel=new Panels();
			panel.map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.map.rows=10;
		panel.map.columns=10;
		panel.calculateGridPosition();
		assertEquals(DrawMap.BLOCKSIZE*10,panel.gridHeight);
		assertEquals(DrawMap.BLOCKSIZE*10,panel.gridWidth);
	}

}
