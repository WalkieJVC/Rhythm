CREATE DATABASE  IF NOT EXISTS `rhythm` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rhythm`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: rhythm
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `ArtistID` int NOT NULL AUTO_INCREMENT,
  `ArtistName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ArtistID`),
  UNIQUE KEY `ArtistID_UNIQUE` (`ArtistID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'GoodKnight'),(2,'Egzog'),(3,'Isolated'),(4,'Rival'),(5,'Chris Later'),(6,'Hoober');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cd`
--

DROP TABLE IF EXISTS `cd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cd` (
  `CDID` int NOT NULL AUTO_INCREMENT,
  `CDName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`CDID`),
  UNIQUE KEY `CDID_UNIQUE` (`CDID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd`
--

LOCK TABLES `cd` WRITE;
/*!40000 ALTER TABLE `cd` DISABLE KEYS */;
INSERT INTO `cd` VALUES (1,'SongsByEgzog'),(2,'MyFavorites');
/*!40000 ALTER TABLE `cd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cd_songs`
--

DROP TABLE IF EXISTS `cd_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cd_songs` (
  `CD_SongsID` int NOT NULL AUTO_INCREMENT,
  `CDID` int NOT NULL,
  `SongID` int NOT NULL,
  PRIMARY KEY (`CD_SongsID`),
  UNIQUE KEY `CD_SongsID_UNIQUE` (`CD_SongsID`),
  KEY `fk_CD_Songs_Songs_idx` (`SongID`),
  KEY `fk_CD_Songs_CD_idx` (`CDID`),
  CONSTRAINT `fk_CD_Songs_CD` FOREIGN KEY (`CDID`) REFERENCES `cd` (`CDID`),
  CONSTRAINT `fk_CD_Songs_Songs` FOREIGN KEY (`SongID`) REFERENCES `song` (`SongID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd_songs`
--

LOCK TABLES `cd_songs` WRITE;
/*!40000 ALTER TABLE `cd_songs` DISABLE KEYS */;
INSERT INTO `cd_songs` VALUES (1,1,2),(2,1,3),(3,2,4),(4,2,5),(5,2,6),(6,2,2);
/*!40000 ALTER TABLE `cd_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `LoginID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varbinary(255) NOT NULL,
  PRIMARY KEY (`LoginID`),
  UNIQUE KEY `idLogin_UNIQUE` (`LoginID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `PlaylistID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `PlaylistName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`PlaylistID`),
  UNIQUE KEY `idtable1_UNIQUE` (`PlaylistID`),
  KEY `fk_Playlist_User_idx` (`UserID`),
  CONSTRAINT `fk_Playlist_User` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists_songs`
--

DROP TABLE IF EXISTS `playlists_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlists_songs` (
  `Playlists_SongsID` int NOT NULL AUTO_INCREMENT,
  `PlaylistID` int NOT NULL,
  `SongID` int NOT NULL,
  PRIMARY KEY (`Playlists_SongsID`),
  UNIQUE KEY `Playlists_SongsID_UNIQUE` (`Playlists_SongsID`),
  KEY `fk_Playlist_Songs_Playlist_idx` (`PlaylistID`),
  KEY `fk_Playlists_Songs_Songs_idx` (`SongID`),
  CONSTRAINT `fk_Playlist_Songs_Playlist` FOREIGN KEY (`PlaylistID`) REFERENCES `playlist` (`PlaylistID`),
  CONSTRAINT `fk_Playlists_Songs_Songs` FOREIGN KEY (`SongID`) REFERENCES `song` (`SongID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists_songs`
--

LOCK TABLES `playlists_songs` WRITE;
/*!40000 ALTER TABLE `playlists_songs` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlists_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `SessionID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `LoginDate` date NOT NULL,
  `LoginTime` time NOT NULL,
  `LogoutDate` date DEFAULT NULL,
  `LogoutTime` time DEFAULT NULL,
  PRIMARY KEY (`SessionID`),
  UNIQUE KEY `idSession_UNIQUE` (`SessionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `SongID` int NOT NULL AUTO_INCREMENT,
  `ArtistID` int NOT NULL,
  `SongName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SongPath` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SongID`),
  UNIQUE KEY `SongID_UNIQUE` (`SongID`),
  KEY `fk_Song_Artist1_idx` (`ArtistID`),
  CONSTRAINT `fk_Song_Artist` FOREIGN KEY (`ArtistID`) REFERENCES `artist` (`ArtistID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,1,'Freedom','src/main/resources/com/rhythm/SongFiles/Freedom,Goodknight.wav'),(2,2,'Royalty','src/main/resources/com/rhythm/SongFiles/Royalty,Egzog.mp3'),(3,2,'No Rival','src/main/resources/com/rhythm/SongFiles/NoRival,Egzog.mp3'),(4,3,'Circus','src/main/resources/com/rhythm/SongFiles/Circus,Isolated.mp3'),(5,4,'Falling','src/main/resources/com/rhythm/SongFiles/Falling,Rival.mp3'),(6,5,'There\'s Nobody Else','src/main/resources/com/rhythm/SongFiles/There\'sNobodyElse,ChrisLater.mp3'),(7,6,'Higher','src/main/resources/com/rhythm/SongFiles/Higher,Hoober.mp3');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `LoginID` int NOT NULL,
  `FirstName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LastName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PhoneNumber` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Email` varchar(320) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `idUser_UNIQUE` (`UserID`),
  KEY `fk_User_Login1_idx` (`LoginID`),
  CONSTRAINT `fk_User_Login` FOREIGN KEY (`LoginID`) REFERENCES `login` (`LoginID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'rhythm'
--

--
-- Dumping routines for database 'rhythm'
--
/*!50003 DROP FUNCTION IF EXISTS `GetSessionID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GetSessionID`(_userID INT, _currentDate DATE, _currentTime Time(2)) RETURNS int
BEGIN
    RETURN (SELECT SessionID FROM Session WHERE UserID = _userID AND LoginDate = _currentDate AND LoginTime = _currentTime);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddSongToPlaylist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddSongToPlaylist`(_playlistID INT, _songID INT)
BEGIN
	IF EXISTS (SELECT SongID FROM Playlists_Songs WHERE PlaylistID = _playlistID AND SongID = _songID) THEN
		SELECT 'false' as `Value`;
	ELSE
		INSERT INTO Playlists_Songs (PlaylistID, SongID)
		VALUES(_playlistID, _songID);
		
        SELECT 'true' as `Value`;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ClearDatabase` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClearDatabase`()
BEGIN
	SET SQL_SAFE_UPDATES = 0;
    
    DELETE FROM Session;
    DELETE FROM Playlists_Songs;
    DELETE FROM Playlist;
    DELETE FROM User;
    DELETE FROM Login;
    
	ALTER TABLE Session AUTO_INCREMENT = 1;
    ALTER TABLE Playlists_Songs AUTO_INCREMENT = 1;
    ALTER TABLE Playlist AUTO_INCREMENT = 1;
    ALTER TABLE User AUTO_INCREMENT = 1;
	ALTER TABLE Login AUTO_INCREMENT = 1;
    
    SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CreatePlaylist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreatePlaylist`(_playlistName VARCHAR(25), _songID INT, _sessionID INT)
BEGIN
	SET @_userID = (SELECT UserID FROM Session WHERE SessionID = _sessionID);
    
    IF NOT EXISTS (SELECT PlaylistName FROM Playlist WHERE PlaylistName = _playlistName AND UserID = @_userID) THEN
		INSERT INTO Playlist (UserID, PlaylistName)
		VALUES (@_userID, _playlistName);
		
		SET @_playlistID = (SELECT PlaylistID FROM Playlist WHERE PlaylistName = _playlistName);
		
		INSERT INTO Playlists_Songs (PlaylistID, SongID)
		VALUES (@_playlistID, _songID);
        
        SELECT 'true' AS `Value`;
	ELSE
		SELECT 'false' AS `Value`;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteAccount` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteAccount`(_username VARCHAR(16))
BEGIN
	SET @_loginID = (SELECT LoginID FROM Login WHERE Username = _username);
    SET @_userID = (SELECT UserID FROM User WHERE LoginID = @_loginID);
    
    SET SQL_SAFE_UPDATES = 0;
    
    DELETE FROM Session WHERE UserID = @_userID;
    DELETE FROM Playlists_Songs WHERE PlaylistID IN (SELECT PlaylistID FROM Playlist WHERE UserID = @_userID);
    DELETE FROM Playlist WHERE UserID = @_userID;
    DELETE FROM User WHERE LoginID = @_loginID;
    DELETE FROM Login WHERE LoginID = @_loginID;
    
    SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeletePlaylist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletePlaylist`(_playlistID INT)
BEGIN
	SET SQL_SAFE_UPDATES = 0;
        
	DELETE FROM Playlists_Songs WHERE PlaylistID = _playlistID;
	DELETE FROM Playlist WHERE PlaylistID = _playlistID;
        
	SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `FinalizeSession` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `FinalizeSession`(_sessionID INT, _currentDate DATE, _currentTime TIME)
BEGIN
	UPDATE Session
    SET LogoutDate = _currentDate, LogoutTime = _currentTime
    WHERE SessionID = _sessionID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetAllSongs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllSongs`()
BEGIN
	SELECT SongName, ArtistName AS `Artist`, SongPath AS `Path` FROM Song INNER JOIN Artist WHERE Song.ArtistID = Artist.ArtistID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetCDs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCDs`()
BEGIN
	SELECT * FROM CD;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetPlaylists` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPlaylists`(_sessionID INT)
BEGIN
	SET @_userID = (Select UserID From Session WHERE SessionID = _sessionID);
    
    Select PlaylistID, PlaylistName FROM Playlist WHERE UserID = @_userID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetSongs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSongs`()
BEGIN
	SELECT Song.SongName, ArtistName AS `Artist`, SongPath AS `Path`, Song.SongID FROM Song INNER JOIN Artist WHERE Song.ArtistID = Artist.ArtistID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetSongsFromCD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSongsFromCD`(_CDID INT)
BEGIN
	SELECT DISTINCT Song.SongName, Artist.ArtistName AS `Artist`, SongPath AS `Path`, Song.SongID AS `SongID`
    FROM CD_Songs JOIN CD, Song, Artist
    WHERE CD_Songs.CDID = _CDID AND CD_Songs.SongID = Song.SongID AND Song.ArtistID = Artist.ArtistID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetSongsFromPlaylist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSongsFromPlaylist`(_playlistID INT)
BEGIN
	SELECT DISTINCT Song.SongName, Artist.ArtistName AS `Artist`, SongPath AS `Path`, Song.SongID AS `SongID`
    FROM Playlists_Songs JOIN CD, Song, Artist
    WHERE Playlists_Songs.PlaylistID = _playlistID AND Playlists_Songs.SongID = Song.SongID AND Song.ArtistID = Artist.ArtistID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetUserInfo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUserInfo`(_username VARCHAR(16))
BEGIN
	SET @_loginID = (SELECT LoginID FROM Login WHERE Username = _username);
    
    SELECT * FROM User WHERE LoginID = @_loginID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetUsername` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUsername`(_sessionID INT)
BEGIN
	SELECT Username FROM Login JOIN User, Session WHERE SessionID = _sessionID AND Session.UserID = User.UserID AND User.LoginID = Login.LoginID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InitializeSession` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InitializeSession`(_userID INT, _currentDate DATE, _currentTime TIME)
BEGIN
	INSERT INTO Session (UserID, LoginDate, LoginTime)
    VALUES (_userID, _currentDate, _currentTime);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Login`(_username VARCHAR(16), _password VARCHAR(40))
BEGIN
	SET @_currentDate = CURDATE();
	SET @_currentTime = TIME_FORMAT(CURTIME(), "%T");
    SET @_username = LOWER(_username);
    SET @_password = AES_ENCRYPT(_password, 'WalkieRhythmKey');
	IF EXISTS (SELECT Username FROM Login WHERE Username = @_username AND Password = @_password) THEN
		SET @_loginID = (SELECT LoginID FROM Login WHERE Username = @_username AND Password = @_password);
		SET @_userID = (SELECT UserID FROM User WHERE LoginID = @_loginID);
        
		CALL InitializeSession (@_userID, @_currentDate, @_currentTime);

		SELECT 'True' AS `Value`, GetSessionID (@_userID, @_currentDate, @_currentTime) AS `SessionID`;
	ELSE
		SELECT 'False' AS `Value`;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Logout` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Logout`(_sessionID INT)
BEGIN
	SET @_currentDate = CURDATE();
	SET @_currentTime = TIME_FORMAT(CURTIME(), "%T");
    
    CALL FinalizeSession (_sessionID, @_currentDate, @_currentTime);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Register` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Register`(_firstName VARCHAR(100), _lastName VARCHAR(100), _phoneNumber VARCHAR(100), _email VARCHAR(100), _username VARCHAR(100), _password VARCHAR(100))
BEGIN
	SET @_username = LOWER(_username);
    SET @_email = LOWER(_email);

	IF EXISTS (SELECT Username FROM Login WHERE Username = @_username) THEN
		SELECT 'False' AS `Value`;
	ELSE
		Set @_password = AES_ENCRYPT(_password, 'WalkieRhythmKey');
		INSERT INTO Login (Username, Password)
        VALUES (@_username, @_password);
        
        SET @_loginID = (SELECT LoginID FROM Login WHERE Username = @_username);
        
        INSERT INTO User (LoginID, FirstName, LastName, PhoneNumber, Email)
        VALUES (@_loginID, _firstName, _lastName, _phoneNumber, @_email);
		SELECT 'True' AS `Value`;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `RemoveSongFromPlaylist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `RemoveSongFromPlaylist`(_playlistID INT, _songID INT)
BEGIN
	IF EXISTS (SELECT SongID FROM Playlists_Songs WHERE PlaylistID = _playlistID AND SongID = _songID) THEN
        SET SQL_SAFE_UPDATES = 0;
        
        DELETE FROM Playlists_Songs WHERE PlaylistID = _playlistID AND SongID = _songID;
        
        SET SQL_SAFE_UPDATES = 1;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-13 22:18:30
