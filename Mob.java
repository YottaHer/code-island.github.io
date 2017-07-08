
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * The class representing a mob.
 */
public class Mob extends Rectangle {
	public int xC, yC;	// coordinates of the direction
	public int mobSize = 40;	// size of the image of critter
	public int mobWalk = 0;		// the steps that the critter takes
	public int upward = 0, downward = 1, right = 2, left = 3;	// 4 ints that represent the direction a monster will take
	public int direction = right;	// initial direction
	public int mobID;
	public int health;
	public int walkFrame;		//The time after last walk.
	public int walkSpeed;		//The time between two times of walk.
	public DrawMap map;
	public static int mobidentify = 0;

	public Image image1=new ImageIcon("res/mob.png").getImage(); //Image of first cirtter.
	public Image image2=new ImageIcon("res/Critter2.png").getImage();//Image of second critter.
	public Image image3=new ImageIcon("res/Critter3.png").getImage();//Image of second critter.
	public Image fireImage=new ImageIcon("res/fire.png").getImage();
	public Image iceImage=new ImageIcon("res/ice.png").getImage();

	public boolean inGame = false;	// monster still alive?
	public int step;	//The number of steps the critter having made.
	public int burnTime;	//The length of time after last burning.
	public int burnLength;	//The length of time of burning.
	public int freezeTime;	//The length of time after last freezing.
	public int freezeLength;	//The length of time of freezing.
	/*
	 * Last direction moved.
	 */
	public boolean hasUpward = false;	//Record if previous direction is up.
	public boolean hasDownward = false; //Record if previous direction is down.
	public boolean hasLeft = false;		//Record if previous direction is left.
	public boolean hasRight = false;	//Record if previous direction is right.
	
	/**
	 * The constructor of a mob.
	 * @param map The map of the game.
	 */
	public Mob(DrawMap map) 
	{
		this.map=map;
		
		
	}
	
	/**
	 * Initial a new critter.
	 */
	public void spawnMob() 
	{
			setBounds(DrawMap.entryX*DrawMap.BLOCKSIZE, DrawMap.entryY*DrawMap.BLOCKSIZE, mobSize, mobSize);
			direction=right;	
			xC = DrawMap.entryX;	
			yC = DrawMap.entryY;
			health=200;
			walkFrame=0;
			walkSpeed=8;
			mobWalk=0;
			inGame = true;
			step=0;
			burnTime=0;
			burnLength=0;
			freezeTime=0;
			freezeLength=0;
			hasUpward = false;
			hasDownward = false;
			hasLeft = false;
			hasRight = false;
			
			//Decide first direction at beginning.
			if(!hasUpward) {
				try {
					if(map.columns>yC+1&&DrawMap.map[xC ][yC+1]!=1&&DrawMap.map[xC][yC+1]<10){
						direction = downward;
					}
				} catch (Exception e) {}
			}
			
			if(!hasDownward) {
				try {
					if(yC>0&&DrawMap.map[xC][yC-1]!= 1&&DrawMap.map[xC][yC-1]<10){
						direction = upward;
					}
				} catch (Exception e) {}
			}
			
			if(!hasLeft) {
				try {
					if(xC+1<map.rows&&DrawMap.map[xC+1][yC ] != 1&&DrawMap.map[xC+1][yC]<10){
						direction = right;
					}
				} catch (Exception e) {}
			}
			
			if(!hasRight) {
				try {
					if(xC>0&&DrawMap.map[xC-1][yC] != 1&&DrawMap.map[xC-1][yC]<10){
						direction = left;
					}
				} catch (Exception e) {}
			}
	}
	
	/**
	 * Delete the dead mob. 
	 */
	public void deleteMob() 
	{
		inGame = false;
	}
	
	/**
	 * Steal the coin when reaching exit.
	 */
	public void loseCoin() 
	{
		map.loseCoin();
	}
	// walkframe is how many seconds in between am I moving the monster
	// walkspeed is the maximum
	
