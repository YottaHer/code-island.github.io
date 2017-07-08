package TheGame;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The second type of critter.
 * @author Grp12
 *
 */
public class BasicCritter implements Critter {

	/**
	 * return First type of critters
	 */
	
	public Image spawnCriter(int waveLevel) {
		// TODO Auto-generated method stub
		
		 if (waveLevel==1){

			 Image image1=new ImageIcon("res/mob.png").getImage();
			 return image1;

		 }
		return null;
			
	}

}
