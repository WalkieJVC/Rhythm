package com.rhythm;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class RegisterController extends FrontEndInitializerController {
    //Variables
    private final ArrayList<TextInputControl> _fields = new ArrayList<>();

    //FXML Related Variables
    @FXML
    private Label _messageLabel;
    @FXML
    private TextField _firstName;
    @FXML
    private TextField _lastName;
    @FXML
    private TextField _phoneNumber;
    @FXML
    private TextField _email;
    @FXML
    private TextField _username;
    @FXML
    private PasswordField _password;

    //Override Methods
    //Initialize Method
    public void Initialize(Pane _rootPage){
        this._rootPage = _rootPage;
        //Adds every field to the fields arraylist
        _fields.add(_firstName);
        _fields.add(_lastName);
        _fields.add(_phoneNumber);
        _fields.add(_email);
        _fields.add(_username);
        _fields.add(_password);

        SetFieldRestrictions();

        //Deselects the TextField when it initializes
        for (TextInputControl _field: _fields){
            _field.setFocusTraversable(false);
        }
    }

    //FXML Methods
    @FXML //Register Method
    private void Register(){
        //For-Loop used to iterate through every field in the arraylist _fields
        for (TextInputControl _field: _fields){
            //Checks if the field is empty
            if (_field.getText().equals("")){
                //Displays a message
                _messageLabel.setText("ALL Fields Must Be Filled.");
                return; //Returns
            }
        }

        //Gets the text from the text fields and assigns them to their respected variables
        String _fName = _firstName.getText();
        String _lName = _lastName.getText();
        String _pNumber = _phoneNumber.getText();
        String _eMail = _email.getText();
        String _uName = _username.getText();
        String _pWord = _password.getText();


       //Checks if the user input valid inputs by calling the ValidateInput Method from the InputValidator Class
        if (!InputValidator.ValidateInput(_fName, InputType.FirstName)){
            //Displays a Message
            DisplayMessage("First Name Is Not Valid.");
            return; //Returns
        } else if (!InputValidator.ValidateInput(_lName, InputType.LastName)){
            //Displays a Message
            DisplayMessage("Last Name Is Not Valid.");
            return; //Returns
        } else if (!InputValidator.ValidateInput(_pNumber, InputType.PhoneNumber)){
            //Displays a Message
            DisplayMessage("Phone Number Is Not Valid.");
            return; //Returns
        } else if (!InputValidator.ValidateInput(_eMail, InputType.Email)){
            //Displays a Message
            DisplayMessage("Email Is Not Valid.");
            return; //Returns
        } else if (!InputValidator.ValidateInput(_uName, InputType.Username)){
            //Displays a Message
            DisplayMessage("Username Is Not Valid.");
            return; //Returns
        } else if (!InputValidator.ValidateInput(_pWord, InputType.Password)){
            //Displays a Message
            DisplayMessage("Password Is Not Valid.");
            return; //Returns
        }

        //Calls the Register Method From the QueryManager and passes the first and last name, phone number, email, username, and password as parameters
        if (_queryHandler.Register(_fName, _lName, _pNumber, _eMail, _uName, _pWord)){
            Login("Account Successfully Created!");
            return;
        }
        DisplayMessage("Username Already Exists.");
    }

    @FXML //ToLoginPage Method
    private void Login(String _message){
        //Calls the ToLoginPage Method of the SceneManager Class
        SceneManager.LoadLogin(_rootPage, _message);
    }

    @FXML //ToLoginPage Method
    private void Login(){
        //Calls the ToLoginPage Method of the SceneManager Class
        SceneManager.LoadLogin(_rootPage, "");
    }

    //Regular Methods
    //Clear Method
    public void Clear() {
        ClearFields();
        _messageLabel.setText("");
    }

    //ClearFields Method
    private void ClearFields(){
        //For-loop used to iterate through every instance of the arraylist fields.
        for (TextInputControl field : _fields) {
            //Clears the field
            field.clear();
        }
    }

    //DisplayMessage Method
    private void DisplayMessage(String _message){
        _messageLabel.setText(_message);
    }

    private void SetFieldRestrictions(){
        _phoneNumber.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d")) {
                _phoneNumber.setText(t1.replaceAll("\\D", ""));
            }
        });

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
