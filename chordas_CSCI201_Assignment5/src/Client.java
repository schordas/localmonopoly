import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class Client extends Thread{
	static Game_Board GB; 
	static volatile GameData GD;
	static Player clientPlayer;
	static Socket s;
	static int clientNum = 0;
	static ConnectionThread CT;
	String hostname;
	int port;
	public static void setImages(){
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
	public Client(String hostname, int port){
		System.out.println("Client Started");
		this.hostname = hostname;
		this.port = port;
		GD = new GameData(null);
	}
	public static void main (String [] args){
		Client client = new Client("127.0.0.1", 10000);
		client.start();
	}
	@Override
	public void run() {
		System.out.println("RUN Started");
		CT = new ConnectionThread(hostname, port);
		CT.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		CT.send(new Messages(Messages.ClientEnum.test, "is the server listening"));
		CT.send(new Messages(Messages.ClientEnum.loggedOn, "HELLO WORLD"));
	}
	public static void send(Messages m){
		CT.send(m);
	}
}
class ConnectionThread extends Thread{
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Socket s;
	boolean isAlive;
	@Override
	public void run() {
		try {
			while (true) {
				Messages m;
				while ((m = (Messages) ois.readObject()) != null) {
					//				while(m!=null){
//										System.out.println(m.object + "check");
					System.out.println(m.serverType+": "+m.object);
					if(m.serverType==Messages.ServerEnum.sendingGameData){
						GameData gd = (GameData)m.object;	
						System.out.println("Printing GD: "+ gd);
						Client.GD = gd;
						Client.GB = new Game_Board();
					}
					else if (m.serverType==Messages.ServerEnum.sendGrpMessage){
						String str = (String)m.object;
						int num = Integer.parseInt(str.substring(0, 1));
						str = str.substring(1);
						Client.GB.grpMessage(str, num);
					}
					else if (m.serverType==Messages.ServerEnum.sendIndMessage){
						String str = (String)m.object;
						String from = str.substring(0,1);
						str = str.substring(1);
						String to = str.substring(0,1);
						str = str.substring(1);
						int recipient = Integer.parseInt(to);
						if(recipient == Client.clientNum)
							Client.GB.indMessage(str,from);
					}
					else if (m.serverType==Messages.ServerEnum.setClientNum){
						Integer i = (Integer)m.object;
						Client.clientNum = i;
					}
					else if(m.serverType==Messages.ServerEnum.move){
						Integer i = (Integer)m.object;
						System.out.println("Moved "+i);
						Client.GB.move(i);
					}
					else if(m.serverType == Messages.ServerEnum.updatePlayerBalance){
						Integer i = (Integer)m.object;
						Client.GB.updateGUIBalance();
					}
					else if(m.serverType == Messages.ServerEnum.updateBankBalance){
						Integer i = (Integer)m.object;
						Client.GD.setBankBalance(i);
						Client.GB.updateGUIBalance();
					}
					else if(m.serverType==Messages.ServerEnum.move){
						Integer i = (Integer)m.object;
						System.out.println("Current Location: " + i);
						Client.GB.setSpaceOwner(i);
					}
					else if(m.serverType==Messages.ServerEnum.setUpgrade){
						Integer i = (Integer)m.object;
						Client.GB.setUpgrade(i);
					}
					else if(m.serverType==Messages.ServerEnum.setRent){
						Integer i = (Integer)m.object;
						Client.GB.setRent(i);
					}
					else if(m.serverType==Messages.ServerEnum.updateLandLord){
						Integer i = (Integer)m.object;
						Client.GD.playersArray.get(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner() - 1)
						.setBalance(i);
						Client.GB.updateLandLordBalance(Client.GD.spaceData.get(Client.GD.playersArray.get(Client.GD.currentPlayer - 1).getCurrentLocation()).getOwner());
					}
					else if(m.serverType==Messages.ServerEnum.sendingIsInitialized){
						String s = (String)m.object;	
						System.out.println("Printing isInitialized: "+ s);
						if(s.compareToIgnoreCase("YES")==0){
							//ask for server to send game data
							send(new Messages(Messages.ClientEnum.getGameData, null));
						}
						else{
							//choose players
							Client.setImages();
							Object numPlayers[] = {2, 3, 4};
							Object defaultNum = 2;
							final Object players = JOptionPane.showInputDialog(null, "Choose Desired Number of Players", "Welcome to Monop-L.A.", 
									JOptionPane.INFORMATION_MESSAGE, null, numPlayers, defaultNum);
							if (players == null){
								System.exit(1);

							}
							Client.GD.totalNumPlayers = Integer.parseInt(players.toString());

							ArrayList<Integer> selectedImages = new ArrayList<Integer>();
							for (int i = 0; i < Integer.parseInt(players.toString()); i++) {
								Client.GD.playersArray.add(new Player());
								Client.GD.playersArray.get(i).setNumber(i);
								Client.GD.playersArray.get(i).setCurrentLocation(0);
								if (Integer.parseInt(players.toString()) == 4)
									Client.GD.playersArray.get(i).setBalance(2000);
								else if (Integer.parseInt(players.toString()) == 3)
									Client.GD.playersArray.get(i).setBalance(2666);
								else 
									Client.GD.playersArray.get(i).setBalance(3000);
								final int player = i;
								JPanel p = new JPanel(new GridLayout(2, 4));
								ArrayList<JRadioButton> rb = new ArrayList<JRadioButton>();
								ButtonGroup group1 = new ButtonGroup();

								HashMap<Integer, Integer> iconImageMap = new HashMap<Integer, Integer>();
								for (int j = 0; j < Client.GD.playerIcons.size(); j++) {
									if (selectedImages.contains(j))
										continue;
									rb.add(new JRadioButton(Client.GD.playerIcons.get(j)));
									group1.add(rb.get(rb.size()-1));
									iconImageMap.put(rb.size()-1, j);
								}

								for (int j = 0; j < rb.size(); j++) {
									final int index = j;
									final ButtonGroup tmp = group1;
									final HashMap<Integer, Integer> tmpMap = iconImageMap;

									rb.get(index).addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											Client.GD.playersArray.get(player).setImage(tmpMap.get(index));
											for (Enumeration<AbstractButton> buttons = tmp.getElements(); buttons.hasMoreElements();) {
												AbstractButton button = buttons.nextElement();

												if (button.isSelected()) {
													button.setBackground(Color.red);
													button.setOpaque(true);
												} else {
													button.setBackground(null);
												}
											}
										}
									});
									p.add(rb.get(index));
								}
								rb.get(0).doClick();
								JOptionPane.showMessageDialog(null, p, "Player " + (i+1) + 
										" Choose Your Game Piece", JOptionPane.INFORMATION_MESSAGE);
								selectedImages.add(Client.GD.playersArray.get(i).getImage());
							}
							send(new Messages(Messages.ClientEnum.sendInitializeData, Client.GD.playersArray));
						}

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public ConnectionThread(String hostname, int port){
		try {
			s = new Socket(hostname, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isAlive = true;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(s.getInputStream());



		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public void send(Messages m){
		System.out.println(oos);
		try {
			oos.writeObject(m);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
