/**
 * The strategy interface 
 */
public interface Strategy {
	/**
	 * 
	 * @param mobs The critters.
	 * @param t The tower whose strategy is being set.
	 * @return The ID of target critter.
	 */
	public int findTarget(Mob[] mobs,Tower t);


}
