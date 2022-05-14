package com.rhythm;

import javafx.application.Application;
import javafx.stage.Stage;

public class Rhythm extends Application {
    public static void StartApplication(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage _stage){
        SceneManager.Initialize(_stage);
    }
}
