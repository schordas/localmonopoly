
public class Messages implements java.io.Serializable{


	private static final long serialVersionUID = 1L;

	public Messages (ClientEnum clientType, Object object){
		this.clientType= clientType;
		this.object = object;
		this.send = Sender.client;
	}
	public Messages(ServerEnum serverType, Object object){
		this.serverType = serverType;
		this.object = object;
		this.send = Sender.server;
	}
	ClientEnum clientType;
	ServerEnum serverType;
	Object object;
	Sender send;
	public enum ClientEnum {
		buy, 
		upgrade, 
		forfeit, 
		roll,
		payRent,
		drawCommunity,
		drawWildCard,
		sendIndMessage,
		sendGrpMessage,
		test,
		sendInitializeData,
		loggedOn,
		getGameData,
		updatePlayerBalance,
		updateBankBalance,
		setOwner,
		setUpgrade,
		setRent,
		updateLandLord,
	}
	
	public enum ServerEnum {
		sendingGameData,
		sendingIsInitialized,
		move,
		updatePlayerBalance,
		updateBankBalance,
		setOwner,
		setUpgrade,
		setRent,
		updateLandLord,
		sendGrpMessage,
		setClientNum,
		sendIndMessage
	}
	
	public enum Sender{
		client,
		server
	}
	
	@Override
	public String toString() {
		String msg = "**********message" + send;
		if(send == Sender.client){
			msg += "client";
		}
		else
			msg += "server";
		// TODO Auto-generated method stub
		return msg;
	}
}
