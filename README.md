# AutoRental

A JavaFX desktop application for managing the basic records of a vehicle-rental service.

## Features

- Manage catalogues for cities, stores, clients, vehicles, and rentals.
- Represent vehicles as cars or two-wheeled vehicles.
- Calculate rental costs using a vehicle’s daily rate and the rental duration.

## Project Structure

- `src/apal/core/` — data models for cities, stores, clients, vehicles, and rentals.
- `src/apal/gui/` — JavaFX application, catalogue screens, and menu images.
- `InfoFX` starts the application; `MainSceneCreator` builds its main menu.

## Rental Creation on `main`

The rental creation logic runs only when the serial-number field is not blank. A rental is added when:

1. The serial number matches an existing vehicle’s license plate.
2. The client name matches an existing client’s full name.
3. The pickup-store name matches an existing store.
4. Both date/time values parse using `dd/MM/yyyy HH:mm`.
5. The pickup time is before or equal to the return time.

Names and serial numbers are compared exactly, including capitalization and spaces.

**Current limitations:**

- The return-store name is saved as entered but is not checked against existing stores.
- If a vehicle, client, or pickup store does not match, the rental is silently not added.
- Invalid or empty date/time input can cause a parsing exception.
- Rental codes are randomly chosen from `0–999` without checking for duplicates.

## Requirements

The workspace is configured for JDK 25 and JavaFX 27. See `.vscode/settings.json`.

## Data Storage

Records are held in memory and are lost when the application closes.
