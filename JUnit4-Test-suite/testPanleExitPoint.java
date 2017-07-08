package Tests_v2;

import static org.junit.Assert.*;
import TheGame.*;
import org.junit.*;
import java.awt.Point;
import java.io.IOException;

public class testPanleExitPoint {
	@Test
	public void test()
	{
		Panels panel = null;
		try {
			panel=new Panels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		panel.defineExit();
		assertTrue(panel.defineExitPoint);
		
	}

}
