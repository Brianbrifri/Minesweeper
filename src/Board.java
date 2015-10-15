/* 
 * Project 5
 * This is a minesweeper program with icons
 * that uses recursion
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;


@SuppressWarnings("serial")
public class Board extends JFrame {
	
	//Create 2 JButtons, start and show as well as an 8x8
	//2D array of cells named cellButton
	JButton start = new JButton("Start");
	JButton show = new JButton("Show");
	Cell cellButton[][] = new Cell[8][8];
	
	//Constructor that creates panels
	public Board() {
		
		//minefield panel with an 8x8 grid layout
		JPanel mineField = new JPanel();    
		mineField.setLayout(new GridLayout(8, 8));
		
		//Create instance of listener from listener class
		ListenerClass listener = new ListenerClass();
		
		//Add cellButton/cells to the panel and add the listener at same time
		for (int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				cellButton[i][j] = new Cell();
				cellButton[i][j].addActionListener(listener);
				mineField.add(cellButton[i][j]);
				cellButton[i][j].setEnabled(false);
			}
		}    
	   
		//Create a boarderpanel and top panel. Top Panel holds the 
		//show and start cellButton and border panel holds top panel
		//and minefield
		JPanel boarderPanel = new JPanel(new BorderLayout());  
		JPanel topPanel = new JPanel(new GridLayout(1, 2));
		
		//Add start and show cellButton to top panel
		topPanel.add(start, BorderLayout.NORTH); 
		topPanel.add(show, BorderLayout.NORTH); 
		
		//Add listeners to start and stop cellButton
		start.addActionListener(listener);
		show.addActionListener(listener);
		
		//Add top panel and minefield to boarder panel
		boarderPanel.add(topPanel, BorderLayout.NORTH);    
		boarderPanel.add(mineField, BorderLayout.CENTER);
		
		//Add boarder panel into the frame    
		add(boarderPanel, BorderLayout.CENTER);    
	}
	
	//Main method
	public static void main(String[] args) {    
		Board frame = new Board();    
		frame.setTitle("Mine Sweeper");    
		frame.setSize(500, 500);    
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		frame.setVisible(true);  
	}
		
	
	//Expand function with recursion
	//This part checks all cells around for mines and adds them into sum
	public void expand(int row, int column) {
		int sum = 0;
		for(int i = row - 1; i <= row + 1; i++) {
			for(int j = column - 1; j <= column + 1; j++) {
				if(!(row == i && column == j) && i >= 0 && j >= 0 && i < cellButton.length && j < cellButton[0].length) {
					if(cellButton[i][j].getIsMine()) {
						sum++;
					}
				}
			}
		}
		
		//Calls setNumberIcon on the array location and sets image
		//based on how many mines are around
		cellButton[row][column].setNumberIcon(sum);
		
		//If there are no mines around, expand again and check around
		if(sum == 0) {
			for (int i = row -1; i <= row + 1; i++) {
				for(int j = column - 1; j <= column + 1; j++) {
					if(!(row == i && column == j) && i >= 0 && j >= 0 && i < cellButton.length && j < cellButton[0].length) {
						try {
							//If the button is not a mine and the cell has not been cleared, call expand again
							if(!cellButton[i][j].getIsMine() && cellButton[i][j].getTheIcon() != null) {
								expand(i, j);
							}
						}
						catch(ArrayIndexOutOfBoundsException ex) {						
						}
					}
				}
			}
		}	
	}
	
	//Listener that detects what button is clicked then acts based on that
	class ListenerClass implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//If the show button is clicked
			if(e.getSource() == show) {
				show.setEnabled(false);
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						//If the button is a mine, call showWhereMinesAre method
						//that sets icons to flags
						if(cellButton[i][j].getIsMine()) {
							cellButton[i][j].showWhereMinesAre();
						}
					}
				}	
			}
			
			//If start button is clicked
			else if(e.getSource() == start) {
				Random rand = new Random();
				//disable start button for future use
				start.setEnabled(false);
				//set random coordinates to mines, if already a mine, decrease counter and try again
				for(int i = 1; i <= 8; i++) {
					int x = rand.nextInt(8);
					int y = rand.nextInt(8);
					if(!cellButton[x][y].getIsMine()) {
						cellButton[x][y].setAsMine();
					}

					else {
						i--;
						continue;
					}
			}
			for (int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
				cellButton[i][j].setEnabled(true);
			}
		}
			}

			//If a cell button, loop through array to find matching button
			else  {
				for (int i = 0; i < cellButton.length; i++) {
					for(int j = 0; j < cellButton[0].length; j++) {
						//once match found, if a mine was clicked, call setMineIcon method
						if (e.getSource() == cellButton[i][j]){
							if(cellButton[i][j].getIsMine()) {
								for (int k = 0; k < cellButton.length; k++) {
									for (int m = 0; m < cellButton[0].length; m++) {
										if(cellButton[k][m].getIsMine()) {
											cellButton[k][m].setMineIcon();
										}

									}
								}
							}
							//otherwise, expand recursively
							else {
								expand(i, j);
							}
						}
					}
				}
			}
		}	
	}
}

