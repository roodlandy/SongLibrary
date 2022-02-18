package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AddSongController {
	@FXML Button submitButton;
	
	private Stage stage;
	private Scene scene;
	
	public void submitSong(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader();  
		loader.setLocation(getClass().getResource("/view/songLibrary.fxml"));
		GridPane root = (GridPane)loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		
		SongLibraryController songLibraryController = loader.getController();
		songLibraryController.start(stage);
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
