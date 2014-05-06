//Samuel Chordas 2652701844
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Game_Board extends JFrame{
//	static ArrayList <NewPanel> gameSquares= new ArrayList <NewPanel>();
//	static ArrayList <Space> spaceData = new ArrayList <Space>();
//	static ArrayList <ImageIcon> playerIcons = new ArrayList <ImageIcon>();
//	static ArrayList <Player> playersArray = new ArrayList <Player>();
//	static ArrayList <Card> communityCardArray = new ArrayList<Card>();
//	static ArrayList <Card> wildCardArray = new ArrayList<Card>();
//	static Bank bankBalance = new Bank(2000);
	static JLabel p1 = new JLabel();
	static JLabel p2 = new JLabel();
	static JLabel p3 = new JLabel();
	static JLabel p4 = new JLabel();
	static JLabel bank = new JLabel();
	static JPanel left = new JPanel(new GridBagLayout());
	static JPanel right = new JPanel(new GridBagLayout());
	static JPanel center = new JPanel(new GridBagLayout());
//	static int diceVal = 0;
//	static int currentPlayer = 0;
//	static int totalNumPlayers = 0;
	JLabel currentPlayerLabel;

	public void setWildCards(){
		for (int i = 0; i < 5; i++){
			Card card = new Card();
			Client.GD.wildCardArray.add(card);
		}
		//card 0
		Client.GD.wildCardArray.get(0).setMessage("While driving between properties, you grew impatient with L.A. traffic.\n "
				+ "You decided to drive the shoulder on the 10, hit the median, and totaled that fancy ride.\n"
				+ "You lose a quarter of your money to repairs...drive safe.\n"
				+ "Cost: $");
		Client.GD.wildCardArray.get(0).setTitle("Oops!");
		Client.GD.wildCardArray.get(0).setCost(0);
		//card 1
		Client.GD.wildCardArray.get(1).setMessage("You decide to take a load off and go to Vegas!\n"
				+ "You win $100.\n"
				+ "Lucky you!");
		Client.GD.wildCardArray.get(1).setTitle("Winner Winner!");
		Client.GD.wildCardArray.get(1).setCost(100);
		//card 2
		Client.GD.wildCardArray.get(2).setMessage("One of your properties has been infested with rats!\n"
				+ "It costs you 50% of your money to exterminate them.\n"
				+ "Cost: $");
		Client.GD.wildCardArray.get(2).setTitle("Ah Rats!");
		Client.GD.wildCardArray.get(2).setCost(100);
		//card 3
		Client.GD.wildCardArray.get(3).setMessage("After spending some time learning Java, you make an app and it takes off!\n"
				+ "You earn $500. You may soon be playing Monopoly: Silicon Valley!");
		Client.GD.wildCardArray.get(3).setTitle("Coding Pays Off!");
		Client.GD.wildCardArray.get(3).setCost(500);
		//card 4
		Client.GD.wildCardArray.get(4).setMessage("All this real estate business gets overwhelming!\n"
				+ "You take a day trip to the beach. You fall asleep and wake up with a severe sunburn!\n"
				+ "You lose several work days to recovery, and have to pay $100 for the property up keep\n"
				+ "that you normally do yourself.");
		Client.GD.wildCardArray.get(4).setTitle("Ouch!");
		Client.GD.wildCardArray.get(4).setCost(100);
	}
	public void setCommunityCards(){
		for (int i = 0; i < 5; i++){
			Card card = new Card();
			Client.GD.communityCardArray.add(card);
		}
		//card 0
		Client.GD.communityCardArray.get(0).setMessage("You allow for a community festival to be held on your property\n"
				+ "for a portion of the profit. You make $100.");
		Client.GD.communityCardArray.get(0).setTitle("Festival");
		Client.GD.communityCardArray.get(0).setCost(100);
		//card 1
		Client.GD.communityCardArray.get(1).setMessage("You fall on hard times and can't afford your payments.\n"
				+ "The bank is going to reposses some of your assests.\n"
				+ "Pay the bank: $");
		Client.GD.communityCardArray.get(1).setTitle("Tough Times");
		Client.GD.communityCardArray.get(1).setCost(0);
		//card 2
		Client.GD.communityCardArray.get(2).setMessage("New tax laws hit you hard.\n"
				+ "Pay the bank: $100");
		Client.GD.communityCardArray.get(2).setTitle("Tax Increase");
		Client.GD.communityCardArray.get(2).setCost(100);
		//card 3
		Client.GD.communityCardArray.get(3).setMessage("For giving back to the community you receive a substantial\n"
				+ "tax break! Take $150 from the bank.");
		Client.GD.communityCardArray.get(3).setTitle("Tax Break");
		Client.GD.communityCardArray.get(3).setCost(150);
		//card 4
		Client.GD.communityCardArray.get(4).setMessage("The community is upset with all your rennovations and takes you to court.\n"
				+ "Pay the bank $150.");
		Client.GD.communityCardArray.get(4).setTitle("There Goes the Neighborhood");
		Client.GD.communityCardArray.get(4).setCost(150);
	}
	public int[] communityCardDraw(int cardNum, int currentPlayer){
		int value [] = {0, 0};
		if (Client.GD.playersArray.get(currentPlayer -1).getBalance() <= 150){
			cardNum = 0;
		}
		if (cardNum == 0){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.communityCardArray.get(cardNum).getMessage(), 
					Client.GD.communityCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer -1).getBalance() + 
					Client.GD.communityCardArray.get(cardNum).getCost());
			value[0] = Client.GD.playersArray.get(currentPlayer -1).getBalance() + 
					Client.GD.communityCardArray.get(cardNum).getCost();
		}
		else if (cardNum == 1){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.communityCardArray.get(cardNum).getMessage()
					+ (int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50), 
					Client.GD.communityCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() + (int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50));
			Client.GD.playersArray.get(currentPlayer - 1).setBalance((int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()-
					Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50));
			value[0] = (int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()-
					Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50);
			value[1] = Client.GD.bankBalance.getBankBalance() + (int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50);
			
		}
		else if (cardNum == 2){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.communityCardArray.get(cardNum).getMessage(), 
					Client.GD.communityCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() + Client.GD.communityCardArray.get(cardNum).getCost());
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer -1).getBalance()-
					Client.GD.communityCardArray.get(cardNum).getCost());
			value[0] = Client.GD.playersArray.get(currentPlayer -1).getBalance()-
					Client.GD.communityCardArray.get(cardNum).getCost();
			value[1] = Client.GD.bankBalance.getBankBalance() + Client.GD.communityCardArray.get(cardNum).getCost();
		}
		else if (cardNum == 3){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.communityCardArray.get(cardNum).getMessage(), 
					Client.GD.communityCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() - Client.GD.communityCardArray.get(cardNum).getCost());
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer -1).getBalance() + 
					Client.GD.communityCardArray.get(cardNum).getCost());
			value[0] = Client.GD.playersArray.get(currentPlayer -1).getBalance() + 
					Client.GD.communityCardArray.get(cardNum).getCost();
			value[1] = Client.GD.bankBalance.getBankBalance() - Client.GD.communityCardArray.get(cardNum).getCost();
		}
		else if (cardNum == 4){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.communityCardArray.get(cardNum).getMessage(), 
					Client.GD.communityCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() + Client.GD.communityCardArray.get(cardNum).getCost());
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer -1).getBalance() - 
					Client.GD.communityCardArray.get(cardNum).getCost());
			value[0] = Client.GD.playersArray.get(currentPlayer -1).getBalance() - 
					Client.GD.communityCardArray.get(cardNum).getCost();
			value[1] = Client.GD.bankBalance.getBankBalance() + Client.GD.communityCardArray.get(cardNum).getCost();
		}
		updateGUIBalance();
		return value;
		
	}
	public int wildCardDraw(int cardNum, int currentPlayer){
		int value = 0;
		if (Client.GD.playersArray.get(currentPlayer -1).getBalance() <= 100){
			cardNum = 1;
		}
		if (cardNum == 0){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.wildCardArray.get(cardNum).getMessage()
					+  (int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()*.25),Client.GD. wildCardArray.get(cardNum).getTitle(), 
					JOptionPane.INFORMATION_MESSAGE);

			Client.GD.playersArray.get(currentPlayer - 1).setBalance((int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()-
					Client.GD.playersArray.get(currentPlayer -1).getBalance()*.25));
			value = (int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()-
					Client.GD.playersArray.get(currentPlayer -1).getBalance()*.25);
		}
		else if(cardNum == 1){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.wildCardArray.get(cardNum).getMessage(), 
					Client.GD.wildCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer - 1).getBalance() + 
					Client.GD.wildCardArray.get(cardNum).getCost());
			value = Client.GD.playersArray.get(currentPlayer - 1).getBalance() + 
					Client.GD.wildCardArray.get(cardNum).getCost();
		}
		else if(cardNum == 2){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.wildCardArray.get(cardNum).getMessage() + 
					(int)(Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50), Client.GD.wildCardArray.get(cardNum).getTitle(),
					JOptionPane.INFORMATION_MESSAGE);
			//set Balance
			Client.GD.playersArray.get(currentPlayer - 1).setBalance((int)(Client.GD.playersArray.get(currentPlayer - 1).getBalance() -
					Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50));
			value = (int)(Client.GD.playersArray.get(currentPlayer - 1).getBalance() -
					Client.GD.playersArray.get(currentPlayer -1).getBalance()*.50);
		}
		else if (cardNum == 3){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.wildCardArray.get(cardNum).getMessage(), 
					Client.GD.wildCardArray.get(cardNum).getTitle(),JOptionPane.INFORMATION_MESSAGE);
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer - 1).getBalance() +
					Client.GD.wildCardArray.get(cardNum).getCost());
			value = Client.GD.playersArray.get(currentPlayer - 1).getBalance() +
					Client.GD.wildCardArray.get(cardNum).getCost();
		}
		else if (cardNum == 4){
			JOptionPane.showMessageDialog(Game_Board.this, Client.GD.wildCardArray.get(cardNum).getMessage(), 
					Client.GD.wildCardArray.get(cardNum).getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Client.GD.playersArray.get(currentPlayer - 1).setBalance(Client.GD.playersArray.get(currentPlayer - 1).getBalance() -
					Client.GD.wildCardArray.get(cardNum).getCost());
			value = Client.GD.playersArray.get(currentPlayer - 1).getBalance() -
					Client.GD.wildCardArray.get(cardNum).getCost();
		}
		//update GUI balance
		updateGUIBalance();
		return value;
	}
 	public void setImages(){
 		Client.GD.playerIcons.add(new ImageIcon("public/boot.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/dog.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/hat.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/helicopter.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/racecar.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/ring.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/robot.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/ship.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/thimble.jpg"));
 		Client.GD.playerIcons.add(new ImageIcon("public/wheelBarrow.jpg"));
	}
 	public void setSpaces(){
 		Client.GD.spaceData.get(0).setProperty("Start");
 		Client.GD.spaceData.get(0).setSafe(true);
		//1
 		Client.GD.spaceData.get(1).setProperty("Crenshaw");
 		Client.GD.spaceData.get(1).setValue(60);
 		Client.GD.spaceData.get(1).setRent(2);
		//2
 		Client.GD.spaceData.get(2).setProperty("Compton");
 		Client.GD.spaceData.get(2).setValue(60);
 		Client.GD.spaceData.get(2).setRent(2);
		//3
 		Client.GD.spaceData.get(3).setProperty("Watts");
 		Client.GD.spaceData.get(3).setValue(60);
 		Client.GD.spaceData.get(3).setRent(2);
		//4
 		Client.GD.spaceData.get(4).setProperty("Lynwood");
 		Client.GD.spaceData.get(4).setValue(60);
 		Client.GD.spaceData.get(4).setRent(2);
		//5
 		Client.GD.spaceData.get(5).setProperty("Inglewood");
 		Client.GD.spaceData.get(5).setValue(70);
 		Client.GD.spaceData.get(5).setRent(3);
		//6
 		Client.GD.spaceData.get(6).setProperty("Watts Towers");
 		Client.GD.spaceData.get(6).setValue(100);
 		Client.GD.spaceData.get(6).setRent(3);
		//7
 		Client.GD.spaceData.get(7).setProperty("USC");
 		Client.GD.spaceData.get(7).setValue(200);
 		Client.GD.spaceData.get(7).setRent(10);
		//8
 		Client.GD.spaceData.get(8).setProperty("Culver City");
 		Client.GD.spaceData.get(8).setValue(90);
 		Client.GD.spaceData.get(8).setRent(3);
		//9
 		Client.GD.spaceData.get(9).setProperty("La Brea Tar Pits");
 		Client.GD.spaceData.get(9).setValue(150);
 		Client.GD.spaceData.get(9).setRent(4);
		//10
 		Client.GD.spaceData.get(10).setProperty("Safe");
 		Client.GD.spaceData.get(10).setSafe(true);
		//11
 		Client.GD.spaceData.get(11).setProperty("Westlake");
 		Client.GD.spaceData.get(11).setValue(80);
 		Client.GD.spaceData.get(11).setRent(3);
		//12
 		Client.GD.spaceData.get(12).setProperty("Korea Town");
 		Client.GD.spaceData.get(12).setValue(120);
 		Client.GD.spaceData.get(12).setRent(6);
		//13
 		Client.GD.spaceData.get(13).setProperty("Downtown");
 		Client.GD.spaceData.get(13).setValue(180);
 		Client.GD.spaceData.get(13).setRent(9);
		//14
 		Client.GD.spaceData.get(14).setProperty("Staple's Center");
 		Client.GD.spaceData.get(14).setValue(150);
 		Client.GD.spaceData.get(14).setRent(8);
		//15
 		Client.GD.spaceData.get(15).setProperty("Draw Wildcard");
 		Client.GD.spaceData.get(15).setWildCard(true);
		//16
 		Client.GD.spaceData.get(16).setProperty("Dodger's Stadium");
 		Client.GD.spaceData.get(16).setValue(150);
 		Client.GD.spaceData.get(16).setRent(8);
		//17
 		Client.GD.spaceData.get(17).setProperty("Griffith Park");
 		Client.GD.spaceData.get(17).setValue(200);
 		Client.GD.spaceData.get(17).setRent(10);
		//18
 		Client.GD.spaceData.get(18).setProperty("Griffith Observatory");
 		Client.GD.spaceData.get(18).setValue(200);
 		Client.GD.spaceData.get(18).setRent(10);
		//19
 		Client.GD.spaceData.get(19).setProperty("Long Beach");
 		Client.GD.spaceData.get(19).setValue(150);
 		Client.GD.spaceData.get(19).setRent(8);
		//20
 		Client.GD.spaceData.get(20).setProperty("Safe");
 		Client.GD.spaceData.get(20).setSafe(true);
		//21
 		Client.GD.spaceData.get(21).setProperty("Venice");
 		Client.GD.spaceData.get(21).setValue(200);
 		Client.GD.spaceData.get(21).setRent(10);
		//22
 		Client.GD.spaceData.get(22).setProperty("Santa Monica Pier");
 		Client.GD.spaceData.get(22).setValue(200);
 		Client.GD.spaceData.get(22).setRent(10);
		//23
 		Client.GD.spaceData.get(23).setProperty("3rd St. Prominade");
 		Client.GD.spaceData.get(23).setValue(200);
 		Client.GD.spaceData.get(23).setRent(10);
		//24
 		Client.GD.spaceData.get(24).setProperty("Pacific Palisades");
 		Client.GD.spaceData.get(24).setValue(200);
 		Client.GD.spaceData.get(24).setRent(10);
		//25
 		Client.GD.spaceData.get(25).setProperty("Malibu");
 		Client.GD.spaceData.get(25).setValue(400);
 		Client.GD.spaceData.get(25).setRent(50);
		//26
 		Client.GD.spaceData.get(26).setProperty("Hollywood");
 		Client.GD.spaceData.get(26).setValue(350);
 		Client.GD.spaceData.get(26).setRent(28);
		//27
 		Client.GD.spaceData.get(27).setProperty("Hollywood Bowl");
 		Client.GD.spaceData.get(27).setValue(200);
 		Client.GD.spaceData.get(27).setRent(10);
		//28
 		Client.GD.spaceData.get(28).setProperty("Beverly Hills");
 		Client.GD.spaceData.get(28).setValue(500);
 		Client.GD.spaceData.get(28).setRent(80);
		//29
 		Client.GD.spaceData.get(29).setProperty("Beverly Center");
 		Client.GD.spaceData.get(29).setValue(200);
 		Client.GD.spaceData.get(29).setRent(10);
		//30
 		Client.GD.spaceData.get(30).setProperty("Safe");
 		Client.GD.spaceData.get(30).setSafe(true);
		
		//31
 		Client.GD.spaceData.get(31).setProperty("Westwood");
 		Client.GD.spaceData.get(31).setValue(300);
 		Client.GD.spaceData.get(31).setRent(25);
		//32
 		Client.GD.spaceData.get(32).setProperty("UCLA");
 		Client.GD.spaceData.get(32).setValue(200);
 		Client.GD.spaceData.get(32).setRent(10);
		//33
 		Client.GD.spaceData.get(33).setProperty("Getty Center");
 		Client.GD.spaceData.get(33).setValue(200);
 		Client.GD.spaceData.get(33).setRent(10);
		//34
 		Client.GD.spaceData.get(34).setProperty("Manhatten Beach");
 		Client.GD.spaceData.get(34).setValue(400);
 		Client.GD.spaceData.get(34).setRent(40);
		//35
 		Client.GD.spaceData.get(35).setProperty("Draw Community");
 		Client.GD.spaceData.get(35).setCommunity(true);
		//36
 		Client.GD.spaceData.get(36).setProperty("Hermosa Beach");
 		Client.GD.spaceData.get(36).setValue(400);
 		Client.GD.spaceData.get(36).setRent(40);
		//37
 		Client.GD.spaceData.get(37).setProperty("Disneyland");
 		Client.GD.spaceData.get(37).setValue(300);
 		Client.GD.spaceData.get(37).setRent(20);
		//38
 		Client.GD.spaceData.get(38).setProperty("Newport Beach");
 		Client.GD.spaceData.get(38).setValue(500);
 		Client.GD.spaceData.get(38).setRent(50);
		//39
 		Client.GD.spaceData.get(39).setProperty("Laguna Beach");
 		Client.GD.spaceData.get(39).setValue(500);
 		Client.GD.spaceData.get(39).setRent(50);
	}
	public void updateGUIBalance(){
		bank.setText("Bank Balance: : $" + Client.GD.bankBalance.getBankBalance());
		if (Client.GD.currentPlayer == 1){
			p1.setText("Player " + (Client.GD.playersArray.get(0).number + 1) +": $" 
					+ Client.GD.playersArray.get(0).getBalance());
			
		}
		else if (Client.GD.currentPlayer == 2){
			p2.setText("Player " + (Client.GD.playersArray.get(1).number + 1) +": $" 
					+ Client.GD.playersArray.get(1).getBalance());
		}
		else if (Client.GD.currentPlayer == 3){
			p3.setText("Player " + (Client.GD.playersArray.get(2).number + 1) +": $" 
					+ Client.GD.playersArray.get(2).getBalance());
		}
		else{
			p4.setText("Player " + (Client.GD.playersArray.get(3).number + 1) +": $" 
					+ Client.GD.playersArray.get(3).getBalance());
		}
	}
	public void updateLandLordBalance(int playerNum){
		if (playerNum == 1){
			p1.setText("Player " + (Client.GD.playersArray.get(0).number + 1) +": $" 
					+ Client.GD.playersArray.get(0).getBalance());
		}
		else if (playerNum == 2){
			p2.setText("Player " + (Client.GD.playersArray.get(1).number + 1) +": $" 
					+ Client.GD.playersArray.get(1).getBalance());
		}
		else if (playerNum == 3){
			p3.setText("Player " + (Client.GD.playersArray.get(2).number + 1) +": $" 
					+ Client.GD.playersArray.get(2).getBalance());
		}
		else{
			p4.setText("Player " + (Client.GD.playersArray.get(3).number + 1) +": $" 
					+ Client.GD.playersArray.get(3).getBalance());
		}
	}
	public void move(Integer a){
		Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setCurrentLocation(a);
		boolean playerIsIn = false;	
		while(!playerIsIn){
			if (Client.GD.currentPlayer < Client.GD.numPlayers){
				Client.GD.currentPlayer++;
			}	
			else{
				Client.GD.currentPlayer = 1;
			}
//			Client.GD.currentPlayer = (Client.GD.currentPlayer+1)%Client.GD.numPlayers;
			if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).isOut()){
				playerIsIn = false;
			}
			else{
				playerIsIn = true;
			}
		}
		currentPlayerLabel.setText("Current Player: " + "Player " + Client.GD.currentPlayer);
		center.repaint();
		center.validate();
		if (Client.GD.totalNumPlayers == 1){
			JOptionPane.showMessageDialog(Game_Board.this, "Congratulations! You are the last player left!\n"
					+ "You Win!", "PLAYER " + Client.GD.currentPlayer + " WINS", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
		}
		
		for (int k = 0; k < Client.GD.gameSquares.size(); k++) {
			Client.GD.gameSquares.get(k).repaint();
//			Client.GD.gameSquares.get(k).invalidate();
//			Client.GD.gameSquares.get(k).validate();
		}
	}
	public void setSpaceOwner(int i){
		Client.GD.spaceData.get(i).setOwner(Client.GD.currentPlayer);
	}
	public void setUpgrade(int i){
		Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).setUpgradeCost(i);
	}
	public void setRent(int i){
		Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).setRent(i);
	}
	public void grpMessage(String message, int num){
		String from = "Group Message From ";
		if (num ==1){
			from += "Player 1";
		}
		else if (num == 2){
			from += "Player 2";
		}
		else if (num == 3){
			from += "Player 3";
		}
		else{
			from += "Player 4";
		}
		JOptionPane.showConfirmDialog(Game_Board.this, message, from, JOptionPane.DEFAULT_OPTION);
	}
	public void indMessage(String message, String from){
		String sender = "Private message from Player " + from;
		JOptionPane.showConfirmDialog(Game_Board.this, message, sender, JOptionPane.DEFAULT_OPTION);
		
	}
	public Game_Board(){
		super("CSCI 201 Monop-L.A.");
		Client.GD.currentPlayer = 1;
		setImages();
		setWildCards();
		setCommunityCards();
		setSize(1300,800);
		setLayout(null);
		JMenuBar jmb = new JMenuBar();
		JMenu messengerMenu = new JMenu("Messenger");
		JMenuItem indMessage = new JMenuItem("Private Message");
		indMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Object Player
				// TODO Auto-generated method stub
				ArrayList<Integer> players = new ArrayList<Integer>();
				for(int i = 1; i <= Client.GD.playersArray.size(); i++){
					if((i)!=Client.clientNum){
						players.add(i);
					}
				}
				Object [] playersObj = players.toArray();
				Object defaultNum = playersObj[0];
				Object recipient = JOptionPane.showInputDialog(Game_Board.this, "Choose Recipient", "Private Message", JOptionPane.OK_CANCEL_OPTION, null, playersObj, defaultNum);
				String message = JOptionPane.showInputDialog(Game_Board.this, "Message", "Group Message", JOptionPane.OK_CANCEL_OPTION);
				String clientTo = recipient.toString();
				clientTo+=message;
				String clientFrom = String.valueOf(Client.clientNum);
				clientFrom+=clientTo;
				Client.send(new Messages(Messages.ClientEnum.sendIndMessage, clientFrom));
				
			}
		});
		JMenuItem grpMessage = new JMenuItem("Group Message");
		grpMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String client = String.valueOf(Client.clientNum);
				String message = JOptionPane.showInputDialog(Game_Board.this, "Message", "Group Message", JOptionPane.OK_CANCEL_OPTION);
				if(message!=null){
					System.out.println("Message: " + message);
					client+=message;
					Client.send(new Messages(Messages.ClientEnum.sendGrpMessage, client));
				}
				
			}
		});
		messengerMenu.add(indMessage);
		messengerMenu.add(grpMessage);
		jmb.add(messengerMenu);
		this.setJMenuBar(jmb);
		int panelStartX = 0;
		int panelStartY = 0;
		int panelWidth = 115;
		int panelHeight = 67;
