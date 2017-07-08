package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

public class testCheckValid {
	DrawMap map;
	
	@Test
	public void test()
	{
		try {
			map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DrawMap.map=new int[3][3];
		map.rows=3;
		map.columns=3;
		int map2[][]={{0,1,1},{0,1,0},{1,1,1,}};
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
					DrawMap.map[i][j]=map2[i][j];
		assertFalse(map.checkIfValid());
		map.defineEntryPoint(new Point(20,20));
		assertFalse(map.checkIfValid());
		map.defineExitPoint(new Point(100,60));
		assertFalse(map.checkIfValid());
		map.map[1][1]=0;
		assertTrue(map.checkIfValid());
		
	}		

}
