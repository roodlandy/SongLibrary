package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.SongLibraryController;

public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {		
		// set up FXML loader
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(getClass().getResource("/view/songLibrary.fxml"));
		
		// load the fxml
		GridPane root = (GridPane)loader.load();

		// get the controller (Do NOT create a new Controller()!!)
		// instead, get it through the loader
		SongLibraryController songLibraryController = loader.getController();
		songLibraryController.start(primaryStage);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); 

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
}
