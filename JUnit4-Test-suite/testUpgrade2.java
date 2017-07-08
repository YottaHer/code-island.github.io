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
public class testUpgrade2 {
	public Tower t=new Tower(new Point(0,0),0,10);
	
	/**
	 * Test the increase of firingRate after upgrade.
 	 */
	@Test
	public void test()
	{
		t.range=4;
		t.upGrade();
		assertEquals(6,t.range);
	}
	
	@Test
	public void test2()
	{
		t.refundAmount=3;
		t.upGrade();
		assertEquals(6,t.refundAmount);
	}

}
