package com.rhythm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class CDsTabController extends UserEndInitializerController{

    //Variables
    private int _selectedCD;
    private int _selectedSong;
    private ArrayList<CD> _cDs;
    private ArrayList<Song> _songs;
    private CD _cD;
    private Song _song;
    private MusicPlayer <Media> _musicPlayer;
    private MediaPlayer _player;
    private Image _playIcon, _pauseIcon, _nextSongIcon, _previousSongIcon;

    //FXML Related Variables
    @FXML
    private ListView<CD> _cDsListView;
    @FXML
    private ListView<Song> _songsListView;
    @FXML
    private Label _messageLabel, _songNameLabel, _artistNameLabel, _cDNameLabel;
    @FXML
    private Slider _songSlider, _volumeSlider;
    @FXML
    private Button _playPauseButton, _nextSongButton, _previousSongButton;

    public void Initialize(){
        this._playIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/PlayIcon.png")).toString());
        this._pauseIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/PauseIcon.png")).toString());
        this._nextSongIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/NextSongIcon.png")).toString());
        this._previousSongIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/PreviousSongIcon.png")).toString());

        SetNextSongButtonIcon();
        SetPreviousSongButtonIcon();
        SetOnVolumeChange();

        //Sets _selectedSong to a default value of -1
        _selectedCD = -1;
        _selectedSong = -1;
        //Calls the GetCDs Method from the _queryHandler and the returned value is assigned to _cDs
        _cDs = _queryHandler.GetCDs();
        //Calls the PopulateListView Method
        PopulateCDListView();
    }

    @FXML //Play Method
    private void Play(){
        //Calls the play method from the media player
        _musicPlayer.Play();
        SetButtonToPause();
    }

    @FXML
    private void Resume(){
        //Calls the play method from the media player
        _musicPlayer.Resume();
        SetButtonToPause();
    }

    @FXML //Pause Method
    private void Pause(){
        _musicPlayer.Pause();
        SetButtonToPlay();
    }

    void Stop() {
        if (_musicPlayer != null){
            _musicPlayer.Stop();
            SetButtonToPlay();
        }
    }

    @FXML
    private void NextSong(){
        try {
            if (_songsListView.getItems().get(_selectedSong + 1) != null){
                _songsListView.getSelectionModel().select(_selectedSong + 1);
            }
        }catch (Exception _e) {
            _songsListView.getSelectionModel().select(0);
        }
        SelectSong();
        Play();
    }

    @FXML
    private void PreviousSong(){
        _songsListView.getSelectionModel().select(_selectedSong - 1);
        SelectSong();
        Play();
    }

    @FXML
    private void SelectCD(){
        //local variable
        int _tempIndexHolder = _cDsListView.getSelectionModel().getSelectedIndex();
        //Checks if the selected song is already selected
        if (_selectedCD != _tempIndexHolder){
            //Checks if the _mediaPlayer is not null
            if (_musicPlayer != null) {
                //Calls the stop method from the _mediaPlayer
                _musicPlayer.Stop();
            }
            //Assigns the _tempIndexHolder value to _selectedSong variable
            _selectedCD = _tempIndexHolder;

            //Calls the getSelectedItem from the SelectionModel of the ListView and the returned item is assigned to _cD
            _cD = _cDsListView.getSelectionModel().getSelectedItem();

            //Displays the song name with the _cDNameLabel
            _cDNameLabel.setText(_cD.getCDName());

            //Calls the GetSongs Method from the _queryHandler and the returned value is assigned to _songs
            _songs = _queryHandler.GetSongs(_cD.getCDiD(), "CD");

            PopulateSongListView();
        }
    }

    private void PopulateCDListView() {
        //Adds all the songs in the _songs to the list view
        _cDsListView.getItems().addAll(_cDs);
        //Adds a cell factory to display the name and artist of the song and still being able to store the object itself
        _cDsListView.setCellFactory(param -> new ListCell<>() {
            @Override //updateItem Method
            protected void updateItem(CD _cD, boolean empty) {
                super.updateItem(_cD, empty);

                //Checks if the object, songName, and artist are not null and if its empty
                if (empty || _cD == null || _cD.getCDName() == null) {
                    setText(null); //Sets the text to null
                } else {
                    //Sets the text to song name by artist
                    setText(_cD.getCDName());
                }
            }
        });
        //Calls the selectFirst method from the selection model of the list view
        _cDsListView.getSelectionModel().selectFirst();
        //Calls the SelectSong method
        SelectCD();
    }

    private void PopulateSongListView() {
        _songsListView.getItems().clear();
        _selectedSong = -1;
        //Adds all the songs in the _songs to the list view
        _songsListView.getItems().addAll(_songs);
        //Adds a cell factory to display the name and artist of the song and still being able to store the object itself
        _songsListView.setCellFactory(param -> new ListCell<>() {
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
        //Calls the selectFirst method from the selection model of the list view
        _songsListView.getSelectionModel().selectFirst();
        //Calls the SelectSong method
        SelectSong();
    }

    @FXML
    private void SelectSong(){
        _songSlider.setValue(0);
        //local variable
        int _tempIndexHolder = _songsListView.getSelectionModel().getSelectedIndex();
        //Checks if the selected song is already selected
        if (_selectedSong != _tempIndexHolder){
            //Checks if the _mediaPlayer is not null
            if (_musicPlayer != null)
            {
                //Calls the stop method from the _mediaPlayer
                _musicPlayer.Stop();
            }
            //Assigns the _tempIndexHolder value to _selectedSong variable
            _selectedSong = _tempIndexHolder;

            //Calls the getSelectedItem from the SelectionModel of the ListView and the item is assigned to _song
            _song = _songsListView.getSelectionModel().getSelectedItem();

            //Displays the song Name
            _songNameLabel.setText(_song.getSongName());
            //Displays the artist name
            _artistNameLabel.setText("By: " + _song.getArtist());

            InitializeMediaPlayer();
            //Calls the SetButtonToPlay Method
            SetButtonToPlay();

            SetOnMouseReleased();

            HandleNextSong(_tempIndexHolder);
            HandlePreviousSong(_tempIndexHolder);
        }
    }

    private void HandleNextSong(int _tempIndexHolder) {
        //Try-Catch to catch any errors
        try {
            //Displays the next song on the list
            _messageLabel.setText("Next Up: " + _songsListView.getItems().get(_tempIndexHolder + 1).getSongName() + " by " +
                    _songsListView.getItems().get(_tempIndexHolder + 1).getArtist());
        } catch (Exception _e) {
            _nextSongButton.setDisable(true);
            //Displays a message
            _messageLabel.setText("This is the final song");
            return; //Returns
        }
        _nextSongButton.setDisable(false);
    }

    private void HandlePreviousSong(int _tempIndexHolder){
        //Try-Catch to catch any errors
        try{
            if (_songsListView.getItems().get(_tempIndexHolder - 1) != null){
                _previousSongButton.setDisable(false);
            }
        }catch (Exception _e){
            _previousSongButton.setDisable(true);
            return; //Returns
        }
    }

    public void SetOnMouseReleased() {
        //Skips to a certain point of the video based on the value of the slider
        _songSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                _musicPlayer.Seek(Duration.seconds(_songSlider.getValue()));
                if (_musicPlayer.GetIsPlaying()) {
                    _musicPlayer.Resume();
                    return;
                }
                //Calls the SetButtonToPlay() Method
                SetButtonToPlay();
            }
        });
    }

    private void InitializeMediaPlayer(){
        //Gets the song path instantiates a media and with that instantiates a new MusicPlayer;
        _musicPlayer = new MusicPlayer(new Media(_song.getSongPath()), _songSlider);

        _player = _musicPlayer.getMediaPlayer();
        SetOnSongEnd();

        //Calls the setOnReady method of the music player
        _musicPlayer.SetOnReady();

        //Calls the currentTimeProperty method of the music player
        _musicPlayer.CurrentTimeProperty();

        //Calls the setOnMousePressed method of the music player
        _musicPlayer.SetOnMousePressed();

        //Calls the AdjustVolume method of the music player
        _musicPlayer.AdjustVolume(_volumeSlider.getValue() / 100);
    }

    public void SetOnSongEnd(){
        _player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                NextSong();
            }
        });
    }

    //SetButtonToPlay Method
    private void SetButtonToPlay(){
        ImageView _imageView = new ImageView(_playIcon);
        _imageView.setFitHeight(25);
        _imageView.setFitWidth(25);
        _playPauseButton.setGraphic(_imageView);
        _playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Play();
            }
        });
    }

    //SetButtonToPause Method
    private void SetButtonToPause(){
        ImageView _imageView = new ImageView(_pauseIcon);
        _imageView.setFitHeight(25);
        _imageView.setFitWidth(25);
        _playPauseButton.setGraphic(_imageView);
        _playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pause();
            }
        });
    }

    private void SetNextSongButtonIcon(){
        ImageView _imageView = new ImageView(_nextSongIcon);
        _imageView.setFitHeight(15);
        _imageView.setFitWidth(15);
        _nextSongButton.setGraphic(_imageView);
    }

    private void SetPreviousSongButtonIcon(){
        ImageView _imageView = new ImageView(_previousSongIcon);
        _imageView.setFitHeight(15);
        _imageView.setFitWidth(15);
        _previousSongButton.setGraphic(_imageView);
    }

    private void SetOnVolumeChange() {
        _volumeSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                _musicPlayer.AdjustVolume(_volumeSlider.getValue() / 100);
            }
        });

        _volumeSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                _musicPlayer.AdjustVolume(_volumeSlider.getValue() / 100);
            }
        });
    }
}
