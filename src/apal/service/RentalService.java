package apal.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import apal.core.Rental;
import apal.core.Vehicle;

public class RentalService {
	private static final int RENTAL_CODE_RANGE = 1000;

	private final List<Rental> rentals = new ArrayList<>();
	private final Random random = new Random();
	private final VehicleService vehicleService;
	private final ClientService clientService;
	private final StoreService storeService;

	public RentalService(VehicleService vehicleService, ClientService clientService, StoreService storeService) {
		this.vehicleService = vehicleService;
		this.clientService = clientService;
		this.storeService = storeService;
	}

	public List<Rental> getAll() {
		return List.copyOf(rentals);
	}

	public Rental create(String vehiclePlate, String clientName, String pickupStore, LocalDateTime pickupDateTime,
			String returnStore, LocalDateTime returnDateTime) {
		requireText(vehiclePlate, "Enter a vehicle serial number.");
		requireText(clientName, "Enter a client name.");
		requireText(pickupStore, "Enter a pickup store.");
		requireText(returnStore, "Enter a return store.");
		if (pickupDateTime == null || returnDateTime == null) {
			throw new IllegalArgumentException("Enter both pickup and return date/time values.");
		}

		Vehicle vehicle = vehicleService.getAll().stream()
				.filter(item -> item.getVehicleLicensePlate().equals(vehiclePlate))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						"No vehicle matches that serial number. Enter the exact serial number of an existing vehicle."));
		if (clientService.findByFullName(clientName).isEmpty()) {
			throw new IllegalArgumentException(
					"No client matches that name. Enter the exact full name of an existing client.");
		}
		if (storeService.getAll().stream().noneMatch(store -> store.getStoreName().equals(pickupStore))) {
			throw new IllegalArgumentException(
					"No pickup store matches that name. Enter the exact name of an existing store.");
		}
		if (storeService.getAll().stream().noneMatch(store -> store.getStoreName().equals(returnStore))) {
			throw new IllegalArgumentException(
					"No return store matches that name. Enter the exact name of an existing store.");
		}
		if (returnDateTime.isBefore(pickupDateTime)) {
			throw new IllegalArgumentException("The return date and time must not be before the pickup date and time.");
		}

		long daysBetween = ChronoUnit.DAYS.between(pickupDateTime, returnDateTime);
		float totalCost = daysBetween == 0 ? vehicle.getVehicleDayCost() : vehicle.getVehicleDayCost() * daysBetween;
		Rental rental = new Rental(generateUniqueRentalCode(), vehiclePlate, clientName, pickupStore, pickupDateTime,
				returnStore, returnDateTime, totalCost);
		rentals.add(rental);
		return rental;
	}

	public void delete(int rentalCode) {
		Rental rental = rentals.stream()
				.filter(item -> item.getRentalCode() == rentalCode)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Rental was not found."));
		long daysBeforePickup = ChronoUnit.DAYS.between(LocalDateTime.now(), rental.getRentalDateTime());
		if (daysBeforePickup <= 2) {
			throw new IllegalArgumentException("A rental can only be deleted more than two days before pickup.");
		}
		rentals.remove(rental);
	}

	private int generateUniqueRentalCode() {
		for (int attempt = 0; attempt < RENTAL_CODE_RANGE; attempt++) {
			int candidate = random.nextInt(RENTAL_CODE_RANGE);
			if (rentals.stream().noneMatch(rental -> rental.getRentalCode() == candidate)) {
				return candidate;
			}
		}
		throw new IllegalStateException("No unique rental code is available.");
	}

	private static void requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}
