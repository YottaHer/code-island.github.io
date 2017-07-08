package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import TheGame.*;
import org.junit.*;

/**
 * Test strategy to attack critter nearest the tower.
 */
public class testNearTower {
	Mob[] mobs=new Mob[3];
	NearExit s=new NearExit();
	Point e=new Point(1,2);
	Tower t=new Tower (e,0,0);
	
	/**
	 * Test if the tower attack the nearest one.
	 */
	@Test
	public void testFindTarget()
	{
		mobs[0]=new Mob(null);
		mobs[1]=new Mob(null);
		mobs[2]=new Mob(null);
		mobs[0].x=1;
		mobs[0].y=1;
		mobs[1].x=0;
		mobs[1].y=0;
		mobs[0].x=0;
		mobs[1].y=1;
		mobs[0].inGame=true;
		mobs[1].inGame=false;
		mobs[2].inGame=true;
		assertEquals(0,s.findTarget(mobs, t));
		
	}

}
