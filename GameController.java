/**
 * Singleton pattern implementation for Game Controller
 * @author Dap
 *
 */
public class GameController {
	/**
	 * Creating a single object
	 */
	private static GameController instance= new GameController();
	// boolean variable
	public static boolean playStatus=false;
	/**
	 * private constructor
	 */
	private GameController(){}
	/**
	 * create an instance if it was not created
	 * @return
	 */
	public static GameController getInstance(){
		if (instance==null){
			instance=new GameController();
		}
		return instance;
	}
	/**
	 * method that sets game controls	
	 */
	
	public void setGame() {	
		//System.out.println("Game starting..."+ System.nanoTime());	
		playStatus = true;
		
	}
	
	
}
