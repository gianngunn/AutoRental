package apal.core;

public class Client {
	
	private String clientID;
	private String clientDrivingL;
	private String clientFullName;
	private String clientEmail;
	private String clientAddress;
	private int clientPhoneN;
	
	public Client(String clientID, String clientDrivingL, String clientFullName, String clientEmail,
			String clientAddress, int clientPhoneN) {
		super();
		this.clientID = clientID;
		this.clientDrivingL = clientDrivingL;
		this.clientFullName = clientFullName;
		this.clientEmail = clientEmail;
		this.clientAddress = clientAddress;
		this.clientPhoneN = clientPhoneN;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientDrivingL() {
		return clientDrivingL;
	}

	public void setClientDrivingL(String clientDrivingL) {
		this.clientDrivingL = clientDrivingL;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public int getClientPhoneN() {
		return clientPhoneN;
	}

	public void setClientPhoneN(int clientPhoneN) {
		this.clientPhoneN = clientPhoneN;
	}
	
	
	
}
