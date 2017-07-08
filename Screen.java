import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *The class is the screen of the game. 
 * 
 *
 */

public class Screen extends JPanel implements ActionListener,Observer {
	
	int selectedTowerID;
	

	
	public Panels panel;
	public JPanel centerPanel;
	public JPanel northPanel;
	public JPanel westPanel;
	public JPanel eastPanel;
	public JPanel southPanel;
	public JButton defineExit, defineEntry, saveMap;
	public JButton addTower1, addTower2, addTower3,startWave;
	public JButton upgradeTower, sellTower;
	public JButton strategy1,strategy2,strategy3,strategy4;//Representing buttons for three kinds of strategies. 
	public JButton individualTowerLog, collectiveTowerLog, waveLog1, waveLog2, waveLog3, globalLog;
	public JLabel currencyLabel;
	public JLabel towerCharacteristicsLabel;
	public JLabel towerRange, towerLevel, towerPower, towerFiring, towerUpgradeCost, towerRefund;
	public JLabel effect;//Special effect label.
	public JLabel scoreTitle;
	public JLabel score[]=new JLabel[5];
	
	private Font font;
	
	
	/**
	 * The constructor of the screen.
	 * @throws IOException
	 */
	public Screen() throws IOException
	{
		super();

		this.setLayout(new BorderLayout());
		
		southPanel();
		eastPanel();
		westPanel();
		northPanel();
		centerPanel();
	
	
		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(westPanel, BorderLayout.WEST);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
		
		forbid();
		
		panel.map.addObserver(this);
	
	
	}

	
	/**
	 * Create the north part of the screen.
	 */
	private void northPanel()															//The north panel is empty for now
	{	
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());	
	}


	/**
	 * Create the west part of the screen.
	 */
	private void westPanel()												//initialize all the components in the west panel (the add tower buttons)
	{
		
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		Icon a = new ImageIcon("res/tower.png");
		Icon b = new ImageIcon("res/tower.png");
		Icon c = new ImageIcon("res/tower.png");
		addTower1 = new JButton();
		addTower1.setIcon(b);
		addTower2 = new JButton();
		addTower2.setIcon(a);
		addTower3 = new JButton(" ");
		addTower3.setIcon(c);
		currencyLabel = new JLabel(" ");
		startWave =new JButton("start new wave");
		individualTowerLog = new JButton("Tower Log   ");
		collectiveTowerLog = new JButton("Collective Tower Log");
		waveLog1 = new JButton("Wave 1 Log ");
		waveLog2 = new JButton("Wave 2 Log ");
		waveLog3 = new JButton("Wave 3 Log ");
		globalLog = new JButton("Global Log    ");
		scoreTitle=new JLabel("Highest Score");
		for (int i=0;i<5;i++)
			score[i]=new JLabel(Integer.toString(i+1)+" : 0");
		
		westPanel.add(addTower1);
		westPanel.add(addTower2);
		westPanel.add(addTower3);
		westPanel.add(currencyLabel);
		westPanel.add(startWave);
		westPanel.add(scoreTitle);
		for (int i=0;i<5;i++)
			westPanel.add(score[i]);
		westPanel.add(waveLog1);
		westPanel.add(waveLog2);
		westPanel.add(waveLog3);
		westPanel.add(globalLog);
		westPanel.add(individualTowerLog);
		westPanel.add(collectiveTowerLog);
		addTower1.addActionListener(this);
		addTower2.addActionListener(this);
		addTower3.addActionListener(this);
		startWave.addActionListener(this);
		individualTowerLog.addActionListener(this);
		collectiveTowerLog.addActionListener(this);
		waveLog1.addActionListener(this);
		waveLog2.addActionListener(this);
		waveLog3.addActionListener(this);
		globalLog.addActionListener(this);
		
		waveLog3.setEnabled(false);
		waveLog2.setEnabled(false);
		waveLog1.setEnabled(false);
	}


	/**
	 * Create the east part of the screen.
	 */
	private void eastPanel()						//initializes all the components in the west Panel (shows characteristics of the selected tower
	{	
		eastPanel = new JPanel();
		
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		
		towerCharacteristicsLabel = new JLabel(" Tower Characteristics ");
		this.towerPower = new JLabel("Power : ------");
		this.towerFiring = new JLabel("Firing Rate : ------");
		this.towerLevel = new JLabel("level : ------");
		this.towerRange = new JLabel("Range : ------");
		this.towerRefund = new JLabel("Refund Rate : ------");
		this.towerUpgradeCost = new JLabel("Upgrade Cost : ------");
		this.effect=new JLabel("special effect :------");
		
		this.upgradeTower = new JButton("Upgrade");
		this.sellTower = new JButton("Sell Tower");
		strategy1=new JButton("Shoot Critter Nearest To Tower ");
		strategy2=new JButton("Shoot Critter Nearest To Exit");
		strategy3=new JButton("Shoot Strongest Critter");
		strategy4=new JButton("Shoot Weakest Critter");
		
		towerCharacteristicsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(towerCharacteristicsLabel);
		eastPanel.add(Box.createGlue());
		
		towerPower.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(this.towerPower);
		eastPanel.add(Box.createGlue());
		
		towerFiring.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(this.towerFiring);
		eastPanel.add(Box.createGlue());
		
		towerLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(this.towerLevel);
		eastPanel.add(Box.createGlue());
		
		towerRange.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(this.towerRange);
		eastPanel.add(Box.createGlue());
		
		towerRefund.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(this.towerRefund);
		eastPanel.add(Box.createGlue());
		
		towerUpgradeCost.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(this.towerUpgradeCost);
		eastPanel.add(Box.createGlue());
		
		effect.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(effect);
		eastPanel.add(Box.createGlue());
		
		upgradeTower.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(upgradeTower);
		eastPanel.add(Box.createGlue());
		
		sellTower.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(sellTower);
		eastPanel.add(Box.createGlue());
		
		strategy1.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(strategy1);
		eastPanel.add(Box.createGlue());
		
		strategy2.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(strategy2);
		eastPanel.add(Box.createGlue());
		
		strategy3.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(strategy3);
		eastPanel.add(Box.createGlue());
		
		strategy4.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(strategy4);
		eastPanel.add(Box.createGlue());
		
		upgradeTower.addActionListener(this);
		sellTower.addActionListener(this);
		strategy1.addActionListener(this);
		strategy2.addActionListener(this);
		strategy3.addActionListener(this);
		strategy4.addActionListener(this);
	}


	/**
	 * Create the south part of the screen.
	 */
	private void southPanel()							//Initializes the components of the south panel(defineEntery, defineExit, saveMap)
	{
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		
		defineExit = new JButton("Define exit Point");
		defineEntry = new JButton("Define Entry Point");
		saveMap = new JButton("Save Map");
		
		southPanel.add(defineEntry);
		southPanel.add(defineExit);
		southPanel.add(saveMap);
		
		saveMap.addActionListener(this);
		defineEntry.addActionListener(this);
		defineExit.addActionListener(this);
	}


	/**
	 * Create center part of the game.
	 * @throws IOException
	 */
	private void centerPanel() throws IOException {				//This panel contains the map
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(Color.GREEN);
		
		
		panel = new Panels();
		panel.screen=this;
		centerPanel.add(panel, BorderLayout.CENTER);
		
		
	}
	/**
	 * React to the button on screen.
	 */
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println(selectedTowerID);
		if(e.getSource() == saveMap)
		{
			try {
				panel.saveMap();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == defineEntry)
			panel.defineEntry();
		else if(e.getSource() ==  defineExit)
			panel.defineExit();
		else if(e.getSource() == addTower1)
		{
			panel.addTower(10);
		}
		else if(e.getSource() == addTower2)
			panel.addTower(11);
		else if(e.getSource() == addTower3 )
			panel.addTower(12);
		else if(e.getSource() == individualTowerLog)
			panel.map.viewIndividualTowerLog(selectedTowerID);
		else if(e.getSource() == collectiveTowerLog)
			panel.map.viewCollectiveTowerLog();
		else if(e.getSource() == upgradeTower)
			panel.map.upgradeTower(selectedTowerID);
		else if(e.getSource() == waveLog1)
			panel.map.firstWaveLog();
		else if(e.getSource() == waveLog2)
			panel.map.secondWaveLog();
		else if(e.getSource() == waveLog3)
			panel.map.thirdWaveLog();
		else if(e.getSource() == globalLog)
			panel.map.globalLog();
		else if(e.getSource() == sellTower)
		{
			panel.map.removeTower(selectedTowerID);
			selectedTowerID = -1;					//-1 means no tower is selected.
		}
		else if (e.getSource()==startWave)
		{
			panel.waveInitial();
			
			if(Panels.waveLevel == 1 )
				waveLog1.setEnabled(true);
			
			if(Panels.waveLevel == 2 )
				waveLog2.setEnabled(true);
			
			if(Panels.waveLevel == 3 )
				waveLog3.setEnabled(true);
			
			
			
			addTower1.setEnabled(false);
			addTower2.setEnabled(false);
			addTower3.setEnabled(false);
			upgradeTower.setEnabled(false);
			//sellTower.setEnabled(false);
			startWave.setEnabled(false);
		}
		else if (selectedTowerID!=-1&&e.getSource()==strategy1)
		{
			panel.map.setStrategy(selectedTowerID,new NearTower());
		}
		else if (selectedTowerID!=-1&&e.getSource()==strategy2)
		{
			panel.map.setStrategy(selectedTowerID,new NearExit());
		}
		else if (selectedTowerID!=-1&&e.getSource()==strategy3)
		{
			panel.map.setStrategy(selectedTowerID,new Strongest());
		}
		else
		{
			panel.map.setStrategy(selectedTowerID,new Weakest());
		}
		
	}
	
	/**
	 * Update the view after the change of model.
	 */
	public void update(Observable o, Object arg) {
		
		selectedTowerID = ((DrawMap)o).focusedTowerID;
		if(selectedTowerID != (-1))
		{
			
			Tower t = ((DrawMap)o).t[selectedTowerID];
			
			towerCharacteristicsLabel.setText(" Tower Characteristics ");
			
			if(t.level == 1)
				this.towerLevel.setText("level : Weak");
			else if(t.level == 2)
				this.towerLevel.setText("Level : Medium");
			else if(t.level == 3)
				this.towerLevel.setText("Level : Strong");
			
			if(t.range == 4)
				this.towerRange.setText("Range : low");
			else if(t.range == 6)
				this.towerRange.setText("Range : Medium");
			else if(t.range == 8)
				this.towerRange.setText("Range : Full");
			
			
			
			this.towerPower.setText("Power : "+t.power);
			this.towerFiring.setText("Firing Rate : "+t.firingRate);
			if(t.level == 3)
				this.towerUpgradeCost.setText("Upgrade Cost : ------");
			else
				this.towerUpgradeCost.setText("Upgrade Cost : " + t.upgradeCost);
			this.towerRefund.setText("Refund Rate : " + t.refundAmount);
			this.currencyLabel.setText("$" + panel.map.currency);
			this.effect.setText(Tower.effect[t.towerType-10]);
		}
		else
		{
			towerCharacteristicsLabel.setText(" Tower Characteristics ");
			this.towerPower.setText("Power : ------");
			this.towerFiring.setText("Firing Rate : ------");
			this.towerLevel.setText("level : ------");
			this.towerRange.setText("Range : ------");
			this.towerRefund.setText("Refund Rate : ------");
			this.towerUpgradeCost.setText("Upgrade Cost : ------");
			currencyLabel.setFont(font);
			this.currencyLabel.setText("$" + panel.map.currency);
			this.effect.setText("Upgrade Cost : ------");
		}
		for (int i=0;i<5;i++)
			score[i].setText(Integer.toString(i+1)+" : "+Integer.toString(panel.map.score[i]));
	}	
	
	/** Forbit the tower button in the creation of map.
	 * 
	 */
	public void forbid()
	{
		addTower1.setEnabled(false);
		addTower2.setEnabled(false);
		addTower3.setEnabled(false);
		upgradeTower.setEnabled(false);
		sellTower.setEnabled(false);
		startWave.setEnabled(false);
		strategy1.setEnabled(false);
		strategy2.setEnabled(false);
		strategy3.setEnabled(false);
		strategy4.setEnabled(false);
	}
	
	/**The tower buttons can be used.
	 * The map buttons is forbidden.
	 * 
	 */
	public void changeButton()
	{
		addTower1.setEnabled(true);
		addTower2.setEnabled(true);
		addTower3.setEnabled(true);
		upgradeTower.setEnabled(true);
		sellTower.setEnabled(true);
		strategy1.setEnabled(true);
		strategy2.setEnabled(true);
		strategy3.setEnabled(true);
		strategy4.setEnabled(true);
		startWave.setEnabled(true);
		defineEntry.setEnabled(false);
		defineExit.setEnabled(false);
		saveMap.setEnabled(false);
	}
	
	/**
	 * Make buttons about tower enabled.
	 */
	public void towerButton()
	{
		addTower1.setEnabled(true);
		addTower2.setEnabled(true);
		addTower3.setEnabled(true);
		upgradeTower.setEnabled(true);
		sellTower.setEnabled(true);
		strategy1.setEnabled(true);
		strategy2.setEnabled(true);
		strategy3.setEnabled(true);
		strategy4.setEnabled(true);
		startWave.setEnabled(true);
	}
	
	
}
