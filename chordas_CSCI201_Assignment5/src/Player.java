//Samuel Chordas 2652701844
import java.awt.Image;


public class Player implements java.io.Serializable{
	int number = 0;
	int balance = 0;
	int currentLocation = 0;
	int image;
	private boolean isOut = false;
	public boolean isOut() {
		return isOut;
	}
	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void setCurrentLocation(int currentLocation){
		if ((this.currentLocation + currentLocation) == 40){
			this.currentLocation = 0;
		}
		else if ((this.currentLocation + currentLocation) > 40){
			this.currentLocation = this.currentLocation + currentLocation - 40;
		}
		else
			this.currentLocation = this.currentLocation + currentLocation;
	}
	public int getCurrentLocation(){
		return this.currentLocation;
	}
	public void setImage(int num) {
		image = num;
	}
	
	public int getImage() {
		return image;
	}

}
