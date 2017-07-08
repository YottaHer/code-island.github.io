package TheGame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * The class is the frame of the game.
 *
 */
public class Frame extends JFrame implements ActionListener{
	
	private Screen screen;
	
	/**
	 * The constructor of frame.
	 * @throws IOException
	 */
	private Frame() throws IOException
	{
		setSize(1366, 718);
		setTitle("Tower Defense Game (fromScratchForFun)");
		setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.RED);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initializeScreen();
		setMenu();
		setVisible(true);	
		
	}
	
	
	/**
	 * The class create the menu of the game.
	 */
	public void setMenu()												//sets up the JMenu
	{
		JMenu me=new JMenu("Map");
		JMenuItem option3=new JMenuItem("Create New Map"); 
		JMenuItem option2=new JMenuItem("Load Map");
		JMenuItem option1=new JMenuItem("Start Game");
		JMenuItem option4=new JMenuItem("Exit");
		JMenuItem option5=new JMenuItem("Save Game");
		JMenuItem option6=new JMenuItem("Load Game");
		me.add(option5);
		me.add(option6);
		me.add(option1);
		me.add(option2);
		me.add(option3);
		me.add(option4);
		JMenuBar ba=new JMenuBar();
		ba.add(me);
		setJMenuBar(ba);
		option5.addActionListener(this);
		option6.addActionListener(this);
		option1.addActionListener(this);
		option2.addActionListener(this);
		option4.addActionListener(this);
		option3.addActionListener(this);
		
	}
		
	/**
	 * The class create the screen of the game.
	 * @throws IOException
	 */
	private void initializeScreen() throws IOException					//initialize Screen
	{
		screen = new Screen();
		add(screen);
	}
	
	/**
	 * The main function which starts the game.
	 * @param args The string input from command.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Frame frame = new Frame();
	}


	/**
	 * The reaction after putting a button.
	 */
	public void actionPerformed(ActionEvent e)							//performs actions when user event occurs
	{	
		String s=e.getActionCommand();
		if (s.equals("Create New Map"))
		{
			screen.panel.map.createNewMap();
			this.repaint();
		}
		else if (s.equals("Exit")) 
			System.exit(0);
		else if(s.equals("Load Map"))
		{
			try {
				screen.panel.map.loadMap();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.repaint();
		}
		else if (s.equals("Start Game"))
		{
			/**
			 * creating a GameController instance
			 * Game Controller is used as a SingleObject
			 */
			//Panels.waveLevel=0;
			GameController obj1 = GameController.getInstance();
			obj1.setGame();
						
			if (GameController.playStatus ==true){
				try {
					screen.panel.map.playMap();
					screen.changeButton();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if (s.equals("Save Game"))
		{
			try {
				screen.panel.saveGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (s.equals("Load Game"))
		{
			try {
				screen.panel.loadGame(this.screen);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
	}
}
