package apal.service;

import java.util.ArrayList;
import java.util.List;

import apal.core.City;

public class CityService {
	private final List<City> cities = new ArrayList<>();
	private int nextCode = 1;

	public List<City> getAll() {
		return List.copyOf(cities);
	}

	public List<City> findByName(String name) {
		return cities.stream()
				.filter(city -> city.getCityName().equals(name))
				.toList();
	}

	public City add(String name) {
		requireText(name, "City name is required.");
		City city = new City(nextCode++, name);
		cities.add(city);
		return city;
	}

	public void rename(int code, String name) {
		requireText(name, "City name is required.");
		City city = findByCode(code);
		city.setCityName(name);
	}

	public void delete(int code) {
		City city = findByCode(code);
		cities.remove(city);
	}

	private City findByCode(int code) {
		return cities.stream()
				.filter(city -> city.getCityCode() == code)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("City was not found."));
	}

	private static void requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}
