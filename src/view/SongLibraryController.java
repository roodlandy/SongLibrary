package view;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Song;


public class SongLibraryController {        
	@FXML TableView<Song> songTable;
	@FXML TableColumn<Song, String> songNameCol;
	@FXML TableColumn<Song, String> artistNameCol;
	@FXML Label songNameLabel;
	@FXML Label albumNameLabel;
	@FXML Label artistNameLabel;
	@FXML Label songYearLabel;
	@FXML Button editButton;
	@FXML Button deleteButton;
	

	private ObservableList<String> obsList;   
	
	private ObservableList<Song> songList;
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void start(Stage mainStage) { 
		populateSongList();
		displaySongTable();
		
		TableViewSelectionModel<Song> selectionModel = songTable.getSelectionModel();
		selectionModel.select(0);
		displaySongDetails();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		selectionModel.selectedIndexProperty().addListener((obs, oldVal, newVal) -> displaySongDetails());
	}
	
	public void addSong(String[] songDetails) {
		Song song = new Song(songDetails);
		songList.add(song);
		displaySongTable();
		displaySongDetails();
	}
	
	public void changeSongDetails(String[] songDetails) {
		Song song = songTable.getSelectionModel().getSelectedItem();
		song.setSongDetails(songDetails);
		displaySongTable();
		displaySongDetails();
		//
	}
	
	public void editSong(ActionEvent e) throws IOException {  
		FXMLLoader loader = new FXMLLoader();  
		loader.setLocation(getClass().getResource("/view/editSong.fxml"));
		GridPane root = (GridPane)loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		EditSongController editSongController = loader.getController();
		
		Song selectedSong = songTable.getSelectionModel().getSelectedItem();
		editSongController.start(selectedSong);
		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void addSong(ActionEvent e) throws IOException {  
		root = FXMLLoader.load(getClass().getResource("/view/addSong.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void deleteSong(ActionEvent e) {
		TableViewSelectionModel<Song> selectionModel = songTable.getSelectionModel();
		int selectedIndex = selectionModel.getSelectedIndex();
		if (songList.size() > selectedIndex) {
			selectionModel.select(selectedIndex + 1);
		}else if(songList.size() > 0) {
			selectionModel.select(selectedIndex);
		}
		songList.remove(selectedIndex);
		
	}
	
	private void populateSongList() {
		songList = FXCollections.observableArrayList(
				new Song("Rams", "Bengals", "--", "--"),
				new Song("Rams", "Aengals", "--", "--"),
				new Song("Packers","Tolts", "--", "--"),
				new Song("49ers","Giants", "--", "--"),
				new Song("Packers","Colts", "--", "--"),
				new Song("Cowboys","Broncos", "--", "--"),
				new Song("Vikings","Dolphins", "--", "1999"),
				new Song("Titans","Seahawks", "--", "--"),
				new Song("Steelers","Jaguars", "--", "2001")
				);
	}
	
	private void displaySongTable() {
		songNameCol.setCellValueFactory(new PropertyValueFactory<Song, String> ("songName"));
		artistNameCol.setCellValueFactory(new PropertyValueFactory<Song, String> ("artistName"));
		songNameCol.setSortType(TableColumn.SortType.ASCENDING);	
		
		songTable.setItems(songList);
		songTable.getSortOrder().add(songNameCol);
		songTable.sort();
		songTable.getSortOrder().add(artistNameCol);
		songTable.sort();
	}
	
	private void displaySongDetails() {
		if (songList.size() == 0) {
			displayBlankDetails();
			return;
		}
		Song selectedSong = songTable.getSelectionModel().getSelectedItem();
		songNameLabel.setText(selectedSong.getSongName());
		albumNameLabel.setText("From " + selectedSong.getAlbumName());
		artistNameLabel.setText("By " + selectedSong.getArtistName());
		songYearLabel.setText("Year " + selectedSong.getSongYear());
	}
	
	private void displayBlankDetails() {
		songNameLabel.setText("No Songs");
		albumNameLabel.setText("");
		artistNameLabel.setText("");
		songYearLabel.setText("");
	}

}
