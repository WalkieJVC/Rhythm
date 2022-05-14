package com.rhythm;

public class Playlist {
    //Variables
    private final int _playlistID;
    private final String _playlistName;

    //Constructor
    public Playlist(int _playlistID, String _playlistName){
        this._playlistID = _playlistID;
        this._playlistName = _playlistName;
    }

    //Getters
    public int getPlaylistID(){return this._playlistID;}
    public String getPlaylistName(){return this._playlistName;}
}
