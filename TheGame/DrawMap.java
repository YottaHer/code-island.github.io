package TheGame;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.Date;


/**
 * The class represents the map of the game.
 *
 */
public class DrawMap extends Observable{
	
	public static int entryX, entryY;								//position of the entry BLOCK
	public static int[][] map;
	public static int BLOCKSIZE=40;								//Since the block is square height=width=size
	public Tower[] t;
	public int towerCount;
	public int focusedTowerID;
	public int currency;
	public int rows;
	public int columns;
	public JOptionPane inputDialogue;
	public boolean isEntryDefined;
	public boolean isExitDefined;
	public int exitX, exitY;	
	public int score[]=new int[5];
	public String log[]=new String[1000];
	public int logNum;										//The number of logs in map log.
	
	public static GameLog[] gameLog = new GameLog[100];
	public static int logCount = 0;
	
	private String fileName;
	private boolean newMapCreated;
	public int xClick;
	public int yClick;								
	private boolean click;									//Set true when mouse is pressed for dragging
	private boolean saveFlag=false;
	public static boolean saveMapCompleted=false;
	public static boolean loadMapCompleted=false;
	public static String namevar;  // store file name 
	
	/**
	 * The constructor of the map.
	 * @throws IOException
	 */
	public DrawMap() throws IOException
	{
		for(int i=0;i<100;i++)
			gameLog[i] = new GameLog();
		
		this.currency = 50;
		this.focusedTowerID = -1;
		t = new Tower[25];
		inputDialogue = new JOptionPane();
		newMapCreated = false;
		entryX = entryY = -1;
		exitX = exitY = -1;
		isEntryDefined = false;
		isExitDefined = false;
		rows = 0;
		columns = 0;
		BLOCKSIZE = 40;
		for (int i=0;i<5;i++)
			score[i]=0;
		logNum=0;
	}
	
