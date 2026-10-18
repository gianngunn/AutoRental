package apal.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import apal.service.CityService;
import apal.service.ClientService;
import apal.service.RentalService;
import apal.service.StoreService;
import apal.service.VehicleService;

public class InfoFX extends Application{
	
	//Stage
	static Stage mainStage;
	//Scenes
	static Scene mainScene, cityScene, storeScene, clientScene, vehicleScene, rentalScene;
	
	
	@Override
	public void start(Stage primaryStage)  {
		
		mainStage = primaryStage;
		CityService cityService = new CityService();
		ClientService clientService = new ClientService();
		StoreService storeService = new StoreService(cityService);
		VehicleService vehicleService = new VehicleService();
		RentalService rentalService = new RentalService(vehicleService, clientService, storeService);

		SceneCreator mainSceneCreator = new MainSceneCreator(650, 300);
		mainScene = mainSceneCreator.createScene();
		
		SceneCreator citySceneCreator = new CitySceneCreator(650, 300, cityService);
		cityScene = citySceneCreator.createScene();
		
		SceneCreator storeSceneCreator = new StoreSceneCreator(650, 300, storeService);
		storeScene = storeSceneCreator.createScene();
		
		SceneCreator vehicleSceneCreator = new VehicleSceneCreator(1300, 500, vehicleService);
		vehicleScene = vehicleSceneCreator.createScene();
		
		SceneCreator clientSceneCreator = new ClientSceneCreator(1000, 450, clientService);
		clientScene = clientSceneCreator.createScene();
		
		SceneCreator rentalSceneCreator = new RentalSceneCreator(1000, 450, rentalService);
		rentalScene = rentalSceneCreator.createScene();
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Car Rental Service");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
