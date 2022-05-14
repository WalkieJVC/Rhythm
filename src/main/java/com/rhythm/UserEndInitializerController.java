package com.rhythm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class UserEndInitializerController extends BaseController{
    //Variables
    int _sessionID;
    String _username;
    private SongsTabController _songsTabController;
    private CDsTabController _cDsTabController;
    private PlaylistsTabController _playlistsTabController;

    private final ArrayList<Rectangle> _indicators = new ArrayList<>();
    private final ArrayList<Button> _buttons = new ArrayList<>();
    private User _user;

    //FXML Variables
    @FXML
    private Pane _rootTabPane;
    @FXML
    private Label _welcomeLabel;
    @FXML
    private Rectangle _accountSelectionIndicator;
    @FXML
    private Button _accountButton;
    @FXML
    private Rectangle _songsSelectionIndicator;
    @FXML
    private Button _songsButton;
    @FXML
    private Rectangle _cDsSelectionIndicator;
    @FXML
    private Button _cDsButton;
    @FXML
    private Rectangle _playlistsSelectionIndicator;
    @FXML
    private Button _playlistsButton;

    public void Initialize(int _sessionID, PopupType _type){
        //Assigns the value of session id to the variable _sessionID of this instance
        this._sessionID = _sessionID;

        _username = _queryHandler.GetUsername(_sessionID);

        //Displays a welcome message
        _welcomeLabel.setText("Welcome " + _username);

        //Adds the indicator rectangles to the _indicators ArrayList
        _indicators.add(_accountSelectionIndicator);
        _indicators.add(_songsSelectionIndicator);
        _indicators.add(_cDsSelectionIndicator);
        _indicators.add(_playlistsSelectionIndicator);

        //Adds the buttons to the _buttons ArrayList
        _buttons.add(_accountButton);
        _buttons.add(_songsButton);
        _buttons.add(_cDsButton);
        _buttons.add(_playlistsButton);

        //Calls the GetUserInfo and passes the _username as a parameter;
        _user = _queryHandler.GetUserInfo(_username);

        //Calls the SetIndicatorsInvisible method
        SetIndicatorsInvisible();
        switch (_type){
            case SongsTab -> SelectSongsTab();
            case CDsTab -> SelectCDsTab();
            case PlaylistsTab -> SelectPlaylistsTab();
        }
    }

    @FXML
    void Logout(){
        //Calls the Logout method of the query manager and passes the _sessionID as a parameter
        _queryHandler.Logout(_sessionID);
        StopMusic();
        //Calls the ToLoginPage method from the scene manager, and passes the _rootPane as a parameter
        SceneManager.LoadUserFront("Successfully Logged Out!");
    }

    @FXML //SelectAccountTab Method
    private void SelectAccountTab(){
        StopMusic();
        EnableTabButtons();
        SetIndicatorsInvisible();
        _accountSelectionIndicator.setStyle("-fx-opacity: 1; -fx-stroke-width: 0;");
        _accountButton.setDisable(true);
        AccountTabController _accountTabController = (AccountTabController) SceneManager.LoadPage("AccountTab", _rootTabPane);
        _accountTabController.Initialize(_user, _rootPane, _sessionID);
    }

    @FXML //SelectSongsTab Method
    private void SelectSongsTab(){
        StopMusic();
        EnableTabButtons();
        SetIndicatorsInvisible();
        _songsSelectionIndicator.setStyle("-fx-opacity: 1; -fx-stroke-width: 0;");
        _songsButton.setDisable(true);
        _songsTabController = (SongsTabController) SceneManager.LoadPage("SongsTab", _rootTabPane);
        _songsTabController.Initialize();
    }

    @FXML //SelectCDsTab Method
    private void SelectCDsTab(){
        StopMusic();
        EnableTabButtons();
        SetIndicatorsInvisible();
        _cDsSelectionIndicator.setStyle("-fx-opacity: 1; -fx-stroke-width: 0;");
        _cDsButton.setDisable(true);
        _cDsTabController = (CDsTabController) SceneManager.LoadPage("CDsTab", _rootTabPane);
        _cDsTabController.Initialize();
    }

    @FXML //SelectPlaylistsTab Method
    private void SelectPlaylistsTab(){
        StopMusic();
        EnableTabButtons();
        SetIndicatorsInvisible();
        _playlistsSelectionIndicator.setStyle("-fx-opacity: 1; -fx-stroke-width: 0;");
        _playlistsButton.setDisable(true);
        _playlistsTabController = (PlaylistsTabController) SceneManager.LoadPage("PlaylistsTab", _rootTabPane);
        _playlistsTabController.Initialize(this._sessionID);
    }

    private void StopMusic(){
        //Checks if the _songsTabController is not null
        if (_songsTabController != null){
            //Calls the Stop Method from the _songsTabController
            _songsTabController.Stop();
        }

        //Checks if the _songsTabController is not null
        if (_cDsTabController != null){
            //Calls the Stop Method from the _cDsTabController
            _cDsTabController.Stop();
        }

        //Checks if the _songsTabController is not null
        if (_playlistsTabController != null){
            //Calls the Stop Method from the _cDsTabController
            _playlistsTabController.Stop();
        }
    }

    //SetIndicatorsInvisible Method
    private void SetIndicatorsInvisible(){
        //For-loop used to iterate through every indicator in the indicators list
        for (Rectangle _indicator: _indicators){
            //Sets the style of the indicator
            _indicator.setStyle("-fx-opacity: 0; -fx-stroke-width: 0;");
        }
    }

    //EnableTabButtons Method
    private void EnableTabButtons(){
        //For-loop used to iterate through every button in the buttons list
        for (Button _button: _buttons){
            //Enables the buttons
            _button.setDisable(false);
        }
    }
}
