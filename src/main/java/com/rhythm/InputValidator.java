package com.rhythm;

public class InputValidator {
    //ValidateInput method used to validate the input of the user
    public static boolean ValidateInput(String _string, InputType _inputType)
    {
        //Validator variable
        Validator<String> _stringValidator;

        //Switch used to handle different logics for different input types.
        switch (_inputType) {
            case FirstName, LastName -> {
                //Creates an instance of the validator and uses the String passed and a regex string to construct the object
                _stringValidator = new Validator<>(_string, "^(?=[^ ])(?!.*([ ])\\1)[a-zA-Z' ]{1,25}+(?<=[^ ])$");
                //Checks if the String validator returns true by giving a pattern and the string
                return _stringValidator.IsStringValid(_inputType);
            }
            case Email -> {
                //Creates an instance of the validator and uses the String passed and a regex string to construct the object
                _stringValidator = new Validator<>(_string,
                        "^[^-._ ](?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-._][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
                //Checks if the email validator returned false
                return _stringValidator.IsStringValid(_inputType);
            }
            case PhoneNumber -> {
                //Creates an instance of the validator and uses the String passed and a regex string to construct the object
                _stringValidator = new Validator<>(_string, "^[^0][0-9]{4,15}$");
                //Checks if the String validator returns true by giving a pattern and the string
                return _stringValidator.IsStringValid(_inputType);
            }
            case Username -> {
                //Creates an instance of the validator and uses the String passed and a regex string to construct the object
                _stringValidator = new Validator<>(_string, "^[a-zA-Z0-9]{5,16}$");
                //Checks if the String validator returns true by giving a pattern and the string
                return _stringValidator.IsStringValid(_inputType);
            }
            case Password -> {
                //Creates an instance of the validator and uses the String passed and a regex string to construct the object
                _stringValidator = new Validator<>(_string, "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&]).{10,40}$");
                //Checks if the String validator returns true by giving a pattern and the string
                return _stringValidator.IsStringValid(_inputType);
            }
            case PlaylistName -> {
                //Creates an instance of the validator and uses the String passed and a regex string to construct the object
                _stringValidator = new Validator<>(_string, "^(?=[^ ])(?!.*([ ])\\1)[a-zA-Z ]{1,20}+(?<=[^ ])$");
                //Checks if the String validator returns true by giving a pattern and the string
                return _stringValidator.IsStringValid(_inputType);
            }
        }
        //If it does not pass the check it returns false
        return false;
    }
}
