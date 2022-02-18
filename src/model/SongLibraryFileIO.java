package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongLibraryFileIO {
	
	public static ObservableList<Song> getSongListFromFile() throws Exception{
		ObservableList<Song> songList = FXCollections.observableArrayList();
		
		File songFile = new File("/model/songFile.txt");
		BufferedReader br = new BufferedReader(new FileReader(songFile));
		
		String st;
		
		while ((st = br.readLine()) != null) {
			String[] songEntry = st.split(";");
			songList.add(new Song(songEntry));
		}
		
		return songList;
	}
	
	public static void songListToFile(ObservableList<Song> songList) throws IOException{
		File songFile = new File("/model/songFile.txt");
		songFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter("/model/songFile.txt", false));
		for(Song song : songList) {
			writer.write(song.toString());
			writer.newLine();
		}
		writer.close();
	}
}
