package apal.gui;

import java.util.ArrayList;
import java.util.List;

import apal.core.Car;
import apal.core.TwoWheeled;
import apal.core.Vehicle;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class VehicleSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	//List of vehicle
	public static ArrayList<Vehicle> vehicleList;
	//Flow Pane
	FlowPane buttonFlowPane, goFlowPane;
	//Grid Panes
	GridPane rootGridPane, inputFieldsPane, inputSearchField;
	//Vehicle Scene buttons
	Button newVehicleBtn, updateVehicleBtn, deleteVehicleBtn, backBtn, goBtn;
	//Vehicle scene labels
	Label licPlateLbl, modelLbl, fuelLbl, typeLbl, engineLbl, costLbl, seatHLbl, lugSystemLbl, seatNLbl, doorsNLbl;
	//Store scene TextFields
	TextField searchBox, licPlateField, modelField, fuelField, typeField, engineField, costField, seatHField, lugSystemField, seatNField, doorsNField;
	//ComboBox
	public static ComboBox<String> searchCombo, cCorTCombo;
	//TableView
	public static TableView<Vehicle> vehicleTableView;
	
	
	
	
	public VehicleSceneCreator(double width, double height){
		super(width, height);
		
		//Initialize fields
		vehicleList = new ArrayList<>();
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();
		goFlowPane = new FlowPane();
		licPlateLbl = new Label("Vehicle license plate: ");
		modelLbl = new Label("Vehicle model: ");
		fuelLbl = new Label("Vehicle fuel: ");
		typeLbl = new Label("Vehicle type: ");
		engineLbl = new Label("Vehicle engine: ");
		costLbl = new Label("Vehicle cost: ");
		licPlateField = new TextField();
		modelField = new TextField();
		fuelField = new TextField();
		typeField = new TextField();
		engineField = new TextField();
		costField = new TextField();
		
		
		seatHField = new TextField();
		lugSystemField = new TextField();
		seatHLbl = new Label("Seat height(for two wheeled only): ");
		lugSystemLbl = new Label("Luggage system(for two wheeled only):");
		
		seatNField = new TextField();
		doorsNField = new TextField();
		seatNLbl = new Label("Seat number(for four+ wheeled only):");
		doorsNLbl = new Label("Doors number(for four+ wheeled only):");
		
		newVehicleBtn = new Button("New Vehicle");
		updateVehicleBtn = new Button("Update");
		deleteVehicleBtn = new Button("Delete");
		backBtn = new Button("Go Back");
		goBtn = new Button("Go");
		inputFieldsPane = new GridPane();
		vehicleTableView = new TableView<>();
		inputSearchField = new GridPane();
		
		searchCombo = new ComboBox<>();
		searchCombo.getItems().add("Show all");
		searchCombo.getItems().add("TwoWheeled");
		searchCombo.getItems().add("Car");
		searchCombo.setPromptText("Select what you want to search");
		cCorTCombo = new ComboBox<>();
		cCorTCombo.getItems().add("TwoWheeled");
		cCorTCombo.getItems().add("Car");
		cCorTCombo.setPromptText("Select what you want to add");
		
		
		
		//Attach events
		backBtn.setOnMouseClicked(this);
		newVehicleBtn.setOnMouseClicked(this);
		updateVehicleBtn.setOnMouseClicked(this);
		deleteVehicleBtn.setOnMouseClicked(this);
		vehicleTableView.setOnMouseClicked(this);
		goBtn.setOnMouseClicked(this);
		
		//Customize flowPane
		buttonFlowPane.setHgap(10);
		buttonFlowPane.getChildren().add(newVehicleBtn);
		buttonFlowPane.getChildren().add(updateVehicleBtn);
		buttonFlowPane.getChildren().add(deleteVehicleBtn);
		buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
		
		
		//Customize gridPane
		inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
		inputFieldsPane.setVgap(10);
		inputFieldsPane.setHgap(10);
		inputFieldsPane.add(cCorTCombo, 0, 0);
		inputFieldsPane.add(licPlateLbl, 0, 1);
		inputFieldsPane.add(licPlateField, 1, 1);
		inputFieldsPane.add(modelLbl, 0, 2);
		inputFieldsPane.add(modelField, 1, 2);
		inputFieldsPane.add(fuelLbl, 0, 3);
		inputFieldsPane.add(fuelField, 1, 3);
		inputFieldsPane.add(typeLbl,0 , 4);
		inputFieldsPane.add(typeField,1 , 4);
		inputFieldsPane.add(engineLbl, 0, 5);
		inputFieldsPane.add(engineField, 1, 5);
		inputFieldsPane.add(costLbl,0 , 6);
		inputFieldsPane.add(costField,1 , 6);
		inputFieldsPane.add(seatHLbl, 0, 7);
		inputFieldsPane.add(seatHField, 1, 7);
		inputFieldsPane.add(lugSystemLbl, 0, 8);
		inputFieldsPane.add(lugSystemField, 1, 8);
		inputFieldsPane.add(seatNLbl, 0, 9);
		inputFieldsPane.add(seatNField, 1, 9);
		inputFieldsPane.add(doorsNLbl, 0, 10);
		inputFieldsPane.add(doorsNField, 1, 10);
		
		
		
		inputSearchField.setAlignment(Pos.TOP_LEFT);
		inputSearchField.setVgap(10);
		inputSearchField.setHgap(10);
		
		inputSearchField.add(searchCombo, 0, 0);
		inputSearchField.add(goBtn, 0, 1);
		inputSearchField.add(vehicleTableView, 0, 2);
		//Customize rootScene2
		//rootGridPane.setGridLinesVisible(true);
		rootGridPane.setVgap(10);
		rootGridPane.setHgap(10);
		rootGridPane.add(inputFieldsPane, 1, 0);
		//rootGridPane.add(vehicleTableView, 0, 1);							
		rootGridPane.add(buttonFlowPane, 0, 2);
		rootGridPane.add(backBtn, 1, 2);
		rootGridPane.add(inputSearchField, 0,0);
	
		
		//Customize tableView
		TableColumn<Vehicle, String> licPlateColumn = new TableColumn<>("License Plate");
		licPlateColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleLicensePlate"));
		vehicleTableView.getColumns().add(licPlateColumn);
		
		TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
		vehicleTableView.getColumns().add(modelColumn);
		
		TableColumn<Vehicle, String> fuelColumn = new TableColumn<>("Fuel");
		fuelColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleFuel"));
		vehicleTableView.getColumns().add(fuelColumn);
		
		TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
		vehicleTableView.getColumns().add(typeColumn);
		
		TableColumn<Vehicle, String> engineColumn = new TableColumn<>("Engine");
		engineColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleEngine"));
		vehicleTableView.getColumns().add(engineColumn);
		
		TableColumn<Vehicle, String> costColumn = new TableColumn<>("Cost");
		costColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleDayCost"));
		vehicleTableView.getColumns().add(costColumn);
		
		TableColumn<Vehicle, ?> seatNumberColumn = new TableColumn<>("Seat Number");
		seatNumberColumn.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
		vehicleTableView.getColumns().add(seatNumberColumn);
		
		TableColumn<Vehicle, ?> doorsNumberColumn = new TableColumn<>("Doors Number");
		doorsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("doorsNumber"));
		vehicleTableView.getColumns().add(doorsNumberColumn);
		
		TableColumn<Vehicle, ?> seatHeightColumn = new TableColumn<>("Seat Height");
		seatHeightColumn.setCellValueFactory(new PropertyValueFactory<>("seatHeight"));
		vehicleTableView.getColumns().add(seatHeightColumn);
		
		TableColumn<Vehicle, ?> luggageSystemColumn = new TableColumn<>("Luggage System");
		luggageSystemColumn.setCellValueFactory(new PropertyValueFactory<>("luggageSystem"));
		vehicleTableView.getColumns().add(luggageSystemColumn);
		
		
		
		
		
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
		
		if(event.getSource() == newVehicleBtn) {
			int k = 0;
			for (int i = 0; i < vehicleList.size(); i++) {
				if(vehicleList.get(i).getVehicleLicensePlate().equals(licPlateField.getText())) {
					 k = 1;
					 break;
				}
			}
				
			if(k == 0) {
			if(cCorTCombo.getValue() == "Car") {
				
				if(!(licPlateField.getText().isBlank())) {
					
					String vehicleLicensePlate = licPlateField.getText();
					String vehicleModel = modelField.getText();
					String vehicleFuel = fuelField.getText();
					String vehicleType = typeField.getText();
					String vehicleEngine = engineField.getText();
					float vehicleDayCost = Float.valueOf(costField.getText());
					int seatNumber = Integer.valueOf(seatNField.getText());
					int doorsNumber = Integer.valueOf(doorsNField.getText());
					createCar(vehicleLicensePlate, vehicleModel, vehicleFuel, vehicleType
							, vehicleEngine, vehicleDayCost, seatNumber, doorsNumber);
					tableSync();
					clearTextFields();
				}
			}
			
			if(cCorTCombo.getValue() == "TwoWheeled") {
				
				
					
						if(!(licPlateField.getText().isBlank())) {
					
							String vehicleLicensePlate = licPlateField.getText();
							String vehicleModel = modelField.getText();
							String vehicleFuel = fuelField.getText();
							String vehicleType = typeField.getText();
							String vehicleEngine = engineField.getText();
							float vehicleDayCost = Float.valueOf(costField.getText());
							float seatHeight = Float.valueOf(seatHField.getText());
							String luggageSystem = lugSystemField.getText();
							createTwoWheeled(vehicleLicensePlate, vehicleModel, vehicleFuel, vehicleType
									, vehicleEngine, vehicleDayCost, seatHeight, luggageSystem);
							tableSync();
							clearTextFields();
							
						}
				
			}
		  }
		}
			
		
	if(event.getSource() == updateVehicleBtn) {
		
		if(cCorTCombo.getValue() == "Car") {
			
			if(!(licPlateField.getText().isBlank())) {
				//String vehicleLicensePlate = licPlateField.getText();
				String vehicleModel = modelField.getText();
				String vehicleFuel = fuelField.getText();
				String vehicleType = typeField.getText();
				String vehicleEngine = engineField.getText();
				float vehicleDayCost = Float.valueOf(costField.getText());
				int seatNumber = Integer.valueOf(seatNField.getText());
				int doorsNumber = Integer.valueOf(doorsNField.getText());
				String k = "";
				Car selectedLic = (Car) vehicleTableView.getSelectionModel().getSelectedItem();
				if(selectedLic != null) {
					k = selectedLic.getVehicleLicensePlate();
				}
				updateCar(k, vehicleModel, vehicleFuel, vehicleType
						, vehicleEngine, vehicleDayCost, seatNumber, doorsNumber);
				tableSync();
				clearTextFields();
			}
		}
		
		if(cCorTCombo.getValue() == "TwoWheeled") {
			if(!(licPlateField.getText().isBlank())) {
			//String vehicleLicensePlate = licPlateField.getText();
			String vehicleModel = modelField.getText();
			String vehicleFuel = fuelField.getText();
			String vehicleType = typeField.getText();
			String vehicleEngine = engineField.getText();
			float vehicleDayCost = Float.valueOf(costField.getText());
			float seatHeight = Float.valueOf(seatHField.getText());
			String luggageSystem = lugSystemField.getText();
			String k = "";
			TwoWheeled selectedLic = (TwoWheeled) vehicleTableView.getSelectionModel().getSelectedItem();
			if(selectedLic != null) {
				k = selectedLic.getVehicleLicensePlate();
			}
			updateTwoWheeled(k, vehicleModel, vehicleFuel, vehicleType
					, vehicleEngine, vehicleDayCost, seatHeight, luggageSystem);
			tableSync();
			clearTextFields();
			}
		}
	}
		if(event.getSource() == deleteVehicleBtn) {
			if(!(licPlateField.getText().isBlank())) {
			deleteVehicle(licPlateField.getText());
			
			tableSync();
			clearTextFields();
			}
		}
		
		
		
		
	if(event.getSource() == vehicleTableView) {
		Vehicle selectedVehicle = vehicleTableView.getSelectionModel().getSelectedItem();
		if(selectedVehicle != null) {
			
			if(selectedVehicle instanceof Car){
				licPlateField.setText(selectedVehicle.getVehicleLicensePlate());
				modelField.setText(selectedVehicle.getVehicleModel());
				fuelField.setText(selectedVehicle.getVehicleFuel());
				typeField.setText(selectedVehicle.getVehicleType());
				engineField.setText(selectedVehicle.getVehicleEngine());
				costField.setText(String.valueOf(selectedVehicle.getVehicleDayCost()));
				seatNField.setText(String.valueOf(((Car) selectedVehicle).getSeatNumber()));
				doorsNField.setText(String.valueOf(((Car) selectedVehicle).getDoorsNumber()));
				seatHField.setText("");
				lugSystemField.setText("");
			}
			
			if(selectedVehicle instanceof TwoWheeled) {
				licPlateField.setText(selectedVehicle.getVehicleLicensePlate());
				modelField.setText(selectedVehicle.getVehicleModel());
				fuelField.setText(selectedVehicle.getVehicleFuel());
				typeField.setText(selectedVehicle.getVehicleType());
				engineField.setText(selectedVehicle.getVehicleEngine());
				costField.setText(String.valueOf(selectedVehicle.getVehicleDayCost()));
				seatHField.setText(String.valueOf(((TwoWheeled) selectedVehicle).getSeatHeight()));
				lugSystemField.setText(((TwoWheeled) selectedVehicle).getLuggageSystem());
				seatNField.setText("");
				doorsNField.setText("");
			}
		
		}

	}
		
	if(event.getSource() == goBtn) {
		sTable();
	}
		
	}

	
	

	

	public void createCar(String vehicleLicensePlate, String vehicleModel, String vehicleFuel, String vehicleType,
			String vehicleEngine, float vehicleDayCost, int seatNumber, int doorsNumber) {
		Car c = new Car(vehicleLicensePlate, vehicleModel, vehicleFuel, vehicleType, vehicleEngine, vehicleDayCost, seatNumber, doorsNumber);
		vehicleList.add(c);
	}
	
	public void createTwoWheeled(String vehicleLicensePlate, String vehicleModel, String vehicleFuel,
			String vehicleType, String vehicleEngine, float vehicleDayCost, float seatHeight, String luggageSystem) {
		TwoWheeled d = new TwoWheeled(vehicleLicensePlate, vehicleModel, vehicleFuel, vehicleType, vehicleEngine, vehicleDayCost, seatHeight, luggageSystem);
		vehicleList.add(d);
		
	}

	public void updateCar(String k, String vehicleModel, String vehicleFuel, String vehicleType, String vehicleEngine,
			float vehicleDayCost, int seatNumber, int doorsNumber) {
		for(Vehicle d : vehicleList) {
			if(d instanceof Car) {
				if((d.getVehicleLicensePlate()) == k) {
					((Car)d).setVehicleModel(vehicleModel);
					((Car)d).setVehicleFuel(vehicleFuel);
					((Car)d).setVehicleEngine(vehicleEngine);
					((Car)d).setVehicleType(vehicleType);
					((Car)d).setVehicleDayCost(vehicleDayCost);
					((Car)d).setSeatNumber(seatNumber);
					((Car)d).setDoorsNumber(doorsNumber);
				}
			}
		}
	}
	
	public void updateTwoWheeled(String k, String vehicleModel, String vehicleFuel, String vehicleType,
			String vehicleEngine, float vehicleDayCost, float seatHeight, String luggageSystem) {
		for(Vehicle d :vehicleList) {
			if(d instanceof TwoWheeled) {
				if((d.getVehicleLicensePlate()) == k) {
					((TwoWheeled)d).setVehicleModel(vehicleModel);
					((TwoWheeled)d).setVehicleFuel(vehicleFuel);
					((TwoWheeled)d).setVehicleEngine(vehicleEngine);
					((TwoWheeled)d).setVehicleType(vehicleType);
					((TwoWheeled)d).setVehicleDayCost(vehicleDayCost);
					((TwoWheeled)d).setSeatHeight(seatHeight);
					((TwoWheeled)d).setLuggageSystem(luggageSystem);
				}
			}
		}
		
	}
	
	
	
	public void deleteVehicle(String vehicleLicensePlate) {
		for (int i = 0; i < vehicleList.size(); i++) {
			if(vehicleList.get(i).getVehicleLicensePlate().equals(vehicleLicensePlate)) {
				vehicleList.remove(i);
				break;
			}
		}
	}
	
	public void sTable() {
			
		if(searchCombo.getValue() == "Show all") {
			tableSync();
		}
		if(searchCombo.getValue() == "TwoWheeled") {
			List<Vehicle> items = vehicleTableView.getItems();
			items.clear();
			for(Vehicle d : vehicleList) {
				if(d instanceof TwoWheeled) {
					items.add((TwoWheeled) d);
				}
			}
		}
		if(searchCombo.getValue() == "Car"){
			List<Vehicle> items = vehicleTableView.getItems();
			items.clear();
			for(Vehicle d : vehicleList) {
				if(d instanceof Car) {
					items.add((Car) d);
				}
			}
		}
	}
	
	
	public void tableSync() {
		List<Vehicle> items = vehicleTableView.getItems();
		items.clear();
		for(Vehicle d : vehicleList) {
			if(d instanceof Vehicle) {
				items.add((Vehicle) d);
			}
		}
	}
	
	public void clearTextFields(){
		licPlateField.setText("");
		modelField.setText("");
		fuelField.setText("");
		typeField.setText("");
		engineField.setText("");
		costField.setText("");
		seatNField.setText("");
		doorsNField.setText("");
		seatHField.setText("");
		lugSystemField.setText("");
	}
	
	public void clearSearchField() {
		searchBox.setText("");
	}
}
