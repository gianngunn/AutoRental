package apal.core;

public abstract class Vehicle {

	private String vehicleLicensePlate;
	private String vehicleModel;
	private String vehicleFuel;
	private String vehicleType;
	private String vehicleEngine;
	private float vehicleDayCost;
	
	Vehicle(String vehicleLicensePlate, String vehicleModel, String vehicleFuel, String vehicleType,
			String vehicleEngine, float vehicleDayCost) {
		super();
		this.vehicleLicensePlate = vehicleLicensePlate;
		this.vehicleModel = vehicleModel;
		this.vehicleFuel = vehicleFuel;
		this.vehicleType = vehicleType;
		this.vehicleEngine = vehicleEngine;
		this.vehicleDayCost = vehicleDayCost;
	}

	public String getVehicleLicensePlate() {
		return vehicleLicensePlate;
	}

	public void setVehicleLicensePlate(String vehicleLicensePlate) {
		this.vehicleLicensePlate = vehicleLicensePlate;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleFuel() {
		return vehicleFuel;
	}

	public void setVehicleFuel(String vehicleFuel) {
		this.vehicleFuel = vehicleFuel;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleEngine() {
		return vehicleEngine;
	}

	public void setVehicleEngine(String vehicleEngine) {
		this.vehicleEngine = vehicleEngine;
	}

	public float getVehicleDayCost() {
		return vehicleDayCost;
	}

	public void setVehicleDayCost(float vehicleDayCost) {
		this.vehicleDayCost = vehicleDayCost;
	}
	
	
	
	
}
