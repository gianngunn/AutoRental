package apal.core;

public class TwoWheeled	extends Vehicle {
	
	private float seatHeight;
	private String luggageSystem;
	
	public TwoWheeled(String vehicleLicensePlate, String vehicleModel, String vehicleFuel, String vehicleType,
			String vehicleEngine, float vehicleDayCost, float seatHeight, String luggageSystem) {
		
		super( vehicleLicensePlate, vehicleModel, vehicleFuel, vehicleType, vehicleEngine, vehicleDayCost);
		
		this.seatHeight = seatHeight;
		this.luggageSystem = luggageSystem;
	
	}

	public float getSeatHeight() {
		return seatHeight;
	}

	public void setSeatHeight(float seatHeight) {
		this.seatHeight = seatHeight;
	}

	public String getLuggageSystem() {
		return luggageSystem;
	}

	public void setLuggageSystem(String luggageSystem) {
		this.luggageSystem = luggageSystem;
	}
	
	
	
}
