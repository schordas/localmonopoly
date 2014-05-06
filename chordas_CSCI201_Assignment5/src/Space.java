//Samuel Chordas 2652701844
public class Space implements java.io.Serializable{
	String property = "";
	boolean isWildCard = false;
	boolean isSafe = false;
	boolean isCommunity = false;
	int owner = 0;
	int value = 0;
	int upgradeCost = 0;
	int rent = 0;
	public String getProperty() {
		return property;
	}
	public void setWildCard(boolean isWildCard){
		this.isWildCard = isWildCard;
		this.isCommunity = false;
		this.isSafe = false;
	}
	public boolean getWildCard(){
		return this.isWildCard;
	}
	public void setSafe(boolean isSafe){
		this.isWildCard = false;
		this.isCommunity = false;
		this.isSafe = isSafe;
	}
	public boolean getSafe(){
		return this.isSafe;
	}
	public void setCommunity(boolean isCommunity){
		this.isWildCard = false;
		this.isCommunity = isCommunity;
		this.isSafe = false;
	}
	public boolean getCommunity(){
		return this.isCommunity;
	}
	public void setOwner(int owner){
		this.owner = owner;
	}
	public int getOwner(){
		return this.owner;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getUpgradeCost() {
		return upgradeCost;
	}
	public void setUpgradeCost(int upgradeCost) {
		if(this.upgradeCost != 0){
			upgradeCost = (int)(upgradeCost + upgradeCost*.20);
		}
		this.upgradeCost = upgradeCost;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	

}
