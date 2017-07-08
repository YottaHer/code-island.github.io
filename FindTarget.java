/**
 * The interface to call strategy
 *
 */
public class FindTarget {
	private Strategy strategy;
	
	/**
	 * Set the strategy.
	 * @param s The strategy planed to set.
	 */
	public void setStrategy(Strategy s)
	{
		strategy=s;
	}
	
	/**
	 * Interface to call the strategy.
	 * @param mobs The critter.
	 * @param t The tower whose strategy is being set.
	 * @return The ID of the target critter.
	 */
	public int findTarget(Mob[] mobs,Tower t)
	{
		return strategy.findTarget(mobs, t);
	}

}
