# AutoRental — Version 2

A JavaFX desktop application for managing records for a vehicle-rental service.

## Features

- Manage catalogues for cities, stores, clients, vehicles, and rentals.
- Represent vehicles as cars or two-wheeled vehicles.
- Calculate rental costs using a vehicle's daily rate and rental duration.

## Project Structure

- `src/apal/core/` — data models for cities, stores, clients, vehicles, and rentals.
- `src/apal/gui/` — JavaFX application, catalogue screens, and menu images.
- `InfoFX` starts the application; `MainSceneCreator` builds the main menu.

## Version 2 Changes

Compared with `main`, Version 2 improves rental creation and deletion:

- **Rental form validation:** Checks required fields and verifies that the vehicle, client, pickup store, and return store match existing records. Names and serial numbers must match exactly, including capitalization and spaces.
- **Clear error messages:** Displays an alert when required information is missing, a record cannot be found, or a date/time value is invalid.
- **Date and time handling:** The prompts match the 24-hour `dd/MM/yyyy HH:mm` format. The return time cannot be before pickup; equal times are allowed.
- **Safer deletion:** Requires a rental to be selected and uses the selected rental's pickup date. Deletion is allowed only when pickup is more than two days away.
- **Duplicate-code checks:** Rental codes are randomly chosen from `0–999`; the app checks generated codes against existing rentals and retries if a duplicate is found.

## Current Limitations

- **Rental-code generation:** The generator makes up to 1,000 random attempts. Since it can pick the same used code more than once, it may fail to find an available code even when one remains.
- **Storage:** Records are kept in memory and are lost when the application closes.

## Planned for Version 3

- Improve rental-code generation so it reliably selects an unused code and detects when the entire code range is occupied.
- Add persistent storage so records remain available after the application closes.

## Requirements

The workspace is configured for JDK 25 and JavaFX 27.
