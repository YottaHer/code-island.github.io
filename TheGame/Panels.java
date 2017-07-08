package TheGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The panel where the map located.
 *
 */
public class Panels extends JPanel implements Runnable
{

	private	int gridHorizentalPositioning, gridVerticalPositioning;					//used to place the grid in the center
	private int tType;
	private MouseHandler mouseHandler;								//object of mouseHandler class to respond to mouse events on grid
	private Thread thread;
	
	public GameLog[] gameLog = new GameLog[100];
	public int logCount = 0;
	
	public static Screen screen;
	public static int[][] hasAttack=new int[100][100];	            //Record which tower attack which critter.  
	public static boolean inWave=false;
	public int gridHeight, gridWidth;												//keep track of grid height and width
	public boolean defineEntryPoint, defineExitPoint, addTower, removeTower;				//set true when user clicks the define entry/exit button until a block on the
	public boolean disableDragDraw;																//grid is chosen as entry or exit
	public DrawMap map;												//Map class object
	public Mob[] mobs=new Mob[100];
	public int hasCreate;											//The number of critters having been created in one wave.
	public static int needCreate;                                          //The number of critters need to be created in one wave.  
	public static int hasKill;										//The number of critters having been killed in one wave.
	public static int numberExit=0;                                   //The number of critters having reached exit.    
	public static int waveLevel=0;
	public static int totalKill=0;											//The number of critters having been killed from the start of game.
	public int score=0;
	
	public static boolean loadGameCompleted=false;
	public static boolean saveGameCompleted=false;
	/**
	 * The constructor of the panel.
	 * @throws IOException
	 */
	public Panels() throws IOException
	{		
		this.setBackground(Color.blue);
		map = new DrawMap();	
		for (int i=0;i<mobs.length;i++)
			mobs[i]=new Mob(map);
		initializeMouseListener();
		
		defineEntryPoint = defineExitPoint = addTower = disableDragDraw = removeTower = false;
		thread = new Thread(this);
		thread.start();
		
	}
	
	/**
	 * The reaction after putting remove tower button.
	 */
	public void removeTower()
	{
		defineEntryPoint = defineExitPoint = addTower = false;
		removeTower = true;
		disableDragDraw = true;	
	}
	
	/**
	 * destroy the tower.
	 * @param e The location of the mouse.
	 */
	public void destroyTower(Point e)
	{
		removeTower = false;
		disableDragDraw = false;
	}
	
	/**
	 * The reaction to add a tower.
	 * @param towerType The type of tower added.
	 */
	public void addTower(int towerType)
	{
		defineEntryPoint = defineExitPoint = removeTower = false;
		addTower = true;
		disableDragDraw = true;
		this.tType = towerType;
	}
	
	/**
	 * Place a new tower.
	 * @param e The location of the mouse.
	 */
	public void placeTower(Point e)
	{
		map.placeTower(e, this.tType);
		addTower = false;
		disableDragDraw = false;	
	}
	
	/**
	 * The reaction to click the map to define enxit point.
	 * @param p The location of the mouse.
	 */
	public void defineExitPoint(Point p)							//invokes the defineExit method of map object to allocate
	{																//a block as an exit for critters
		map.defineExitPoint(p);	
		defineExitPoint = false;
	}	
	
	/**
	 * The reaction to click the map to define entrance.
	 * @param p The location of the mouse.
	 */
	public void defineEntryPoint(Point p)						//invokes the defineEntry method of map object to allocate
	{															//a block as an entry for critters
		map.defineEntryPoint(p);
		defineEntryPoint = false;	
	}

	/**
	 * The reaction to define the entrance.
	 */
	public void defineEntry()
	{
		defineExitPoint = addTower = disableDragDraw = removeTower = false;
		defineEntryPoint = true;
	}
	
	/**
	 * The reaction to define the exit.
	 */
	public void defineExit()
	{
		defineEntryPoint = addTower = disableDragDraw = removeTower = false;
		defineExitPoint =  true;
	}
	
	/**
	 * Create a mouselistener to get the mouse.
	 */
	private void initializeMouseListener()						//add mouse listener to this JPanel
	{
		mouseHandler = new MouseHandler(this);
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
	}
	
	/**
	 * React to the savemap button.
	 * @throws IOException
	 */
	public void saveMap() throws IOException					//invokes the saveMap() method of Map
	{
			map.saveMap();
	}
	
	/**
	 * Get the size and up-left corner of the map.
	 */
	public void calculateGridPosition()							//Calculates the position of grid
	{
		gridHeight = map.rows * map.BLOCKSIZE;
		gridWidth = map.columns * map.BLOCKSIZE;
		
		int gridLength = map.rows * map.BLOCKSIZE;
		gridHorizentalPositioning = ((1000 - gridLength)/2);
		
		gridLength = map.columns * map.BLOCKSIZE;
		gridVerticalPositioning = ((620 - gridLength)/2);
		
		
	}
	
