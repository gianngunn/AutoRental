package apal.gui;

import javafx.scene.Scene;
import javafx.scene.control.Alert;

public abstract class SceneCreator {
	double width;
	double height;
	
	public SceneCreator(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	abstract Scene createScene();

	protected boolean runServiceAction(String title, Runnable action) {
		try {
			action.run();
			return true;
		} catch (IllegalArgumentException | IllegalStateException exception) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(exception.getMessage());
			alert.showAndWait();
			return false;
		}
	}
	
}
