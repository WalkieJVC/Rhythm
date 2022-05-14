package com.rhythm;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class AddPlaylistController extends PopupBaseController{
    //Variables
    ArrayList<Song> _songs;

    //FXML Related Variables
    @FXML
    TextField _playlistNameField;
    @FXML
    ComboBox<Song> _songsField;
    @FXML
    Label _messageLabel;
    @FXML
    Label _songsFieldLabel;

    //Initialize Method
    public void Initialize(int _sessionID){
        this._sessionID = _sessionID;

        _songs = _queryHandler.GetSongs();

        _playlistNameField.setFocusTraversable(false);

        _songsField.getItems().addAll(_songs);

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

        SetFieldRestrictions();
        SetStringConverter();
    }

    private void SetStringConverter(){
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
    }

    @FXML
    private void CreatePlaylist(){
        //Local Variables
        boolean _hasBeenCreated;
        if (_playlistNameField.getText().equals("") || _songsField.getSelectionModel().getSelectedItem() == null){
            _messageLabel.setText("All Fields Must Be Filled");
            return;
        }

        if (InputValidator.ValidateInput(_playlistNameField.getText(), InputType.PlaylistName)){
            _hasBeenCreated = _queryHandler.CreateNewPlaylist(_playlistNameField.getText(), _songsField.getSelectionModel().getSelectedItem().getSongID(), _sessionID);

            if (!_hasBeenCreated){
                _messageLabel.setText("You Already Have a Playlist With That Name");
                return;
            }
            SceneManager.LoadRhythm(_sessionID, PopupType.PlaylistsTab);
            return;
        }

        _messageLabel.setText("Invalid Playlist Name Format");
    }

    @FXML //Select Method
    private void Select() {
        //Sets the license plate label text
        _songsFieldLabel.setText("");
    }

    @FXML
    private void Clear(){
        _playlistNameField.clear();
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

    private void SetFieldRestrictions(){
        _playlistNameField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("[a-zA-Z0-9 ]")) {
                _playlistNameField.setText(t1.replaceAll("[^a-zA-Z0-9 ]", ""));
            }
        });
    }
}