	/**
	 * Draw the map.
	 */
	public void paint(Graphics g)					//Draw/updates grid using the information in Map object
	{	
		
		super.paintComponent(g); 
		calculateGridPosition();
		this.setBounds(new Rectangle(gridHorizentalPositioning, gridVerticalPositioning, gridHeight, gridWidth ));	//bound Grid Position
		
		g.setColor(Color.GRAY);
		for(int x=0;x<map.rows;x++)
			for(int y=0;y<map.columns;y++)
			{
				if(map.map[x][y] == 1)
				{
					g.drawRect((x*map.BLOCKSIZE), (y*map.BLOCKSIZE), map.BLOCKSIZE, map.BLOCKSIZE);		
					
				}
				else if(map.map[x][y] == 0)
				{
					g.fillRect((x*map.BLOCKSIZE), (y*map.BLOCKSIZE), map.BLOCKSIZE, map.BLOCKSIZE);
				}
			}
		if( map.isEntryDefined ) 							//if Entry point is defined sets it's color to black
		{
			String label="Entry";
			g.setColor(Color.RED);			
			g.fillRect((map.entryX*map.BLOCKSIZE), (map.entryY*map.BLOCKSIZE), map.BLOCKSIZE, map.BLOCKSIZE);
			g.drawString(label, map.entryX*map.BLOCKSIZE, map.entryY*map.BLOCKSIZE+(map.entryX*map.BLOCKSIZE)/2);
		}
		
		if(map.isExitDefined)								//if Exit point is defined sets it's color to black
		{   
			String label="Exit";
			g.setColor(Color.YELLOW);
			g.fillRect((map.exitX*map.BLOCKSIZE), (map.exitY*map.BLOCKSIZE), map.BLOCKSIZE, map.BLOCKSIZE);
			g.drawString(label, map.exitX*map.BLOCKSIZE, map.exitY*map.BLOCKSIZE);
		}
		if(map.towerCount > 0)
		{
			int xAverage;
			for(int i=0;i<map.towerCount;i++)
			{
				g.setColor(Color.WHITE);
				if(map.t[i].towerType == 10)				//The first type.
				{
					if(map.focusedTowerID == map.t[i].towerID)
					{
						g.setColor(Color.BLACK);
					}
					g.fillRect( (map.t[i].location.x * map.BLOCKSIZE) + 5, (map.t[i].location.y * map.BLOCKSIZE) + 5, 30, 30);
					xAverage =( ( (map.BLOCKSIZE * map.t[i].range)/2) + ( ( (map.t[i].range)/3) * map.BLOCKSIZE) ) / 2;
					g.drawOval( ((map.t[i].location.x * map.BLOCKSIZE) - (xAverage)) , ( (map.t[i].location.y * map.BLOCKSIZE) - (xAverage)), map.BLOCKSIZE * map.t[i].range, map.BLOCKSIZE * map.t[i].range);
				}
				else if(map.t[i].towerType == 11)			//The second type.
				{
					if(map.focusedTowerID == map.t[i].towerID)
					{
						g.setColor(Color.BLACK);
					}
					g.fillRect( (map.t[i].location.x * map.BLOCKSIZE) + 5, (map.t[i].location.y * map.BLOCKSIZE) + 5, 30, 30);
					g.drawRect( (map.t[i].location.x - ((map.t[i].range)/2)) * map.BLOCKSIZE, map.t[i].location.y * map.BLOCKSIZE, map.t[i].range * map.BLOCKSIZE, 40 );
				}
				else if(map.t[i].towerType == 12)			//The third type.
				{
					if(map.focusedTowerID == map.t[i].towerID)
					{
						g.setColor(Color.BLACK);
					}
					g.fillRect( (map.t[i].location.x * map.BLOCKSIZE) + 5, (map.t[i].location.y * map.BLOCKSIZE) + 5, 30, 30);
					g.drawRect( (map.t[i].location.x * map.BLOCKSIZE), (map.t[i].location.y - ((map.t[i].range)/2)) * map.BLOCKSIZE, 40, map.t[i].range * map.BLOCKSIZE );
				}	
				
			}	
			
			
		}
		if (inWave)
			for (int i=0;i<mobs.length;i++)
				if (mobs[i].inGame==true)
					mobs[i].draw(g);
		for (int i=0;i<map.towerCount;i++)
			for (int j=0;j<mobs.length;j++)
				if (this.hasAttack[i][j]>0&&mobs[j].inGame)
				{
					
						hasAttack[i][j]--;
					if (hasAttack[i][j]==0)
						mobs[j].hurt(map.t[i].power);
					g.setColor(Color.RED);
					g.drawLine(map.t[i].location.x*DrawMap.BLOCKSIZE+DrawMap.BLOCKSIZE/2, map.t[i].location.y*DrawMap.BLOCKSIZE+DrawMap.BLOCKSIZE/2, mobs[j].x+DrawMap.BLOCKSIZE/2, mobs[j].y+DrawMap.BLOCKSIZE/2);
				
				}
	}
	
