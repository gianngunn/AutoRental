package apal.gui;

import java.util.ArrayList;
import java.util.List;

import apal.core.City;

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
	
	//List of city
	public static ArrayList<City> citiesList;
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
	int i = City.MAX_CODE();
	
	
	public CitySceneCreator(double width, double height) {
		super(width, height);
		
		//Initialize fields
		citiesList = new ArrayList<>();
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
			if(!(nameField.getText().isBlank())){
			String cityName = nameField.getText();
			String cityCode = String.valueOf(i);
			
			createCity(cityName, cityCode);
			}
			tableSync();
			clearTextFields();
		}
		
		if(event.getSource() == updateCityBtn) {
			if(!(nameField.getText().isBlank())){
			String cityName = nameField.getText();
			String cityCode = String.valueOf(i);
			int k = 0; 
			City selectedCode = citiesTableView.getSelectionModel().getSelectedItem();
			if(selectedCode != null) {
				k= selectedCode.getCityCode();
			}
			
			updateCity(cityName, cityCode ,k);
			
			tableSync();
			clearTextFields();
		}
		}
		
		if(event.getSource() == deleteCityBtn) {
			if(!(nameField.getText().isBlank())){
			deleteCity(nameField.getText());
			
			tableSync();
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
			sTable(cityName);
		}
		
		if(event.getSource() == viewAllBtn) {
			tableSync();
		}
		
	}

	public void createCity(String cityName, String cityCode ) {
		City d = new City(Integer.valueOf(cityCode) , cityName);
		citiesList.add(d);
		
	}
	
	public void sTable(String cityName) {
		List<City> items = citiesTableView.getItems();
		items.clear();
		for(City d : citiesList) {
			if(d.getCityName().equals(cityName) ) {
				items.add((City)d);
			}
		}
		clearSearchField();
	}
	
	public void tableSync() {
		List<City> items = citiesTableView.getItems();
		items.clear();
		for(City d : citiesList) {
			if(d instanceof City) {
				items.add((City) d);
			}
		}
	}
	public void updateCity(String cityName, String cityCode,int k) {
		
		for(City d : citiesList) {
			if((d.getCityCode()) == k) {
				d.setCityName(cityName);
			}
		}
	}
	
	
	public void deleteCity(String cityName) {
		for (int i = 0; i < citiesList.size(); i++) {
			if(citiesList.get(i).getCityName().equals(cityName)) {
				citiesList.remove(i);
				break;
			}
		}
	}
	
	
	
	public void clearTextFields() {
		nameField.setText("");
		idField.setText("");
	}
	
	public void clearSearchField() {
		searchBox.setText("");
	}
	
	
}
