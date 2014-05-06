import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Server extends JFrame implements Runnable{// extends Thread {
	static ArrayList<ServerThread> threadList = new ArrayList<ServerThread>();
	static GameData GD;
	int port;
	static int playerCounter = 0;
	static boolean isInitialized = false;
	public Server(int port) {
		System.out.println("Server Started");
		this.port = port;
	}

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);
			
			while (true) {
				Socket s = ss.accept();
				ServerThread st = new ServerThread(s);
				threadList.add(st);
				playerCounter+=1;
				st.start();
				
			}
		} catch (IOException ioe) {
			System.out.println("IOException in Server constructor: "
					+ ioe.getMessage());
		}
	}
	public static void announceToEverybodyExcept(ServerThread st, Messages m){
		for(int a=0; a<threadList.size(); a++){
			if(threadList.get(a)!=st){
				threadList.get(a).send(m);
			}
		}
	}
	public static void main(String[] args) {
		Server server = new Server(10000);
		Thread t = new Thread(server);
		t.start();
	}

}

class ServerThread extends Thread {
	ObjectInputStream ois;
	ObjectOutputStream oos;
	private Socket s;
	

	public ServerThread(Socket s) {
		System.out.println("New Thread: " + s);
		this.s = s;
	}
	public void send(Messages m){
		try {
			oos.writeObject(m);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (true) {
				Messages m;
				while ((m = (Messages) ois.readObject()) != null) {
					send(new Messages(Messages.ServerEnum.setClientNum, Server.playerCounter));
					System.out.println(Server.threadList.size()+"->"+m.clientType+":"+m.object);
					if(m.clientType==Messages.ClientEnum.sendInitializeData && Server.isInitialized==false){
						ArrayList<Player> pArray = (ArrayList<Player>)m.object;
						Server.GD = new GameData(pArray);
						Server.GD.playersArray = pArray;
						System.out.println("Sending GameData: "+Server.GD);
						send(new Messages(Messages.ServerEnum.sendingGameData, Server.GD));
					}
					else if(m.clientType==Messages.ClientEnum.loggedOn){
						//check to see if GameData is initialized
						System.out.println("Initialized? "+Server.isInitialized);
						if(Server.isInitialized)
							send(new Messages(Messages.ServerEnum.sendingIsInitialized, "YES"));
						else
							send(new Messages(Messages.ServerEnum.sendingIsInitialized, "NO"));
					}
//					else if(m.clientType==Messages.ClientEnum.test){
//						System.out.println("SENT");
//					}
					else if(m.clientType==Messages.ClientEnum.getGameData){
						System.out.println("Sending initialized GameData: "+Server.GD);
						send(new Messages(Messages.ServerEnum.sendingGameData, Server.GD));
					}
					else if(m.clientType==Messages.ClientEnum.roll){
						Integer i = (Integer)m.object;
						Server.GD.playersArray.get(Server.GD.currentPlayer).setCurrentLocation(i);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.move, i));
					}
					else if(m.clientType==Messages.ClientEnum.updatePlayerBalance){
						Integer i = (Integer)m.object;
						Server.GD.playersArray.get(Server.GD.currentPlayer).setBalance(i);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.updatePlayerBalance, i));
					}
					else if(m.clientType==Messages.ClientEnum.updateBankBalance){
						Integer i = (Integer)m.object;
						Server.GD.setBankBalance(i);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.updateBankBalance, i));
					}
					else if(m.clientType==Messages.ClientEnum.setOwner){
						Integer i = (Integer)m.object;
						Server.GD.spaceData.get(i).setOwner(Client.GD.currentPlayer);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.setOwner, i));
					}
					else if(m.clientType==Messages.ClientEnum.sendIndMessage){
						String str = (String)m.object;
						String clientRecipient = str.substring(0, 1);
						String message = str.substring(1);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.sendIndMessage, str));
					}
					else if(m.clientType==Messages.ClientEnum.setUpgrade){
						Integer i = (Integer)m.object;
						Server.GD.spaceData.get(Server.GD.playersArray.get(Client.GD.currentPlayer).getCurrentLocation()).setUpgradeCost(i);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.setUpgrade, i));
					}
					else if(m.clientType==Messages.ClientEnum.setRent){
						Integer i = (Integer)m.object;
						Server.GD.spaceData.get(Server.GD.playersArray.get(Client.GD.currentPlayer).getCurrentLocation()).setRent(i);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.setRent, i));
					}
					else if(m.clientType==Messages.ClientEnum.updateLandLord){
						Integer i = (Integer)m.object;
						Server.GD.playersArray.get(Server.GD.spaceData.get(Server.GD.playersArray.get(Server.GD.currentPlayer).getCurrentLocation()).getOwner() - 1)
						.setBalance(i);
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.updateLandLord, i));
					}
					else if(m.clientType==Messages.ClientEnum.sendGrpMessage){
						String str = (String)m.object;
						Server.announceToEverybodyExcept(this, new Messages(Messages.ServerEnum.sendGrpMessage, str));
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}