package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AddSongController {
	@FXML Label errorLabel;
	@FXML TextField songNameField;
	@FXML TextField artistNameField;
	@FXML TextField albumNameField;
	@FXML TextField songYearField;
	@FXML Button submitButton;
	
	private Stage stage;
	private Scene scene;
	
	/*TODO */
	
	public void submitSong(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader();  
		loader.setLocation(getClass().getResource("/view/songLibrary.fxml"));
		GridPane root = (GridPane)loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		
		SongLibraryController songLibraryController = loader.getController();
		songLibraryController.start(stage);
		
		String[] songDetails;
		try {
			songDetails = getSongDetails();
		}
		catch(Exception exception) {
			errorLabel.setText(exception.getMessage());
			return;
		}
		
		// call songLibraryController.addSong with Song constructor parameters as arguments
		songLibraryController.addSong(songDetails);
	
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private String[] getSongDetails() throws Exception{
		int songYear;
		String[] songDetails = new String[4];
		if(songNameField.getText().trim().isEmpty()) {
			throw new Exception("Song name not given");
		}
		if(artistNameField.getText().trim().isEmpty()) {
			throw new Exception("Artist name not given");
		}
		if(!songYearField.getText().trim().isEmpty()) {
			try {
				songYear = Integer.parseInt(songYearField.getText());
				if (songYear < 0) {
					throw new Exception("Song year is negative");
				}
				songDetails[3] = songYearField.getText();
			}catch (NumberFormatException nfe) {
				throw new Exception("Song year is not a number");
			}
		}else {
			songDetails[3] = "--";
		}
				
		songDetails[0] = songNameField.getText();
		songDetails[1] = artistNameField.getText();
		if(albumNameField.getText().trim().isEmpty()) {
			songDetails[2] = "--";
		}else {
			songDetails[2] = albumNameField.getText();
		}
		if(albumNameField.getText().trim().isEmpty()) {
			songDetails[2] = "--";
		}else {
			songDetails[2] = albumNameField.getText();
		}
		
		return songDetails;
	}
}
