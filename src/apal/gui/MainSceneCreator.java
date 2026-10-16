package apal.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
	
	    //Flow Pane (root node)
		FlowPane rootFlowPane;
		//Main scene buttons
		Button cityBtn, storeBtn, clientBtn, vehicleBtn, rentalBtn; 
		
		
				
		
	public MainSceneCreator(double width, double height) {
		super(width, height);
		rootFlowPane = new FlowPane();
		//button image
		Image iconC = new Image(getClass().getResourceAsStream("city.png"));
		ImageView c = new ImageView(iconC);
		c.setFitHeight(50);
		c.setFitWidth(50);
		
		Image iconS = new Image(getClass().getResourceAsStream("store.png"));
		ImageView s = new ImageView(iconS);
		s.setFitHeight(50);
		s.setFitWidth(50);
		
		Image iconCl = new Image(getClass().getResourceAsStream("client.png"));
		ImageView cl = new ImageView(iconCl);
		cl.setFitHeight(50);
		cl.setFitWidth(50);
		
		Image iconV = new Image(getClass().getResourceAsStream("vehicles.png"));
		ImageView v = new ImageView(iconV);
		v.setFitHeight(50);
		v.setFitWidth(50);
		
		Image iconR = new Image(getClass().getResourceAsStream("rent.png"));
		ImageView r = new ImageView(iconR);
		r.setFitHeight(50);
		r.setFitWidth(50);
		
		cityBtn = new Button("Cities",c);
		storeBtn = new Button("Stores",s);
		clientBtn = new Button("Clients",cl);
		vehicleBtn = new Button("Vehicles",v);
		rentalBtn = new Button("Vehicle Rental",r);
		
		//attach handle event to buttons
		cityBtn.setOnMouseClicked(this);
		storeBtn.setOnMouseClicked(this);
		clientBtn.setOnMouseClicked(this);
		vehicleBtn.setOnMouseClicked(this);
		rentalBtn.setOnMouseClicked(this);
		//setup Flow pane
		rootFlowPane.setHgap(10);
		rootFlowPane.setAlignment(Pos.CENTER);
				
		//add buttons to rootFlowPane
			
		rootFlowPane.getChildren().add(cityBtn);
		rootFlowPane.getChildren().add(storeBtn);
		rootFlowPane.getChildren().add(clientBtn);
		rootFlowPane.getChildren().add(vehicleBtn);
		rootFlowPane.getChildren().add(rentalBtn);
	}
	
	@Override
	public Scene createScene() {
		
		return new Scene(rootFlowPane, width, height);
		
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(event.getSource() == cityBtn) {
			InfoFX.mainStage.setTitle("City Catalogue");
			InfoFX.mainStage.setScene(InfoFX.cityScene);
		}
		
		if(event.getSource() == storeBtn) {
			InfoFX.mainStage.setTitle("Store Catalogue");
			InfoFX.mainStage.setScene(InfoFX.storeScene);
		}
		
		if(event.getSource() == clientBtn) {
			InfoFX.mainStage.setTitle("Client Catalogue");
			InfoFX.mainStage.setScene(InfoFX.clientScene);
		}
		
		if(event.getSource() == vehicleBtn) {
			InfoFX.mainStage.setTitle("Vehicle Catalogue");
			InfoFX.mainStage.setScene(InfoFX.vehicleScene);
		}
		
		if(event.getSource() == rentalBtn) {
			InfoFX.mainStage.setTitle("Rental Catalogue");
			InfoFX.mainStage.setScene(InfoFX.rentalScene);
		}
	}

	
}
