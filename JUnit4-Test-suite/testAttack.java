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
public class testAttack {
	Mob[] mobs=new Mob[3];
	Point e=new Point(0,0);
	Tower t=new Tower (e,0,0);
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
		mobs[1]=new Mob(map);
		mobs[2]=new Mob(map);
		mobs[0].x=0;
		mobs[0].y=1;
		mobs[1].x=1;
		mobs[1].y=0;
		mobs[2].x=0;
		mobs[2].y=0;
		mobs[0].step=1;
		mobs[1].step=2;
		mobs[2].step=3;
		mobs[0].health=100;
		mobs[1].health=200;
		mobs[2].health=300;
		mobs[0].inGame=true;
		mobs[1].inGame=true;
		mobs[2].inGame=true;
		t.firingRate=0;
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
	
		t.setStrategy(new Strongest());
		t.attack(mobs);
		assertEquals(20,Panels.hasAttack[0][2]);
	}
	
	/**
	 * Test if a mob is attack by setting nearTower strategy. 
	 */
	@Test
	public void test2()
	{
		t.setStrategy(new NearTower());
		t.attack(mobs);
		assertEquals(20,Panels.hasAttack[0][2]);
		
	}
	
	/**
	 * Test if a mob is attack by setting nearExit strategy. 
	 */
	@Test
	public void test3()
	{
		t.setStrategy(new NearExit());
		t.attack(mobs);
		assertEquals(20,Panels.hasAttack[0][2]);
		
	}

}