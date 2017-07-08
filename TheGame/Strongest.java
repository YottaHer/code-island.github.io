package TheGame;
/**
 * This class call the strategy of shooting the strongest.
 *
 */
public class Strongest implements Strategy {
	/**
	 * The method to implement strongest strategy.
	 */
	public int findTarget(Mob[] mobs,Tower t)
	{
		int maxhealth=-1;
		int target=-1;
		for (int i=0;i<mobs.length;i++)
		if (mobs[i].inGame&&t.inRange(mobs[i]))
		{
			if (mobs[i].health>maxhealth)
			{
				target=i;
				maxhealth=mobs[i].health;
			}
		}
		return target;
	}

}
