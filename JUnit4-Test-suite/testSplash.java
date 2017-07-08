package Tests_v2;

/**
 * Test the effect of splash.
 */
import static org.junit.Assert.*;
import java.awt.Point;
import TheGame.*;
import org.junit.*;

public class testSplash {
	Tower t=new Tower(new Point(0,0),0,10);
	Mob[] mobs=new Mob[2];
	
	/**
	 * Test if mobs hurt by splash.
	 */
	@Test
	public void test()
	{
		t.range=1;
		t.firingRate=0;
		mobs[0]=new Mob(null);
		mobs[0].x=0;
		mobs[0].y=0;
		mobs[1]=new Mob(null);
		mobs[1].x=0;
		mobs[1].y=DrawMap.BLOCKSIZE;
		mobs[0].inGame=true;
		mobs[1].inGame=true;
		mobs[0].health=mobs[1].health=160;
		t.attack(mobs);
		assertTrue(mobs[1].health<160);
	}
}