	public int spawnTime = 500; //The length of time between to critters.
	public int spawnFrame = 0;	//The length of the time after last critter.
	
	
	/**
	 * This method create critters.
	 */
	public void mobSpawner() {
		if(spawnFrame >= spawnTime) {
			hasCreate++;
			for(int i=0; i< mobs.length; i++) {
				if(!mobs[i].inGame) {
					for (int j=0;j<map.towerCount;j++)
					{
						hasAttack[j][i]=0;
						
					}
					//System.out.println(i);
					mobs[i].spawnMob();
					break;
				}
			}
			spawnFrame = 0;
		} else {
			spawnFrame += 1;
		}
	}
	
	
	/**
	 * Run the thread.
	 */
	public void run() {
		while(true)
		{
			//System.out.println("run");
			super.repaint();
			repaint();
			try {
				if (inWave)
				{
					
					if (needCreate>hasCreate){
						//System.out.println("cr");
						mobSpawner();
					}
					
					for(int i = 0; i<mobs.length;i++) {
						if(mobs[i].inGame) {
							mobs[i].physic();
						}
					} 
					map.attack(mobs);
				}
				if (hasKill+numberExit==needCreate&&inWave==true)
				{
					inWave=false;
					screen.towerButton();
					
					for (int i=0;i<map.towerCount;i++)
						for (int j=0;j<100;j++)
							this.hasAttack[i][j]=0;
					score+=waveLevel*hasKill;
					
				}
				/*if (totalKill>10)
				{
					score+=waveLevel*hasKill;
					map.log[map.logNum++]="Result : Game Victory";
					renewScore(score);
					//reset critter wave level
					waveLevel=0;
					try {
						map.saveData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Game Victory", null, JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}*/
				if (map.currency<0||numberExit>10)
				{
					score+=waveLevel*hasKill;
					map.log[map.logNum++]="Result : Game Over";
					renewScore(score);
					//reset critter wave level
					waveLevel=0;
					try {
						map.saveData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Game Over", null, JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}
	


	
	/**
	 * This method initial information for a new wave.
	 */
	public void waveInitial()
	{
	 
		
		waveLevel++;
		
		if (waveLevel==1){
		
			totalKill=0;
			score=0;
			Critter critter = CritterFactory.createCritter(CritterFactory.BasicCritter);
			critter .spawnCriter(waveLevel);
			//System.out.println("wave level " + waveLevel);
			needCreate=7;
		
		}else if (waveLevel==2){
			this.hasKill=0;
			this.numberExit=0;
			
			Critter critter = CritterFactory.createCritter(CritterFactory.IntermediateCritter);
			critter.spawnCriter(waveLevel);
			//System.out.println("wave level " + waveLevel);
			needCreate=9;
	
		}else if (waveLevel==3){
			this.hasKill=0;
			this.numberExit=0;
			
			Critter critter = CritterFactory.createCritter(CritterFactory.AdvancedCritter);
		critter .spawnCriter(waveLevel);
			//System.out.println("wave level " + waveLevel);
			needCreate=12;
		
		}
		  
				hasCreate=hasKill=0;
				//needCreate=7;
				spawnFrame=0;
				inWave=true;
				
		 
				if (waveLevel>3){
					JOptionPane.showMessageDialog(null, "Sorry Maximum level has been reached!Program will close", null, JOptionPane.ERROR_MESSAGE);
					inWave=false;
					map.log[map.logNum++]="Result : Game Victory";
					renewScore(score);
					//reset critter wave level
					try {
						map.saveData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Game Victory", null, JOptionPane.ERROR_MESSAGE);
					waveLevel=0;
					System.exit(0);
			
				}
		
		

	}
	
	/**
	 * Renew the highest scores.
	 * @param score The new score.
	 */
	public void renewScore(int score)
	{
		map.renewScore(score);
	}
	
	/**
	 * Save the game.
	 * @throws IOException
	 */
	public void saveGame() throws IOException
	{
		map.saveGame();
		saveGameCompleted=true;
	}
	
	/**
	 * Load the game.
	 * @param s The screen of the game
	 * @throws IOException
	 */
	public void loadGame(Screen s) throws IOException
	{
		map.loadGame(s);
		loadGameCompleted=true;
	}
}