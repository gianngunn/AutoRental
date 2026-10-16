package apal.core;

import java.time.LocalDateTime;


public class Rental {
	
	private int rentalCode;
	private String rentalVehicle;
	private String rentalClient;
	private String rentalStore;
	private LocalDateTime rentalDateTime;
	
	private String rentalStoreRe;
	private LocalDateTime rentalDateTimeRe;
	
	private float rentalTotalCost;
	
	public Rental(int rentalCode, String rentalVehicle, String rentalClient, String rentalStore, LocalDateTime rentalDateTime,
			 String rentalStoreRe, LocalDateTime rentalDateTimeRe,  float rentalTotalCost) {
		super();
		this.rentalCode = rentalCode;
		this.rentalVehicle = rentalVehicle;
		this.rentalClient = rentalClient;
		this.rentalStore = rentalStore;
		this.rentalDateTime = rentalDateTime;
	
		this.rentalStoreRe = rentalStoreRe;
		this.rentalDateTimeRe = rentalDateTimeRe;
		
		this.rentalTotalCost = rentalTotalCost;
	}

	public int getRentalCode() {
		return rentalCode;
	}

	public void setRentalCode(int rentalCode) {
		this.rentalCode = rentalCode;
	}

	public String getRentalVehicle() {
		return rentalVehicle;
	}

	public void setRentalVehicle(String rentalVehicle) {
		this.rentalVehicle = rentalVehicle;
	}

	public String getRentalClient() {
		return rentalClient;
	}

	public void setRentalClient(String rentalClient) {
		this.rentalClient = rentalClient;
	}

	public String getRentalStore() {
		return rentalStore;
	}

	public void setRentalStore(String rentalStore) {
		this.rentalStore = rentalStore;
	}

	public LocalDateTime getRentalDateTime() {
		return rentalDateTime;
	}

	public void setRentalDateTime(LocalDateTime rentalDateTime) {
		this.rentalDateTime = rentalDateTime;
	}


	public String getRentalStoreRe() {
		return rentalStoreRe;
	}

	public void setRentalStoreRe(String rentalStoreRe) {
		this.rentalStoreRe = rentalStoreRe;
	}

	public LocalDateTime getRentalDateTimeRe() {
		return rentalDateTimeRe;
	}

	public void setRentalDateRe(LocalDateTime rentalDateTimeRe) {
		this.rentalDateTimeRe = rentalDateTimeRe;
	}


	public float getRentalTotalCost() {
		return rentalTotalCost;
	}

	public void setRentalTotalCost(float rentalTotalCost) {
		this.rentalTotalCost = rentalTotalCost;
	}
	
	
	
}
