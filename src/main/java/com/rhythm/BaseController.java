package com.rhythm;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class BaseController {
    //Variables
    Stage _stage;
    private double _xOffset;
    private double _yOffset;
    QueryHandler _queryHandler = new QueryHandler();

    //FXML Related Variables
    @FXML
    private Button _button;
    @FXML
    BorderPane _rootPane;
    @FXML
    Pane _rootPage;

        //Methods
    @FXML //QuitApplication Method
    void QuitApplication() {
        Platform.exit(); //Quits the application
    }

    @FXML //MinimizeApplication Method
    private void MinimizeApplication() {
        //Gets the stage
        _stage = (Stage) _rootPane.getScene().getWindow();
        //Minimizes the application
        _stage.setIconified(true);
    }

    @FXML //HoverEnter Method
    private void HoverEnter(Event _event) {
        //Gets the button
        _button = (Button) _event.getSource();
        //Checks for the button ID
        if (_button.getId().matches("_noBackgroundButton"))
        {
            //If ID matches, sets the style of the button to text fill while
            _button.setStyle("-fx-text-fill: #ffffff;" +
                    "-fx-background-color: transparent;");
            return; //Returns
        }
        //If not, only changes the background color
        _button.setStyle("-fx-background-color: rgba(120,177,255,0.15);");
    }

    @FXML //HoverExit Method
    private void HoverExit() {
        //Checks for the button ID
        if (_button.getId().matches("_noBackgroundButton"))
        {
            //If ID matches, sets the style of the button to text fill while
            _button.setStyle("-fx-text-fill: #b5b5b5;" +
                    "-fx-background-color: transparent;");
            return; //Returns
        }
        //If not, only changes the background color
        _button.setStyle("-fx-background-color: transparent;");
    }

    @FXML //DragWindow Method
    private void DragWindow(Event _e) {
        Pane _pane = (Pane) _e.getSource();

        if (_pane.getId().equals("_moveable")){
            //Gets the scene
            Scene _scene = _rootPane.getScene();
            //Gets the stage from the scene
            _stage = (Stage) _rootPane.getScene().getWindow();

            //Uses the set on mouse pressed event to get the x and y position of the scene
            _scene.setOnMousePressed(event -> {
                //Gets the x and y position of the scene
                _xOffset = event.getSceneX();
                _yOffset = event.getSceneY();
            });

            //Uses the set on mouse dragged event to move the window by setting the x and y position of the stage
            _pane.setOnMouseDragged(event -> {
                //Moves the stage
                _stage.setX(event.getScreenX() - _xOffset);
                _stage.setY(event.getScreenY() - _yOffset);
            });
        }
    }
}
