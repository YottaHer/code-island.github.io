package Tests_v2;

import static org.junit.Assert.*;
import TheGame.*;
import org.junit.*;
import java.awt.Point;
import java.io.IOException;

public class testSetStrategy {

	@Test
	public void test()
	{
		Tower t=new Tower(new Point(0,0), 0, 10);
		Strategy s=new Strongest();
		t.setStrategy(s);
		assertEquals(t.strategy,s);
	}
}
