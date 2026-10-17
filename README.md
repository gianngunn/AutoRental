AutoRental
A JavaFX desktop app for managing the records of a vehicle-rental service.

What it manages
The main menu provides catalogues for cities, stores, clients, vehicles, and rentals. The app starts from InfoFX; each catalogue screen is built in apal.gui.

Main data types
Classes in apal.core represent cities, stores, clients, rentals, and vehicles. Vehicles have two types: Car and TwoWheeled.

A rental records its vehicle, client, pickup and return stores, dates, and total cost. The cost is calculated from the vehicle’s daily rate and rental duration.

Creating a rental
A rental is added only when:

All required fields are filled in.
The vehicle’s serial number matches an existing vehicle.
The client name matches an existing client’s full name.
Both pickup and return store names match existing stores.
Both dates use the format dd/MM/yyyy HH:mm.
The return time is not before the pickup time.
Names and serial numbers must match exactly, including capitalization and spaces. If a check fails, the app displays an error message. Rental codes are randomly selected from 0–999 and checked against existing rentals to avoid duplicates.

Running and data
The workspace is configured for JDK 25 and JavaFX 27. Records are kept in memory, so they are lost when the app closes.

This version reflects the improvements on the version-2-AutoRental branch.
