package com.rhythm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class LoginController extends FrontEndInitializerController{
    //Variables
    private final ArrayList<TextInputControl> _fields = new ArrayList<>();

    //FXML Variables
    @FXML
    Label _messageLabel;
    @FXML
    private TextField _username;
    @FXML
    private PasswordField _password;

    //Override Methods
    public void Initialize(Pane _rootPage, String _message){
        //Assigns the value of the parameter _rootPage to the variable of this instance _rootPage
        this._rootPage = _rootPage;
        //Adds the fields to the fields ArrayList
        _fields.add(_username);
        _fields.add(_password);

        DisplayMessage(_message);

        //Deselects the TextField when it initializes
        for (TextInputControl _field: _fields){
            //De-selects the field
            _field.setFocusTraversable(false);
        }

        SetFieldRestrictions();
    }

    //FXML Methods
    @FXML
    private void Login(){
        DisplayMessage("Attempting To Login...");
        //For-Loop used to iterate through every field in the arraylist _fields
        for (TextInputControl _field: _fields){
            //Checks if the field is empty
            if (_field.getText().equals("")){
                //Displays a message
                DisplayMessage("ALL Fields Must Be Filled.");
                return; //Returns
            }
        }

        //Gets the valued from the text and password fields and assigns them to their respected variables
        String _uName = _username.getText();
        String _pWord = _password.getText();

        //Calls the Login Method from the query handler, passes the _uName and _pWord as parameters and the returned value is assigned to _sessionID
        int _sessionID = _queryHandler.Login(_uName, _pWord);

        //Checks if _sessionID does not equal -1
        if (_sessionID != -1){
            //Calls the LoadRhythm Method from the scene manager and passes the _rootPane, _uName, and _sessionID as parameters
            SceneManager.LoadRhythm(_sessionID, PopupType.SongsTab);
            return;
        }

        DisplayMessage("Incorrect Username or Password!");
    }

    @FXML
    private void Register(){
        //Calls the ToRegisterPage Method from the SceneManager Class and passes the root pane as a parameter
        SceneManager.LoadRegister(_rootPage);
    }

    public void Clear() {
        //Calls the ClearFields Method
        ClearFields();
        //Sets the text of the message label
        _messageLabel.setText("");
    }

    //Regular Methods
    private void ClearFields(){
        //For-loop used to iterate through every instance of the arraylist fields.
        for (TextInputControl field : _fields) {
            //Clears the field
            field.clear();
        }
    }

    public void DisplayMessage(String _message) {
        _messageLabel.setText(_message);
    }

    private void SetFieldRestrictions(){
        _username.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("[a-zA-Z0-9]")) {
                _username.setText(t1.replaceAll("[^a-zA-Z0-9]", ""));
            }
        });

        _password.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("[a-zA-Z0-9!@#$%&]")) {
                _password.setText(t1.replaceAll("[^a-zA-Z0-9!@#$%&]", ""));
            }
        });
    }
}
