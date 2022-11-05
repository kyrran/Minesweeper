import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Psyky2Main extends MouseAdapter{
	final static int WIDTH = 12; //number of button wide that you display is
	final static int HEIGHT = 12; //number of button high that you display is
	final static int NUM_MINES = 6; //the number of mines to create
	Board boardobject = new Board();
	
	
	//constructor
	public Psyky2Main (){
		JFrame guiFrame = new JFrame(); 
		guiFrame.setTitle("Minesweeper");
		boardobject = new Board();
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(WIDTH,HEIGHT));
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				BoardSquareButton button1 = new BoardSquareButton(50, 50, Color.gray, x, y);
				boardobject.storeButton(x, y, button1);
				buttonpanel.add(button1);
				BoardSquareButton ob2 = boardobject.getButton(x, y);
				ob2.addMouseListener(this);
			}
		}
		
		guiFrame.getContentPane().add(buttonpanel);
		
		boardobject.createMines(NUM_MINES);
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiFrame.setLocationRelativeTo(null); 
    	guiFrame.pack();
    	guiFrame.setVisible(true);
	}
	

		@Override
		public void mouseClicked(MouseEvent e)
		{
			Object button3 = e.getSource();
			BoardSquareButton but = (BoardSquareButton) button3;
			
			//must click a non-investigated square
			if(but.getisIn() == false ) {
			    //left click
				if(SwingUtilities.isLeftMouseButton(e)) {
					//every time it clicks, investigate it 
					but.setisInvestigated(true);
					//if click a mine
					if(but.getisMine() == true) {
						boardobject.finished();
						//prompt
						JOptionPane.showMessageDialog(null,"you lost!");
						boardobject.initialiseall();
						boardobject.createMines(NUM_MINES);	
					} else {
						//if did't click a mine
						isleftnotmine(but.x, but.y, but);
					}
					
					//right click
				} else if (SwingUtilities.isRightMouseButton(e)) {
					//every time right click, it will be labeled
					but.setisPotential(true);
					Color color = but.getBackground();
					if(color != Color.red) {
						but.setisPotential(true);
						but.setBackground(Color.red);
					} else {
						but.setBackground(Color.gray);
						but.setisPotential(false);
					}	
				}
				
				//after click, check the current board
				if(boardobject.hasWon()== true) {
					boardobject.finished();
					//prompt
					JOptionPane.showMessageDialog(null,"you won!");
					boardobject.initialiseall();
					boardobject.createMines(NUM_MINES);
				}
			}
			
		}
		
		
		public void isleftnotmine(int x, int y, BoardSquareButton but2) {
			//it is not a mine
			but2.setBackground(Color.green);
			but2.setisInvestigated(true);
		    int a = boardobject.countSurrounding(x, y);
			but2.setText(Integer.toString(a));
		    //if click a 0
			if( a == 0) {
				
				for(int i = (x-1); i <= (x + 1); i++) {
					for(int j = (y-1); j <= y + 1; j++) {
						//for (x,y) in range
						if ( ((i >= 0) && ( i < Psyky2Main.WIDTH) &&  (j >= 0) && ( j < Psyky2Main.HEIGHT)) == true) {
		                    //if it has not been clicked or label, increment their value on it 
							if(boardobject.getButton(i, j).getisIn() == false && boardobject.getButton(i, j).getisPotential() == false) {
								isleftnotmine(i,j,boardobject.getButton(i,j));	
							}
						}
					}
				}
			}
		}
		
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Psyky2Main instance = new Psyky2Main();

	}
	
	
	
	

}
