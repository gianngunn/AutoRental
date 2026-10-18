package apal.gui;

import javafx.scene.Scene;
import apal.core.Client;
import apal.service.ClientService;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class ClientSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{

	private final ClientService clientService;
	//Flow Pane
	FlowPane buttonFlowPane;
	//Grid Panes
	GridPane rootGridPane, inputFieldsPane, inputSearchField;
	//Client Scene buttons
	Button newClientBtn, updateClientBtn, deleteClientBtn, backBtn, goBtn, viewAllBtn;
	//Client scene labels
	Label cIdLbl, cDrivingLLbl, cFullNameLbl, cEmailLbl, cAddressLbl, cPhoneNLbl;
	//Client scene TextFields
	TextField cIdField, cDrivingLField, cFullNameField, cEmailField, cAddressField, cPhoneNField, searchBox;
	//TableView
	public static TableView<Client> clientTableView;
	
	public ClientSceneCreator(double width, double height, ClientService clientService) {
		super(width, height);
		this.clientService = clientService;
		
		//Initialize fields
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();
		cIdLbl = new Label("Client ID: ");
		cDrivingLLbl = new Label("Client Driving License: ");
		cFullNameLbl = new Label("Client Full Name: ");
		cEmailLbl = new Label("Client Email: ");
		cAddressLbl = new Label("Client Address: ");
		cPhoneNLbl = new Label("Client Phone Number: ");
		cIdField = new TextField();
		cDrivingLField = new TextField();
		cFullNameField = new TextField();
		cEmailField = new TextField();
		cAddressField = new TextField();
		cPhoneNField = new TextField();
		searchBox = new TextField();
		newClientBtn = new Button("New Client");
		updateClientBtn = new Button("Update");
		deleteClientBtn = new Button("Delete");
		backBtn = new Button("Go Back");
		goBtn = new Button("Go");
		viewAllBtn = new Button("View All");
		inputFieldsPane = new GridPane();
		clientTableView = new TableView<>();
		inputSearchField = new GridPane();
		searchBox.setPromptText("Search Client");
		
		//Attach events
		backBtn.setOnMouseClicked(this);
		newClientBtn.setOnMouseClicked(this);
		updateClientBtn.setOnMouseClicked(this);
		deleteClientBtn.setOnMouseClicked(this);
		clientTableView.setOnMouseClicked(this);
		goBtn.setOnMouseClicked(this);
		viewAllBtn.setOnMouseClicked(this);
		//Customize flowPane
		buttonFlowPane.setHgap(10);
		buttonFlowPane.getChildren().add(newClientBtn);
		buttonFlowPane.getChildren().add(updateClientBtn);
		buttonFlowPane.getChildren().add(deleteClientBtn);
		buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
		
		//Customize gridPane
				inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
				inputFieldsPane.setVgap(10);
				inputFieldsPane.setHgap(10);
				inputFieldsPane.add(cIdLbl, 0, 0);
				inputFieldsPane.add(cIdField, 1, 0);
				inputFieldsPane.add(cDrivingLLbl, 0, 1);
				inputFieldsPane.add(cDrivingLField, 1, 1);
				inputFieldsPane.add(cFullNameLbl, 0, 2);
				inputFieldsPane.add(cFullNameField, 1, 2);
				inputFieldsPane.add(cEmailLbl,0 , 3);
				inputFieldsPane.add(cEmailField,1 , 3);
				inputFieldsPane.add(cAddressLbl, 0, 4);
				inputFieldsPane.add(cAddressField, 1, 4);
				inputFieldsPane.add(cPhoneNLbl,0 , 5);
				inputFieldsPane.add(cPhoneNField,1 , 5);
				
				inputSearchField.setAlignment(Pos.TOP_LEFT);
				inputSearchField.setVgap(10);
				inputSearchField.setHgap(10);
				inputSearchField.add(searchBox, 0, 0);
				inputSearchField.add(goBtn, 1, 0);
				inputSearchField.add(viewAllBtn, 1, 1);
				inputSearchField.add(clientTableView, 0, 2);
				rootGridPane.setVgap(10);
				rootGridPane.setHgap(10);
				rootGridPane.add(inputFieldsPane, 1, 0);						
				rootGridPane.add(buttonFlowPane, 0, 2);
				rootGridPane.add(backBtn, 1, 2);
				rootGridPane.add(inputSearchField, 0,0);
				
				//Customize tableView
				TableColumn<Client, String> cIdColumn = new TableColumn<>("Client ID");
				cIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
				clientTableView.getColumns().add(cIdColumn);
				
				TableColumn<Client, String> cDrivingLColumn = new TableColumn<>("Driving License");
				cDrivingLColumn.setCellValueFactory(new PropertyValueFactory<>("clientDrivingL"));
				clientTableView.getColumns().add(cDrivingLColumn);
				
				TableColumn<Client, String> cFullNameColumn = new TableColumn<>("Full Name");
				cFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientFullName"));
				clientTableView.getColumns().add(cFullNameColumn);
				
				TableColumn<Client, String> cEmailColumn = new TableColumn<>("Client Email");
				cEmailColumn.setCellValueFactory(new PropertyValueFactory<>("clientEmail"));
				clientTableView.getColumns().add(cEmailColumn);
				
				TableColumn<Client, String> cAddressColumn = new TableColumn<>("Client Address");
				cAddressColumn.setCellValueFactory(new PropertyValueFactory<>("clientAddress"));
				clientTableView.getColumns().add(cAddressColumn);
				
				TableColumn<Client, String> cPhoneNColumn = new TableColumn<>("Phone Number");
				cPhoneNColumn.setCellValueFactory(new PropertyValueFactory<>("clientPhoneN"));
				clientTableView.getColumns().add(cPhoneNColumn);
		
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

		if(event.getSource() == newClientBtn) {
			if (runServiceAction("Cannot create client", () -> clientService.add(
					cIdField.getText(), cDrivingLField.getText(), cFullNameField.getText(),
					cEmailField.getText(), cAddressField.getText(), Integer.parseInt(cPhoneNField.getText())))) {
				refreshTable();
				clearTextFields();
			}
		}
		
		if(event.getSource() == updateClientBtn) {
			Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
			if (selectedClient == null) {
				runServiceAction("Cannot update client", () -> {
					throw new IllegalArgumentException("Select a client to update.");
				});
			} else {
				if (runServiceAction("Cannot update client", () -> clientService.update(
						selectedClient.getClientID(), cFullNameField.getText(), cEmailField.getText(),
						cAddressField.getText(), Integer.parseInt(cPhoneNField.getText())))) {
					refreshTable();
					clearTextFields();
				}
			}
		}
		
		if(event.getSource() == deleteClientBtn) {
			Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
			if (selectedClient == null) {
				runServiceAction("Cannot delete client", () -> {
					throw new IllegalArgumentException("Select a client to delete.");
				});
			} else if (runServiceAction("Cannot delete client",
					() -> clientService.delete(selectedClient.getClientID()))) {
					refreshTable();
					clearTextFields();
			}
		}
		
		if(event.getSource() == clientTableView) {
			Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
			if(selectedClient != null) {
				cIdField.setText(selectedClient.getClientID());
				cDrivingLField.setText(selectedClient.getClientDrivingL());
				cFullNameField.setText(selectedClient.getClientFullName());
				cEmailField.setText(selectedClient.getClientEmail());
				cAddressField.setText(selectedClient.getClientAddress());
				cPhoneNField.setText(String.valueOf(selectedClient.getClientPhoneN()));
			}
		}
		
		if(event.getSource() == goBtn ) {
			String clientID = searchBox.getText();
			clientTableView.getItems().setAll(clientService.findById(clientID));
			clearSearchField();
		}
		
		if(event.getSource() == viewAllBtn ) {
			refreshTable();
		}	
		
		
	}

	
	private void refreshTable() {
		clientTableView.getItems().setAll(clientService.getAll());
	}
	
	public void clearTextFields(){
		cIdField.setText("");
		cDrivingLField.setText("");
		cFullNameField.setText("");
		cEmailField.setText("");
		cAddressField.setText("");
		cPhoneNField.setText("");
	}
	
	public void clearSearchField() {
		searchBox.setText("");
	}
	
 }
	
	
