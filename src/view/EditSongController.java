package view;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Song;

public class EditSongController {
	@FXML Label titleLabel;
	@FXML Label errorLabel;
	@FXML TextField songNameField;
	@FXML TextField artistNameField;
	@FXML TextField albumNameField;
	@FXML TextField songYearField;
	@FXML Button submitButton;
	
	private Stage stage;
	private Scene scene;
	private int selectedIndex;
	private Song selectedSong;
	
	public void start(Song song, int index) { 
		titleLabel.setText("Edit " + song.getSongName());
		songNameField.setText(song.getSongName());
		artistNameField.setText(song.getArtistName());
		if (!song.getAlbumName().equals("--")){
			albumNameField.setText(song.getAlbumName());
		}
		if (!song.getSongYear().equals("--")){
			songYearField.setText(song.getSongYear());
		}	
		selectedIndex = index;
		selectedSong = song;
		errorLabel.setWrapText(true);
	}
	
	public void submitEdit(ActionEvent e) throws IOException {
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
		
		if(songLibraryController.checkForDuplicates(songDetails[0] + ";" + songDetails[1], selectedIndex)) {
			errorLabel.setText("This song is already in the library");
			return;
		}
		
		//Asks the user to confirm their edit
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Song");
		alert.setHeaderText("Are you sure you want to edit this song?");
		alert.setContentText(selectedSong.getSongName() + " by " + selectedSong.getArtistName());
		
		Optional<ButtonType> option = alert.showAndWait();
		
		if (option.get() == null) {
			errorLabel.setText("No selection!");
	    } else if (option.get() == ButtonType.OK) {
	    	songLibraryController.changeSongDetails(songDetails, selectedIndex);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    } else if (option.get() == ButtonType.CANCEL) {
	        errorLabel.setText("Cancelled!");
	    } 		
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
