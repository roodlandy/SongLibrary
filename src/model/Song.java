package model;

import javafx.beans.property.SimpleStringProperty;

public class Song {
	private SimpleStringProperty songName;
	private SimpleStringProperty artistName;
	private SimpleStringProperty albumName;
	private SimpleStringProperty songYear;
	
	public Song(String[] songEntry){
		this(songEntry[0], songEntry[1], songEntry[2], songEntry[3]);
	}
	
	public Song(String sName, String aName, String alName, String sYear){
		this.songName = new SimpleStringProperty(sName);
		this.artistName = new SimpleStringProperty(aName);
		this.albumName = new SimpleStringProperty(alName);
		this.songYear = new SimpleStringProperty(sYear);
	}

	public void setSongDetails(String[] songDetails) {
		setSongName(songDetails[0]);
		setArtistName(songDetails[1]);
		setAlbumName(songDetails[2]);
		setSongYear(songDetails[3]);	
	}
	
	public void setSongName(String value) {
		songName.set(value);
	}
	
	public String getSongName() {
		return songName.get();
	}

	
	public void setArtistName(String value) {
		artistName.set(value);
	}
	
	public String getArtistName() {
		return artistName.get();
	}

	public void setAlbumName(String value) {
		albumName.set(value);
	}
	
	public String getAlbumName() {
		return albumName.get();
	}
	
	public void setSongYear(String value) {
		songYear.set(value);
	}
	
	public String getSongYear() {
		return songYear.get();
	}
	
	public String toString() {
		return songName.get() + ";" + artistName.get() + ";" + albumName.get() + ";" + songYear.get();
	}
}