//		Object numPlayers[] = {2, 3, 4};
//		Object defaultNum = 2;
//		final Object players = JOptionPane.showInputDialog(Game_Board.this, "Choose Desired Number of Players", "Welcome to Monop-L.A.", 
//				JOptionPane.INFORMATION_MESSAGE, null, numPlayers, defaultNum);
//		if (players == null){
//			System.exit(1);
//			
//		}
//		Client.GD.totalNumPlayers = Integer.parseInt(players.toString());
//		
//		ArrayList<Integer> selectedImages = new ArrayList<Integer>();
//		for (int i = 0; i < Integer.parseInt(players.toString()); i++) {
//			Client.GD.playersArray.add(new Player());
//			Client.GD.playersArray.get(i).setNumber(i);
//			Client.GD.playersArray.get(i).setCurrentLocation(0);
//			if (Integer.parseInt(players.toString()) == 4)
//				Client.GD.playersArray.get(i).setBalance(2000);
//			else if (Integer.parseInt(players.toString()) == 3)
//				Client.GD.playersArray.get(i).setBalance(2666);
//			else 
//				Client.GD.playersArray.get(i).setBalance(3000);
//			final int player = i;
//			JPanel p = new JPanel(new GridLayout(2, 4));
//			ArrayList<JRadioButton> rb = new ArrayList<JRadioButton>();
//			ButtonGroup group1 = new ButtonGroup();
//			
//			HashMap<Integer, Integer> iconImageMap = new HashMap<Integer, Integer>();
//			for (int j = 0; j < Client.GD.playerIcons.size(); j++) {
//				if (selectedImages.contains(j))
//					continue;
//				rb.add(new JRadioButton(Client.GD.playerIcons.get(j)));
//				group1.add(rb.get(rb.size()-1));
//				iconImageMap.put(rb.size()-1, j);
//			}
//			
//			for (int j = 0; j < rb.size(); j++) {
//				final int index = j;
//				final ButtonGroup tmp = group1;
//				final HashMap<Integer, Integer> tmpMap = iconImageMap;
//				
//				rb.get(index).addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						Client.GD.playersArray.get(player).setImage(tmpMap.get(index));
//						for (Enumeration<AbstractButton> buttons = tmp.getElements(); buttons.hasMoreElements();) {
//				            AbstractButton button = buttons.nextElement();
//
//				            if (button.isSelected()) {
//				                button.setBackground(Color.red);
//				                button.setOpaque(true);
//				            } else {
//				            	button.setBackground(null);
//				            }
//				        }
//					}
//				});
//				p.add(rb.get(index));
//			}
//			rb.get(0).doClick();
//			JOptionPane.showMessageDialog(Game_Board.this, p, "Player " + (i+1) + 
//			" Choose Your Game Piece", JOptionPane.INFORMATION_MESSAGE);
//			selectedImages.add(Client.GD.playersArray.get(i).getImage());
//		}
		
		for (int i = 0; i < 40; i ++){
			Space sd = new Space();
			Client.GD.spaceData.add(sd);
		}
		setSpaces();
		
		for (int i = 0; i < 11; i++){
			NewPanel jpTopRow = new NewPanel(i);
			jpTopRow.setLayout(new BoxLayout(jpTopRow, BoxLayout.PAGE_AXIS));
			jpTopRow.setBounds(panelStartX,panelStartY,panelWidth,panelHeight);
			jpTopRow.setBackground(Color.LIGHT_GRAY);
			jpTopRow.setBorder(BorderFactory.createLineBorder(Color.black));
			panelStartX = panelStartX + panelWidth;
			if (i == 10){
				panelStartX = panelStartX - panelWidth;
			}
			Client.GD.gameSquares.add(jpTopRow);
			add(jpTopRow);
		}
		
		for (int i = 0; i < 10; i++){
			
			NewPanel jpRightColumn = new NewPanel(i + 11);
			panelStartY = panelStartY + panelHeight;
			jpRightColumn.setLayout(new BoxLayout(jpRightColumn, BoxLayout.PAGE_AXIS));
			jpRightColumn.setBounds(panelStartX,panelStartY,panelWidth,panelHeight);
			jpRightColumn.setBackground(Color.LIGHT_GRAY);
			jpRightColumn.setBorder(BorderFactory.createLineBorder(Color.black));
			if (i == 9){
				panelStartX =  panelStartX-panelWidth;
			}
			Client.GD.gameSquares.add(jpRightColumn);
			add(jpRightColumn);
		}
		for (int i = 0; i < 10; i ++){
			NewPanel jpBottomRow = new NewPanel(i + 21);
			jpBottomRow.setLayout(new BoxLayout(jpBottomRow, BoxLayout.PAGE_AXIS));
			jpBottomRow.setBounds(panelStartX,panelStartY,panelWidth,panelHeight);
			jpBottomRow.setBackground(Color.LIGHT_GRAY);
			jpBottomRow.setBorder(BorderFactory.createLineBorder(Color.black));
			panelStartX = panelStartX-panelWidth;
			if (i == 9){
				panelStartY = panelStartY - panelHeight;
				panelStartX = panelStartX + panelWidth;
			}
			Client.GD.gameSquares.add(jpBottomRow);
			add(jpBottomRow);
		}
		for (int i = 0; i < 9; i++){
			NewPanel jpLeftColumn = new NewPanel(i + 31);
			jpLeftColumn.setLayout(new BoxLayout(jpLeftColumn, BoxLayout.PAGE_AXIS));
			jpLeftColumn.setBounds(panelStartX,panelStartY,panelWidth,panelHeight);
			jpLeftColumn.setBackground(Color.LIGHT_GRAY);
			jpLeftColumn.setBorder(BorderFactory.createLineBorder(Color.black));
			panelStartY = panelStartY-panelHeight;
			Client.GD.gameSquares.add(jpLeftColumn);
			add(jpLeftColumn);
		}
		for (int i = 0; i < 40; i++){
				JLabel label = new JLabel(Client.GD.spaceData.get(i).getProperty());
				label.setFont(new Font("Dialog", Font.PLAIN, 11));
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				Client.GD.gameSquares.get(i).add(label);
		}
		//right most panel contains players 1 and 3
		GridBagConstraints gbcLeft = new GridBagConstraints();
		gbcLeft.insets = new Insets(panelHeight*3, panelWidth*2, panelHeight*3, panelWidth*2);
		p1.setFont(new Font("Dialog", Font.BOLD, 17));
		p3.setFont(new Font("Dialog", Font.BOLD, 17));
		left.setBounds(panelWidth, panelHeight, panelWidth*3, panelHeight*9);
		add(left);
		//Center panel contains roll and forfeit button
		GridBagConstraints gbc = new GridBagConstraints();
		bank.setText("Bank Balance: $" + Client.GD.bankBalance.getBankBalance());
		bank.setFont(new Font("Dialog", Font.BOLD, 17));
		gbc.gridx = 0;
		gbc.gridy = 0;
		center.add(bank, gbc);
		currentPlayerLabel = new JLabel("Current Player: Player " +Client.GD.currentPlayer);
		gbc.insets = new Insets(50, panelWidth*2, panelHeight-50, panelWidth*2);
		currentPlayerLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		gbc.gridx = 0;
		gbc.gridy = 1;
		center.add(currentPlayerLabel, gbc);
		JButton roll = new JButton("Roll");
		roll.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				//send server a roll message
				//Client.send();
				int firstDie = (int)(Math.random() * 6 + 1);
				int secondDie = (int)(Math.random() * 6 + 1);
				
				String dieStr = "You rolled a " + firstDie + " and a " + secondDie;
				Client.GD.diceVal = firstDie + secondDie;
				Client.send(new Messages(Messages.ClientEnum.roll, new Integer(Client.GD.diceVal)));
				ImageIcon dice = new ImageIcon("public/dice.jpg");
				JOptionPane.showMessageDialog(Game_Board.this, dieStr, "Dice Roll", JOptionPane.INFORMATION_MESSAGE, dice);
				Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setCurrentLocation(Client.GD.diceVal);
