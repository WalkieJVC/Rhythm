package com.rhythm;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class AddSongController extends PopupBaseController{
    //Variables
    ArrayList<Song> _songs;
    ArrayList<Playlist> _playlists;

    //FXML Related Variables
    @FXML
    ComboBox<Playlist> _playlistsField;
    @FXML
    ComboBox<Song> _songsField;
    @FXML
    Label _songsFieldLabel, _playlistsFieldLabel, _messageLabel;

    //Initialize Method
    public void Initialize(int _sessionID){
        this._sessionID = _sessionID;

        _songs = _queryHandler.GetSongs();
        _playlists = _queryHandler.GetPlaylists(_sessionID);

        _songsField.getItems().addAll(_songs);
        _playlistsField.getItems().addAll(_playlists);

        _songsField.setCellFactory(param -> new ListCell<>() {
            @Override //updateItem Method
            protected void updateItem(Song _song, boolean empty) {
                super.updateItem(_song, empty);
                //Checks if the object, songName, and artist are not null and if its empty
                if (empty || _song == null || _song.getSongName() == null || _song.getArtist() == null) {
                    setText(null); //Sets the text to null
                } else {
                    //Sets the text to song name by artist
                    setText(_song.getSongName() + " by " + _song.getArtist());
                }
            }
        });

        _playlistsField.setCellFactory(param -> new ListCell<>() {
            @Override //updateItem Method
            protected void updateItem(Playlist _playlist, boolean empty) {
                super.updateItem(_playlist, empty);
                //Checks if the object, songName, and artist are not null and if its empty
                if (empty || _playlist == null || _playlist.getPlaylistName() == null) {
                    setText(null); //Sets the text to null
                } else {
                    //Sets the text to song name by artist
                    setText(_playlist.getPlaylistName());
                }
            }
        });

        SetConverter();
    }

    private void SetConverter(){
        _songsField.setConverter(new StringConverter<>() {
            @Override
            public String toString(Song _song) {
                if (_song != null){
                    return (_song.getSongName() + " By " + _song.getArtist());
                }
                return null;
            }

            @Override
            public Song fromString(String string) {
                return null;
            }
        });

        _playlistsField.setConverter(new StringConverter<>() {
            @Override
            public String toString(Playlist _playlist) {
                if (_playlist != null){
                    return (_playlist.getPlaylistName());
                }
                return null;
            }

            @Override
            public Playlist fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void AddSongToPlaylist(){
        //Local Variables
        boolean _hasBeenAdded;
        if (_playlistsField.getSelectionModel().getSelectedItem() == null || _songsField.getSelectionModel().getSelectedItem() == null){
            _messageLabel.setText("All Fields Must Be Filled");
            return;
        }

        _hasBeenAdded = _queryHandler.AddSongToPlaylist(_playlistsField.getSelectionModel().getSelectedItem().getPlaylistID() ,
                _songsField.getSelectionModel().getSelectedItem().getSongID());

        if (!_hasBeenAdded){
            _messageLabel.setText("Your selected playlist already contains that song");
            return;
        }
        SceneManager.LoadRhythm(_sessionID, PopupType.PlaylistsTab);
    }

    @FXML //Select Method
    private void Select(ActionEvent _e) {
        //Local Variables
        final Node source = (Node) _e.getSource(); //Node used to get the id from the source to compare
        String id = source.getId();                //Holds the value for the ID as a string

        //Checks if the id from the source is equal to _vehicleSize
        if (id.equals("_playlistsField")) {
            //Sets the vehicle size label text
            _playlistsFieldLabel.setText("");
            return; //Returns
        }
        //Sets the license plate label text
        _songsFieldLabel.setText("");
    }

    @FXML
    private void Clear(){
        _playlistsField.getSelectionModel().clearSelection();
        _playlistsFieldLabel.setText("Available Playlists");
        _songsField.getSelectionModel().clearSelection();
        _songsFieldLabel.setText("Available Songs");
        _messageLabel.setText("");
    }

    @FXML //RotateArrow Method
    private void RotateArrow(MouseEvent _e) {
        //Calls the rotate method and passes the Songs Field, and the angle to rotate
        Rotate(_songsField, 20);
    }

    @FXML //ResetArrow Method
    private void ResetArrow(MouseEvent _e) {
        //Checks if the source of the mouse event is the _vehicle size comboBox
        if (_songsField.equals(_e.getSource())) {
            //Calls the rotate method and passes the vehicle, and the angle to rotate
            Rotate(_songsField, 0);
            return; //Returns
        }
        //Calls the rotate method and passes the vehicle, and the angle to rotate
        Rotate(_songsField, 0);
    }

    //Rotate Method
    private void Rotate(ComboBox<Song> _comboBox , int _angle) {
        //Adds a rotating effect to the dropdown arrow in the combo box
        _comboBox.lookup(".arrow-button").setStyle("-fx-rotate: " + _angle + ";");
    }
}
