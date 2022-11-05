import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoardSquareButton extends JButton {
	int x, y;
	boolean isMine, isInvestigated, isPotential;
	
	
	//constructor
	public BoardSquareButton (int width, int height, Color Color, int x, int y) {
		this.x = x;
		this.y = y;
		setMinimumSize( new Dimension(width, height) );
		setPreferredSize( new Dimension(width, height) );
		setFont(new Font ("Helvetica", Font.BOLD, 10));
		initialise();
		
	}
	
	public void initialise() {
		this.setText("?");
		this.setBackground(Color.gray);
		this.isInvestigated = false;
		this.isMine = false;
		this.isPotential = false;
	}
	
	
	public void setisMine(boolean a) {
		this.isMine = a;
	}
	
	public void setisInvestigated(boolean a) {
		this.isInvestigated = a;
	}
	
	public void setisPotential(boolean a) {
		this.isPotential = a;
	}
	
	
	
	
	public boolean getisMine() {
		return this.isMine;
	}
	
	public boolean getisIn() {
		return this.isInvestigated;
	}
	
	public boolean getisPotential() {
		return isPotential;
	}
		

}
