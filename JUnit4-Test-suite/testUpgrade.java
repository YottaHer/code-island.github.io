package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import TheGame.*;

/**
 * Test the upgrade method in Tower class.
 *
 */
public class testUpgrade {
	public Tower t=new Tower(new Point(0,0),0,10);
	
	/**
	 * Test the increase of firingRate after upgrade.
 	 */
	@Test
	public void test()
	{
		t.firingRate=500;
		t.upGrade();
		assertEquals(450,t.firingRate);
	}
	
	@Test
	public void test2()
	{
		t.power=80;
		t.upGrade();
		assertEquals(100,t.power);
	}

}
