import java.util.ArrayList;

import javax.swing.ImageIcon;


public class GameData implements java.io.Serializable{
	public ArrayList <NewPanel> gameSquares= new ArrayList <NewPanel>();
	public ArrayList <Space> spaceData = new ArrayList <Space>();
	public ArrayList <ImageIcon> playerIcons = new ArrayList <ImageIcon>();
	public ArrayList <Player> playersArray = new ArrayList <Player>();
	public ArrayList <Card> communityCardArray = new ArrayList<Card>();
	public ArrayList <Card> wildCardArray = new ArrayList<Card>();
	public Bank bankBalance = new Bank(2000);
	public int diceVal = 0;
	public int currentPlayer = 0;
	public Boolean initialized = false;
	public int numPlayers;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Size: "+playersArray.size();
	}
	public GameData(ArrayList <Player> array){
//		playersArray = array;
		if(array==null)
			numPlayers = 0;
		else{
			playersArray = array;
			System.out.println("Calling GameData without null array: "+playersArray.size());
			numPlayers = playersArray.size();
			initialized = true;
			Server.isInitialized = true;
		}
	}
	public ArrayList<NewPanel> getGameSquares() {
		return gameSquares;
	}
	public void setGameSquares(ArrayList<NewPanel> gameSquares) {
		this.gameSquares = gameSquares;
	}
	public ArrayList<Space> getSpaceData() {
		return spaceData;
	}
	public  void setSpaceData(ArrayList<Space> spaceData) {
		this.spaceData = spaceData;
	}
	public  ArrayList<ImageIcon> getPlayerIcons() {
		return playerIcons;
	}
	public  void setPlayerIcons(ArrayList<ImageIcon> playerIcons) {
		this.playerIcons = playerIcons;
	}
	public  ArrayList<Player> getPlayersArray() {
		return playersArray;
	}
	public  void setPlayersArray(ArrayList<Player> playersArray) {
		this.playersArray = playersArray;
	}
	public  ArrayList<Card> getCommunityCardArray() {
		return communityCardArray;
	}
	public  void setCommunityCardArray(ArrayList<Card> communityCardArray) {
		this.communityCardArray = communityCardArray;
	}
	public  ArrayList<Card> getWildCardArray() {
		return wildCardArray;
	}
	public  void setWildCardArray(ArrayList<Card> wildCardArray) {
		this.wildCardArray = wildCardArray;
	}
	public  Bank getBankBalance() {
		return bankBalance;
	}
	public  void setBankBalance(int balance) {
		this.bankBalance = new Bank(balance);
	}
	public  int getDiceVal() {
		return diceVal;
	}
	public  void setDiceVal(int diceVal) {
		this.diceVal = diceVal;
	}
	public  int getCurrentPlayer() {
		return currentPlayer;
	}
	public  void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public  int getTotalNumPlayers() {
		return totalNumPlayers;
	}
	public  void setTotalNumPlayers(int totalNumPlayers) {
		this.totalNumPlayers = totalNumPlayers;
	}
	public  int totalNumPlayers = 0;
}
