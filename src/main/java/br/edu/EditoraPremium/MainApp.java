package br.edu.EditoraPremium;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			System.out.println("MAIN APP");
			
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/CadIndica.fxml"));

			Scene scene = new Scene(root);

			primaryStage.setMaximized(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);

	}

}
