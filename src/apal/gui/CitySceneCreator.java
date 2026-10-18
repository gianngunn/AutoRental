package apal.gui;

import apal.core.City;
import apal.service.CityService;

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

public class CitySceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	
	private final CityService cityService;
	//Flow Pane
	FlowPane buttonFlowPane,goFlowPane;
	//Grid Panes
	GridPane rootGridPane, inputFieldsPane, inputSearchField;
	//Second Scene buttons
	Button newCityBtn, updateCityBtn, deleteCityBtn, backBtn, goBtn, viewAllBtn;
	//Second scene labels
	Label nameLbl, idLbl;
	//Second scene TextFields
	TextField nameField, idField, searchBox;
	//TableView
	public static TableView<City> citiesTableView;
	
	
	public CitySceneCreator(double width, double height, CityService cityService) {
		super(width, height);
		this.cityService = cityService;
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();
		goFlowPane = new FlowPane();
		nameLbl = new Label("Name: ");
		nameField = new TextField();
	    idField = new TextField();
		searchBox = new TextField();
		newCityBtn = new Button("New City");
		updateCityBtn = new Button("Update");
		deleteCityBtn = new Button("Delete");
		backBtn = new Button("Go Back");
		goBtn = new Button("Go");
		viewAllBtn = new Button("View All");
		inputFieldsPane = new GridPane();
		citiesTableView = new TableView<>();
		inputSearchField = new GridPane();
		searchBox.setPromptText("Search City");
		
		
		
		//Attach events
		backBtn.setOnMouseClicked(this);
		newCityBtn.setOnMouseClicked(this);
		updateCityBtn.setOnMouseClicked(this);
		deleteCityBtn.setOnMouseClicked(this);
		citiesTableView.setOnMouseClicked(this);
		goBtn.setOnMouseClicked(this);
		viewAllBtn.setOnMouseClicked(this);
		//Customize flowPane
		buttonFlowPane.setHgap(10);
		buttonFlowPane.getChildren().add(newCityBtn);
		buttonFlowPane.getChildren().add(updateCityBtn);
		buttonFlowPane.getChildren().add(deleteCityBtn);
		buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
		
		
		//Customize gridPane
		inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
		inputFieldsPane.setVgap(10);
		inputFieldsPane.setHgap(10);
		inputFieldsPane.add(nameLbl, 0, 0);
		inputFieldsPane.add(nameField, 1, 0);
		inputSearchField.setAlignment(Pos.TOP_LEFT);
		inputSearchField.setVgap(10);
		inputSearchField.setHgap(10);
		inputSearchField.add(searchBox, 0, 0);
		inputSearchField.add(goBtn, 1, 0);
		inputSearchField.add(viewAllBtn, 1, 1);
		//Customize rootScene2
		//rootGridPane.setGridLinesVisible(true);
		rootGridPane.setVgap(10);
		rootGridPane.setHgap(0);
		rootGridPane.add(inputFieldsPane, 1, 0);
		rootGridPane.add(citiesTableView, 0, 1);
		rootGridPane.add(buttonFlowPane, 0, 2);
		rootGridPane.add(backBtn, 1, 2);
		rootGridPane.add(inputSearchField, 0,0);
		
		
		//Customize tableView
		TableColumn<City, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));
		citiesTableView.getColumns().add(nameColumn);
		
		TableColumn<City, String> idColumn = new TableColumn<>("Code");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("cityCode"));
		citiesTableView.getColumns().add(idColumn);
		
	
		
		
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
		
		if(event.getSource() == newCityBtn ) {
			if (runServiceAction("Cannot create city", () -> cityService.add(nameField.getText()))) {
				refreshTable();
				clearTextFields();
			}
		}
		
		if(event.getSource() == updateCityBtn) {
			City selectedCode = citiesTableView.getSelectionModel().getSelectedItem();
			if (selectedCode == null) {
				runServiceAction("Cannot update city", () -> {
					throw new IllegalArgumentException("Select a city to update.");
				});
			} else {
				if (runServiceAction("Cannot update city",
						() -> cityService.rename(selectedCode.getCityCode(), nameField.getText()))) {
					refreshTable();
					clearTextFields();
				}
			}
		}
		
		if(event.getSource() == deleteCityBtn) {
			City selectedCity = citiesTableView.getSelectionModel().getSelectedItem();
			if (selectedCity == null) {
				runServiceAction("Cannot delete city", () -> {
					throw new IllegalArgumentException("Select a city to delete.");
				});
			} else if (runServiceAction("Cannot delete city",
					() -> cityService.delete(selectedCity.getCityCode()))) {
					refreshTable();
					clearTextFields();
			}
		}
		
		if(event.getSource() == citiesTableView) {
			City selectedCity = citiesTableView.getSelectionModel().getSelectedItem();
			if(selectedCity != null) {
				nameField.setText(selectedCity.getCityName());
			}
		}
		
		if(event.getSource() == goBtn) {
			String cityName = searchBox.getText();
			citiesTableView.getItems().setAll(cityService.findByName(cityName));
			clearSearchField();
		}
		
		if(event.getSource() == viewAllBtn) {
			refreshTable();
		}
		
	}

	private void refreshTable() {
		citiesTableView.getItems().setAll(cityService.getAll());
	}
	
	
	
	public void clearTextFields() {
		nameField.setText("");
		idField.setText("");
	}
	
	public void clearSearchField() {
		searchBox.setText("");
	}
	
	
}
