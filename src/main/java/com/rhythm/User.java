package com.rhythm;

public class User {
    //Variables
    String _firstName;      //Used to store the first name
    String _lastName;       //Used to store the last name
    String _phoneNumber;    //Used to store the phone number
    String _email;          //Used to store the email
    String _username;       //Used to store the username

    //Constructor
    public User(String _firstName, String _lastName, String _phoneNumber, String _email, String _username){
        //Assigns the parameter values to the variables of this instance.
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._phoneNumber = _phoneNumber;
        this._email = _email;
        this._username = _username;
    }

    //Getters
    public String getFirstName(){return this._firstName;}       //Returns the first name
    public String getLastName(){return this._lastName;}         //Returns the last name
    public String getPhoneNumber(){return this._phoneNumber;}   //Returns the phone number
    public String getEmail(){return this._email;}               //Returns the email
    public String getUsername(){return this._username;}         //Returns the username
}
