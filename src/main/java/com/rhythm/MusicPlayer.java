package com.rhythm;

import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer <M extends Media>{
    M _media;
    MediaPlayer _mediaPlayer;
    Slider _slider;
    boolean _isPlaying;

    public MusicPlayer(M _media, Slider _slider) {
        this._media = _media;
        _mediaPlayer = new MediaPlayer(_media);
        this._slider = _slider;
        this._isPlaying = false;
    }

    public void Play(){
        Resume();
    }

    public void Resume(){
        //Seeks the duration in seconds by getting the value on the slider
        _mediaPlayer.seek(Duration.seconds(_slider.getValue()));
        //Plays the song
        _mediaPlayer.play();
        _isPlaying = true;
    }

    public void Pause(){
        //Pauses the song
        _mediaPlayer.pause();
        _isPlaying = false;
    }

    public void Stop(){
        //Checks if the _mediaPlayer is not null
        if (_mediaPlayer != null){
            //Stops the song
            _mediaPlayer.stop();
            _isPlaying = false;
        }
    }

    public void SetOnReady(){
        //Sets the max length of the slider based on the duration of the media
        _mediaPlayer.setOnReady(() -> _slider.setMax(_media.getDuration().toSeconds()));
    }

    public void CurrentTimeProperty(){
        //Sets the value to the slider as the song progresses by using a listener
        _mediaPlayer.currentTimeProperty().addListener((observableValue, duration, t1) -> _slider.setValue(t1.toSeconds()));
    }

    public void SetOnMousePressed(){
        _slider.setOnMousePressed(mouseEvent -> _mediaPlayer.pause());
    }

    public boolean GetIsPlaying(){
        return _isPlaying;
    }

    public MediaPlayer getMediaPlayer(){
        return _mediaPlayer;
    }

    public void Seek(Duration _seconds) {
        _mediaPlayer.seek(_seconds);
    }

    public void AdjustVolume(double _value) {
        _mediaPlayer.setVolume(_value);
    }
}
