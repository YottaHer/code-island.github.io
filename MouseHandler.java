import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 * The class handle the action of the mouse.
 *
 */
public class MouseHandler implements MouseInputListener {
	
	Panels p;
	
	/**
	 * The constructor of the mouse handler.
	 * @param panels The panel inked to the handler.
	 */
	public MouseHandler(Panels panels)
	{
		p = panels;
	}
	
	/**
	 * Handle click of the mouse.
	 */
	public void mouseClicked(MouseEvent e)
	{
		if(p.defineEntryPoint)
		{
			p.defineEntryPoint(e.getPoint());
		}
		else if(p.defineExitPoint)
		{
			p.defineExitPoint(e.getPoint());
		}
		else if(p.addTower)
		{
			p.placeTower(e.getPoint());
		}
		else
			p.map.towerCharacterWindow(e.getPoint());
	}
	

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		p.map.check(e.getPoint());
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
	/**
	 * Handle the drag of mouse.
	 */
	public void mouseDragged(MouseEvent e) {
		int x = e.getPoint().x;
		int y = e.getPoint().y;
		if( ( (p.gridWidth > y) && (p.gridHeight > x ) ) && ( (x > 0) && (y > 0) ) && (!p.disableDragDraw) )      	//Check to see if the mouse is dragged within the grid
			p.map.dragDraw(e.getPoint());													//defined boundaries
		
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
