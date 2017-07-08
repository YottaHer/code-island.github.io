/**
 * This class call the strategy of shooting the weakest.
 *
 */
public class Weakest implements Strategy {
	/**
	 * The method to implement weakest strategy.
	 */
	public int findTarget(Mob[] mobs,Tower t)
	{
		int minhealth=1000000000;
		int target=-1;
		for (int i=0;i<mobs.length;i++)
		if (mobs[i].inGame&&t.inRange(mobs[i]))
		{
			if (mobs[i].health<minhealth)
			{
				target=i;
				minhealth=mobs[i].health;
			}
		}
		return target;
	}

}
