package com.rhythm;

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
import java.util.Collections;
import java.util.Objects;

public class SongsTabController extends UserEndInitializerController{
    //Variables
    private int _selectedSong;
    private ArrayList<Song> _songs;
    private Song _song;
    private MusicPlayer <Media> _musicPlayer;
    private MediaPlayer _player;
    private Image _playIcon, _pauseIcon, _nextSongIcon, _previousSongIcon, _shuffleIcon;


    //FXML Related Variables
    @FXML
    private ListView<Song> _songsListView;
    @FXML
    private Label _messageLabel, _songNameLabel, _artistNameLabel;
    @FXML
    private Slider _songSlider, _volumeSlider;
    @FXML
    private Button _playPauseButton, _nextSongButton, _previousSongButton, _shuffleButton;

    //Initialize Method
    public void Initialize(){
        //Initializes the Icons
        this._playIcon = new javafx.scene.image.Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/PlayIcon.png")).toString());
        this._pauseIcon = new javafx.scene.image.Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/PauseIcon.png")).toString());
        this._nextSongIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/NextSongIcon.png")).toString());
        this._previousSongIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/PreviousSongIcon.png")).toString());
        this._shuffleIcon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/ShuffleIcon.png")).toString());

        //Sets the button icons
        SetNextSongButtonIcon();
        SetPreviousSongButtonIcon();
        SetShuffleButtonLogo();
        //Sets the volume change listener
        SetOnVolumeChange();

        //Sets _selectedSong to a default value of -1
        _selectedSong = -1;
        //Calls the GetSongs Method from the _queryHandler and the returned value is assigned to _songs
        _songs = _queryHandler.GetSongs();
        //Calls the PopulateListView Method
        PopulateListView();
    }



    private void PopulateListView() {
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
    private void Shuffle(){
        //Sets the selected song to a default value of -1
        _selectedSong = -1;
        //Clears the list view
        _songsListView.getItems().clear();
        //Shuffles the _songs list
        Collections.shuffle(_songs);
        //Calls the PopulateListView Method
        PopulateListView();
    }

    @FXML
    private void SelectSong() {
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

            //Sets the on mouse release listener
            SetOnMouseReleased();

            //Calls the HandleNextSong Method and passes the _tempIndexHolder as a parameter
            HandleNextSong(_tempIndexHolder);
            //Calls the HandlePreviousSong Method and passes the _tempIndexHolder as a parameter
            HandlePreviousSong(_tempIndexHolder);
        }
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

    @FXML //NextSong Method
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

    @FXML //PreviousSong Method
    private void PreviousSong(){
        //Selects the previous song from the list
        _songsListView.getSelectionModel().select(_selectedSong - 1);
        //Calls the SelectSong Method
        SelectSong();
        //Calls the Play Method
        Play();
    }

    //-------------------------- Methods that change the graphic of the buttons ----------------------------------------
    //SetNextSongButtonIcon Method
    private void SetNextSongButtonIcon(){
        ImageView _imageView = new ImageView(_nextSongIcon);
        _imageView.setFitHeight(15);
        _imageView.setFitWidth(15);
        _nextSongButton.setGraphic(_imageView);
    }
    //SetPreviousSongButtonIcon Method
    private void SetPreviousSongButtonIcon(){
        ImageView _imageView = new ImageView(_previousSongIcon);
        _imageView.setFitHeight(15);
        _imageView.setFitWidth(15);
        _previousSongButton.setGraphic(_imageView);
    }
    //SetShuffleButtonLogo Method
    private void SetShuffleButtonLogo(){
        ImageView _imageView = new ImageView(_shuffleIcon);
        _shuffleButton.setGraphic(_imageView);
    }
    //------------------------------Methods that change/set the button action event ------------------------------------
    //HandleNextSong Method
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
        _nextSongButton.setDisable(false); //Disables the button
    }

    //HandlePreviousSong Method
    private void HandlePreviousSong(int _tempIndexHolder){
        //Try-Catch to catch any errors
        try{
            if (_songsListView.getItems().get(_tempIndexHolder - 1) != null){
                _previousSongButton.setDisable(false);
            }
        }catch (Exception _e){
            _previousSongButton.setDisable(true);
        }
    }

    //------------------------------ Methods that the graphics of the buttons ------------------------------------------
    //SetButtonPlayGraphic Method
    private void SetButtonPlayGraphic() {
        ImageView _imageView = new ImageView(_playIcon);
        _imageView.setFitHeight(25);
        _imageView.setFitWidth(25);
        _playPauseButton.setGraphic(_imageView);
    }
    //SetButtonPauseGraphic Method
    private void SetButtonPauseGraphic() {
        ImageView _imageView = new ImageView(_pauseIcon);
        _imageView.setFitHeight(25);
        _imageView.setFitWidth(25);
        _playPauseButton.setGraphic(_imageView);
    }

    //------------------------------ Methods that set the state of the _playPauseButton --------------------------------
    //SetButtonToPlay Method
    private void SetButtonToPlay(){
        SetButtonPlayGraphic();
        _playPauseButton.setOnAction(actionEvent -> Play());
    }
    //SetButtonToPause Method
    private void SetButtonToPause(){
        SetButtonPauseGraphic();
        _playPauseButton.setOnAction(actionEvent -> Pause());
    }

    //---------------------------------- Methods that set the listeners ------------------------------------------------
    //SetOnMouseReleased Method
    public void SetOnMouseReleased() {
        //Skips to a certain point of the video based on the value of the slider
        _songSlider.setOnMouseReleased(mouseEvent -> {
            _musicPlayer.Seek(Duration.seconds(_songSlider.getValue()));
            if (_musicPlayer.GetIsPlaying()) {
                _musicPlayer.Resume();
                return;
            }
            //Calls the SetButtonToPlay() Method
            SetButtonToPlay();
        });
    }

    //SetOnSongEnd Method
    public void SetOnSongEnd(){
        _player.setOnEndOfMedia(this::NextSong);
    }

    //SetOnVolumeChange Method
    private void SetOnVolumeChange() {
        _volumeSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (_musicPlayer != null) {
                    _musicPlayer.AdjustVolume(_volumeSlider.getValue() / 100);
                }
            }
        });

        _volumeSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (_musicPlayer != null) {
                    _musicPlayer.AdjustVolume(_volumeSlider.getValue() / 100);
                }
            }
        });
    }
}
