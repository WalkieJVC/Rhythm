package com.rhythm;

public class FrontEndInitializerController extends BaseController{
    //Initialize Method
    public void Initialize(String _message){
        //Calls the LoadMainMenu from the scene manager and passes the _rootPage as a parameter
        SceneManager.LoadLogin(_rootPage, _message);
    }
}
