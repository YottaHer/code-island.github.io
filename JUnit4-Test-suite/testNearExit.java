package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import TheGame.*;
import org.junit.*;

/**
 * test strategy to attack critter nearest exit
 *
 */
public class testNearExit {
	Mob[] mobs=new Mob[3];
	NearExit s=new NearExit();
	Point e=new Point(1,2);
	Tower t=new Tower (e,0,0);
	
	/**
	 * Test if the tower attack the one closed to exit.
	 */
	@Test
	public void testFindTarget()
	{
		mobs[0]=new Mob(null);
		mobs[1]=new Mob(null);
		mobs[2]=new Mob(null);
		mobs[0].x=1;
		mobs[0].step=1;
		mobs[1].step=3;
		mobs[2].step=2;
		mobs[0].inGame=true;
		mobs[1].inGame=false;
		mobs[2].inGame=true;
		assertEquals(2,s.findTarget(mobs, t));
		
	}

}
