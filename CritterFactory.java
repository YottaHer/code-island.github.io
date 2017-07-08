

/**
 * 
 */

/**
 * The crtter factory.
 * @author Dap
 *
 */
public class CritterFactory {

	/**
	 * The constructor of of critter factory. 
	 */
	private CritterFactory(){};

	//private static int  waveLevel;
	static final int BasicCritter=1;
	static final int IntermediateCritter=2;
	static final int AdvancedCritter=3;
	/* (non-Javadoc)
	 * @see Critter#spawnCriter()
	 */
	
	/**
	 * The interface of the method to create the critter.
	 */
	static public Critter createCritter(int waveLevel) {
		// TODO Auto-generated method stub
		switch( waveLevel){
		case BasicCritter: 
			return new BasicCritter();
		
		case IntermediateCritter:
			return new IntermediateCritter();
		
		case AdvancedCritter: 
			return new AdvancedCritter();
		}
	
		return null;	
		
	}

}
