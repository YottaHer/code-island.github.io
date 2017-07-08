package Tests_v2;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import TheGame.*;

import org.junit.*;

/**
 * Test the freeze method in Mob class.
 *
 */
public class testMobFreeze {
	Mob mob=new Mob(null);
	
	/**
	 * Test if a mob become slower after being frozen. 
	 */
	@Test
	public void test()
	{
		mob.inGame=true;
		mob.health=160;
		mob.walkSpeed=4;
		mob.freezeLength=0;
		mob.freeze();
		assertSame(8,mob.walkSpeed);
		mob.freeze();
		assertSame(8,mob.walkSpeed);
	}
	
}
