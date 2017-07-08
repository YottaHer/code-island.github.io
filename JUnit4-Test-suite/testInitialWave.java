package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test the initialWave method in Panels class.
 *
 */
public class testInitialWave {
	Panels panel;
	
	/**
	 * Test if the wavelevel is right.
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
		panel.waveLevel=2; 
		panel.map.map=new int [1][1];
		panel.waveInitial();
		assertEquals(3,panel.waveLevel);
	}

}
