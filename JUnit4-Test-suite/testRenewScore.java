package Tests_v2;

import static org.junit.Assert.*;
import TheGame.*;
import org.junit.*;
import java.awt.Point;
import java.io.IOException;

public class testRenewScore {

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
		for (int i=0;i<5;i++)
			map.score[i]=1;
		map.renewScore(2);
		assertEquals(2,map.score[0]);
	}
	
	@Test
	public void tes2()
	{
		try {
			map=new DrawMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0;i<5;i++)
			map.score[i]=1;
		map.renewScore(0);
		assertEquals(1,map.score[4]);
	}
}
