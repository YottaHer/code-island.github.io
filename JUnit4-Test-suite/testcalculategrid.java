package Tests_v2;

import static org.junit.Assert.*;

import org.junit.Test;



import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import TheGame.Panels;


public class testcalculategrid {


	@Test
		public void testgrid() throws IOException {
			System.out.println("in the test 2");
			int height = 100;
			Panels entry = new Panels();
			entry.calculateGridPosition();
			entry.gridHeight = 200;
			assertFalse(height>entry.gridHeight);
	}

	
	@Test
	public void testgrid2() throws IOException {
		System.out.println("in the test");
	int width = 200;
	Panels entry = new Panels();
	entry.calculateGridPosition();
	entry.gridWidth = 100;
	assertTrue(width>entry.gridWidth);
}
	
}

