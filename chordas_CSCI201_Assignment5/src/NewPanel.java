//Samuel Chordas 2652701844
import java.awt.Graphics;

import javax.swing.JPanel;


public class NewPanel extends JPanel{
	int id = 0;
	public NewPanel(int id){
		this.id = id;
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int [] x = {10, 60, 10, 60};
		int [] y = {14, 14, 41, 41};
		for (int i = 0; i < Game_Board.playersArray.size(); i++){
			if (Game_Board.playersArray.get(i).isOut()){
				continue;
			}
			if (Game_Board.playersArray.get(i).getCurrentLocation() == id){
				g.drawImage(Game_Board.playerIcons.get(Game_Board.playersArray.get(i).getImage()).getImage(), x[i], y[i], 
						30, 20, null);
			}
		}
		if (Game_Board.spaceData.get(id).getOwner() != 0) {
			g.drawString(new Integer(Game_Board.spaceData.get(id).getOwner()).toString(), 50, 30);
		}
	}

}