//				
//				//safe square*******************
				if (Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getSafe()){
					JOptionPane.showMessageDialog(Game_Board.this, "This is a safe spot, relax until your next roll",
							"Safe Square", JOptionPane.INFORMATION_MESSAGE);
				}
				//Wild Card
				else if(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer -1).getCurrentLocation()).getWildCard()){//wild card
					JOptionPane.showMessageDialog(Game_Board.this, "You landed on the Wild Card square. Press ok to draw a card",
							"Wild Card", JOptionPane.INFORMATION_MESSAGE);
					int value = 0;
					value = wildCardDraw((int)(Math.random()*5), Client.GD.currentPlayer);
					Client.send(new Messages(Messages.ClientEnum.updatePlayerBalance, new Integer(value)));
				}
				//Community
				else if(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer -1).getCurrentLocation()).getCommunity()){
					JOptionPane.showMessageDialog(Game_Board.this, "You landed on the Community Card square. Press ok to draw a card",
							"Community Card", JOptionPane.INFORMATION_MESSAGE);
					int value[] = {0,0};
					value = communityCardDraw((int)(Math.random()*5), Client.GD.currentPlayer);
					if(value[1] != 0){
						Client.send(new Messages(Messages.ClientEnum.updateBankBalance, new Integer(value[1])));
					}
					Client.send(new Messages(Messages.ClientEnum.updatePlayerBalance, new Integer(value[0])));
					
					//Client.send();
					//tell server to tell everybody else to update the players $
				}
				//property not owned by anyone*************
				else if (Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() == 0){ 
					//buy option
					if (JOptionPane.showConfirmDialog(Game_Board.this, 
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getProperty() + ": \n"
							+ "This property has no owner, would you like to buy it? \n"+
							"Cost: $" + Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).value,
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getProperty(), 
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						//Can't afford****************
						if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance() < 
								Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).value){
							JOptionPane.showMessageDialog(Game_Board.this, "You do not have the funds to purchase this property",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
						else if(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance() ==
								Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).value){
							JOptionPane.showMessageDialog(Game_Board.this, "You cannot buy this property because you will have a"
									+ " balance of $0.",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
						//purchase****************
						else{
							//deduct balance**************
							Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setBalance(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance()
									- Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue());
							int value = Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance()
									- Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue();
							Client.send(new Messages(Messages.ClientEnum.updatePlayerBalance, new Integer(value)));
							//set owner***************
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).setOwner(Client.GD.currentPlayer);
							Client.send(new Messages(Messages.ClientEnum.setOwner, new Integer(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation())));
							//set Upgrade cost**************
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).setUpgradeCost((int)
									(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue() -(
											Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue() 
									*.60)));
							Client.send(new Messages(Messages.ClientEnum.setUpgrade, new Integer((int)
									(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue() -(
											Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue() 
									*.60)))));
							Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() + 
									Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue());
							Client.send(new Messages(Messages.ClientEnum.updateBankBalance, new Integer(Client.GD.bankBalance.getBankBalance() + 
									Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue())));
							//update GUI with new balance************
							updateGUIBalance();
						}
						
					}
				}
				//property owned by current player*************
				else if ((Client.GD.playersArray.get(Client.GD.currentPlayer - 1).number + 1) == 
						Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer -1).getCurrentLocation()).getOwner()){
					//upgrade option**************
					if (JOptionPane.showConfirmDialog(Game_Board.this, 
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getProperty() + ": \n"
						+ "You own this property, would you like to upgrade it? \n"+
						"Cost: $" + Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost()
						+"\nRent after upgrade: " +
						(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent() + 
								(int)(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent()*.20) + 1),
								Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getProperty(), 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						
						//Cant afford upgrade***************
						if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance() < 
								Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost()){
							JOptionPane.showMessageDialog(Game_Board.this, "You do not have the funds to upgrade this property",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
						else if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance() == 
								Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost()){
							JOptionPane.showMessageDialog(Game_Board.this, "You cannot upgrade this property because you "
									+ "will have a balance of $0.",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
						else {//purchase**************
							//deduct balance**************
							Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setBalance(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance()
									- Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost());
							int value = Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance()
									- Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost();
							Client.send(new Messages(Messages.ClientEnum.updatePlayerBalance, new Integer(value)));
							Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() + 
									Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost());
							int value2 = Client.GD.bankBalance.getBankBalance() + 
									Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost();
							Client.send(new Messages(Messages.ClientEnum.updateBankBalance, new Integer(value2)));
							//set Upgrade cost***********
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).setUpgradeCost((int)
									(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost() +(
											Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue() 
									*.40)));
							int value3 = (int)
									(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getUpgradeCost() +(
											Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getValue() 
									*.40));
							Client.send(new Messages(Messages.ClientEnum.setUpgrade, new Integer(value3)));
							//set Rent
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).setRent((int)
									(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent() +(
											Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent() 
									*.40)));
							int value4 = (int)
									(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent() +(
											Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent() 
									*.40));
							Client.send(new Messages(Messages.ClientEnum.setRent, new Integer(value4)));
//							//update GUI with balance***************
							updateGUIBalance();
//							
						}
					}	
				}
				
				else{ //property owned by someone other than current player*********
					JOptionPane.showMessageDialog(Game_Board.this, Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getProperty() 
							+ ":\n" + "This property is owned by player " + 
							Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() +
							". You must pay rent.\n" + "Rent: $" + Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent(),
							"Pay Up", JOptionPane.WARNING_MESSAGE);
					int balanceBeforeRent = Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance();
					//deduct current player balance**************
					Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setBalance(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance()
							- Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent());
					int value = Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance()
							- Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent();
					Client.send(new Messages(Messages.ClientEnum.updatePlayerBalance, new Integer(value)));
					
					int playerNum = 0;
					if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance() <= 0){//if balance is zero or less they are out
						
						JOptionPane.showMessageDialog(Game_Board.this, "You are out of money!\nYou are out of the game, your turn will be skipped"
								+ " over for the remainder of the game",
								"Out of Money", JOptionPane.WARNING_MESSAGE);
						Client.GD.playersArray.get(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() - 1)
						.setBalance(Client.GD.playersArray.get(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() - 1).getBalance()
								+ balanceBeforeRent); //doesn't pay full rent, only what they have
						Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setOut(true);
						Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setBalance(0);
						Client.GD.totalNumPlayers--;
						//reset all properties owned by current player
						for (int i = 0; i < Client.GD.spaceData.size(); i++){
							if (Client.GD.spaceData.get(i).getOwner() == Client.GD.currentPlayer){
								//set to no owner
								Client.GD.spaceData.get(i).setOwner(0);
								//increase cost of property
								Client.GD.spaceData.get(i).setValue(Client.GD.spaceData.get(i).getValue() + Client.GD.spaceData.get(i).getUpgradeCost());
							}
						}
						playerNum = Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner();
						
					}
					else{
					//increase owner balance *************
						Client.GD.playersArray.get(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() - 1)
						.setBalance(Client.GD.playersArray.get(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() - 1).getBalance()
								+ Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent());
						int value2 = Client.GD.playersArray.get(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() - 1).getBalance()
								+ Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getRent();
						Client.send(new Messages(Messages.ClientEnum.updatePlayerBalance, new Integer(value2)));
						playerNum = Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner();
					}
					//Update GUI *********
					updateGUIBalance();
					updateLandLordBalance(playerNum);
					

				}
				boolean playerIsIn = false;	
				while(!playerIsIn){
					if (Client.GD.currentPlayer < Client.GD.numPlayers){
						Client.GD.currentPlayer++;
					}	
					else{
						Client.GD.currentPlayer = 1;
					}
//					Client.GD.currentPlayer = (Client.GD.currentPlayer+1)%Client.GD.numPlayers;
					if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).isOut()){
						playerIsIn = false;
					}
					else{
						playerIsIn = true;
					}
				}
				currentPlayerLabel.setText("Current Player: " + "Player " + Client.GD.currentPlayer);
				center.repaint();
				center.validate();
				if (Client.GD.totalNumPlayers == 1){
					JOptionPane.showMessageDialog(Game_Board.this, "Congratulations! You are the last player left!\n"
							+ "You Win!", "PLAYER " + Client.GD.currentPlayer + " WINS", JOptionPane.INFORMATION_MESSAGE);
					System.exit(1);
				}
				
				for (int k = 0; k < Client.GD.gameSquares.size(); k++) {
					Client.GD.gameSquares.get(k).repaint();
//					Client.GD.gameSquares.get(k).invalidate();
//					Client.GD.gameSquares.get(k).validate();
				}
				
			}
		});
		gbc.insets = new Insets(panelHeight, panelWidth*2, panelHeight, panelWidth*2);
		gbc.gridx = 0;
		gbc.gridy = 2;
		center.add(roll, gbc);
		JLabel title = new JLabel("MONOP-L.A.");
		title.setFont(new Font("Dialog", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 3;
		center.add(title,gbc);
		JButton forfeit = new JButton("Forfeit");
		forfeit.setForeground(Color.RED);
		forfeit.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				if(JOptionPane.showConfirmDialog(Game_Board.this, "Clicking OK will remove you from the game permanently!\n"
						+ "Are you sure you want to forfeit?", "WARNING!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					//take out of game
					Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setOut(true);
					//give money to bank
					Client.GD.bankBalance.setBankBalance(Client.GD.bankBalance.getBankBalance() + Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getBalance());
					//set balance to zero
					Client.GD.playersArray.get(Client.GD.currentPlayer - 1).setBalance(0);
					//decrease players
					Client.GD.totalNumPlayers--;
					updateGUIBalance();
					//reset all properties owned by current player
					for (int i = 0; i < Client.GD.spaceData.size(); i++){
						if (Client.GD.spaceData.get(i).getOwner() == Client.GD.currentPlayer){
							//set to no owner
							Client.GD.spaceData.get(i).setOwner(0);
							//increase cost of property
							Client.GD.spaceData.get(i).setValue(Client.GD.spaceData.get(i).getValue() + Client.GD.spaceData.get(i).getUpgradeCost());
						}
					}
					boolean playerIsIn = false;	
					while(!playerIsIn){
						if (Client.GD.currentPlayer < Client.GD.numPlayers){
							Client.GD.currentPlayer++;
						}	
						else{
							Client.GD.currentPlayer = 1;
						}
						if (Client.GD.playersArray.get(Client.GD.currentPlayer - 1).isOut()){
							playerIsIn = false;
						}
						else{
							playerIsIn = true;
						}
					}
					currentPlayerLabel.setText("Current Player: " + "Player " + Client.GD.currentPlayer);
					center.repaint();
					center.validate();
					if (Client.GD.totalNumPlayers == 1){
						JOptionPane.showMessageDialog(Game_Board.this, "Congratulations! You are the last player left!\n"
								+ "You Win!", "PLAYER " + Client.GD.currentPlayer + " WINS", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}
				for (int k = 0; k < Client.GD.gameSquares.size(); k++) {
					Client.GD.gameSquares.get(k).repaint();
				} 	
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 4;
		center.setBounds(panelWidth*4, panelHeight, panelWidth*3, panelHeight*9);
		center.add(forfeit, gbc);
		add(center);
		
		GridBagConstraints gbcRight = new GridBagConstraints();
		gbcRight.insets = new Insets(panelHeight*3, panelWidth*2, panelHeight*3, panelWidth*2);
		p2.setFont(new Font("Dialog", Font.BOLD, 17));
		p4.setFont(new Font("Dialog", Font.BOLD, 17));
		
		right.setBounds(panelWidth*7, panelHeight, panelWidth*3, panelHeight*9);
		add(right);
		System.out.println("Game_Board Line 865 numPlayers: "+Client.GD.numPlayers);
		if (Client.GD.numPlayers==2){
			p1.setText("Player " + (Client.GD.playersArray.get(0).number + 1) +": $" 
					+ Client.GD.playersArray.get(0).balance);
			gbcLeft.gridx = 0;
			gbcLeft.gridy = 0;
			left.add(p1, gbcLeft);
			p2.setText("Player " + (Client.GD.playersArray.get(1).number + 1) +": $"+ 
					Client.GD.playersArray.get(1).balance);
			gbcRight.gridx = 0;
			gbcRight.gridy = 0;
			right.add(p2, gbcRight);	
		}
		else if (Client.GD.numPlayers==3){
			p1.setText("Player " + (Client.GD.playersArray.get(0).number + 1) +": $"+ 
					Client.GD.playersArray.get(0).balance);
			gbcLeft.gridx = 0;
			gbcLeft.gridy = 0;
			left.add(p1, gbcLeft);
			p2.setText("Player " + (Client.GD.playersArray.get(1).number + 1) +": $"+ 
					Client.GD.playersArray.get(1).balance);
			gbcRight.gridx = 0;
			gbcRight.gridy = 0;
			right.add(p2, gbcRight);
			p3.setText("Player " + (Client.GD.playersArray.get(2).number + 1) +": $"+ 
					Client.GD.playersArray.get(2).balance);
			gbcLeft.gridx = 0;
			gbcLeft.gridy = 1;
			left.add(p3, gbcLeft);
			
		}
		else if (Client.GD.numPlayers==4){
			p1.setText("Player " + (Client.GD.playersArray.get(0).number + 1) +": $"+ 
					Client.GD.playersArray.get(0).balance);
			gbcLeft.gridx = 0;
			gbcLeft.gridy = 0;
			left.add(p1, gbcLeft);
			p2.setText("Player " + (Client.GD.playersArray.get(1).number + 1) +": $"+ 
					Client.GD.playersArray.get(1).balance);
			gbcRight.gridx = 0;
			gbcRight.gridy = 0;
			right.add(p2, gbcRight);
			p3.setText("Player " + (Client.GD.playersArray.get(2).number + 1) +": $"+ 
					Client.GD.playersArray.get(2).balance);
			gbcLeft.gridx = 0;
			gbcLeft.gridy = 1;
			left.add(p3, gbcLeft);
			p4.setText("Player " + (Client.GD.playersArray.get(3).number + 1) +": $"+ 
					Client.GD.playersArray.get(3).balance);
			gbcRight.gridx = 0;
			gbcRight.gridy = 1;
			right.add(p4, gbcRight);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String [] args){
		//new GUI();
		new Game_Board();
	}
}
