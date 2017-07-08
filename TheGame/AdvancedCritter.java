package TheGame;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * The third type of critter.
 * @author Grp12
 *
 */
public class AdvancedCritter implements Critter {

	/**
	 * return Third type of critters
	 */
	public Image spawnCriter(int waveLevel) {
		// TODO Auto-generated method stub
		if (waveLevel==3){

			 Image image1=new ImageIcon("res/Critter3.png").getImage();
			 return image1;

		 }
		return null;
	}

}