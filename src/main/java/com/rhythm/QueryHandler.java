package com.rhythm;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class QueryHandler {
    //Variables
    private Connection _connection;
    private CallableStatement _statement;
    private ResultSet _result;

    //----------------------------------------ACCOUNT RELATED METHODS---------------------------------------------------
    //Login Method
    public int Login(String _username, String _password){
        //Initializes the _sP_Register string with the sql call statement.
        String _sP_Login = "call `Rhythm`.`Login` ('" + _username + "','" + _password + "');";
        int _sessionID = -1; //Variable to hold the sessionID. Default -1

        //Calls the ExecuteQuery Method, passes _sP_Login variable as a parameter and the and assigns the returned value to variable _result
        _result = ExecuteQuery(_sP_Login);

        //Try-Catch to catch any errors
        try {
            //Checks if the result set has next
            if(_result != null && _result.next())
            {
                //Gets the boolean value from the column value from the column value and assigns it to the variable _isLoggedIn
                boolean _isLoggedIn = Boolean.parseBoolean(_result.getString("Value"));

                if (_isLoggedIn){
                    _sessionID = Integer.parseInt(_result.getString("SessionID"));
                }
                //Calls the CloseConnection Method
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On Login");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        return _sessionID; //Returns -1
    }

    //Register Method
    public boolean Register(String _firstName, String _lastName, String _phoneNumber, String _email, String _username, String _password){
        //Initializes the _sP_Register string with the sql call statement.
        String _sP_Register = "call `Rhythm`.`Register` ('" + _firstName + "','" + _lastName + "','" + _phoneNumber +
                "','" + _email + "','" + _username + "','" + _password + "');";

        //Calls the ExecuteQuery Method and passes the string procedure call as a parameter. The returned ResultSet is assigned to the variable _result
        _result = ExecuteQuery(_sP_Register);

        //Try-Catch to catch any errors
        try {
            //Checks if the result set has next
            if(_result != null && _result.next())
            {
                //Gets the boolean value from the column value from the column value and assigns it to the variable _isRegistered
                boolean _isRegistered = Boolean.parseBoolean(_result.getString("Value"));

                //Calls the CloseConnection Method
                CloseConnection();
                //Returns the boolean value of _isRegistered
                return _isRegistered;
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On Register");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        return false; //Returns false
    }

    //Logout Method
    public void Logout(int _sessionID){
        //Initializes the _sP_Register string with the sql call statement.
        String _sP_Register = "call `Rhythm`.`Logout` ('" + _sessionID + "');";
        //Calls the ExecuteQuery Method and passes the string procedure call as a parameter. The returned ResultSet is assigned to the variable _result
        Execute(_sP_Register);
    }

    public void DeleteAccount(String _username){
        //Initializes the _sP_Register string with the sql call statement.
        String _sP_Register = "call `Rhythm`.`DeleteAccount` ('" + _username + "');";
        //Calls the ExecuteQuery Method and passes the string procedure call as a parameter. The returned ResultSet is assigned to the variable _result
        Execute(_sP_Register);
    }

    //getUserInfo Method
    public User GetUserInfo(String _username){
        //Local Variable
        String _firstName;      //Used to store the first name of the user
        String _lastName;       //Used to store the last name of the user
        String _phoneNumber;    //Used to store the phone number of the user
        String _email;          //Used to store the email of the user

        //Initializes the _sP_Register string with the sql call statement.
        String _sP_GetUserInfo = "call `Rhythm`.`GetUserInfo` ('" + _username + "');";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_GetUserInfo);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            if (_result != null && _result.next())
            {
                //Gets the string value for the users data and assigns them to their respected variables
                _firstName = _result.getString("FirstName");
                _lastName = _result.getString("LastName");
                _phoneNumber = _result.getString("PhoneNumber");
                _email = _result.getString("Email");

                CloseConnection();
                //Returns a new initialized User
                return new User(_firstName, _lastName, _phoneNumber, _email, _username);
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On GetUserInfo");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        return null; //Returns Null
    }

    //----------------------------------------SONG RELATED METHODS------------------------------------------------------
    //GetSongsMethod
    public ArrayList<Song> GetSongs(){
        //Local Variables
        ArrayList<Song> _songs = new ArrayList<>();
        String _songName;
        String _artist;
        String _songPath;
        int _songID;
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_GetSongs = "call `Rhythm`.`GetSongs` ();";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_GetSongs);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            while(_result != null && _result.next()){
                //Gets the values from the columns in the result set and assigns it to their respected variables
                _songName = _result.getString("SongName");
                _artist = _result.getString("Artist");
                _songID = Integer.parseInt(_result.getString("SongID"));
                _songPath = new File(_result.getString("Path")).toURI().toString();

                //Initialized a new song and adds it to the songs array list
                _songs.add(new Song(_songName, _artist, _songPath, _songID));
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On GetSongs");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        //Returns the songs array list
        return _songs;
    }

    public ArrayList<CD> GetCDs(){
        //Local Variables
        ArrayList<CD> _cDs = new ArrayList<>();
        int _cDID;
        String _cDName;
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_GetCDs = "call `Rhythm`.`GetCDs` ();";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_GetCDs);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            while(_result != null && _result.next()){
                //Gets the values from the columns in the result set and assigns it to their respected variables
                _cDID = Integer.parseInt(_result.getString("CDID"));
                _cDName = _result.getString("CDName");

                _cDs.add(new CD(_cDID, _cDName));
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On GetCDs");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        //Returns the songs array list
        return _cDs;
    }

    public ArrayList<Playlist> GetPlaylists(int _sessionID){
        //Local Variables
        ArrayList<Playlist> _playlists = new ArrayList<>();
        int _playlistID;
        String _playlistName;
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_GetPlaylists = "call `Rhythm`.`GetPlaylists` (" + _sessionID + ");";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_GetPlaylists);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            while(_result != null && _result.next()){
                //Gets the values from the columns in the result set and assigns it to their respected variables
                _playlistID = Integer.parseInt(_result.getString("PlaylistID"));
                _playlistName = _result.getString("PlaylistName");

                _playlists.add(new Playlist(_playlistID, _playlistName));
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On GetPlaylists");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        //Returns the songs array list
        return _playlists;
    }

    public ArrayList<Song> GetSongs(int _id, String _string){
        //Local Variables
        ArrayList<Song> _songs = new ArrayList<>();
        String _songName;
        String _artist;
        String _songPath;
        int _songID;

        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_GetSongs = "call `Rhythm`.`GetSongsFrom"+ _string +"` (" + _id + ");";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_GetSongs);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            while(_result != null && _result.next()){
                //Gets the values from the columns in the result set and assigns it to their respected variables
                _songName = _result.getString("SongName");
                _artist = _result.getString("Artist");
                _songID = Integer.parseInt(_result.getString("SongID"));
                _songPath = new File(_result.getString("Path")).toURI().toString();
                //Initialized a new song and adds it to the songs array list
                _songs.add(new Song(_songName, _artist, _songPath, _songID));
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On GetSongs");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        //Returns the songs array list
        return _songs;
    }

    public String GetUsername(int _sessionID) {
        //Local Variables
        String _username = null;
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_GetSongs = "call `Rhythm`.`GetUsername` (" + _sessionID + ");";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_GetSongs);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            if(_result != null && _result.next()){
                //Gets the value from the Username column of the result set and returns it
                _username = _result.getString("Username");
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On GetUsername");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        return _username; //Returns null
    }

    public boolean CreateNewPlaylist(String _playlistName, int _songID, int _sessionID) {
        //Local Variable
        boolean _hasBeenCreated = false;
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_CreatePlaylist = "call `Rhythm`.`CreatePlaylist` ('" + _playlistName + "', " + _songID + ", " + _sessionID + ");";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_CreatePlaylist);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            if(_result != null && _result.next()){
                //Gets the boolean value from the result set
                _hasBeenCreated = Boolean.parseBoolean(_result.getString("Value"));
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On CreateNewPlaylist");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        //Closes the connection to the database
        CloseConnection();
        return _hasBeenCreated; //Returns _hasBeenCreated
    }

    public boolean AddSongToPlaylist(int _playlistID, int _songID) {
        boolean _hasBeenAdded = false;
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_AddSongToPlaylist = "call `Rhythm`.`AddSongToPlaylist` (" + _playlistID + ", " + _songID + ");";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        _result = ExecuteQuery(_sP_AddSongToPlaylist);

        //Try-Catch to catch any errors
        try {
            //Checks if _result has next
            if(_result != null && _result.next()){
                //Gets the boolean value from the result set
                _hasBeenAdded = Boolean.parseBoolean(_result.getString("Value"));
            }
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On AddSongToPlaylist");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        CloseConnection();
        return _hasBeenAdded; //Returns _hasBeenAdded
    }

    public void RemoveSongFromPlaylist(int _playlistID, int _songID) {
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_AddSongToPlaylist = "call `Rhythm`.`RemoveSongFromPlaylist` (" + _playlistID + ", " + _songID + ");";

        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        Execute(_sP_AddSongToPlaylist);
        //Closes the connection
        CloseConnection();
    }

    public void DeletePlaylist(int _playlistID) {
        //Initializes the _sP_GetSongs string with the sql call statement.
        String _sP_AddSongToPlaylist = "call `Rhythm`.`DeletePlaylist` (" + _playlistID + ");";
        //Calls the ExecuteQuery and passes _sP_GetUserInfo as a parameter. The returned result set is assigned to _result
        Execute(_sP_AddSongToPlaylist);
        //Closes the connection
        CloseConnection();
    }

    //----------------------------------------QUERY RELATED METHODS-----------------------------------------------------
    //ExecuteQuery Method
    private void Execute(String _query){
        //Calls the EstablishConnection Method
        EstablishConnection();
        //Try-Catch to catch any errors
        try {
            //Calls the prepareCall method from the connection class and passes the string query as a parameter
            _statement = _connection.prepareCall(_query);
            //Calls the execute method in the callable statement
            _statement.execute();
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On Execute");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        //Calls the CloseConnection Method
        CloseConnection();
    }

    //ExecuteQuery Method
    private ResultSet ExecuteQuery(String _query){
        //Calls the EstablishConnection Method
        EstablishConnection();
        //Try-Catch to catch any errors
        try {
            //Calls the prepareCall method from the connection class and passes the string query as a parameter
            _statement = _connection.prepareCall(_query);
            //Calls the executeQuery from the callable statement and the returned ResultSet is returned
            return _statement.executeQuery();
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On ExecuteQuery");
            //Displays the stack trace error
            _e.printStackTrace();
        }
        return null; //Returns null
    }

    //----------------------------------------CONNECTION RELATED METHODS------------------------------------------------

    //EstablishConnection Method
    private void EstablishConnection(){
        //Local Variables
        String _databaseName = "DatabaseName";
        String _databaseUsername = "DatabaseUsername";
        String _databasePassword = "DatabasePassword";
        String _url = "jdbc:mysql://localhost:3306/" + _databaseName;

        //Try-Catch used to catch any errors
        try {
            //Sets the class for name
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Calls the getConnection method from the Driver Manager and passes the url, database username, and the database password as parameters
            _connection = DriverManager.getConnection(_url, _databaseUsername, _databasePassword);
        }catch (Exception _e) {
            //Displays an error message
            System.out.println("ERROR On EstablishConnection");
            //Displays the stack trace error
            _e.printStackTrace();
        }
    }

    //CloseConnection Method
    private void CloseConnection(){
        //Try-Catch to catch any errors
        try {
            //Calls the close Method from the Connection
            _connection.close();
        }catch (Exception _e){
            //Displays an error message
            System.out.println("ERROR On CloseConnection");
            //Displays the stack trace error
            _e.printStackTrace();
        }
    }



}
