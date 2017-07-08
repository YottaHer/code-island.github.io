package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test hurt method in Mob class.
 *
 */
public class testMobHurt {
	Mob mob=new Mob(null);
	
	
	/**
	 * Test if the mob is alive after being hurt.
	 */
	@Test
	public void test()
	{
		mob.inGame=true;
		mob.health=160;
		try {
			mob.map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mob.hurt(80);
		assertTrue(mob.inGame);
		mob.hurt(80);
		assertFalse(mob.inGame);
	}

}