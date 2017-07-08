package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;

import TheGame.*;
import org.junit.*;

/**
 * Test attack method in Tower class.
 *
 */
public class testTowerAttack2 {
	Mob[] mobs=new Mob[1];
	Point e=new Point(0,0);
	Tower t=new Tower (e,0,10);
	DrawMap map;
	
	@Before
	public void initial()
	{
		try {
			map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mobs[0]=new Mob(map);
		mobs[0].x=0;
		mobs[0].y=0;
		mobs[0].step=1;
		mobs[0].health=100;
		mobs[0].inGame=true;
		t.firingRate=10;
		t.power=80;
		t.range=1;
		t.towerID=0;
	}
	
	/**
	 * Test if a mob is attack by setting strongest strategy. 
	 */
	@Test
	public void test1()
	{
	
		for (int i=0;i<=10;i++)
		{
			t.attack(mobs);
			
		}
		assertEquals(20,Panels.hasAttack[0][0]);
	}
	


}