package Tests_v2;

import static org.junit.Assert.*;
import java.awt.Point;
import java.io.IOException;
import TheGame.*;

import org.junit.*;

/**
 * Test mob's walk.
 *
 */
public class testMobWalk {
	DrawMap map;
	Mob mob=new Mob(map);
	
	/**
	 * Test if the mob get to exit.
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
		mob.walkSpeed=4;
		for (int i=0;i<16*DrawMap.BLOCKSIZE;i++)
			mob.physic();
		assertEquals(2,mob.xC);
		assertEquals(1,mob.yC);
		
			
			
	}	

}
