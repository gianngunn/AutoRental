package apal.gui;

import apal.core.Rental;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import apal.service.RentalService;
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
	
private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

private final RentalService rentalService;
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
		
public RentalSceneCreator (double width, double height, RentalService rentalService) {
	super (width, height);
	this.rentalService = rentalService;
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
	pickUpDateTimeField.setPromptText("dd/MM/yyyy HH:mm");
	DropOffStoreLbl = new Label("DropOff Store:");
	DropOffStoreField = new TextField();
	returnDateTimeLbl = new Label("Return Date Time:");
	returnDateTimeField = new TextField();
	returnDateTimeField.setPromptText("dd/MM/yyyy HH:mm");
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
				LocalDateTime pickUpDT;
				LocalDateTime returnDT;
				try {
					pickUpDT = LocalDateTime.parse(pickUpDateTimeField.getText(), DATE_FORMAT);
					returnDT = LocalDateTime.parse(returnDateTimeField.getText(), DATE_FORMAT);
				} catch (java.time.format.DateTimeParseException exception) {
					showError("Enter both dates in the format dd/MM/yyyy HH:mm, for example 25/12/2026 14:30.");
					return;
				}
				if (runServiceAction("Cannot create rental", () -> rentalService.create(
						serialForCarField.getText(), clientNameField.getText(), pickUpStoreField.getText(),
						pickUpDT, DropOffStoreField.getText(), returnDT))) {
					refreshTable();
					clearTextFields();
				}
			}
			
			
			
			if(event.getSource() == deleteRentalBtn) {
				Rental selectedRental = rentalTableView.getSelectionModel().getSelectedItem();
				if (selectedRental == null) {
					runServiceAction("Cannot delete rental", () -> {
						throw new IllegalArgumentException("Select a rental to delete.");
					});
				} else if (runServiceAction("Cannot delete rental",
						() -> rentalService.delete(selectedRental.getRentalCode()))) {
					refreshTable();
					clearTextFields();
				}
			
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

		private void refreshTable() {
			rentalTableView.getItems().setAll(rentalService.getAll());
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