	/**
	 * Place a tower on the map.
	 * @param e The location of the mouse.
	 * @param tType The type of the tower.
	 */
	public void placeTower(Point e, int tType)							//Place tower on the map
	{	
		Point p = new Point(calculateBox(e));
		if(checkTowerPositionValidity(p, tType))
		{
			this.t[towerCount] = new Tower(p, towerCount, tType);
			gameLog[logCount].CreateLog("Tower Placed", "Tower", towerCount, (Panels.waveLevel + 1));
			logCount++;
			towerCount++;
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Remove a tower from map.
	 * @param tid The index of the tower removed.
	 */
	public void removeTower(int tid)									//remove Tower on the map
	{
		if(tid >= 0)
		{
			map[t[tid].location.x][t[tid].location.y] = 1;
			gameLog[logCount].CreateLog("Tower Removed", "Tower", tid, (Panels.waveLevel + 1));
			logCount++;
			currency += t[tid].refundAmount;
				while((tid + 2) <= towerCount)
				{
					t[tid].removeTower(t[++tid]);
				}
				towerCount--;
				focusedTowerID = -1;
				this.setChanged();
				this.notifyObservers();
		}
	}
	
	/**
	 * Check the presence of tower on the location.
	 * @param e The location of the tower.
	 * @return The check result.
	 */
	public boolean checkTowerPresence(Point e)						//Check weather a tower is present on a block.
	{
		if((map[e.x][e.y] == 12) || (map[e.x][e.y] == 11) || (map[e.x][e.y] == 12))
			return true;
		else
			return false;
	}
	
	/**
	 * The reaction to the drag of mouse.
	 * @param p The location of the mouse.
	 */
	public void dragDraw(Point p)								//draws map when mouse is dragged on the grid
	{
		int xBlock = -1, yBlock = -1;
		Point e = calculateBox(p);
		
		xBlock = e.x;
		yBlock = e.y;

		if( xClick == xBlock && yClick == yBlock)
		{		
				if(!click)
				{
					if(map[xBlock][yBlock] == 1)
					{
						map[xBlock][yBlock] = 0;
					}
					else if(map[xBlock][yBlock] == 0)
					{
						map[xBlock][yBlock] = 1;
					}
					click = true;
					
				}		
		}
		else
		{
			check(p);
			click = false;
		}
	}
	
	/**
	 * Get the row and column of the mouse.
	 * @param p The row and column.
	 */
	public void check(Point p)									//calculates the block position where mouse is clicked for dragging
	{
		this.click = false;
		xClick = yClick = -1;
		Point e = calculateBox(p);
		xClick = e.x;
		yClick = e.y;
	}
	
	/**
	 * Add exit on the mapp.
	 * @param p The location of the mouse.
	 */
	public void defineExitPoint(Point p)						//define a new exit point
	{
		Point e = new Point();
		e = calculateBox(p);
		if(isExitDefined)										//Neutralize the previously defined exit Point if any
			map[exitX][exitY] = 1;
		
		if( e.x == (rows - 1) )
		{
			map[e.x][e.y] = 4;
			exitX = e.x;
			exitY = e.y;
			isExitDefined = true;
		}
	}
	
	/**
	 * Add entrance to the map.
	 * @param p The location of the mouse.
	 */
	public void defineEntryPoint(Point p)						//define new entry point
	{
		
		Point e = new Point();
		e = calculateBox(p);
		if(isEntryDefined)
			map[entryX][entryY] = 1;							//Neutralize the previously defined entry Point if any
		
		if(   (e.x == 0  ) )
		{
			map[e.x][e.y] = 3;
			entryX = e.x;
			entryY = e.y;
			isEntryDefined = true;
		}
		
	}
	
	/**
	 * Get the row and column of the mouse.
	 * @param p The location of the mouse.
	 * @return The row and column of the mouse.
	 */
	public Point calculateBox(Point p)								//Simply Calculates the block number from pixel co-ordinates
	{
		int xBlock = -1, yBlock = -1;
		int xPosition = p.x;
		int yPosition = p.y;
		
		while(xPosition>0)
		{
			xPosition -= BLOCKSIZE;
			xBlock++;
		}
		while(yPosition>0)
		{
			yPosition -= BLOCKSIZE;
			yBlock++;
		}
		
		Point p2 = new Point(xBlock, yBlock); 
		
		return p2;
		
	}
	
	/**
	 * Create a new map.
	 */
	public void createNewMap()
	{
		
		towerCount = 0;
		String selection1 = (String)JOptionPane.showInputDialog(inputDialogue, "Enter Rwos");
		
		String selection2 = (String)JOptionPane.showInputDialog(inputDialogue, "Enter Columns");
		
		this.currency = 50;
		
		
		map = new int[Integer.parseInt(selection2)][Integer.parseInt(selection1)];
		columns = Integer.parseInt(selection1);
		rows = Integer.parseInt(selection2);
		for(int x=0;x<rows;x++)
			for(int y=0;y<columns;y++)
				map[x][y] = 1;
		newMapCreated = true;
		isEntryDefined = isExitDefined = false;
		for (int i=0;i<5;i++)
			score[i]=0;
		logNum=0;
		log[logNum++]=getTime()+(" Map Was Created");
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Save the map.
	 * @throws IOException
	 */
	public void saveMap() throws IOException
	{
		if(checkIfValid())
		{
			if(newMapCreated)
			{
				fileName = JOptionPane.showInputDialog("Enter Map Name: ");
			}
			saveData();
		}
		else
			JOptionPane.showMessageDialog(null, "Map invalid", null, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Load a saved map.
	 * @throws IOException
	 */
	public void loadMap() throws IOException										
	{	
		File file = new File(System.getProperty("user.dir"));
		int returnValue = 0;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(file);
		chooser.showOpenDialog(null);
		if( returnValue == JFileChooser.APPROVE_OPTION ) {
				file = chooser.getSelectedFile() ;
				fileName = file.getName();
				namevar=fileName;
		}
		getData();
		log[logNum++]=getTime()+(" Map Was Edited");
		this.setChanged();
		this.notifyObservers();
		loadMapCompleted=true;
	}
	
	/**
	 * Start a game of a map.
	 * @throws IOException
	 */
	public void playMap() throws IOException										
	{	
		File file = new File(System.getProperty("user.dir"));
		int returnValue = 0;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(file);
		chooser.showOpenDialog(null);
		if( returnValue == JFileChooser.APPROVE_OPTION ) {
				file = chooser.getSelectedFile() ;
				fileName = file.getName();
		}
		getData();
		log[logNum++]=getTime()+(" Map Was Played");
		saveData();
		this.setChanged();
		this.notifyObservers();
		
	}
	
	/**
	 * Load the saved towers.
	 */
	public void loadTowers()
	{
		towerCount = 0;
		for(int i=0;i<this.rows;i++)
			for(int j = 0;j<this.columns;j++)
			{
				
				if(map[i][j] == 10)
				{
					//System.out.println(i + " : " + j);
					t[towerCount] = new Tower(new Point(i,j), towerCount, 10);
					towerCount++;
				}
				else if(map[i][j] == 11)
				{
					//System.out.println(i + " : " + j);
					t[towerCount] = new Tower(new Point(i,j), towerCount, 11);
					towerCount++;
				}
				else if(map[i][j] == 12)
				{
					//System.out.println(i + " : " + j);
					t[towerCount] = new Tower(new Point(i,j), towerCount, 12);
					towerCount++;
				}
			}
	}
	
	/**
	 * Upgrade the tower.
	 * @param tid The id of the tower.
	 */
	public void upgradeTower(int tid)
	{
		if(tid >= 0)
		{
			if(t[tid].upgradeCost > currency)
			{
				JOptionPane.showMessageDialog(null, "Not Enough Money", null, JOptionPane.ERROR_MESSAGE);
				return;
			}
			currency -= t[tid].upgradeCost;
			t[tid].upGrade();
			gameLog[logCount].CreateLog("Tower Upgraded", "Tower", tid, (Panels.waveLevel + 1));
			logCount++;
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Check if the map is valid.
	 * @return The check result.
	 */
	public boolean checkIfValid()									//This function checks to see weather the map is valid or not before saving
	{
		if(!isEntryDefined)
		{
			JOptionPane.showMessageDialog(null, "Entry Point Not Defined", null, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!isExitDefined)
		{
			JOptionPane.showMessageDialog(null, "Exit Point Not Defined", null, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(isEntryDefined && isExitDefined)
		{
			
			
			
		
			int startX = entryX;
			int startY = entryY;
			
		
			char previousBlock = 'k';
		
			while(map[startX][startY] != 4)
			{
				
				
				
				
				
				
					if( (startX != (rows - 1)) && ( (map[startX + 1][startY] == 0) || (map[startX + 1][startY] == 4) ) && (previousBlock != 'd'))
					{
						
							startX++;
							previousBlock = 'a';
							
							
						
						
						
					}
					
					else if( (startY != (columns - 1)) && (previousBlock != 's') && ( (map[startX][startY + 1] == 0) || (map[startX][startY + 1] == 4) ) )
					{
						
							startY++;
							previousBlock = 'w';
							
						
						
					}
					
					else if( (startX != 0) && (previousBlock != 'k') && (map[startX - 1][startY] == 0) && (previousBlock != 'a'))
					{
						
							startX--;
							previousBlock = 'd';
							
							
					}
					
					else if( (startY != 0) && (previousBlock != 'w') && ( (map[startX][startY - 1] == 0) || (map[startX][startY - 1] == 4) ) )
					{
						
						
							startY--;
							previousBlock = 's';
							
							
						
						
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Path between entry and exit point", null, JOptionPane.ERROR_MESSAGE);
						return false;
					}
					
			}
			return true;
		}
				return false;
	}
	
	/**
	 * Check if the position is valid to put a tower.
	 * @param e The locaion of the mouse.
	 * @param tType The type of the tower.
	 * @return The check result.
	 */
	public boolean checkTowerPositionValidity(Point e, int tType)
	{
		if( (e.x == entryX && e.y == entryY) || (e.x == exitX && e.y == exitY) || (map[e.x][e.y] != 1) )
		{
			JOptionPane.showMessageDialog(null, "Ivalid Block", null, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if((tType == 10))
		{
			if(currency < Tower.cTowerCost)
			{
				JOptionPane.showMessageDialog(null, "Not Enough Money", null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
				currency -= 25;
		}
		else if((tType == 11))
		{
			if(currency < Tower.hTowerCost)
			{
				JOptionPane.showMessageDialog(null, "Not Enough Money", null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
				currency -= 10;
		}
		else if((tType == 12))
		{
			if(currency < Tower.vTowerCost)
			{
				JOptionPane.showMessageDialog(null, "Not Enough Money", null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
				currency -= 10;
		}
			map[e.x][e.y] = tType;									//Lowest grade Tower. High grade towers have values 11, 12, 13, 14, 15 respectively
			return true;
		
	}
	
	/**
	 * Change the focused tower after clicking the map.
	 * @param e The location of the mouse.
	 */
	public void towerCharacterWindow(Point e)
	{
		Point ee = new Point(this.calculateBox(e));
		if((map[ee.x][ee.y] == 10) || (map[ee.x][ee.y] == 11) || (map[ee.x][ee.y] == 12))
		{
			int i = 0;
			for(i=0;i<towerCount;i++)
			{
				if( (this.t[i].location.x == ee.x) && (this.t[i].location.y == ee.y) )
				{
					this.focusedTowerID = t[i].towerID;
					this.setChanged();
					this.notifyObservers();
					break;
				}
			}
			
		}
	}
	
	/**
	 * Lost coin when a critter reach exit.
	 */
	public void loseCoin()
	{
		currency-=10;
		setChanged();
		notifyObservers();
		
	}
	
	/**
	 * Let the towers attack critters.
	 * @param mobs The critters.
	 */
	public void attack(Mob[] mobs)
	{
		//System.out.println("attack");
		for (int i=0;i<towerCount;i++)
			t[i].attack(mobs);
	}
	
	/**
	 * Notify observers the change.
	 */
	public void change()
	{
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Set strategy for a selected tower.
	 * @param ID The ID of selected tower.
	 * @param s
	 */
	public void setStrategy(int ID,Strategy s)
	{
		t[ID].setStrategy(s);
	}
	

	/**
	 * Save the game.
	 * @throws IOException
	 */
	public void saveGame() throws IOException
	{
		{
			fileName = JOptionPane.showInputDialog("Enter Game Name: ");
			namevar=fileName;
		}
		if(fileName != null)
		{
			
				{
					//GameController obj1 = GameController.getInstance();
					
					FileOutputStream out = new FileOutputStream(fileName);
					//out.write(Integer.parseInt(obj1.toString()));
					out.write(Panels.waveLevel);
					
					out.write(rows);
					out.write(columns);
					out.write(currency);
					for(int i=0;i<rows;i++)
						for(int j=0;j<columns;j++)
						{
							out.write(map[i][j]);
						}
					out.close();
				}
			}
	  	}


	
	/**
	 * load the game.
	 * @param s The screen of the game.
	 * @throws IOException
	 */

	public void loadGame(Screen s) throws IOException
	{
		{	
			File file = new File(System.getProperty("user.dir"));
			int returnValue = 0;
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(file);
			chooser.showOpenDialog(null);
			if( returnValue == JFileChooser.APPROVE_OPTION ) {
					file = chooser.getSelectedFile() ;
					fileName = file.getName();
					namevar=fileName;
			}
			
			FileInputStream in = new FileInputStream(this.fileName);
			
			Panels.waveLevel=in.read();
			
			rows = in.read();
			columns = in.read();
			currency = in.read();
			map = new int[rows][columns];
			for(int i=0;i<rows;i++)
				for(int j=0;j<columns;j++)
				{
					map[i][j] = in.read();
					if(map[i][j] == 3)
					{
						isEntryDefined=true;
						entryX = i;
						 entryY = j;
						 
					}
					else if(map[i][j] == 4)
					{
						isExitDefined=true;
						exitX = i;
						exitY = j;
					}
				}
			in.close();
			
			//isEntryDefined = true;
			//isExitDefined = true;
			
			loadTowers();
			change();
			s.changeButton();
		}
	}
	
	/**
	 * Get current time.
	 * @return The current time.
	 */
	public String getTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * Get data from map file.
	 * @throws IOException
	 */
	public void getData() throws IOException
	{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.fileName));
		this.rows = in.read();
		this.columns = in.read();
		//System.out.println(rows);
		//System.out.println(columns);
		//this.currency = in.read();
		DrawMap.map = new int[rows][columns];
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
			{
				map[i][j] = in.read();
				if(map[i][j] == 3) 
				{
					entryX = i;
					entryY = j;
					isEntryDefined = true;
				}
				else if(map[i][j] == 4)
				{
					exitX = i;
					exitY = j;
					
				}
			}
		for (int i=0;i<5;i++)
			score[i]=in.readInt();
		logNum=in.readInt();
		for (int i=0;i<logNum;i++)
		{
			log[i]=in.readUTF();
			System.out.println(log[i]);
		}
		in.close();
		isEntryDefined = true;
		isExitDefined = true;
		
		loadTowers();
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Save data to map file.
	 * @throws IOException
	 */
	public void saveData() throws IOException
	{
		if(fileName != null)
		{
			saveMapCompleted=true;
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			namevar=fileName;
			
			out.write(this.rows);
			out.write(columns);
			//out.write(this.currency);
			for(int i=0;i<rows;i++)
				for(int j=0;j<columns;j++)
				{
				if (map[i][j]!=3&&map[i][j]!=4&&map[i][j]!=0)
					out.write(1);
				else
					out.write(map[i][j]);
				}
			
			for (int i=0;i<5;i++)
				out.writeInt(score[i]);
			out.writeInt(logNum);
			for (int i=0;i<logNum;i++)
			{
				out.writeUTF(log[i]);
			}
			out.close();
			
		}
	}
	
	/**
	 * Renew highest score.
	 * @param newscore The new score.
	 */
	public void renewScore(int newscore)
	{
		int i;
		for (i=0;i<5;i++)
			if (score[i]<newscore)
				break;
		for (int j=4;j>i;j--)
			score[j]=score[j-1];
		if (i<5)
		score[i]=newscore;
		change();
	}
	
/**
 * This method is called to display the log of a specific tower selected by the user	
 * @param id is the id of the tower whose log the user wants to view
 */
	public void viewIndividualTowerLog(int id)
	{
		
		
		System.out.println("");
		System.out.println("*****************************************");
		System.out.println("              Tower " + id +" log              ");
		System.out.println("*****************************************");
		
	
		for(int i=0;i<logCount;i++)
		{
			if(gameLog[i].elementID == id)
				gameLog[i].displayTowerLog();
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
	}
	
	/**
	 * This method displays the log of all the towers that are currently present on the map using the type variable in the gamelog class to filter the log entries
	 */
	public void viewCollectiveTowerLog()
	{
		System.out.println("");
		System.out.println("*****************************************");
		System.out.println("          Collective  Tower  log         ");
		System.out.println("*****************************************");
		
	
		for(int i=0;i<logCount;i++)
		{
			if(gameLog[i].type == "Tower")
				gameLog[i].displayTowerLog();
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	
	/**
	 * This function displays the log events that occurred during the first waqvr of the critters
	 */
	public void firstWaveLog()
	{
		System.out.println("");
		System.out.println("*****************************************");
		System.out.println("              First Wave Log             ");
		System.out.println("*****************************************");
		
	
		for(int i=0;i<logCount;i++)
		{
			if(gameLog[i].waveNumber == 1)
				gameLog[i].displayWaveLog();
		}
		
		System.out.println("");
		System.out.println("");

	}
	
	/**
	 * This function displays the log events that occurred during the second wave of the critters
	 */
	public void secondWaveLog()
	{
		System.out.println("");
		System.out.println("*****************************************");
		System.out.println("              Second Wave Log             ");
		System.out.println("*****************************************");
		
	
		for(int i=0;i<logCount;i++)
		{
			if(gameLog[i].waveNumber == 2)
				gameLog[i].displayWaveLog();
		}
		
		System.out.println("");
		System.out.println("");
	}
	
	
	/**
	 * This function displays the log of the third wave of critters
	 */
	public void thirdWaveLog()
	{
		System.out.println("");
		System.out.println("*****************************************");
		System.out.println("              Third Wave Log             ");
		System.out.println("*****************************************");
		
	
		for(int i=0;i<logCount;i++)
		{
			if(gameLog[i].waveNumber == 3)
				gameLog[i].displayWaveLog();
		}
		
		System.out.println("");
		System.out.println("");
	}
	
	
	
	/**
	 * This function displays global log of the game
	 */
	public void globalLog()
	{
		System.out.println("");
		System.out.println("*****************************************");
		System.out.println("              Global Log                 ");
		System.out.println("*****************************************");
		
	
		for(int i=0;i<logCount;i++)
		{
				gameLog[i].displayWaveLog();
		}
		
		System.out.println("");
		System.out.println("");
	}
		
		
}























