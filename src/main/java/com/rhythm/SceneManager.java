package com.rhythm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class SceneManager {
        //Variables
    private static Stage _stage;
    private static FXMLLoader _fxmlLoader;
    private static Pane _pane;
    private static BorderPane _root;
    private static Scene _scene;


    //InitializePrimaryStage
    public static void Initialize(Stage stage) {
        //Assigns the parameter stage to the variable _stage
        _stage = stage;
        //Creates a new instance of an FXMLLoader and gets the LoginPage fxml file
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/BaseFront.fxml"));

        //Try-Catch used to catch any errors
        try{
            //Creates a new scene instance and passes the loaded fxml file and the window dimensions
            _scene = new Scene(_fxmlLoader.load(), 640, 720);
        }catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println("Unable To Load - ERROR Cause: " + _e.getCause());
            _e.printStackTrace();
            return; //Returns
        }

        //Creates a new instance of an Image and gets the path to the logo
        Image _icon = new Image(Objects.requireNonNull(Main.class.getResource("ImageFiles/RhythmLogo.png")).toString());
        //Sets the Icon of the window
        _stage.getIcons().add(_icon);
        //Sets the window to undecorated
        _stage.initStyle(StageStyle.UNDECORATED);
        //Makes the window not resizeable
        _stage.setResizable(false);
        //Sets the scene
        _stage.setScene(_scene);
        _root = (BorderPane) _scene.getRoot();
        //Gets the controller from the _fxmlLoader
        FrontEndInitializerController _controller = _fxmlLoader.getController();
        //Calls the Initialize Method from the _controller
        _controller.Initialize("Welcome to Rhythm");
    }

    public static void LoadUserFront(String _message){
        _stage.hide();
        //Creates an instance of the FXMLLoader
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/BaseFront.fxml"));
        //Try-Catch used to catch any errors
        try{
            //Loads the fxml file and assigns it to the borderPane
            _root.getChildren().setAll((Node) _fxmlLoader.load());
        }catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println(_e.getCause());
            _e.printStackTrace();
            return; //Returns
        }
        //_sets all the children of the root pane to the borderPane

        //Sets the title of the window
        _stage.setTitle("Rhythm - Login");
        ChangeStageSize(640);
        //Shows the stage
        _stage.show();
        //Gets the controller from the _fxmlLoader
        FrontEndInitializerController _controller = _fxmlLoader.getController();
        //Calls the Initialize Method from the _controller
        _controller.Initialize(_message);
    }

    public static LoginController LoadLogin(Pane _rootPage, String _message){
        //Creates an instance of the FXMLLoader
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/Login.fxml"));
        //Try-Catch used to catch any errors
        try{
            //Loads the fxml file and assigns it to the borderPane
            _pane = _fxmlLoader.load();
        }catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println(_e.getCause());
            _e.printStackTrace();
            return null; //Returns
        }
        //_sets all the children of the root pane to the borderPane
        _rootPage.getChildren().setAll(_pane);
        //Sets the title of the window
        _stage.setTitle("Rhythm - Login");
        ChangeStageSize(640);
        _stage.centerOnScreen();
        //Shows the stage
        _stage.show();
        //Gets the controller from the _fxmlLoader
        LoginController _controller = _fxmlLoader.getController();
        //Calls the Initialize Method from the _controller
        _controller.Initialize(_rootPage, _message);
        return _controller;
    }

    public static void LoadRegister(Pane _rootPage){
        //Creates an instance of the FXMLLoader
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/Register.fxml"));
        //Try-Catch used to catch any errors
        try{
            //Loads the fxml file and assigns it to the borderPane
            _pane = _fxmlLoader.load();
        }catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println(_e.getCause());
            _e.printStackTrace();
            return; //Returns
        }
        //Sets all the children of the root pane to the borderPane
        _rootPage.getChildren().setAll(_pane);
        //Sets the title of the window
        _stage.setTitle("Rhythm - Register");
        //Shows the stage
        _stage.show();
        //Gets the controller from the _fxmlLoader
        RegisterController _controller = _fxmlLoader.getController();
        //Calls the Initialize Method from the _controller
        _controller.Initialize(_rootPage);
    }

    public static void LoadRhythm(int _sessionID, PopupType _type){
        _stage.hide();
        //Creates an instance of the FXMLLoader
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/RhythmBase.fxml"));
        //Try-Catch used to catch any errors
        try{
            //Loads the fxml file and assigns it to the borderPane
            _pane = _fxmlLoader.load();
        }catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println(_e.getCause());
            _e.printStackTrace();
            return; //Returns
        }
        //Sets all the children of the root pane to the borderPane
        _root.getChildren().setAll(_pane);
        //Sets the title of the window
        _stage.setTitle("Rhythm - Register");
        ChangeStageSize(1280);
        _stage.centerOnScreen();
        //Shows the stage
        _stage.show();
        //Gets the controller from the _fxmlLoader
        UserEndInitializerController _controller = _fxmlLoader.getController();
        //Calls the Initialize Method from the _controller
        _controller.Initialize(_sessionID, _type);
    }

    //InitializeSmallWindow Method
    public static void LoadPopup(PopupType _type, int _sessionID){
        _stage.hide();
        //Creates an instance of the FXMLLoader
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/PopupBase.fxml"));
        //Try-Catch used to catch any errors
        try{
            //Loads the fxml file and assigns it to the borderPane
            _pane = _fxmlLoader.load();
        }catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println(_e.getCause());
            _e.printStackTrace();
            return; //Returns
        }
        //Sets all the children of the root pane to the borderPane
        _root.getChildren().setAll(_pane);

        if (_type.toString().equals("AddPlaylist")){
            //Sets the title of the window
            _stage.setTitle("Rhythm - Create New Playlist");
        } else {
            //Sets the title of the window
            _stage.setTitle("Rhythm - Add Song To Playlist");
        }
        ChangeStageSize(640);
        _stage.centerOnScreen();
        //Shows the stage
        _stage.show();
        //Gets the controller from the _fxmlLoader
        PopupBaseController _controller = _fxmlLoader.getController();
        //Calls the Initialize Method from the _controller
        _controller.Initialize(_type, _sessionID);
    }

    public static Object LoadPage(String _tabName, Pane _rootTab){
        //Creates an instance of the FXMLLoader
        _fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLFiles/" + _tabName + ".fxml"));
        try {
            //Loads the fxml file and assigns it to the borderPane
            _pane = _fxmlLoader.load();
        } catch (Exception _e){
            //Displays a message to the console with the cause of the error
            System.out.println("Unable To Load Tab");
            _e.printStackTrace();
        }

        //Sets all the children of the root pane to the borderPane
        _rootTab.getChildren().setAll(_pane);
        //Sets the root tab to the center
        _rootTab.setCenterShape(true);

        //Returns the controller
        return _fxmlLoader.getController();
    }

    //ChangeStageSize Method
    private static void ChangeStageSize(int _width){
        //Changes the width of the stage
        _stage.setWidth(_width);
    }

}