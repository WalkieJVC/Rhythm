package com.rhythm;

public class Song {
    //Variables
    private String _songName;   //Holds the value for the song name
    private String _artist;     //Holds the value for the artist name
    private String _songPath;   //Holds the media for the song
    private int _songID;        //Holds the value for the song id

    //Constructor
    public Song (String _songName, String _artist, String _songPath, int _songID) {
            //Local Variables
        //Assigns the parameter values to the variables of this instance.
        this._songName = _songName;
        this._artist = _artist;
        this._songPath = _songPath;
        this._songID = _songID;
    }

    //Getters
    public String getSongName() {return this._songName;}    //Returns the song name
    public String getArtist() {return this._artist;}        //Returns the artist name
    public String getSongPath() {return this._songPath;}    //Returns the song path
    public int getSongID() {return this._songID;}           //Returns the song ID
}
