-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: MovieBookingSystem
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

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

USE `MovieBookingSystem`;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `id` int NOT NULL,
  `role` int NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `phone_number` varchar(16) DEFAULT NULL,
  `user_id` int NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  KEY `fk_account_role_idx` (`role`),
  KEY `fk_account_user_id_idx` (`user_id`),
  CONSTRAINT `fk_account_role` FOREIGN KEY (`role`) REFERENCES `Role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Admin` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `account_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `pay_rate` int NOT NULL,
  `permissions` binary(4) NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  CONSTRAINT `fk_admin_account_id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_admin_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `Employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_admin_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dubbing`
--

DROP TABLE IF EXISTS `Dubbing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dubbing` (
  `id` int NOT NULL,
  `language_id` int NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_dubbing_language_id` FOREIGN KEY (`id`) REFERENCES `Language` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dubbing`
--

LOCK TABLES `Dubbing` WRITE;
/*!40000 ALTER TABLE `Dubbing` DISABLE KEYS */;
INSERT INTO `Dubbing` VALUES (1,1,'This film is dubbed in English.'),(2,2,'This film is dubbed in Español.'),(3,3,'This film is dubbed in French.'),(4,4,'This film is dubbed in German.'),(5,5,'This film is dubbed in Japanese.'),(6,6,'This film is dubbed in Korean.');
/*!40000 ALTER TABLE `Dubbing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `account_id` int NOT NULL,
  `pay_rate` int NOT NULL,
  `permissions` binary(4) NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  KEY `fk_employee_user_id_idx` (`user_id`),
  KEY `fk_employee_account_Id_idx` (`account_id`),
  CONSTRAINT `fk_employee_account_Id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_employee_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilmRating`
--

DROP TABLE IF EXISTS `FilmRating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FilmRating` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilmRating`
--

LOCK TABLES `FilmRating` WRITE;
/*!40000 ALTER TABLE `FilmRating` DISABLE KEYS */;
INSERT INTO `FilmRating` VALUES (1,'G','General Audiences. Nothing that would offend parents for viewing by children'),(2,'PG','Parental Guidance Suggested. Parents urged to give “parental guidance.” May contain some material parents might not like for their young children.'),(3,'PG-13','Parents strongly cautioned. Parents are urged to be cautious. Some material may be inappropriate for pre-teenagers'),(4,'R','Restricted. Contains some adult material. Parents are urged to learn more about the film before taking their young children with them.'),(5,'NC-17','No one 17 and under admitted. Clearly adult. Children are not admitted.'),(6,'Unrated','This film has not been rated yet by the Motion Picture Association.');
/*!40000 ALTER TABLE `FilmRating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guest`
--

DROP TABLE IF EXISTS `Guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Guest` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `transaction_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_guest_user_id_idx` (`user_id`),
  CONSTRAINT `fk_guest_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest`
--

LOCK TABLES `Guest` WRITE;
/*!40000 ALTER TABLE `Guest` DISABLE KEYS */;
/*!40000 ALTER TABLE `Guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Language`
--

DROP TABLE IF EXISTS `Language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Language` (
  `id` int NOT NULL,
  `language_name` varchar(64) NOT NULL,
  `description` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `language_name_UNIQUE` (`language_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Language`
--

LOCK TABLES `Language` WRITE;
/*!40000 ALTER TABLE `Language` DISABLE KEYS */;
INSERT INTO `Language` VALUES (1,'English','This film\'s original language is in English'),(2,'Español','This film\'s original language is in Español'),(3,'French','This film\'s original language is in French'),(4,'German','This film\'s original language is in German'),(5,'Japanese','This film\'s original language is in Japanese'),(6,'Korean','This film\'s original language is in Korean');
/*!40000 ALTER TABLE `Language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Member` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `account_id` int NOT NULL,
  `points` int NOT NULL DEFAULT '0',
  `discount` int NOT NULL DEFAULT '0',
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  CONSTRAINT `fk_member_account_id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_member_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movie`
--

DROP TABLE IF EXISTS `Movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Movie` (
  `id` int NOT NULL,
  `title` varchar(256) NOT NULL,
  `language_id` int DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `film_rating` int DEFAULT NULL,
  `duration` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `fk_language_idx` (`language_id`),
  KEY `fk_film_rating_id_idx` (`film_rating`),
  CONSTRAINT `fk_movie_film_rating_id` FOREIGN KEY (`film_rating`) REFERENCES `FilmRating` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_language_id` FOREIGN KEY (`language_id`) REFERENCES `Language` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movie`
--

LOCK TABLES `Movie` WRITE;
/*!40000 ALTER TABLE `Movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `Movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaymentInformation`
--

DROP TABLE IF EXISTS `PaymentInformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PaymentInformation` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `state` varchar(256) NOT NULL,
  `zip` int NOT NULL,
  `cvv` int NOT NULL,
  `card_number` int NOT NULL,
  `expiry` date NOT NULL,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `fk_payment_information_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentInformation`
--

LOCK TABLES `PaymentInformation` WRITE;
/*!40000 ALTER TABLE `PaymentInformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `PaymentInformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resolution`
--

DROP TABLE IF EXISTS `Resolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Resolution` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resolution`
--

LOCK TABLES `Resolution` WRITE;
/*!40000 ALTER TABLE `Resolution` DISABLE KEYS */;
INSERT INTO `Resolution` VALUES (1,'Film','This movie was captured with a film camera'),(2,'2K','2048 x 1556 resolution'),(3,'4K','4096 x 2160 resolution'),(4,'IMAX Laser','High tech projection that results in resolutions exceeding 4K resolution in comparison to other projection methods.'),(5,'Cinemascope','Anamorphic widescreen. This format allows for wider projection.');
/*!40000 ALTER TABLE `Resolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sale`
--

DROP TABLE IF EXISTS `Sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sale` (
  `id` int NOT NULL,
  `discount` int NOT NULL COMMENT 'Discount as a percentage. It is stored as an int for precision purposes.',
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sale`
--

LOCK TABLES `Sale` WRITE;
/*!40000 ALTER TABLE `Sale` DISABLE KEYS */;
INSERT INTO `Sale` VALUES (1,70,'Valentine\'s Day Special','A special sale for couples.'),(2,50,'Veteran\'s Day Special','A special sale to honor those who served.'),(3,80,'Movie Junkie Weekend','A sale for those who love to watch a lot of movies.');
/*!40000 ALTER TABLE `Sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seat`
--

DROP TABLE IF EXISTS `Seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Seat` (
  `id` int NOT NULL,
  `theatre_id` int NOT NULL,
  `is_occupied` tinyint NOT NULL,
  `seat_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seat_theatre_id_idx` (`theatre_id`),
  KEY `fk_seat_seat_type_id_idx` (`seat_type_id`),
  CONSTRAINT `fk_seat_seat_type_id` FOREIGN KEY (`seat_type_id`) REFERENCES `SeatType` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_seat_theatre_id` FOREIGN KEY (`theatre_id`) REFERENCES `TheatreRoom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seat`
--

LOCK TABLES `Seat` WRITE;
/*!40000 ALTER TABLE `Seat` DISABLE KEYS */;
INSERT INTO `Seat` VALUES (1,1,0,3),(2,1,0,3),(3,1,0,3),(4,1,0,3),(5,1,0,3),(6,1,0,3),(7,1,0,3),(8,1,0,3),(9,1,0,3),(10,1,0,3),(11,1,0,3),(12,1,0,3),(13,1,0,3),(14,1,0,3),(15,1,0,1),(16,1,0,1),(17,2,0,2),(18,2,0,2),(19,2,0,2),(20,2,0,2),(21,2,0,2),(22,2,0,2),(23,2,0,2),(24,2,0,2),(25,2,0,2),(26,2,0,2),(27,2,0,2),(28,2,0,2),(29,2,0,2),(30,2,0,2),(31,2,0,2),(32,2,0,2),(33,2,0,2),(34,2,0,2),(35,2,0,2),(36,2,0,2),(37,2,0,2),(38,2,0,2),(39,2,0,2),(40,2,0,2),(41,2,0,2),(42,2,0,2),(43,2,0,2),(44,2,0,2),(45,2,0,1),(46,2,0,1),(47,3,0,4),(48,3,0,4),(49,3,0,4),(50,3,0,4),(51,3,0,4),(52,3,0,4),(53,3,0,4),(54,3,0,4),(55,3,0,4),(56,3,0,4),(57,3,0,4),(58,3,0,4),(59,3,0,4),(60,3,0,4),(61,3,0,1);
/*!40000 ALTER TABLE `Seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SeatType`
--

DROP TABLE IF EXISTS `SeatType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SeatType` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SeatType`
--

LOCK TABLES `SeatType` WRITE;
/*!40000 ALTER TABLE `SeatType` DISABLE KEYS */;
INSERT INTO `SeatType` VALUES (1,'Disabled','A seat that comes with amenities to assist handicapped viewers.'),(2,'Regular','A regular seat.'),(3,'Couple','A seat that can be paired with another couple\'s seat. Couple seats do not have a divider in the middle, and it can recline.'),(4,'Reclining','A regular seat that can also recline.');
/*!40000 ALTER TABLE `SeatType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Showing`
--

DROP TABLE IF EXISTS `Showing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Showing` (
  `id` int NOT NULL,
  `movie_id` int NOT NULL,
  `resolution_id` int DEFAULT NULL,
  `subtitle_id` int DEFAULT NULL,
  `dubbing_id` int DEFAULT NULL,
  `sound_type_id` int DEFAULT NULL,
  `status` varchar(45) NOT NULL COMMENT 'Status will say SHOWING or NOT SHOWING',
  `theatre_id` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_id_idx` (`movie_id`),
  KEY `fk_resolution_id_idx` (`resolution_id`),
  KEY `fk_subtitle_id_idx` (`subtitle_id`),
  KEY `fk_dubbing_id_idx` (`dubbing_id`),
  KEY `fk_sound_type_id_idx` (`sound_type_id`),
  KEY `fk_theatre_id_idx` (`theatre_id`),
  CONSTRAINT `fk_showing_dubbing_id` FOREIGN KEY (`dubbing_id`) REFERENCES `Dubbing` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_showing_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `Movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_showing_resolution_id` FOREIGN KEY (`resolution_id`) REFERENCES `Resolution` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_showing_sound_type_id` FOREIGN KEY (`sound_type_id`) REFERENCES `SoundSystem` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_showing_subtitle_id` FOREIGN KEY (`subtitle_id`) REFERENCES `Subtitle` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Showing`
--

LOCK TABLES `Showing` WRITE;
/*!40000 ALTER TABLE `Showing` DISABLE KEYS */;
/*!40000 ALTER TABLE `Showing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SoundSystem`
--

DROP TABLE IF EXISTS `SoundSystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SoundSystem` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SoundSystem`
--

LOCK TABLES `SoundSystem` WRITE;
/*!40000 ALTER TABLE `SoundSystem` DISABLE KEYS */;
INSERT INTO `SoundSystem` VALUES (1,'5.1 surround sound','Left Center Right (LCR) surround system.'),(2,'7.1 surround sound','Seven speakers which adds two more speakers to the 5.1 surround sound system.'),(3,'Dolby','The Dolby Digital Surround Sound System. A powerful surround sound audio system using overhead speakers'),(4,'DTS','Digital Theatre Sound'),(5,'IMAX','The IMAX sound system'),(6,'THX','The THX sound system');
/*!40000 ALTER TABLE `SoundSystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subtitle`
--

DROP TABLE IF EXISTS `Subtitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Subtitle` (
  `id` int NOT NULL,
  `language_id` int DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subtitle_language_id_idx` (`language_id`),
  CONSTRAINT `fk_subtitle_language_id` FOREIGN KEY (`language_id`) REFERENCES `Language` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subtitle`
--

LOCK TABLES `Subtitle` WRITE;
/*!40000 ALTER TABLE `Subtitle` DISABLE KEYS */;
INSERT INTO `Subtitle` VALUES (1,1,'This film has English subtitles.'),(2,2,'This film has Español subtitles.'),(3,3,'This film has French subtitles.'),(4,4,'This film has German subtitles.'),(5,5,'This film has Japanese subtitles.'),(6,6,'This film has Korean subtitles.');
/*!40000 ALTER TABLE `Subtitle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TheatreRoom`
--

DROP TABLE IF EXISTS `TheatreRoom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TheatreRoom` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `num_seats` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TheatreRoom`
--

LOCK TABLES `TheatreRoom` WRITE;
/*!40000 ALTER TABLE `TheatreRoom` DISABLE KEYS */;
INSERT INTO `TheatreRoom` VALUES (1,'Theatre Room 1',16),(2,'Theatre Room 2',30),(3,'Theatre Room 3',15);
/*!40000 ALTER TABLE `TheatreRoom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ticket` (
  `id` int NOT NULL,
  `movie_id` int NOT NULL,
  `showing_time` int NOT NULL,
  `price` int NOT NULL,
  `seat_id` int NOT NULL,
  `theatre_id` int NOT NULL,
  `status` int NOT NULL,
  `ticket_type` int NOT NULL,
  `bought_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_id_idx` (`movie_id`),
  KEY `fk_showing_time_id_idx` (`showing_time`),
  KEY `fk_seat_id_idx` (`seat_id`),
  KEY `fk_theatre_id_idx` (`theatre_id`),
  KEY `fk_status_idx` (`status`),
  KEY `fk_ticket_type_id_idx` (`ticket_type`),
  KEY `fk_bought_by_idx` (`bought_by`),
  CONSTRAINT `fk_ticket_bought_by` FOREIGN KEY (`bought_by`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `Movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_seat_id` FOREIGN KEY (`seat_id`) REFERENCES `Seat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_showing_time_id` FOREIGN KEY (`showing_time`) REFERENCES `Showing` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_status_id` FOREIGN KEY (`status`) REFERENCES `TicketStatus` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_theatre_id` FOREIGN KEY (`theatre_id`) REFERENCES `TheatreRoom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_ticket_type_id` FOREIGN KEY (`ticket_type`) REFERENCES `TicketType` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketStatus`
--

DROP TABLE IF EXISTS `TicketStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TicketStatus` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketStatus`
--

LOCK TABLES `TicketStatus` WRITE;
/*!40000 ALTER TABLE `TicketStatus` DISABLE KEYS */;
INSERT INTO `TicketStatus` VALUES (1,'Unsold','This ticket has not been bought yet'),(2,'Sold','This ticket has been sold'),(3,'Used','This ticket has already been redeemed and used for a movie'),(4,'Expired','This ticket has not been bought or used but is now invalid since the showing date has already passed.');
/*!40000 ALTER TABLE `TicketStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketType`
--

DROP TABLE IF EXISTS `TicketType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TicketType` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `suggested_price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketType`
--

LOCK TABLES `TicketType` WRITE;
/*!40000 ALTER TABLE `TicketType` DISABLE KEYS */;
INSERT INTO `TicketType` VALUES (1,'Regular','Regular ticket for one.',1299),(2,'Kids','Cheaper tickets for kids that are 8 or under.',899),(3,'Senior','Cheaper tickets for adults that are 60 or older.',1099);
/*!40000 ALTER TABLE `TicketType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` int NOT NULL,
  `account_id` int DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_account_id_idx` (`account_id`),
  KEY `fk_user_status_idx` (`status`),
  CONSTRAINT `fk_user_account_id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_user_status` FOREIGN KEY (`status`) REFERENCES `UserStatus` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserStatus`
--

DROP TABLE IF EXISTS `UserStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserStatus` (
  `id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserStatus`
--

LOCK TABLES `UserStatus` WRITE;
/*!40000 ALTER TABLE `UserStatus` DISABLE KEYS */;
INSERT INTO `UserStatus` VALUES (1,'Guest','This user is a guest. Only transaction information is stored for this user.'),(2,'Active','sThis user is an active member of our system. They have an account associated with them.'),(3,'Deactivated','This user is no longer an active member of our system. We only keep their important records when it\'s needed in the future.');
/*!40000 ALTER TABLE `UserStatus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 18:36:36
