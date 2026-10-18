package apal.service;

import java.util.ArrayList;
import java.util.List;

import apal.core.Car;
import apal.core.TwoWheeled;
import apal.core.Vehicle;

public class VehicleService {
	private final List<Vehicle> vehicles = new ArrayList<>();

	public List<Vehicle> getAll() {
		return List.copyOf(vehicles);
	}

	public List<Vehicle> findByType(String type) {
		if ("Car".equals(type)) {
			return vehicles.stream().filter(Car.class::isInstance).toList();
		}
		if ("TwoWheeled".equals(type)) {
			return vehicles.stream().filter(TwoWheeled.class::isInstance).toList();
		}
		return getAll();
	}

	public Car addCar(String plate, String model, String fuel, String type, String engine,
			float dayCost, int seats, int doors) {
		requireNewPlate(plate);
		Car car = new Car(plate, model, fuel, type, engine, dayCost, seats, doors);
		vehicles.add(car);
		return car;
	}

	public TwoWheeled addTwoWheeled(String plate, String model, String fuel, String type, String engine,
			float dayCost, float seatHeight, String luggageSystem) {
		requireNewPlate(plate);
		TwoWheeled vehicle = new TwoWheeled(plate, model, fuel, type, engine, dayCost, seatHeight, luggageSystem);
		vehicles.add(vehicle);
		return vehicle;
	}

	public void updateCar(String plate, String model, String fuel, String type, String engine,
			float dayCost, int seats, int doors) {
		Vehicle vehicle = findByPlate(plate);
		if (!(vehicle instanceof Car car)) {
			throw new IllegalArgumentException("Select a car to update.");
		}
		car.setVehicleModel(model);
		car.setVehicleFuel(fuel);
		car.setVehicleType(type);
		car.setVehicleEngine(engine);
		car.setVehicleDayCost(dayCost);
		car.setSeatNumber(seats);
		car.setDoorsNumber(doors);
	}

	public void updateTwoWheeled(String plate, String model, String fuel, String type, String engine,
			float dayCost, float seatHeight, String luggageSystem) {
		Vehicle vehicle = findByPlate(plate);
		if (!(vehicle instanceof TwoWheeled twoWheeled)) {
			throw new IllegalArgumentException("Select a two-wheeled vehicle to update.");
		}
		twoWheeled.setVehicleModel(model);
		twoWheeled.setVehicleFuel(fuel);
		twoWheeled.setVehicleType(type);
		twoWheeled.setVehicleEngine(engine);
		twoWheeled.setVehicleDayCost(dayCost);
		twoWheeled.setSeatHeight(seatHeight);
		twoWheeled.setLuggageSystem(luggageSystem);
	}

	public void delete(String plate) {
		vehicles.remove(findByPlate(plate));
	}

	private void requireNewPlate(String plate) {
		requireText(plate, "Vehicle license plate is required.");
		if (vehicles.stream().anyMatch(vehicle -> vehicle.getVehicleLicensePlate().equals(plate))) {
			throw new IllegalArgumentException("A vehicle with that license plate already exists.");
		}
	}

	private Vehicle findByPlate(String plate) {
		return vehicles.stream()
				.filter(vehicle -> vehicle.getVehicleLicensePlate().equals(plate))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Vehicle was not found."));
	}

	private static void requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}
