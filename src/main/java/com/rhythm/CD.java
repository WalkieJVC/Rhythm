package com.rhythm;

public class CD {
    //Variables
    private final int _cDiD;
    private final String _cDName;

    //Constructor
    public CD(int _cDiD, String _cDName){
        this._cDiD = _cDiD;
        this._cDName = _cDName;
    }

    //Getters
    public int getCDiD(){return this._cDiD;}
    public String getCDName(){return this._cDName;}
}
