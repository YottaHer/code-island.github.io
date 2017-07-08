package TheGame;
/**
 * This class call the strategy of shooting the critter nearest to exit.
 *
 */
public class NearExit implements Strategy {
	/**
	 * The method to implement nearExit strategy.
	 */
	public int findTarget(Mob[] mobs,Tower t)
	{
		int maxstep=-1;
		int target=-1;
		for (int i=0;i<mobs.length;i++)
		if (mobs[i].inGame&&t.inRange(mobs[i]))
		{
			if (mobs[i].step>maxstep)
			{
				target=i;
				maxstep=mobs[i].step;
			}
		}
		return target;
	}

}
