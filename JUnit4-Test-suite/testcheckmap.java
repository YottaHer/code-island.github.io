package Tests_v2;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

import TheGame.DrawMap;


public class testcheckmap {

	@Test
	public void test() throws IOException {
		int xClick=-1;
		int yClick=-1;
		DrawMap d1 = new DrawMap();
		Point p = new Point();
		d1.check(p );
		assertEquals(xClick,yClick);
		assertTrue(xClick<1);
		assertFalse(!(xClick==yClick));

	}

}
