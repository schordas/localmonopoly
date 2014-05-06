//Samuel Chordas 2652701844
public class Card implements java.io.Serializable{
	String title = "";
	String message = "";
	int cost = 0;
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setCost(int cost){
		this.cost = cost;
	}
	public int getCost(){
		return this.cost;
	}

}
