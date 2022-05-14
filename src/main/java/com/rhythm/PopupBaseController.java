package com.rhythm;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class PopupBaseController extends BaseController{
    //Variables
    int _sessionID;
    AddPlaylistController _addPlaylistController;
    AddSongController _addSongController;

    //FXML Related Variables
    @FXML
    Pane _rootPage;

    public void Initialize(PopupType _type, int _sessionID){
        this._sessionID = _sessionID;

        switch (_type){
            case AddPlaylist -> {
                _addPlaylistController = (AddPlaylistController) SceneManager.LoadPage(_type.toString(), _rootPage);
                _addPlaylistController.Initialize(_sessionID);
            }
            case AddSong -> {
                _addSongController = (AddSongController) SceneManager.LoadPage(_type.toString(), _rootPage);
                _addSongController.Initialize(_sessionID);
            }
        }
    }

    @FXML
    private void Cancel(){
        SceneManager.LoadRhythm(_sessionID, PopupType.PlaylistsTab);
    }
}
