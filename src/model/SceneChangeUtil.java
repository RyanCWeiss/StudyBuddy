package model;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChangeUtil {

	public SceneChangeUtil() {
		
	}
	
	public void switchScenes(String fxml, Event event) {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/" + fxml));
			
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxml));
//			root = loader.load();

			Scene mainScene = new Scene(root);
	        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        primaryStage.setScene(mainScene);
	        mainScene.getStylesheets().add("/view/style.css");
	        primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
