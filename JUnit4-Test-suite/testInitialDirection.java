package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;

import org.junit.*;

/**
 * Test if a mob can get right direction at beginning.
 *
 */
public class testInitialDirection {
	DrawMap map;
	Mob mob=new Mob(map);
	
	/**
	 * Test if a mob find right direction. 
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
		int map2[][]={{0,1,1},{0,0,1},{1,0,1,}};
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
					DrawMap.map[i][j]=map2[i][j];
		
		map.entryX=0;
		map.entryY=0;
		map.exitX=2;
		map.exitY=1;
		mob.map=map;
		mob.spawnMob();
		assertEquals(mob.right,mob.direction);
		
			
			
	}	

}