	/**
	 * The change of the state of mob.
	 */
	public void physic() {
		
		if (burnTime>=burnLength)
		{
			burnLength=0;
			burnTime=0;
		}
		else
		{
			burnTime+=1;
			if (burnTime%50==0)
			hurt(15);
		}
		if (freezeTime>=freezeLength)
		{
			walkSpeed=4;
			freezeLength=0;
			freezeTime=0;
		}
		else
			freezeTime+=1;
		if(walkFrame >= walkSpeed) {
			step++;
			if(direction == right) {
				x += 1;
			} else if(direction == upward) {
				y -= 1;
			} else if(direction == downward) {
				y += 1;
			} else if(direction == left) {
				x -= 1;
			}
			
			mobWalk +=1;
			if(mobWalk == DrawMap.BLOCKSIZE) {
				
				if(direction == right) {
					xC += 1;
					hasRight = true;
				} else if(direction == upward) {
					yC -= 1;
					hasUpward = true;
				} else if(direction == downward) {
					yC += 1;
					hasDownward = true;
				} else if(direction == left) {
					xC -= 1;
					hasLeft = true;
				}
				//System.out.println("Direction : " + direction);
				if(!hasUpward) {
					try {
						if(map.columns>yC+1&&DrawMap.map[xC ][yC+1]!=1&&DrawMap.map[xC][yC+1]<10){
							direction = downward;
						}
					} catch (Exception e) {}
				}
				
				if(!hasDownward) {
					try {
						if(yC>0&&DrawMap.map[xC][yC-1]!= 1&&DrawMap.map[xC][yC-1]<10){
							direction = upward;
						}
					} catch (Exception e) {}
				}
				
				if(!hasLeft) {
					try {
						if(xC+1<map.rows&&DrawMap.map[xC+1][yC ] != 1&&DrawMap.map[xC+1][yC]<10){
							direction = right;
						}
					} catch (Exception e) {}
				}
				
				if(!hasRight) {
					try {
						if(xC>0&&DrawMap.map[xC-1][yC] != 1&&DrawMap.map[xC-1][yC]<10){
							direction = left;
						}
					} catch (Exception e) {}
				}
				
				if(DrawMap.map[xC][yC] == 4) {
					Panels.numberExit++;
					deleteMob();
					loseCoin();
				}
				
				hasUpward = false;
				hasDownward = false;
				hasLeft = false;
				hasRight = false;
				mobWalk = 0;
			}
			walkFrame = 0;
		} else {
			walkFrame += 1;
		}
	}
	/**
	 * Draw critters graphic
	 * @param g Graphics to paint panel. 
	 */
	public void draw(Graphics g) {
		if(inGame) {

			if (Panels.waveLevel==1){
				Critter critter = CritterFactory.createCritter(CritterFactory.BasicCritter);
				g.drawImage(critter .spawnCriter(Panels.waveLevel), x, y, width, height, null);
			}else if (Panels.waveLevel==2){
				Critter critter = CritterFactory.createCritter(CritterFactory.IntermediateCritter);
				g.drawImage(critter .spawnCriter(Panels.waveLevel), x, y, width, height, null);
			}else if (Panels.waveLevel==3){
				Critter critter = CritterFactory.createCritter(CritterFactory.AdvancedCritter);
				g.drawImage(critter .spawnCriter(Panels.waveLevel), x, y, width, height, null);
			}

		
			if (burnLength>0)
				g.drawImage(fireImage, x+10, y-20, 20, 20, null);
			if (freezeLength>0)
				g.drawImage(iceImage, x+10, y-20, 20, 20, null);
			g.setColor(Color.RED);
			g.drawString(Integer.toString(health), x+10, y-40);
		}
	}
	
	/**
	 * The effect of being hit.
	 * @param hitpoint The amount of health should be lost.
	 */
	public void hurt(int hitpoint)
	{
		health-=hitpoint;
		if (health<=0)
		{
			DrawMap.gameLog[DrawMap.logCount].CreateLog("Critter Killed", "gameplay", mobidentify++, Panels.waveLevel);
			DrawMap.logCount++;
			deleteMob();
			map.currency+=5;
			map.change();
			Panels.hasKill++;
			Panels.totalKill++;
			
		}
	}
	
	/**
	 * The method producing the effect of freezing.
	 */
	public void freeze()
	{
		if (freezeLength==0)
			walkSpeed+=4;
		else
			freezeTime=0;
		freezeLength=200;
	}
	
	/**
	 * The method producing the effect of burning.
	 */
	public void burning()
	{
		burnLength=200;	
		burnTime=0;
	}
}
