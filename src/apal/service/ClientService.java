package apal.service;

import java.util.ArrayList;
import java.util.List;

import apal.core.Client;

public class ClientService {
	private final List<Client> clients = new ArrayList<>();

	public List<Client> getAll() {
		return List.copyOf(clients);
	}

	public List<Client> findById(String clientId) {
		return clients.stream()
				.filter(client -> client.getClientID().equals(clientId))
				.toList();
	}

	public List<Client> findByFullName(String fullName) {
		return clients.stream()
				.filter(client -> client.getClientFullName().equals(fullName))
				.toList();
	}

	public Client add(String clientId, String drivingLicense, String fullName, String email,
			String address, int phoneNumber) {
		requireText(clientId, "Client ID is required.");
		if (clients.stream().anyMatch(client -> client.getClientID().equals(clientId))) {
			throw new IllegalArgumentException("A client with that ID already exists.");
		}
		if (clients.stream().anyMatch(client -> client.getClientDrivingL().equals(drivingLicense))) {
			throw new IllegalArgumentException("A client with that driving license already exists.");
		}

		Client client = new Client(clientId, drivingLicense, fullName, email, address, phoneNumber);
		clients.add(client);
		return client;
	}

	public void update(String clientId, String fullName, String email, String address, int phoneNumber) {
		Client client = findById(clientId).stream().findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Select an existing client to update."));
		client.setClientFullName(fullName);
		client.setClientEmail(email);
		client.setClientAddress(address);
		client.setClientPhoneN(phoneNumber);
	}

	public void delete(String clientId) {
		Client client = findById(clientId).stream().findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Client was not found."));
		clients.remove(client);
	}

	private static void requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}
