import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

public class Board {
	
	
	BoardSquareButton[][] button = new BoardSquareButton[Psyky2Main.WIDTH][Psyky2Main.HEIGHT]; 
	
	
	
	public BoardSquareButton getButton (int x, int y) {
		return button[x][y];
	}
	
	
	
	public void storeButton (int x, int y, BoardSquareButton b) {
		button[x][y] = b;
		
	}
	
	
	
	public void initialiseall() {
		
		for(int x = 0; x < Psyky2Main.WIDTH; x++) {
			
			for(int y = 0; y < Psyky2Main.HEIGHT; y++) {
				
				button[x][y].initialise();
				
			}	
		}
	}
	
	
	
	
	
	public void createMines(int number) {
		
		number = Psyky2Main.NUM_MINES;
		Random rand = new Random();
		
		int i = 0;
		while(i < number) {
			
			int x = rand.nextInt(Psyky2Main.WIDTH);
			int y = rand.nextInt(Psyky2Main.HEIGHT); 
			
			if(button[x][y].getisMine() == true) {
				i--;
			} else {
				//for test:Let user know which one is mines
				//button[x][y].setBackground(Color.ORANGE);
				button[x][y].setisMine(true);
				i++;
			}	
		}
		
	}
	
	
	
	
	
	public void finished() {
		
		for(int x = 0; x < Psyky2Main.WIDTH; x++) {
			
			for(int y = 0; y < Psyky2Main.HEIGHT; y++) {
				
				if( button[x][y].getisMine() == true) {
				
					if( button[x][y].getisPotential() == false) {
						//for mines are not labeled, still in gray 
						button[x][y].setBackground(Color.orange);
						button[x][y].setText("X");
					
					} else {
						//for mines are labeled, in red
						button[x][y].setBackground(Color.red);
						button[x][y].setText("X");
					}
				
				} else {
					//not mines, but has not been clicked, in gray
					if(button[x][y].getisIn() == false && hasWon() == false) {
						//if lose, ? one don't need to show the value
						button[x][y].setText("?");
						button[x][y].setBackground(Color.gray);
					} else {
						//if win the game
						int number = countSurrounding(x,y);
						button[x][y].setText(Integer.toString(number));
						button[x][y].setBackground(Color.green);
					}
				}
			}
		}
		
		
	}
		
	
	
	
	
	
	public boolean hasWon() {
		int count = 0;
		for(int x = 0; x < Psyky2Main.WIDTH; x++) {
			for(int y = 0; y < Psyky2Main.HEIGHT; y++) {
				if( (button[x][y].isInvestigated == true )|| button[x][y].isMine == true ) {
					count++;
				}
			}
		}
		if(count == Psyky2Main.WIDTH * Psyky2Main.HEIGHT) {
			return true;
		}
		return false;
	}
	
	/*public boolean hasWon() {
		int count = 0;
		for(int x = 0; x < Psyky2Main.WIDTH; x++) {
			for(int y = 0; y < Psyky2Main.HEIGHT; y++) {
				if (button[x][y].isInvestigated == true) {
					count++;
				}
			}
		}
		if(count == Psyky2Main.WIDTH * Psyky2Main.HEIGHT - Psyky2Main.NUM_MINES) {
			return true;
		}
		return false;
		
	}*/
	
	
	
	
	
	
	public int countSurrounding (int x , int y) {
		int number = 0;
		
		if(button[x][y].getisMine()==true) {
			finished();
			JOptionPane.showMessageDialog(null,"you lost!");
			initialiseall();
			createMines(Psyky2Main.NUM_MINES);				
		} else {
			
			for(int i = (x-1); i <= (x + 1); i++) {
				
				for(int j = (y-1); j <= y + 1; j++) {
					
					if ( ((i >= 0) && ( i < Psyky2Main.WIDTH) &&  (j >= 0) && ( j < Psyky2Main.HEIGHT)) == true) {
						
						if(button[i][j].getisMine()==true) {
							number = number + 1; 

				
						}
					}
				}
			}
		}
		
		//button[x][y].setisInvestigated(true);
		
		return number;
	}
	

}
