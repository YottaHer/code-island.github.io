package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;
import org.junit.*;

/**
 * Test defineExit method in map class.
 *
 */
public class testDefineExit{
	DrawMap map;
	
	/**
	 * Test if the method can judge exit can be put some place.
	 */
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
		int map2[][]={{0,1,1},{0,1,0},{1,0,1,}};
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
					DrawMap.map[i][j]=map2[i][j];
		map.defineExitPoint(new Point(20,20));
		assertEquals(0,map.map[0][0]);
		map.defineExitPoint(new Point(100,60));
		assertEquals(4,map.map[2][1]);
	}
}