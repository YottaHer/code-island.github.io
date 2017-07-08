import java.awt.Point;

import javax.swing.JOptionPane;

/**
 * The class represents tower.
 *
 */
public class Tower {
	
	
	public int towerType;
	public int towerID;
	public int range;
	public int power;
	public Point location;
	public int level;
	public int cost;
	public int upgradeCost;
	public int firingRate;//How long the time between two attacks.
	public int refundAmount;
	public int attackCount;//The length of time after last attack.
	public Strategy strategy;//The strategy to find target.
	
	public static int towerCount;
	public static int cTowerCost, hTowerCost, vTowerCost;
	public static String[] effect={"splash","burning","freezing"};
	
	/**
	 * The constructor of tower class.
	 * @param e The location of the mouse.
	 * @param id The id of the tower.
	 * @param tType The type of the tower.
	 */
	public Tower(Point e,int id, int tType)
	{
		cTowerCost = 25;
		hTowerCost = 10;
		vTowerCost = 10;
		
		this.towerType = tType;
		this.towerID = id;
		towerCount = 0;
		location = new Point();
		this.location.x = e.x;
		this.location.y = e.y;
		
		refundAmount = 3;
		range = 4;
		level = 1;
		cost = 10;
		upgradeCost = 5;
		power = 80;
		attackCount=0;
		if (towerType==10)
			firingRate=400;
		else if (towerType==11)
			firingRate=500;
		else
			firingRate=600;
		strategy=new NearTower();
	}
	
	/**
	 * Remove the tower.
	 * @param next The next tower.
	 */
	public void removeTower(Tower next)
	{
		
		this.towerType = next.towerType;
		this.range = next.range;
		this.location.x = next.location.x;
		this.location.y = next.location.y;
		this.level = next.level;
		this.cost = next.cost;
		this.upgradeCost = next.upgradeCost;
		this.firingRate = next.firingRate;
	}
	
	/**
	 * The change of upgrade.
	 */
	public void upGrade()
	{
		if(this.level == 3)
		{
			JOptionPane.showMessageDialog(null, "Max Lavel Reached", null, JOptionPane.ERROR_MESSAGE);
			return;
		}
		power+=20;
		refundAmount += 3;
		range += 2;
		level += 1;
		upgradeCost += 20;
		firingRate -= 50;
	}
	
	/**
	 * 
	 * @param mob The critter being checked.
	 * @return If the critter is in the range of the tower.
	 */
	public boolean inRange(Mob mob)
	{
		int xd=location.x*DrawMap.BLOCKSIZE-mob.x;//The horizontal distance between tower and mob.
		int yd=location.y*DrawMap.BLOCKSIZE-mob.y;//The vertical distance between tower and mob.
		if (xd*xd+yd*yd<=range*DrawMap.BLOCKSIZE*range*DrawMap.BLOCKSIZE)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This method set the strategy to find target.
	 * @param strategy The strategy being set.
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy=strategy; 
	}
	
	/**
	 * 
	 * @param mobs The critters.
	 */
	public void attack(Mob[] mobs)
	{
		if (attackCount<firingRate)
		{
			attackCount++;
			return;
		}
		else
		{
			attackCount=0;
		}
		int i=strategy.findTarget(mobs, this);
		if (i==-1)
			return;
		Panels.hasAttack[towerID][i]=20;
		if (this.towerType==10)
		{
				/*
				if (i==1)
					System.out.println("sp");
				*/
				for (int j=0;j<mobs.length;j++)
					if (mobs[j].inGame)
					{
						
						if (j!=i&&(Math.abs(mobs[j].x-mobs[i].x)+Math.abs(mobs[j].y-mobs[i].y))<5*DrawMap.BLOCKSIZE)
						{	
							mobs[j].hurt(80);
							DrawMap.gameLog[DrawMap.logCount].CreateLog("Critter Hit by Tower", "gameplay",  this.towerID, Panels.waveLevel);
							DrawMap.logCount++;
							/*
							if (j==1)
							System.out.println("h");
							*/
						}
					}
		}
		else if (towerType==11)
		{
			mobs[i].burning();
			DrawMap.gameLog[DrawMap.logCount].CreateLog("Critter burnt  by Tower", "gameplay", this.towerID, Panels.waveLevel);
			DrawMap.logCount++;
		}
		else if (towerType==12)
		{
			/*if (i==1)
				System.out.println("fr");
			*/
			mobs[i].freeze( );
			DrawMap.gameLog[DrawMap.logCount].CreateLog("Critter Frozen by Tower", "gameplay", this.towerID, Panels.waveLevel);
			DrawMap.logCount++;
		}
			
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
