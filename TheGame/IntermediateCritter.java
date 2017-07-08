package TheGame;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The second type of the critter.
 * @author Grp12
 *
 */
public class IntermediateCritter implements Critter {
/**
 * return second type of critters
 */
	
	public Image spawnCriter(int waveLevel) {
		// TODO Auto-generated method stub
		if (waveLevel==2){

			 Image image1=new ImageIcon("res/Critter2.png").getImage();
			 return image1;

		 }
		return null;
		
	}

}
