package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test if the currency increase after killing a mob.
 *
 */
public class testCurrencyInc {
	DrawMap map;
	Mob mob=new Mob(map);
	
	/**
	 * Test the increase of currency.
	 */
	@Test
	public void test()
	{
		try {
			map=new DrawMap();
			map.columns=50;
			mob.map=map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mob.health=160;
		mob.hurt(80);
		assertEquals(50,map.currency);
		mob.hurt(80);
		assertEquals(55,map.currency);
	}

}
