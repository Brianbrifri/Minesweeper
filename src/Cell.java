import javax.swing.*;


@SuppressWarnings("serial")
//Cell class that contains all info to change icons and a boolean mine flag
public class Cell extends JButton {
	
	//Initialize ImageIcon variables and boolean
	ImageIcon mine, one, two, three, four, five, six, seven, eight, flag, unknown;
	boolean isMine = false;
	
	public Cell() {
		//Set variables to match with their respective images
		mine = new ImageIcon(this.getClass().getResource("mine.png"));
		unknown = new ImageIcon(this.getClass().getResource("unknown.png"));
		one = new ImageIcon(this.getClass().getResource("1.png"));
		two = new ImageIcon(this.getClass().getResource("2.png"));
		three = new ImageIcon(this.getClass().getResource("3.png"));
		four = new ImageIcon(this.getClass().getResource("4.png"));
		five = new ImageIcon(this.getClass().getResource("5.png"));
		six = new ImageIcon(this.getClass().getResource("6.png"));
		seven = new ImageIcon(this.getClass().getResource("7.png"));
		eight = new ImageIcon(this.getClass().getResource("8.png"));
		flag = new ImageIcon(this.getClass().getResource("flag.png"));
		
		//Set icon to a question mark upon creation
		setIcon(unknown);
	}
	
	//Method to set icon to a mine
	public void setMineIcon() {
		setIcon(mine);
	}
	
	//Method to set icon to a number based on how many mines are around it
	public void setNumberIcon(int mines){
		switch(mines) {
			case 0: setIcon(null);
				break;
			case 1: setIcon(one);
				break;
			case 2: setIcon(two);
				break;
			case 3: setIcon(three);
				break;
			case 4: setIcon(four);
				break;
			case 5: setIcon(five);
				break;
			case 6: setIcon(six);
				break;
			case 7: setIcon(seven);
				break;
			case 8: setIcon(eight);
				break;
			default: setIcon(null);
		}
	}
	
	//Method to return icon type. Mainly used to return null
	public Icon getTheIcon() {
		return getIcon();
	}
	
	//Method to check if this cell is a mine
	public boolean getIsMine() {
		return isMine;
	}
	
	//Turns this cell into a mine
	public void setAsMine() {
		isMine = true;
	}
	
	//Shows if this is a mine by turning the icon into a flag
	//If this cell already shows a mine, skip this action
	public void showWhereMinesAre() {
		if(getIcon() == mine) {
		}
		else {
			setIcon(flag);
		}
	}
	
}
