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
				
				if(!(serialForCarField.getText().isBlank())){
					int randomCode = rand.nextInt(1000);
					int k = 0, l = 0, j = 0, t = 0, mul = 0;
					for(int i = 0; i < VehicleSceneCreator.vehicleList.size(); i++) {
						if(VehicleSceneCreator.vehicleList.get(i).getVehicleLicensePlate().equals(serialForCarField.getText())) {
							k = 1;
							break;
						}
					}
					
					for(int i = 0; i < StoreSceneCreator.storeList.size(); i++) {
						if(StoreSceneCreator.storeList.get(i).getStoreName().equals(pickUpStoreField.getText())) {
							l = 1;
							break;
						}
					}
					
					for(int i = 0; i < ClientSceneCreator.clientList.size(); i++) {
						if(ClientSceneCreator.clientList.get(i).getClientFullName().equals(clientNameField.getText())) {
							j = 1;
							break;
						}
					}
					DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
					LocalDateTime pickUpDT = LocalDateTime.parse(pickUpDateTimeField.getText(), dateFormat);
					LocalDateTime returnDT = LocalDateTime.parse(returnDateTimeField.getText(), dateFormat);
					if(pickUpDT.isBefore(returnDT) || pickUpDT.isEqual(returnDT)){
						t = 1;
					}
					if(pickUpDT.isEqual(returnDT)) {mul = 1;}
					
					if(k == 1 && l == 1 && j == 1 && t == 1) {
						int rentalCode = randomCode;
						String rentalVehicle = serialForCarField.getText();
						String rentalClient = clientNameField.getText();
						String rentalStore = pickUpStoreField.getText();
						LocalDateTime rentalDateTime = pickUpDT;
						String rentalStoreRe = DropOffStoreField.getText();
						LocalDateTime rentalDateTimeRe = returnDT;
					    float rentalTotalCost = 1;
					    createRental(rentalCode, rentalVehicle, rentalClient, rentalStore,
					    		rentalDateTime, rentalStoreRe, rentalDateTimeRe, rentalTotalCost, mul);
					    tableSync();
						clearTextFields();
					}
				}
			}
			
			
			
			if(event.getSource() == deleteRentalBtn) {
				LocalDateTime LDTN = LocalDateTime.now();
				
				int daysB = (int)ChronoUnit.DAYS.between(LDTN, LocalDateTime.parse(pickUpDateTimeField.getText()));
				int k = 0;
				Rental selectedCode = rentalTableView.getSelectionModel().getSelectedItem();
				if(selectedCode != null) {
					k = selectedCode.getRentalCode();
				}
				if(daysB > 2) {
					deleteRental(k);
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
