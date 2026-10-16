package apal.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import apal.core.Rental;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

	
public class RentalSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	
Random rand = new Random();

DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	

		
public static ArrayList<Rental> rentalList;
//Flow Pane		
FlowPane buttonFlowPane;
//Grid Panes
GridPane rootGridPane, inputFieldsPane, inputSearchField;
//Second Scene buttons
Button newRentalBtn, deleteRentalBtn, backBtn;
//Second scene labels
Label serialForCarLbl, clientNameLbl , pickUpStoreLbl, pickUpDateTimeLbl, DropOffStoreLbl, returnDateTimeLbl, totalCostLbl;
//Second scene TextFields
TextField serialForCarField, clientNameField, pickUpStoreField, pickUpDateTimeField, DropOffStoreField, returnDateTimeField, totalCostField;
//TableView
public static TableView<Rental> rentalTableView;
		
public RentalSceneCreator (double width, double height) {
	super (width, height);
			
	rentalList = new ArrayList<>();	
	rootGridPane = new GridPane();
	buttonFlowPane = new FlowPane();
	serialForCarLbl = new Label("Serial Number:");
	serialForCarField = new TextField();
	clientNameLbl =  new Label("Client Name:");
	clientNameField = new TextField();
	pickUpStoreLbl = new Label("Pickup Store:");
	pickUpStoreField = new TextField();
	pickUpDateTimeLbl = new Label("Pick Up Date Time:");
	pickUpDateTimeField = new TextField();
	pickUpDateTimeField.setPromptText("dd/MM/yyyy hh:mm");
	DropOffStoreLbl = new Label("DropOff Store:");
	DropOffStoreField = new TextField();
	returnDateTimeLbl = new Label("Return Date Time:");
	returnDateTimeField = new TextField();
	returnDateTimeField.setPromptText("dd/MM/yyyy hh:mm");
	newRentalBtn = new Button("New rental");
	deleteRentalBtn = new Button("Delete rental");
	backBtn = new Button("Go Back");
	inputFieldsPane = new GridPane();
	rentalTableView = new TableView<>();
	inputSearchField = new GridPane();

			
	backBtn.setOnMouseClicked(this);
	newRentalBtn.setOnMouseClicked(this);
	deleteRentalBtn.setOnMouseClicked(this);
	rentalTableView.setOnMouseClicked(this);

			
			
	buttonFlowPane.setHgap(10);
	buttonFlowPane.getChildren().add(newRentalBtn);
	buttonFlowPane.getChildren().add(deleteRentalBtn);
	buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
	
	//Customize gridPane
	inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
	inputFieldsPane.setVgap(10);
	inputFieldsPane.setHgap(10);
			
	inputFieldsPane.add(serialForCarLbl, 0, 0);
	inputFieldsPane.add(serialForCarField, 1, 0 );
	
	inputFieldsPane.add(clientNameLbl, 0, 1);
	inputFieldsPane.add(clientNameField, 1, 1);
			
	inputFieldsPane.add(pickUpStoreLbl, 0, 2);
	inputFieldsPane.add(pickUpStoreField,1, 2);
			
	inputFieldsPane.add(pickUpDateTimeLbl, 0, 3);
	inputFieldsPane.add(pickUpDateTimeField,1, 3);
			
	inputFieldsPane.add(DropOffStoreLbl, 0, 4);
	inputFieldsPane.add(DropOffStoreField,1, 4);
			
	inputFieldsPane.add(returnDateTimeLbl, 0, 5);
	inputFieldsPane.add(returnDateTimeField, 1, 5);

	//inputFieldsPane.add(totalCostLbl, 0, 6);
	//inputFieldsPane.add(totalCostField,1, 6);
			
			
			
	
		
	//Customize rootScene2
	//rootGridPane.setGridLinesVisible(true);
	rootGridPane.setVgap(10);
	rootGridPane.setHgap(10);
	rootGridPane.add(inputFieldsPane, 1, 0);
	rootGridPane.add(rentalTableView, 0, 0);
	rootGridPane.add(buttonFlowPane, 0, 2);
	rootGridPane.add(backBtn, 1, 2);

	
			
	TableColumn<Rental, String> serialPlateColumn = new TableColumn<>("Serial Plate");
	serialPlateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalVehicle"));
	rentalTableView.getColumns().add(serialPlateColumn);
			
	TableColumn<Rental, String> clientNColumn = new TableColumn<>("Client Name");
	clientNColumn.setCellValueFactory(new PropertyValueFactory<>("rentalClient"));
	rentalTableView.getColumns().add(clientNColumn);
	
	TableColumn<Rental, String> rcodeColumn = new TableColumn<>("Rental Code");
	rcodeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalCode"));
	rentalTableView.getColumns().add(rcodeColumn);
	
	TableColumn<Rental, String> rStoreColumn = new TableColumn<>("Rental Store");
	rStoreColumn.setCellValueFactory(new PropertyValueFactory<>("rentalStore"));
	rentalTableView.getColumns().add(rStoreColumn);
	
	TableColumn<Rental, String> rDateTimeColumn = new TableColumn<>("Rental Date");
	rDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDateTime"));
	rentalTableView.getColumns().add(rDateTimeColumn);
	
	TableColumn<Rental, String> rStoreRColumn = new TableColumn<>("Return Store");
	rStoreRColumn.setCellValueFactory(new PropertyValueFactory<>("rentalStoreRe"));
	rentalTableView.getColumns().add(rStoreRColumn);
	
	TableColumn<Rental, String> rDateTimeRColumn = new TableColumn<>("Return Date Time");
	rDateTimeRColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDateTimeRe"));
	rentalTableView.getColumns().add(rDateTimeRColumn);
	
	TableColumn<Rental, String> rTCostColumn = new TableColumn<>("Total Cost");
	rTCostColumn.setCellValueFactory(new PropertyValueFactory<>("rentalTotalCost"));
	rentalTableView.getColumns().add(rTCostColumn);
	
	
			
			
		}
		
		
		
		
		@Override
		Scene createScene() {
			
			return new Scene(rootGridPane, width, height);
		}
		
		@Override
		public void handle(MouseEvent event) {
			
			if(event.getSource() == backBtn) {
				InfoFX.mainStage.setTitle("Car Rental Service");
				InfoFX.mainStage.setScene(InfoFX.mainScene);
			}
			
			if(event.getSource() == newRentalBtn )  {
				List<String> errors = new ArrayList<>();
				if (serialForCarField.getText().isBlank()) {
					errors.add("Enter a vehicle serial number.");
				}
				if (clientNameField.getText().isBlank()) {
					errors.add("Enter a client name.");
				}
				if (pickUpStoreField.getText().isBlank()) {
					errors.add("Enter a pickup store.");
				}
				if (pickUpDateTimeField.getText().isBlank()) {
					errors.add("Enter a pickup date and time.");
				}
				if (DropOffStoreField.getText().isBlank()) {
					errors.add("Enter a return store.");
				}
				if (returnDateTimeField.getText().isBlank()) {
					errors.add("Enter a return date and time.");
				}
				if (!errors.isEmpty()) {
					showError(String.join("\n", errors));
					return;
				}

				boolean vehicleExists = false;
				for (int i = 0; i < VehicleSceneCreator.vehicleList.size(); i++) {
					if (VehicleSceneCreator.vehicleList.get(i).getVehicleLicensePlate()
							.equals(serialForCarField.getText())) {
						vehicleExists = true;
						break;
					}
				}
				if (!vehicleExists) {
					errors.add("No vehicle matches that serial number. Enter the exact serial number of an existing vehicle.");
				}

				boolean clientExists = false;
				for (int i = 0; i < ClientSceneCreator.clientList.size(); i++) {
					if (ClientSceneCreator.clientList.get(i).getClientFullName()
							.equals(clientNameField.getText())) {
						clientExists = true;
						break;
					}
				}
				if (!clientExists) {
					errors.add("No client matches that name. Enter the exact full name of an existing client.");
				}

				boolean pickupStoreExists = false;
				boolean returnStoreExists = false;
				for (int i = 0; i < StoreSceneCreator.storeList.size(); i++) {
					String storeName = StoreSceneCreator.storeList.get(i).getStoreName();
					if (storeName.equals(pickUpStoreField.getText())) {
						pickupStoreExists = true;
					}
					if (storeName.equals(DropOffStoreField.getText())) {
						returnStoreExists = true;
					}
				}
				if (!pickupStoreExists) {
					errors.add("No pickup store matches that name. Enter the exact name of an existing store.");
				}
				if (!returnStoreExists) {
					errors.add("No return store matches that name. Enter the exact name of an existing store.");
				}

				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				LocalDateTime pickUpDT;
				LocalDateTime returnDT;
				try {
					pickUpDT = LocalDateTime.parse(pickUpDateTimeField.getText(), dateFormat);
					returnDT = LocalDateTime.parse(returnDateTimeField.getText(), dateFormat);
				} catch (java.time.format.DateTimeParseException exception) {
					showError("Enter both dates in the format dd/MM/yyyy HH:mm, for example 25/12/2026 14:30.");
					return;
				}
				if (returnDT.isBefore(pickUpDT)) {
					errors.add("The return date and time must not be before the pickup date and time.");
				}
				if (!errors.isEmpty()) {
					showError(String.join("\n", errors));
					return;
				}

				int mul = pickUpDT.isEqual(returnDT) ? 1 : 0;
				int rentalCode = rand.nextInt(1000);
				createRental(rentalCode, serialForCarField.getText(), clientNameField.getText(),
						pickUpStoreField.getText(), pickUpDT, DropOffStoreField.getText(), returnDT, 1, mul);
				tableSync();
				clearTextFields();
			}
			
			
			
			if(event.getSource() == deleteRentalBtn) {
				Rental selectedRental = rentalTableView.getSelectionModel().getSelectedItem();
				if (selectedRental == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Cannot delete rental");
					alert.setHeaderText(null);
					alert.setContentText("Select a rental to delete.");
					alert.showAndWait();
					return;
				}

				LocalDateTime LDTN = LocalDateTime.now();
				int daysB = (int)ChronoUnit.DAYS.between(LDTN, selectedRental.getRentalDateTime());
				if(daysB > 2) {
					deleteRental(selectedRental.getRentalCode());
				}
				tableSync();
				clearTextFields();
			
			}
			if(event.getSource() == rentalTableView) {
				Rental selectedRental = rentalTableView.getSelectionModel().getSelectedItem();
				if(selectedRental != null) {
					serialForCarField.setText(selectedRental.getRentalVehicle());
					pickUpDateTimeField.setText(String.valueOf(selectedRental.getRentalDateTime()));
				}
			}
		}

		private void showError(String message) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Cannot create rental");
			alert.setHeaderText("Please correct the following:");
			alert.setContentText(message);
			alert.showAndWait();
		}

		public void createRental(int rentalCode, String rentalVehicle, String rentalClient, String rentalStore,
				LocalDateTime rentalDateTime, String rentalStoreRe, LocalDateTime rentalDateTimeRe, float rentalTotalCost,
				int mul) {
			float c = 1;
			for(int i = 0; i < VehicleSceneCreator.vehicleList.size(); i++) {
				if(VehicleSceneCreator.vehicleList.get(i).getVehicleLicensePlate().equals(rentalVehicle)) {
					c = VehicleSceneCreator.vehicleList.get(i).getVehicleDayCost(); 
					break;
				}
			}
			
			long daysBetween = ChronoUnit.DAYS.between(rentalDateTime, rentalDateTimeRe);
			
			if(mul == 0) {
				rentalTotalCost = c * daysBetween;
			}
			else {
				rentalTotalCost = c;
			}
			
			Rental d = new Rental(rentalCode, rentalVehicle, rentalClient, rentalStore, rentalDateTime,
					rentalStoreRe, rentalDateTimeRe, rentalTotalCost);
			rentalList.add(d);
		}

		public void deleteRental(int k) {
			for(int i = 0; i < rentalList.size(); i++) {
				if(rentalList.get(i).getRentalCode() == k) {
					rentalList.remove(i);
					break;
				}
			}
			
		}



		public void tableSync() {
			List<Rental> rentals = rentalTableView.getItems();
			rentals.clear();
			for(Rental d : rentalList) {
				if(d instanceof Rental) {
					rentals.add((Rental) d);
				}
			}
		}
		
		public void clearTextFields() {
			serialForCarField.setText("");
		    clientNameField.setText("");
			pickUpStoreField.setText("");
			pickUpDateTimeField.setText("");
			DropOffStoreField.setText("");
			returnDateTimeField.setText("");
			
		}
	
	}
