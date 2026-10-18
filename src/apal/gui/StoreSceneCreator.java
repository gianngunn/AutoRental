package apal.gui;

import apal.core.Store;
import apal.service.StoreService;
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
	private final StoreService storeService;
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
	
	
	public StoreSceneCreator(double width, double height, StoreService storeService) {
		super(width, height);
		this.storeService = storeService;
		
		//Initialize fields
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
			if (runServiceAction("Cannot create store",
					() -> storeService.add(nameField.getText(), cityField.getText()))) {
				refreshTable();
				clearTextFields();
			}
		}
		
		if(event.getSource() == updateStoreBtn) {
			Store selectedCode = storeTableView.getSelectionModel().getSelectedItem();
			if (selectedCode == null) {
				runServiceAction("Cannot update store", () -> {
					throw new IllegalArgumentException("Select a store to update.");
				});
			} else {
				if (runServiceAction("Cannot update store",
						() -> storeService.rename(selectedCode.getStoreCode(), nameField.getText()))) {
					refreshTable();
					clearTextFields();
				}
			}
		}
		
		if(event.getSource() == deleteStoreBtn) {
			Store selectedStore = storeTableView.getSelectionModel().getSelectedItem();
			if (selectedStore == null) {
				runServiceAction("Cannot delete store", () -> {
					throw new IllegalArgumentException("Select a store to delete.");
				});
			} else if (runServiceAction("Cannot delete store",
					() -> storeService.delete(selectedStore.getStoreCode()))) {
					refreshTable();
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
			storeTableView.getItems().setAll(storeService.findByCity(storeCity));
			clearSearchField();
		}
		
		if(event.getSource() == viewAllBtn) {
			refreshTable();
		}
		
		
	}
	private void refreshTable() {
		storeTableView.getItems().setAll(storeService.getAll());
	}
	
	
	
	public void clearTextFields() {
		nameField.setText("");
		cityField.setText("");
	}
	
	public void clearSearchField() {
		searchBox.setText("");
	}
	
}
