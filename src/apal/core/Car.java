package apal.core;

public class Car extends Vehicle {
	
	private int seatNumber;
	private int doorsNumber;
	
	public Car(String vehicleLicensePlate, String vehicleModel, String vehicleFuel, String vehicleType,
			String vehicleEngine, float vehicleDayCost, int seatNumber, int doorsNumber) {
		
		super(vehicleLicensePlate, vehicleModel, vehicleFuel, vehicleType, vehicleEngine, vehicleDayCost);
		
		this.seatNumber = seatNumber;
		this.doorsNumber = doorsNumber;
		
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getDoorsNumber() {
		return doorsNumber;
	}

	public void setDoorsNumber(int doorsNumber) {
		this.doorsNumber = doorsNumber;
	}
	
	
	
}
