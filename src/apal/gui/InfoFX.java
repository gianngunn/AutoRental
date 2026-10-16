package apal.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InfoFX extends Application{
	
	//Stage
	static Stage mainStage;
	//Scenes
	static Scene mainScene, cityScene, storeScene, clientScene, vehicleScene, rentalScene;
	
	
	@Override
	public void start(Stage primaryStage)  {
		
		mainStage = primaryStage;
		SceneCreator mainSceneCreator = new MainSceneCreator(650, 300);
		mainScene = mainSceneCreator.createScene();
		
		SceneCreator citySceneCreator = new CitySceneCreator(650, 300);
		cityScene = citySceneCreator.createScene();
		
		SceneCreator storeSceneCreator = new StoreSceneCreator(650, 300);
		storeScene = storeSceneCreator.createScene();
		
		SceneCreator vehicleSceneCreator = new VehicleSceneCreator(1300, 500);
		vehicleScene = vehicleSceneCreator.createScene();
		
		SceneCreator clientSceneCreator = new ClientSceneCreator(1000, 450);
		clientScene = clientSceneCreator.createScene();
		
		SceneCreator rentalSceneCreator = new RentalSceneCreator(1000, 450);
		rentalScene = rentalSceneCreator.createScene();
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Car Rental Service");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
