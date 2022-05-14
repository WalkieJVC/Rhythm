package com.rhythm;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AccountTabController extends UserEndInitializerController{

    //Variables
    User _user;
    //TextFields
    @FXML
    private TextField _usernameField;
    @FXML
    private TextField _firstNameField;
    @FXML
    private TextField _lastNameField;
    @FXML
    private TextField _phoneNumberField;
    @FXML
    private TextField _emailField;

    //Initialize Method
    public void Initialize(User _user, BorderPane _rootPane, int _sessionID){
        this._user = _user;
        //Sets the text of the text fields to the returned values
        _usernameField.setText(this._user.getUsername());
        _firstNameField.setText(this._user.getFirstName());
        _lastNameField.setText(this._user.getLastName());
        _phoneNumberField.setText(this._user.getPhoneNumber());
        _emailField.setText(this._user.getEmail());

        //Assigns the parameter value of _rootPane to the variable _rootPane of this instance
        this._rootPane = _rootPane;
        //Assigns the parameter value of _sessionID to the variable _sessionID of this instance
        this._sessionID = _sessionID;
    }

    //DeleteAccount Method
    @FXML
    private void DeleteAccount() {
        //Calls the DeleteAccount method from the query manager and passes the returned value from the getUsername method from the user object of this instance.
        _queryHandler.DeleteAccount(this._user.getUsername());

        //Calls the ToLoginPage method from the scene manager, and passes the _rootPane as a parameter
        SceneManager.LoadUserFront("Account Successfully Deleted");
    }
}
