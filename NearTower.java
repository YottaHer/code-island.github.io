/**
 * This class call the strategy of shooting the critter nearest the tower.
 *
 */
public class NearTower implements Strategy {
	/**
	 * The method to implement the nearTower strategy.
	 */
	public int findTarget(Mob[] mobs,Tower t)
	{
		int mindis=1000000000;
		int target=-1;
		for (int i=0;i<mobs.length;i++)
			if (mobs[i].inGame&&t.inRange(mobs[i]))
			{
				int dis=(mobs[i].x-t.location.x*DrawMap.BLOCKSIZE)*(mobs[i].x-t.location.x*DrawMap.BLOCKSIZE)+(mobs[i].y-t.location.y*DrawMap.BLOCKSIZE)*(mobs[i].y-t.location.y*DrawMap.BLOCKSIZE);
				if (dis<mindis)
				{
					target=i;
					mindis=dis;
				}
			}
		return target;
	}

}
