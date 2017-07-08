package Tests_v2;

import static org.junit.Assert.*;
import TheGame.*;

import org.junit.*;
import java.awt.Point;
import java.io.IOException;

/**
 * Test strategy pattern.
 *
 */
public class testFindTarget2 {
	FindTarget f=new FindTarget();
	Mob[] mobs=new Mob[3];
	Point e=new Point(1,2);
	Tower t=new Tower (e,0,0);
	
	
	@Before
	public void initial()
	{
		DrawMap map =null;
		try {
			map = new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mobs[0]=new Mob(map);
		mobs[1]=new Mob(map);
		mobs[2]=new Mob(map);
		mobs[0].health=1;
		mobs[1].health=5;
		mobs[2].health=3;
		mobs[0].step=1;
		mobs[1].step=3;
		mobs[2].step=2;
		mobs[0].x=DrawMap.BLOCKSIZE;
		mobs[0].y=DrawMap.BLOCKSIZE*2;
		mobs[1].x=0;
		mobs[1].y=0;
		mobs[2].x=0;
		mobs[2].y=DrawMap.BLOCKSIZE;
		mobs[0].inGame=true;
		mobs[1].inGame=false;
		mobs[2].inGame=true;
	}
	
	/**
	 * Test if the strategy can find the right target by setting nearTower strategy.
	 */
	@Test
	public void test1()
	{
		f.setStrategy(new NearTower());
		assertEquals(0,f.findTarget(mobs, t));
	}
	
	/**
	 * Test if no target is found if no mob is alve.
	 */
	@Test
	public void test2()
	{
		mobs[0].inGame=false;
		mobs[2].inGame=false;
		f.setStrategy(new NearExit());
		assertEquals(-1,f.findTarget(mobs, t));
	}

}
