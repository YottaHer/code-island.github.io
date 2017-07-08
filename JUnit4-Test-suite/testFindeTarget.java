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
public class testFindeTarget {
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
		mobs[0].inGame=true;
		mobs[1].inGame=false;
		mobs[2].inGame=true;
	}
	
	/**
	 * Test if the class can find right target by setting strongest strategy.
	 */
	@Test
	public void test1()
	{
		f.setStrategy(new Strongest());
		assertEquals(2,f.findTarget(mobs, t));
	}
	

	/**
	 * Test if the class can find right target by setting nearExit strategy.
	 */
	@Test
	public void test2()
	{
		f.setStrategy(new NearExit());
		assertEquals(2,f.findTarget(mobs, t));
	}

}
