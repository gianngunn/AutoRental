package apal.gui;

import java.util.ArrayList;
import java.util.List;
import apal.core.Store;
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

public class StoreSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	//List of store
	public static ArrayList<Store> storeList;
	//Flow Pane
	FlowPane buttonFlowPane,goFlowPane;
	//Grid Panes
	GridPane rootGridPane, inputFieldsPane, inputSearchField;
	//Store Scene buttons
	Button newStoreBtn, updateStoreBtn, deleteStoreBtn, backBtn, goBtn, viewAllBtn;
	//Store scene labels
	Label nameLbl, cityLbl;
	//Store scene TextFields
	TextField nameField, cityField, searchBox;
	//TableView
	public static TableView<Store> storeTableView;
	
	int i = Store.MAX_CODES();
	
	public StoreSceneCreator(double width, double height) {
		super(width, height);
		
		//Initialize fields
		storeList = new ArrayList<>();
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();
		goFlowPane = new FlowPane();
		nameLbl = new Label("Store: ");
		cityLbl = new Label("City: ");
		nameField = new TextField();
		cityField = new TextField();
		searchBox = new TextField();
		newStoreBtn = new Button("New Store");
		updateStoreBtn = new Button("Update");
		deleteStoreBtn = new Button("Delete");
		backBtn = new Button("Go Back");
		goBtn = new Button("Go");
		viewAllBtn = new Button("View All");
		inputFieldsPane = new GridPane();
		storeTableView = new TableView<>();
		inputSearchField = new GridPane();
		searchBox.setPromptText("Search City");
		
		//Attach events
		backBtn.setOnMouseClicked(this);
		newStoreBtn.setOnMouseClicked(this);
		updateStoreBtn.setOnMouseClicked(this);
		deleteStoreBtn.setOnMouseClicked(this);
		storeTableView.setOnMouseClicked(this);
		goBtn.setOnMouseClicked(this);
		viewAllBtn.setOnMouseClicked(this);
		//Customize flowPane
		buttonFlowPane.setHgap(10);
		buttonFlowPane.getChildren().add(newStoreBtn);
		buttonFlowPane.getChildren().add(updateStoreBtn);
		buttonFlowPane.getChildren().add(deleteStoreBtn);
		buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
		
		
		//Customize gridPane
		inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
		inputFieldsPane.setVgap(10);
		inputFieldsPane.setHgap(10);
		inputFieldsPane.add(nameLbl, 0, 0);
		inputFieldsPane.add(nameField, 1, 0);
		inputFieldsPane.add(cityLbl, 0, 1);
		inputFieldsPane.add(cityField, 1, 1);
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
		rootGridPane.add(storeTableView, 0, 1);			
		rootGridPane.add(buttonFlowPane, 0, 2);
		rootGridPane.add(backBtn, 1, 2);
		rootGridPane.add(inputSearchField, 0,0);
			
		
		//Customize tableView
		TableColumn<Store, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("storeName"));
		storeTableView.getColumns().add(nameColumn);
		
		TableColumn<Store, String> scityColumn = new TableColumn<>("Store City");
		scityColumn.setCellValueFactory(new PropertyValueFactory<>("storeCity"));
		storeTableView.getColumns().add(scityColumn);
		
		TableColumn<Store, String> scodeColumn = new TableColumn<>("Store Code");
		scodeColumn.setCellValueFactory(new PropertyValueFactory<>("storeCode"));
		storeTableView.getColumns().add(scodeColumn);
		
		
		
		
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
		
		if(event.getSource() == newStoreBtn) {
			for(int i = 0 ; i <= CitySceneCreator.citiesList.size(); i++ ) {
				if(CitySceneCreator.citiesList.get(i).getCityName().equals(cityField.getText())) {
					if(!(nameField.getText().isBlank())) {
					String storeName = nameField.getText();
					String storeCity = cityField.getText();
					int storeCode = i ;
					createStore(storeCode, storeName, storeCity);
					tableSync();	
					clearTextFields();
					break ;
					}
				}
			}
			clearTextFields();	
		}
		
		if(event.getSource() == updateStoreBtn) {
			if(!(nameField.getText().isBlank())) {
			String storeName = nameField.getText();
			int storeCode = i;
			int k = 0;
			Store selectedCode = storeTableView.getSelectionModel().getSelectedItem();
			if(selectedCode != null) {
				k = selectedCode.getStoreCode();
			}
			updateStore(storeName, storeCode, k);
			
			tableSync();
			clearTextFields();
			}
		}
		
		if(event.getSource() == deleteStoreBtn) {
			if(!(nameField.getText().isBlank())) {
			deleteStore(nameField.getText());
			
			tableSync();
			clearTextFields();
			}
		}
		
		
		
		if(event.getSource() == storeTableView) {
			Store selectedStore = storeTableView.getSelectionModel().getSelectedItem();
			if(selectedStore != null) {
				nameField.setText(selectedStore.getStoreName());
				
			}
		}
		
		if(event.getSource() == goBtn) {
			String storeCity = searchBox.getText();
			sTable(storeCity);
		}
		
		if(event.getSource() == viewAllBtn) {
			tableSync();
		}
		
		
	}
	public void sTable(String storeCity) {
		List<Store> items = storeTableView.getItems();
		items.clear();
		for(Store d : storeList) {
			if(d.getStoreCity().equals(storeCity) ) {
				items.add((Store)d);
			}
		}
		clearSearchField();
	}
	
	
	public void deleteStore(String storeName) {
		for (int i = 0; i < storeList.size(); i++) {
			if(storeList.get(i).getStoreName().equals(storeName)) {
				storeList.remove(i);
				break;
			}
		}
		
	}

	public void updateStore(String storeName, int storeCode, int k) {
		for(Store d : storeList) {
			if((d.getStoreCode()) == k ) {
				d.setStoreName(storeName);
			}
		}
		
	}

	public void tableSync() {
		List<Store> items = storeTableView.getItems();
		items.clear();
		for(Store d : storeList) {
			if(d instanceof Store) {
				items.add((Store) d);
			}
		}
		
	}

	public void createStore(int storeCode, String storeName, String storeCity) {
		Store d = new Store(storeCode, storeName, storeCity);
		storeList.add(d); 
	}
	
	
	
	public void clearTextFields() {
		nameField.setText("");
		cityField.setText("");
	}
	
	public void clearSearchField() {
		searchBox.setText("");
	}
	
}
