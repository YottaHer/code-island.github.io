package TheGame;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GameLog {
	
	String dateTime;
	public String description;
	public String type;
	public int waveNumber;
	public int elementID;
	
	public void CreateLog(String des, String type, int ID, int wave)
	{
		this.description = des;
		this.type = type;
		this.elementID = ID;
		this.waveNumber = wave;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTime = sdf.format(new Date());
		
	}
	
	public void displayTowerLog()
	{
		System.out.println(dateTime + " : " + type + " " + elementID + " : " + description );
	}
	
	public void displayWaveLog()
	{
		System.out.println(dateTime + " : " + type  + " : " + description+ " " + elementID );
	}

}
