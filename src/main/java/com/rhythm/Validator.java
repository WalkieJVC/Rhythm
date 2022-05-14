package com.rhythm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator <T extends String> {

    //Class variables
    T _patternObject;         //Used to store the pattern
    String _userInput;        //Used to store the string

    public Validator(String _string, String _stringPattern) {
        //Casts the string pattern as an object and assigns it to the pattern object variable
        _patternObject = (T) _stringPattern;
        //Assigns the value of input string to _user input
        _userInput = _string;
    }

    public boolean IsStringValid(InputType _inputType) {
        //local variables
        Pattern _pattern;
        Matcher _matcher;

        //Compiles the pattern
        _pattern = Pattern.compile(_patternObject);

        //Adds the email to _matcher.
        _matcher = _pattern.matcher(_userInput);
        //Checks if the input type
        if (_inputType == InputType.Email && _userInput.contains("@")) {
            //if email calls the IsEmailValid method and returns the returned value
            return IsEmailValid(_matcher.matches());
        } else if (_inputType == InputType.Email && !_userInput.contains("@")) {
            return false; //Returns false
        }
        //else it returns the returned value or _matcher.matches
        return _matcher.matches();
    }

    private boolean IsEmailValid(boolean _isValid) {
        //local variables
        int _periodCount = 0;

        //Checks for local length and domain length as well as if the character starts with a letter or a number
        _isValid = (_isValid && _userInput.indexOf('@') <= 64 && _userInput.length() < 256 &&
                Character.isLetter(_userInput.charAt(0)) || Character.isDigit(_userInput.charAt(0)));


        //Checks if the _isValid is true and if the local part of the email ends in a letter or a number.
        if(_isValid && Character.isLetter(_userInput.charAt(_userInput.indexOf('@') - 1)) || Character.isDigit(_userInput.charAt(_userInput.indexOf('@') - 1))) {// if valid
            //For loop used to check for how many periods are after the @ symbol
            for (int i = 0; i < _userInput.substring(_userInput.indexOf('@')).length(); i++) {
                //Checks if the character at position I after the @ is a period
                if(_userInput.substring(_userInput.indexOf('@')).charAt(i) == '.') {
                    //add 1 to the period count variable every time it encounters a period
                    _periodCount++;
                }
            }
        } else {
            //Returns false
            return false;
        }

        //Returns true if _isValid = true and if period count is two or one. Else it returns false.
        return (_periodCount < 3);
    }
}
