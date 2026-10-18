package apal.service;

import java.util.ArrayList;
import java.util.List;

import apal.core.Store;

public class StoreService {
	private final List<Store> stores = new ArrayList<>();
	private final CityService cityService;
	private int nextCode = 1;

	public StoreService(CityService cityService) {
		this.cityService = cityService;
	}

	public List<Store> getAll() {
		return List.copyOf(stores);
	}

	public List<Store> findByCity(String cityName) {
		return stores.stream()
				.filter(store -> store.getStoreCity().equals(cityName))
				.toList();
	}

	public Store add(String name, String cityName) {
		requireText(name, "Store name is required.");
		requireCity(cityName);
		Store store = new Store(nextCode++, name, cityName);
		stores.add(store);
		return store;
	}

	public void rename(int code, String name) {
		requireText(name, "Store name is required.");
		Store store = findByCode(code);
		store.setStoreName(name);
	}

	public void delete(int code) {
		Store store = findByCode(code);
		stores.remove(store);
	}

	private Store findByCode(int code) {
		return stores.stream()
				.filter(store -> store.getStoreCode() == code)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Store was not found."));
	}

	private void requireCity(String cityName) {
		if (cityName == null || cityName.isBlank()
				|| cityService.findByName(cityName).isEmpty()) {
			throw new IllegalArgumentException("Enter the exact name of an existing city.");
		}
	}

	private static void requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}
