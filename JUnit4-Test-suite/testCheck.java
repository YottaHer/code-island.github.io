package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

public class testCheck {
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
		map.check(new Point(20,20));
		assertEquals(map.xClick,0);
		assertEquals(map.yClick,0);
	}

}
