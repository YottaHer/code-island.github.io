package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import TheGame.*;
import org.junit.*;

/**
 * Test the inRange class in Tower class.
 *
 */
public class testInRange {
	Tower t=new Tower(new Point(0,0),0,10);
	Mob[] mobs=new Mob[2];
	
	/**
	 * Test if the method can judge if a mob is in the range of a tower.
	 */
	@Test
	public void test()
	{
		t.range=1;
		mobs[0]=new Mob(null);
		mobs[0].x=0;
		mobs[0].y=DrawMap.BLOCKSIZE;
		assertTrue(t.inRange(mobs[0]));
		mobs[1]=new Mob(null);
		mobs[1].x=DrawMap.BLOCKSIZE;
		mobs[1].y=DrawMap.BLOCKSIZE;
		assertFalse(t.inRange(mobs[1]));
	}
}
