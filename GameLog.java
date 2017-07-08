import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * This class is used to create the log. Each instance of this class stores a single log entry.
 * @author TOSHIBA
 *
 */
public class GameLog {
	
	String dateTime;
	public String description;
	public String type;
	public int waveNumber;
	public int elementID;
	
	/**
	 * Constructor of the GameLog class creates a log entry.
	 * @param des	description of the log entry
	 * @param type	Typer of the log entry. weather it is a tower log or a gameplay log
	 * @param ID	ID of the tower or the critter for which this log entry is created
	 * @param wave	The wave number to which this entry corresponds to.
	 */
	public void CreateLog(String des, String type, int ID, int wave)
	{
		this.description = des;
		this.type = type;
		this.elementID = ID;
		this.waveNumber = wave;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTime = sdf.format(new Date());
		
	}
	
	/**
	 * This function displays the log of a tower. To display collective log, this function is called for all the instances of the class
	 */
	public void displayTowerLog()
	{
		System.out.println(dateTime + " : " + type + " " + elementID + " : " + description );
	}
	
	/**
	 * This method displays the log related to a specific wave of the critters. This function just provides a different display format then the pervious one
	 */
	public void displayWaveLog()
	{
		System.out.println(dateTime + " : " + type  + " : " + description+ " " + elementID );
	}

}
