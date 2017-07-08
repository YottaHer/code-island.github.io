package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import TheGame.*;
import org.junit.*;

/**
 * Test strategy to attack critter with most health.
 *
 */
public class testWeakest {
	Mob[] mobs=new Mob[3];
	Weakest s=new Weakest();
	Point e=new Point(1,2);
	Tower t=new Tower (e,0,0);
	
	/**
	 * Test if the tower attack the strongest critter.
	 */
	@Test
	public void test()
	{
		mobs[0]=new Mob(null);
		mobs[1]=new Mob(null);
		mobs[2]=new Mob(null);
		mobs[0].health=1;
		mobs[1].health=5;
		mobs[2].health=3;
		mobs[0].inGame=true;
		mobs[1].inGame=false;
		mobs[2].inGame=true;
		assertEquals(0,s.findTarget(mobs, t));
		
	}
	

}