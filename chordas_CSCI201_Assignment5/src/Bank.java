//Samuel Chordas 2652701844
public class Bank implements java.io.Serializable{
	
	int bankBalance = 0;
public Bank(int bankBalance){
		this.bankBalance = bankBalance;
	}
	public void setBankBalance(int bankBalance){
		this.bankBalance = bankBalance;
	}
	public int getBankBalance(){
		return this.bankBalance;
	}

}
