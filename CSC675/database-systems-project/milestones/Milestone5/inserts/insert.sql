-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: MovieBookingSystem
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

USE MovieBookingSystem;

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` int NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `phone_number` varchar(16) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
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
  CONSTRAINT `fk_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,1,'pancakes','theadmin@gmail.com','720-512-7454',1,'Sam','Harrison','theadmin'),(2,2,'hotdogs','themovieclerk@gmail.com','707-440-4404',2,'Lily ','Turner','ticketguy'),(3,3,'bacon','randomeployee@hotmail.com','892-123-3278',3,'Jake','Mitchell','jakejakejakem'),(4,4,'waffles','enthusiasticmoviewatcher@yahoo.com',NULL,4,'Chloe','Carter','binge247'),(13,4,'password','maclogarta@gmail.com','123-456-7890',13,'miguel','logarta','maclogarta'),(16,4,'password','yuhuhuhuh@gmail.com','777-441-7890',17,'miguel','logarta','yuhmyyum');
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `account_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `pay_rate` int NOT NULL,
  `permissions` binary(4) DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  CONSTRAINT `fk_admin_account_id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_admin_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `Employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_admin_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES (1,1,1,1,4000,NULL,'2024-04-30 02:23:00');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dubbing`
--

DROP TABLE IF EXISTS `Dubbing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dubbing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `language_id` int NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_dubbing_language_id` FOREIGN KEY (`id`) REFERENCES `Language` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `account_id` int NOT NULL,
  `pay_rate` int NOT NULL,
  `permissions` binary(4) DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  KEY `fk_employee_user_id_idx` (`user_id`),
  KEY `fk_employee_account_Id_idx` (`account_id`),
  CONSTRAINT `fk_employee_account_Id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_employee_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,1,1,4000,NULL,'2024-04-30 02:20:33'),(2,2,2,1800,NULL,'2024-04-30 02:21:26'),(3,3,3,1800,NULL,'2024-04-30 02:21:26');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Feature`
--

DROP TABLE IF EXISTS `Feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Feature` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Feature`
--

LOCK TABLES `Feature` WRITE;
/*!40000 ALTER TABLE `Feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `Feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilmRating`
--

DROP TABLE IF EXISTS `FilmRating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FilmRating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `transaction_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_guest_user_id_idx` (`user_id`),
  CONSTRAINT `fk_guest_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest`
--

LOCK TABLES `Guest` WRITE;
/*!40000 ALTER TABLE `Guest` DISABLE KEYS */;
INSERT INTO `Guest` VALUES (1,5,'2024-04-30 02:05:46');
/*!40000 ALTER TABLE `Guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Language`
--

DROP TABLE IF EXISTS `Language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Language` (
  `id` int NOT NULL AUTO_INCREMENT,
  `language_name` varchar(64) NOT NULL,
  `description` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `language_name_UNIQUE` (`language_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,4,4,100,0,'2024-04-30 02:23:42');
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movie`
--

DROP TABLE IF EXISTS `Movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Movie` (
  `id` int NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movie`
--

LOCK TABLES `Movie` WRITE;
/*!40000 ALTER TABLE `Movie` DISABLE KEYS */;
INSERT INTO `Movie` VALUES (3,'Dune: Part Two',1,'Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee.',3,'02:46:00'),(4,'Spirited Away - Ghibli 2024',5,'In this animated feature by noted Japanese director Hayao Miyazaki, 10-year-old Chihiro (Rumi Hiiragi) and her parents (Takashi Naitô, Yasuko Sawaguchi) stumble upon a seemingly abandoned amusement park. After her mother and father are turned into giant pigs, Chihiro meets the mysterious Haku (Miyu Irino), who explains that the park is a resort for supernatural beings who need a break from their time spent in the earthly realm, and that she must work there to free herself and her parents.',2,'02:15:00'),(5,'Alien',1,'In deep space, the crew of the commercial starship Nostromo is awakened from their cryo-sleep capsules halfway through their journey home to investigate a distress call from an alien vessel. The terror begins when the crew encounters a nest of eggs inside the alien ship. An organism from inside an egg leaps out and attaches itself to one of the crew, causing him to fall into a coma.',4,'01:57:00'),(6,'Everything Everywhere All at Once',1,'Directed by Daniel Kwan and Daniel Scheinert, collectively known as Daniels, the film is a hilarious and big-hearted sci-fi action adventure about an exhausted Chinese American woman (Michelle Yeoh) who can\'t seem to finish her taxes.',4,'02:12:00'),(7,'Encanto',1,'The Madrigals are an extraordinary family who live hidden in the mountains of Colombia in a charmed place called the Encanto. The magic of the Encanto has blessed every child in the family with a unique gift -- every child except Mirabel. However, she soon may be the Madrigals last hope when she discovers that the magic surrounding the Encanto is now in danger.',2,'01:39:00'),(8,'Amélie',3,'\"Amélie\" is a fanciful comedy about a young woman who discretely orchestrates the lives of the people around her, creating a world exclusively of her own making. Shot in over 80 Parisian locations, acclaimed director Jean-Pierre Jeunet (\"Delicatessen\"; \"The City of Lost Children\") invokes his incomparable visionary style to capture the exquisite charm and mystery of modern-day Paris through the eyes of a beautiful ingenue.',4,'02:02:00'),(9,'White Chicks',1,'Two FBI agent brothers, Marcus (Marlon Wayans) and Kevin Copeland (Shawn Wayans), accidentally foil a drug bust. As punishment, they are forced to escort a pair of socialites (Anne Dudek, Rochelle Aytes) to the Hamptons, where they\'re going to be used as bait for a kidnapper. But when the girls realize the FBI\'s plan, they refuse to go. Left without options, Marcus and Kevin decide to pose as the sisters, transforming themselves from African-American men into a pair of blonde, white women.',3,'01:48:00');
/*!40000 ALTER TABLE `Movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaymentInformation`
--

DROP TABLE IF EXISTS `PaymentInformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PaymentInformation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `state` varchar(256) NOT NULL,
  `zip` int NOT NULL,
  `cvv` varchar(256) NOT NULL,
  `card_number` varchar(256) NOT NULL,
  `expiry` varchar(256) NOT NULL,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `fk_payment_information_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentInformation`
--

LOCK TABLES `PaymentInformation` WRITE;
/*!40000 ALTER TABLE `PaymentInformation` DISABLE KEYS */;
INSERT INTO `PaymentInformation` VALUES (1,4,'2024-04-30 02:26:13','248 some city dr','San Francisco','CA',94132,'sdfsdfa','adfadsfadfasdfdcvcvewewfwef','efwfdsfsd','Chloe','Carter');
/*!40000 ALTER TABLE `PaymentInformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resolution`
--

DROP TABLE IF EXISTS `Resolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Resolution` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Admin','Oversees the system.'),(2,'Ticket Clerk','I sit at the front in the ticket booth'),(3,'Employee','General role. Handles a variety of different tasks'),(4,'Member','A customer that is registered in our system.'),(5,'Useful employee','I do useful stuff');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sale`
--

DROP TABLE IF EXISTS `Sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount` int NOT NULL COMMENT 'Discount as a percentage. It is stored as an int for precision purposes.',
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `theatre_id` int NOT NULL,
  `is_occupied` tinyint NOT NULL,
  `seat_type_id` int NOT NULL,
  `row_letter` char(1) NOT NULL,
  `column_number` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seat_theatre_id_idx` (`theatre_id`),
  KEY `fk_seat_seat_type_id_idx` (`seat_type_id`),
  CONSTRAINT `fk_seat_seat_type_id` FOREIGN KEY (`seat_type_id`) REFERENCES `SeatType` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_seat_theatre_id` FOREIGN KEY (`theatre_id`) REFERENCES `TheatreRoom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seat`
--

LOCK TABLES `Seat` WRITE;
/*!40000 ALTER TABLE `Seat` DISABLE KEYS */;
INSERT INTO `Seat` VALUES (1,1,0,3,'A',1),(2,1,0,3,'A',2),(3,1,0,3,'A',3),(4,1,0,3,'A',4),(5,1,0,3,'B',1),(6,1,0,3,'B',2),(7,1,0,3,'B',3),(8,1,0,3,'B',4),(9,1,0,3,'C',1),(10,1,0,3,'C',2),(11,1,0,3,'C',3),(12,1,0,3,'C',4),(13,1,0,3,'D',1),(14,1,0,3,'D',2),(15,1,0,1,'D',3),(16,1,0,1,'D',4),(17,2,0,2,'A',1),(18,2,0,2,'A',2),(19,2,0,2,'A',3),(20,2,0,2,'A',4),(21,2,0,2,'A',5),(22,2,0,2,'B',1),(23,2,0,2,'B',2),(24,2,0,2,'B',3),(25,2,0,2,'B',4),(26,2,0,2,'B',5),(27,2,0,2,'C',1),(28,2,0,2,'C',2),(29,2,0,2,'C',3),(30,2,0,2,'C',4),(31,2,0,2,'C',5),(32,2,0,2,'D',1),(33,2,0,1,'D',2),(34,2,0,1,'D',3),(35,2,0,2,'D',4),(36,2,0,2,'D',5),(37,2,0,2,'E',1),(38,2,0,2,'E',2),(39,2,0,2,'E',3),(40,2,0,2,'E',4),(41,2,0,2,'E',5),(42,2,0,2,'F',1),(43,2,0,2,'F',2),(44,2,0,2,'F',3),(45,2,0,2,'F',4),(46,2,0,2,'F',5),(47,3,0,4,'A',1),(48,3,0,4,'A',2),(49,3,0,4,'A',3),(50,3,0,4,'A',4),(51,3,0,4,'A',5),(52,3,0,4,'B',1),(53,3,0,4,'B',2),(54,3,0,4,'B',3),(55,3,0,4,'B',4),(56,3,0,4,'B',5),(57,3,0,4,'C',1),(58,3,0,1,'C',2),(59,3,0,1,'C',3),(60,3,0,4,'C',4),(61,3,0,4,'C',5),(62,3,0,4,'D',1),(63,3,0,4,'D',2),(64,3,0,4,'D',3),(65,3,0,4,'D',4),(66,3,0,4,'D',5);
/*!40000 ALTER TABLE `Seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SeatType`
--

DROP TABLE IF EXISTS `SeatType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SeatType` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `resolution_id` int DEFAULT NULL,
  `subtitle_id` int DEFAULT NULL,
  `dubbing_id` int DEFAULT NULL,
  `sound_type_id` int DEFAULT NULL,
  `status` varchar(45) NOT NULL COMMENT 'Status will say SHOWING or NOT SHOWING',
  `theatre_id` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Showing`
--

LOCK TABLES `Showing` WRITE;
/*!40000 ALTER TABLE `Showing` DISABLE KEYS */;
INSERT INTO `Showing` VALUES (1,8,NULL,1,3,2,'NOW SHOWING',1,'2024-05-20 19:00:00',NULL),(2,3,4,NULL,NULL,3,'NOW SHOWING',3,'2024-05-20 14:30:00',NULL),(3,5,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-20 13:30:00',NULL),(4,6,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-20 10:30:00',NULL),(5,7,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-20 19:00:00',NULL),(6,9,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-20 22:30:00',NULL),(7,5,NULL,NULL,NULL,2,'NOW SHOWING',1,'2024-05-21 20:00:00',NULL),(8,7,NULL,NULL,NULL,3,'NOW SHOWING',3,'2024-05-21 16:30:00',NULL),(9,3,4,NULL,NULL,3,'NOW SHOWING',3,'2024-05-21 19:30:00',NULL),(10,4,NULL,NULL,1,2,'NOW SHOWING',2,'2024-05-21 11:30:00',NULL),(11,9,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-21 14:30:00',NULL),(12,6,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-21 18:30:00',NULL),(13,5,NULL,NULL,NULL,3,'NOW SHOWING',3,'2024-05-22 15:30:00',NULL),(14,3,4,NULL,NULL,3,'NOW SHOWING',3,'2024-05-22 19:00:00',NULL),(15,4,NULL,1,5,2,'NOW SHOWING',2,'2024-05-22 11:30:00',NULL),(16,8,NULL,1,3,2,'NOW SHOWING',2,'2024-05-22 16:30:00',NULL),(17,9,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-22 19:00:00',NULL),(18,4,NULL,NULL,1,3,'NOW SHOWING',3,'2024-05-23 18:30:00',NULL),(19,4,NULL,NULL,1,2,'NOW SHOWING',2,'2024-05-23 15:30:00',NULL),(20,8,NULL,1,3,2,'NOW SHOWING',2,'2024-05-23 12:30:00',NULL),(21,4,NULL,1,5,3,'NOW SHOWING',3,'2024-05-24 18:30:00',NULL),(22,4,NULL,1,5,2,'NOW SHOWING',2,'2024-05-24 15:30:00',NULL),(23,7,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-24 12:30:00',NULL),(24,6,NULL,NULL,NULL,2,'NOW SHOWING',2,'2024-05-24 19:00:00',NULL);
/*!40000 ALTER TABLE `Showing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShowingFeature`
--

DROP TABLE IF EXISTS `ShowingFeature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ShowingFeature` (
  `showing_id` int NOT NULL,
  `feature_id` int NOT NULL,
  PRIMARY KEY (`showing_id`),
  UNIQUE KEY `showing_id_UNIQUE` (`showing_id`),
  UNIQUE KEY `feature_id_UNIQUE` (`feature_id`),
  KEY `fk_showing_id_idx` (`showing_id`),
  KEY `fk_feature_id_idx` (`feature_id`),
  CONSTRAINT `fk_feature_id` FOREIGN KEY (`feature_id`) REFERENCES `Feature` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_showing_id` FOREIGN KEY (`showing_id`) REFERENCES `Showing` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShowingFeature`
--

LOCK TABLES `ShowingFeature` WRITE;
/*!40000 ALTER TABLE `ShowingFeature` DISABLE KEYS */;
/*!40000 ALTER TABLE `ShowingFeature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SoundSystem`
--

DROP TABLE IF EXISTS `SoundSystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SoundSystem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `language_id` int DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subtitle_language_id_idx` (`language_id`),
  CONSTRAINT `fk_subtitle_language_id` FOREIGN KEY (`language_id`) REFERENCES `Language` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `num_seats` int unsigned NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TheatreRoom`
--

LOCK TABLES `TheatreRoom` WRITE;
/*!40000 ALTER TABLE `TheatreRoom` DISABLE KEYS */;
INSERT INTO `TheatreRoom` VALUES (1,'A',16,'Couple\'s Theater. All the seats are catered towards couples.'),(2,'B',30,'Regular Theatre Room'),(3,'C',15,'Luxury Theatre');
/*!40000 ALTER TABLE `TheatreRoom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `showing_time_id` int NOT NULL,
  `price` int NOT NULL,
  `seat_id` int NOT NULL,
  `theatre_id` int NOT NULL,
  `status` int NOT NULL,
  `ticket_type` int NOT NULL,
  `bought_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_id_idx` (`movie_id`),
  KEY `fk_showing_time_id_idx` (`showing_time_id`),
  KEY `fk_seat_id_idx` (`seat_id`),
  KEY `fk_theatre_id_idx` (`theatre_id`),
  KEY `fk_status_idx` (`status`),
  KEY `fk_ticket_type_id_idx` (`ticket_type`),
  KEY `fk_bought_by_idx` (`bought_by`),
  CONSTRAINT `fk_showing_time_id` FOREIGN KEY (`showing_time_id`) REFERENCES `Showing` (`id`),
  CONSTRAINT `fk_ticket_bought_by` FOREIGN KEY (`bought_by`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `Movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_seat_id` FOREIGN KEY (`seat_id`) REFERENCES `Seat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_status_id` FOREIGN KEY (`status`) REFERENCES `TicketStatus` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_theatre_id` FOREIGN KEY (`theatre_id`) REFERENCES `TheatreRoom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_ticket_type_id` FOREIGN KEY (`ticket_type`) REFERENCES `TicketType` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
INSERT INTO `Ticket` VALUES (1490,8,1,1499,1,1,1,1,NULL),(1491,8,1,1499,2,1,1,1,NULL),(1492,8,1,1499,3,1,1,1,NULL),(1493,8,1,1499,4,1,1,1,NULL),(1494,8,1,1499,5,1,1,1,NULL),(1495,8,1,1499,6,1,1,1,NULL),(1496,8,1,1499,7,1,1,1,NULL),(1497,8,1,1499,8,1,1,1,NULL),(1498,8,1,1499,9,1,1,1,NULL),(1499,8,1,1499,10,1,1,1,NULL),(1500,8,1,1499,11,1,1,1,NULL),(1501,8,1,1499,12,1,1,1,NULL),(1502,8,1,1499,13,1,1,1,NULL),(1503,8,1,1499,14,1,1,1,NULL),(1504,8,1,1499,15,1,1,1,NULL),(1505,8,1,1499,16,1,1,1,NULL),(1506,3,2,1499,47,3,1,1,NULL),(1507,3,2,1499,48,3,1,1,NULL),(1508,3,2,1499,49,3,1,1,NULL),(1509,3,2,1499,50,3,1,1,NULL),(1510,3,2,1499,51,3,1,1,NULL),(1511,3,2,1499,52,3,1,1,NULL),(1512,3,2,1499,53,3,1,1,NULL),(1513,3,2,1499,54,3,1,1,NULL),(1514,3,2,1499,55,3,1,1,NULL),(1515,3,2,1499,56,3,1,1,NULL),(1516,3,2,1499,57,3,1,1,NULL),(1517,3,2,1499,58,3,1,1,NULL),(1518,3,2,1499,59,3,1,1,NULL),(1519,3,2,1499,60,3,1,1,NULL),(1520,3,2,1499,61,3,1,1,NULL),(1521,3,2,1499,62,3,1,1,NULL),(1522,3,2,1499,63,3,1,1,NULL),(1523,3,2,1499,64,3,1,1,NULL),(1524,3,2,1499,65,3,1,1,NULL),(1525,3,2,1499,66,3,1,1,NULL),(1526,5,3,1499,17,2,1,1,NULL),(1527,5,3,1499,18,2,1,1,NULL),(1528,5,3,1499,19,2,1,1,NULL),(1529,5,3,1499,20,2,1,1,NULL),(1530,5,3,1499,21,2,1,1,NULL),(1531,5,3,1499,22,2,1,1,NULL),(1532,5,3,1499,23,2,1,1,NULL),(1533,5,3,1499,24,2,1,1,NULL),(1534,5,3,1499,25,2,1,1,NULL),(1535,5,3,1499,26,2,1,1,NULL),(1536,5,3,1499,27,2,1,1,NULL),(1537,5,3,1499,28,2,1,1,NULL),(1538,5,3,1499,29,2,1,1,NULL),(1539,5,3,1499,30,2,1,1,NULL),(1540,5,3,1499,31,2,1,1,NULL),(1541,5,3,1499,32,2,1,1,NULL),(1542,5,3,1499,33,2,1,1,NULL),(1543,5,3,1499,34,2,1,1,NULL),(1544,5,3,1499,35,2,1,1,NULL),(1545,5,3,1499,36,2,1,1,NULL),(1546,5,3,1499,37,2,1,1,NULL),(1547,5,3,1499,38,2,1,1,NULL),(1548,5,3,1499,39,2,1,1,NULL),(1549,5,3,1499,40,2,1,1,NULL),(1550,5,3,1499,41,2,1,1,NULL),(1551,5,3,1499,42,2,1,1,NULL),(1552,5,3,1499,43,2,1,1,NULL),(1553,5,3,1499,44,2,1,1,NULL),(1554,5,3,1499,45,2,1,1,NULL),(1555,5,3,1499,46,2,1,1,NULL),(1556,6,4,1499,17,2,1,1,NULL),(1557,6,4,1499,18,2,1,1,NULL),(1558,6,4,1499,19,2,1,1,NULL),(1559,6,4,1499,20,2,1,1,NULL),(1560,6,4,1499,21,2,1,1,NULL),(1561,6,4,1499,22,2,1,1,NULL),(1562,6,4,1499,23,2,1,1,NULL),(1563,6,4,1499,24,2,1,1,NULL),(1564,6,4,1499,25,2,1,1,NULL),(1565,6,4,1499,26,2,1,1,NULL),(1566,6,4,1499,27,2,1,1,NULL),(1567,6,4,1499,28,2,1,1,NULL),(1568,6,4,1499,29,2,1,1,NULL),(1569,6,4,1499,30,2,1,1,NULL),(1570,6,4,1499,31,2,1,1,NULL),(1571,6,4,1499,32,2,1,1,NULL),(1572,6,4,1499,33,2,1,1,NULL),(1573,6,4,1499,34,2,1,1,NULL),(1574,6,4,1499,35,2,1,1,NULL),(1575,6,4,1499,36,2,1,1,NULL),(1576,6,4,1499,37,2,1,1,NULL),(1577,6,4,1499,38,2,1,1,NULL),(1578,6,4,1499,39,2,1,1,NULL),(1579,6,4,1499,40,2,1,1,NULL),(1580,6,4,1499,41,2,1,1,NULL),(1581,6,4,1499,42,2,1,1,NULL),(1582,6,4,1499,43,2,1,1,NULL),(1583,6,4,1499,44,2,1,1,NULL),(1584,6,4,1499,45,2,1,1,NULL),(1585,6,4,1499,46,2,1,1,NULL),(1586,7,5,1499,17,2,1,1,NULL),(1587,7,5,1499,18,2,1,1,NULL),(1588,7,5,1499,19,2,1,1,NULL),(1589,7,5,1499,20,2,1,1,NULL),(1590,7,5,1499,21,2,1,1,NULL),(1591,7,5,1499,22,2,1,1,NULL),(1592,7,5,1499,23,2,1,1,NULL),(1593,7,5,1499,24,2,1,1,NULL),(1594,7,5,1499,25,2,1,1,NULL),(1595,7,5,1499,26,2,1,1,NULL),(1596,7,5,1499,27,2,1,1,NULL),(1597,7,5,1499,28,2,1,1,NULL),(1598,7,5,1499,29,2,1,1,NULL),(1599,7,5,1499,30,2,1,1,NULL),(1600,7,5,1499,31,2,1,1,NULL),(1601,7,5,1499,32,2,1,1,NULL),(1602,7,5,1499,33,2,1,1,NULL),(1603,7,5,1499,34,2,1,1,NULL),(1604,7,5,1499,35,2,1,1,NULL),(1605,7,5,1499,36,2,1,1,NULL),(1606,7,5,1499,37,2,1,1,NULL),(1607,7,5,1499,38,2,1,1,NULL),(1608,7,5,1499,39,2,1,1,NULL),(1609,7,5,1499,40,2,1,1,NULL),(1610,7,5,1499,41,2,1,1,NULL),(1611,7,5,1499,42,2,1,1,NULL),(1612,7,5,1499,43,2,1,1,NULL),(1613,7,5,1499,44,2,1,1,NULL),(1614,7,5,1499,45,2,1,1,NULL),(1615,7,5,1499,46,2,1,1,NULL),(1616,9,6,1499,17,2,1,1,NULL),(1617,9,6,1499,18,2,1,1,NULL),(1618,9,6,1499,19,2,1,1,NULL),(1619,9,6,1499,20,2,1,1,NULL),(1620,9,6,1499,21,2,1,1,NULL),(1621,9,6,1499,22,2,1,1,NULL),(1622,9,6,1499,23,2,1,1,NULL),(1623,9,6,1499,24,2,1,1,NULL),(1624,9,6,1499,25,2,1,1,NULL),(1625,9,6,1499,26,2,1,1,NULL),(1626,9,6,1499,27,2,1,1,NULL),(1627,9,6,1499,28,2,1,1,NULL),(1628,9,6,1499,29,2,1,1,NULL),(1629,9,6,1499,30,2,1,1,NULL),(1630,9,6,1499,31,2,1,1,NULL),(1631,9,6,1499,32,2,1,1,NULL),(1632,9,6,1499,33,2,1,1,NULL),(1633,9,6,1499,34,2,1,1,NULL),(1634,9,6,1499,35,2,1,1,NULL),(1635,9,6,1499,36,2,1,1,NULL),(1636,9,6,1499,37,2,1,1,NULL),(1637,9,6,1499,38,2,1,1,NULL),(1638,9,6,1499,39,2,1,1,NULL),(1639,9,6,1499,40,2,1,1,NULL),(1640,9,6,1499,41,2,1,1,NULL),(1641,9,6,1499,42,2,1,1,NULL),(1642,9,6,1499,43,2,1,1,NULL),(1643,9,6,1499,44,2,1,1,NULL),(1644,9,6,1499,45,2,1,1,NULL),(1645,9,6,1499,46,2,1,1,NULL),(1646,5,7,1499,1,1,1,1,NULL),(1647,5,7,1499,2,1,1,1,NULL),(1648,5,7,1499,3,1,1,1,NULL),(1649,5,7,1499,4,1,1,1,NULL),(1650,5,7,1499,5,1,1,1,NULL),(1651,5,7,1499,6,1,1,1,NULL),(1652,5,7,1499,7,1,1,1,NULL),(1653,5,7,1499,8,1,1,1,NULL),(1654,5,7,1499,9,1,1,1,NULL),(1655,5,7,1499,10,1,1,1,NULL),(1656,5,7,1499,11,1,1,1,NULL),(1657,5,7,1499,12,1,1,1,NULL),(1658,5,7,1499,13,1,1,1,NULL),(1659,5,7,1499,14,1,1,1,NULL),(1660,5,7,1499,15,1,1,1,NULL),(1661,5,7,1499,16,1,1,1,NULL),(1662,7,8,1499,47,3,1,1,NULL),(1663,7,8,1499,48,3,1,1,NULL),(1664,7,8,1499,49,3,1,1,NULL),(1665,7,8,1499,50,3,1,1,NULL),(1666,7,8,1499,51,3,1,1,NULL),(1667,7,8,1499,52,3,1,1,NULL),(1668,7,8,1499,53,3,1,1,NULL),(1669,7,8,1499,54,3,1,1,NULL),(1670,7,8,1499,55,3,1,1,NULL),(1671,7,8,1499,56,3,1,1,NULL),(1672,7,8,1499,57,3,1,1,NULL),(1673,7,8,1499,58,3,1,1,NULL),(1674,7,8,1499,59,3,1,1,NULL),(1675,7,8,1499,60,3,1,1,NULL),(1676,7,8,1499,61,3,1,1,NULL),(1677,7,8,1499,62,3,1,1,NULL),(1678,7,8,1499,63,3,1,1,NULL),(1679,7,8,1499,64,3,1,1,NULL),(1680,7,8,1499,65,3,1,1,NULL),(1681,7,8,1499,66,3,1,1,NULL),(1682,3,9,1499,47,3,1,1,NULL),(1683,3,9,1499,48,3,1,1,NULL),(1684,3,9,1499,49,3,1,1,NULL),(1685,3,9,1499,50,3,1,1,NULL),(1686,3,9,1499,51,3,1,1,NULL),(1687,3,9,1499,52,3,1,1,NULL),(1688,3,9,1499,53,3,1,1,NULL),(1689,3,9,1499,54,3,1,1,NULL),(1690,3,9,1499,55,3,1,1,NULL),(1691,3,9,1499,56,3,1,1,NULL),(1692,3,9,1499,57,3,1,1,NULL),(1693,3,9,1499,58,3,1,1,NULL),(1694,3,9,1499,59,3,1,1,NULL),(1695,3,9,1499,60,3,1,1,NULL),(1696,3,9,1499,61,3,1,1,NULL),(1697,3,9,1499,62,3,1,1,NULL),(1698,3,9,1499,63,3,1,1,NULL),(1699,3,9,1499,64,3,1,1,NULL),(1700,3,9,1499,65,3,1,1,NULL),(1701,3,9,1499,66,3,1,1,NULL),(1702,4,10,1499,17,2,1,1,NULL),(1703,4,10,1499,18,2,1,1,NULL),(1704,4,10,1499,19,2,1,1,NULL),(1705,4,10,1499,20,2,1,1,NULL),(1706,4,10,1499,21,2,1,1,NULL),(1707,4,10,1499,22,2,1,1,NULL),(1708,4,10,1499,23,2,1,1,NULL),(1709,4,10,1499,24,2,1,1,NULL),(1710,4,10,1499,25,2,1,1,NULL),(1711,4,10,1499,26,2,1,1,NULL),(1712,4,10,1499,27,2,1,1,NULL),(1713,4,10,1499,28,2,1,1,NULL),(1714,4,10,1499,29,2,1,1,NULL),(1715,4,10,1499,30,2,1,1,NULL),(1716,4,10,1499,31,2,1,1,NULL),(1717,4,10,1499,32,2,1,1,NULL),(1718,4,10,1499,33,2,1,1,NULL),(1719,4,10,1499,34,2,1,1,NULL),(1720,4,10,1499,35,2,1,1,NULL),(1721,4,10,1499,36,2,1,1,NULL),(1722,4,10,1499,37,2,1,1,NULL),(1723,4,10,1499,38,2,1,1,NULL),(1724,4,10,1499,39,2,1,1,NULL),(1725,4,10,1499,40,2,1,1,NULL),(1726,4,10,1499,41,2,1,1,NULL),(1727,4,10,1499,42,2,1,1,NULL),(1728,4,10,1499,43,2,1,1,NULL),(1729,4,10,1499,44,2,1,1,NULL),(1730,4,10,1499,45,2,1,1,NULL),(1731,4,10,1499,46,2,1,1,NULL),(1732,9,11,1499,17,2,1,1,NULL),(1733,9,11,1499,18,2,1,1,NULL),(1734,9,11,1499,19,2,1,1,NULL),(1735,9,11,1499,20,2,1,1,NULL),(1736,9,11,1499,21,2,1,1,NULL),(1737,9,11,1499,22,2,1,1,NULL),(1738,9,11,1499,23,2,1,1,NULL),(1739,9,11,1499,24,2,1,1,NULL),(1740,9,11,1499,25,2,1,1,NULL),(1741,9,11,1499,26,2,1,1,NULL),(1742,9,11,1499,27,2,1,1,NULL),(1743,9,11,1499,28,2,1,1,NULL),(1744,9,11,1499,29,2,1,1,NULL),(1745,9,11,1499,30,2,1,1,NULL),(1746,9,11,1499,31,2,1,1,NULL),(1747,9,11,1499,32,2,1,1,NULL),(1748,9,11,1499,33,2,1,1,NULL),(1749,9,11,1499,34,2,1,1,NULL),(1750,9,11,1499,35,2,1,1,NULL),(1751,9,11,1499,36,2,1,1,NULL),(1752,9,11,1499,37,2,1,1,NULL),(1753,9,11,1499,38,2,1,1,NULL),(1754,9,11,1499,39,2,1,1,NULL),(1755,9,11,1499,40,2,1,1,NULL),(1756,9,11,1499,41,2,1,1,NULL),(1757,9,11,1499,42,2,1,1,NULL),(1758,9,11,1499,43,2,1,1,NULL),(1759,9,11,1499,44,2,1,1,NULL),(1760,9,11,1499,45,2,1,1,NULL),(1761,9,11,1499,46,2,1,1,NULL),(1762,6,12,1499,17,2,1,1,NULL),(1763,6,12,1499,18,2,1,1,NULL),(1764,6,12,1499,19,2,1,1,NULL),(1765,6,12,1499,20,2,1,1,NULL),(1766,6,12,1499,21,2,1,1,NULL),(1767,6,12,1499,22,2,1,1,NULL),(1768,6,12,1499,23,2,1,1,NULL),(1769,6,12,1499,24,2,1,1,NULL),(1770,6,12,1499,25,2,1,1,NULL),(1771,6,12,1499,26,2,1,1,NULL),(1772,6,12,1499,27,2,1,1,NULL),(1773,6,12,1499,28,2,1,1,NULL),(1774,6,12,1499,29,2,1,1,NULL),(1775,6,12,1499,30,2,1,1,NULL),(1776,6,12,1499,31,2,1,1,NULL),(1777,6,12,1499,32,2,1,1,NULL),(1778,6,12,1499,33,2,1,1,NULL),(1779,6,12,1499,34,2,1,1,NULL),(1780,6,12,1499,35,2,1,1,NULL),(1781,6,12,1499,36,2,1,1,NULL),(1782,6,12,1499,37,2,1,1,NULL),(1783,6,12,1499,38,2,1,1,NULL),(1784,6,12,1499,39,2,1,1,NULL),(1785,6,12,1499,40,2,1,1,NULL),(1786,6,12,1499,41,2,1,1,NULL),(1787,6,12,1499,42,2,1,1,NULL),(1788,6,12,1499,43,2,1,1,NULL),(1789,6,12,1499,44,2,1,1,NULL),(1790,6,12,1499,45,2,1,1,NULL),(1791,6,12,1499,46,2,1,1,NULL),(1792,5,13,1499,47,3,1,1,NULL),(1793,5,13,1499,48,3,1,1,NULL),(1794,5,13,1499,49,3,1,1,NULL),(1795,5,13,1499,50,3,1,1,NULL),(1796,5,13,1499,51,3,1,1,NULL),(1797,5,13,1499,52,3,1,1,NULL),(1798,5,13,1499,53,3,1,1,NULL),(1799,5,13,1499,54,3,1,1,NULL),(1800,5,13,1499,55,3,1,1,NULL),(1801,5,13,1499,56,3,1,1,NULL),(1802,5,13,1499,57,3,1,1,NULL),(1803,5,13,1499,58,3,1,1,NULL),(1804,5,13,1499,59,3,1,1,NULL),(1805,5,13,1499,60,3,1,1,NULL),(1806,5,13,1499,61,3,1,1,NULL),(1807,5,13,1499,62,3,1,1,NULL),(1808,5,13,1499,63,3,1,1,NULL),(1809,5,13,1499,64,3,1,1,NULL),(1810,5,13,1499,65,3,1,1,NULL),(1811,5,13,1499,66,3,1,1,NULL),(1812,3,14,1499,47,3,1,1,NULL),(1813,3,14,1499,48,3,1,1,NULL),(1814,3,14,1499,49,3,1,1,NULL),(1815,3,14,1499,50,3,1,1,NULL),(1816,3,14,1499,51,3,1,1,NULL),(1817,3,14,1499,52,3,1,1,NULL),(1818,3,14,1499,53,3,1,1,NULL),(1819,3,14,1499,54,3,1,1,NULL),(1820,3,14,1499,55,3,1,1,NULL),(1821,3,14,1499,56,3,1,1,NULL),(1822,3,14,1499,57,3,1,1,NULL),(1823,3,14,1499,58,3,1,1,NULL),(1824,3,14,1499,59,3,1,1,NULL),(1825,3,14,1499,60,3,1,1,NULL),(1826,3,14,1499,61,3,1,1,NULL),(1827,3,14,1499,62,3,1,1,NULL),(1828,3,14,1499,63,3,1,1,NULL),(1829,3,14,1499,64,3,1,1,NULL),(1830,3,14,1499,65,3,1,1,NULL),(1831,3,14,1499,66,3,1,1,NULL),(1832,4,15,1499,17,2,1,1,NULL),(1833,4,15,1499,18,2,1,1,NULL),(1834,4,15,1499,19,2,1,1,NULL),(1835,4,15,1499,20,2,1,1,NULL),(1836,4,15,1499,21,2,1,1,NULL),(1837,4,15,1499,22,2,1,1,NULL),(1838,4,15,1499,23,2,1,1,NULL),(1839,4,15,1499,24,2,1,1,NULL),(1840,4,15,1499,25,2,1,1,NULL),(1841,4,15,1499,26,2,1,1,NULL),(1842,4,15,1499,27,2,1,1,NULL),(1843,4,15,1499,28,2,1,1,NULL),(1844,4,15,1499,29,2,1,1,NULL),(1845,4,15,1499,30,2,1,1,NULL),(1846,4,15,1499,31,2,1,1,NULL),(1847,4,15,1499,32,2,1,1,NULL),(1848,4,15,1499,33,2,1,1,NULL),(1849,4,15,1499,34,2,1,1,NULL),(1850,4,15,1499,35,2,1,1,NULL),(1851,4,15,1499,36,2,1,1,NULL),(1852,4,15,1499,37,2,1,1,NULL),(1853,4,15,1499,38,2,1,1,NULL),(1854,4,15,1499,39,2,1,1,NULL),(1855,4,15,1499,40,2,1,1,NULL),(1856,4,15,1499,41,2,1,1,NULL),(1857,4,15,1499,42,2,1,1,NULL),(1858,4,15,1499,43,2,1,1,NULL),(1859,4,15,1499,44,2,1,1,NULL),(1860,4,15,1499,45,2,1,1,NULL),(1861,4,15,1499,46,2,1,1,NULL),(1862,8,16,1499,17,2,1,1,NULL),(1863,8,16,1499,18,2,1,1,NULL),(1864,8,16,1499,19,2,1,1,NULL),(1865,8,16,1499,20,2,1,1,NULL),(1866,8,16,1499,21,2,1,1,NULL),(1867,8,16,1499,22,2,1,1,NULL),(1868,8,16,1499,23,2,1,1,NULL),(1869,8,16,1499,24,2,1,1,NULL),(1870,8,16,1499,25,2,1,1,NULL),(1871,8,16,1499,26,2,1,1,NULL),(1872,8,16,1499,27,2,1,1,NULL),(1873,8,16,1499,28,2,1,1,NULL),(1874,8,16,1499,29,2,1,1,NULL),(1875,8,16,1499,30,2,1,1,NULL),(1876,8,16,1499,31,2,1,1,NULL),(1877,8,16,1499,32,2,1,1,NULL),(1878,8,16,1499,33,2,1,1,NULL),(1879,8,16,1499,34,2,1,1,NULL),(1880,8,16,1499,35,2,1,1,NULL),(1881,8,16,1499,36,2,1,1,NULL),(1882,8,16,1499,37,2,1,1,NULL),(1883,8,16,1499,38,2,1,1,NULL),(1884,8,16,1499,39,2,1,1,NULL),(1885,8,16,1499,40,2,1,1,NULL),(1886,8,16,1499,41,2,1,1,NULL),(1887,8,16,1499,42,2,1,1,NULL),(1888,8,16,1499,43,2,1,1,NULL),(1889,8,16,1499,44,2,1,1,NULL),(1890,8,16,1499,45,2,1,1,NULL),(1891,8,16,1499,46,2,1,1,NULL),(1892,9,17,1499,17,2,1,1,NULL),(1893,9,17,1499,18,2,1,1,NULL),(1894,9,17,1499,19,2,1,1,NULL),(1895,9,17,1499,20,2,1,1,NULL),(1896,9,17,1499,21,2,1,1,NULL),(1897,9,17,1499,22,2,1,1,NULL),(1898,9,17,1499,23,2,1,1,NULL),(1899,9,17,1499,24,2,1,1,NULL),(1900,9,17,1499,25,2,1,1,NULL),(1901,9,17,1499,26,2,1,1,NULL),(1902,9,17,1499,27,2,1,1,NULL),(1903,9,17,1499,28,2,1,1,NULL),(1904,9,17,1499,29,2,1,1,NULL),(1905,9,17,1499,30,2,1,1,NULL),(1906,9,17,1499,31,2,1,1,NULL),(1907,9,17,1499,32,2,1,1,NULL),(1908,9,17,1499,33,2,1,1,NULL),(1909,9,17,1499,34,2,1,1,NULL),(1910,9,17,1499,35,2,1,1,NULL),(1911,9,17,1499,36,2,1,1,NULL),(1912,9,17,1499,37,2,1,1,NULL),(1913,9,17,1499,38,2,1,1,NULL),(1914,9,17,1499,39,2,1,1,NULL),(1915,9,17,1499,40,2,1,1,NULL),(1916,9,17,1499,41,2,1,1,NULL),(1917,9,17,1499,42,2,1,1,NULL),(1918,9,17,1499,43,2,1,1,NULL),(1919,9,17,1499,44,2,1,1,NULL),(1920,9,17,1499,45,2,1,1,NULL),(1921,9,17,1499,46,2,1,1,NULL),(1922,4,18,1499,47,3,1,1,NULL),(1923,4,18,1499,48,3,1,1,NULL),(1924,4,18,1499,49,3,1,1,NULL),(1925,4,18,1499,50,3,1,1,NULL),(1926,4,18,1499,51,3,1,1,NULL),(1927,4,18,1499,52,3,1,1,NULL),(1928,4,18,1499,53,3,1,1,NULL),(1929,4,18,1499,54,3,1,1,NULL),(1930,4,18,1499,55,3,1,1,NULL),(1931,4,18,1499,56,3,1,1,NULL),(1932,4,18,1499,57,3,1,1,NULL),(1933,4,18,1499,58,3,1,1,NULL),(1934,4,18,1499,59,3,1,1,NULL),(1935,4,18,1499,60,3,1,1,NULL),(1936,4,18,1499,61,3,1,1,NULL),(1937,4,18,1499,62,3,1,1,NULL),(1938,4,18,1499,63,3,1,1,NULL),(1939,4,18,1499,64,3,1,1,NULL),(1940,4,18,1499,65,3,1,1,NULL),(1941,4,18,1499,66,3,1,1,NULL),(1942,4,19,1499,17,2,1,1,NULL),(1943,4,19,1499,18,2,1,1,NULL),(1944,4,19,1499,19,2,1,1,NULL),(1945,4,19,1499,20,2,1,1,NULL),(1946,4,19,1499,21,2,1,1,NULL),(1947,4,19,1499,22,2,1,1,NULL),(1948,4,19,1499,23,2,1,1,NULL),(1949,4,19,1499,24,2,1,1,NULL),(1950,4,19,1499,25,2,1,1,NULL),(1951,4,19,1499,26,2,1,1,NULL),(1952,4,19,1499,27,2,1,1,NULL),(1953,4,19,1499,28,2,1,1,NULL),(1954,4,19,1499,29,2,1,1,NULL),(1955,4,19,1499,30,2,1,1,NULL),(1956,4,19,1499,31,2,1,1,NULL),(1957,4,19,1499,32,2,1,1,NULL),(1958,4,19,1499,33,2,1,1,NULL),(1959,4,19,1499,34,2,1,1,NULL),(1960,4,19,1499,35,2,1,1,NULL),(1961,4,19,1499,36,2,1,1,NULL),(1962,4,19,1499,37,2,1,1,NULL),(1963,4,19,1499,38,2,1,1,NULL),(1964,4,19,1499,39,2,1,1,NULL),(1965,4,19,1499,40,2,1,1,NULL),(1966,4,19,1499,41,2,1,1,NULL),(1967,4,19,1499,42,2,1,1,NULL),(1968,4,19,1499,43,2,1,1,NULL),(1969,4,19,1499,44,2,1,1,NULL),(1970,4,19,1499,45,2,1,1,NULL),(1971,4,19,1499,46,2,1,1,NULL),(1972,8,20,1499,17,2,1,1,NULL),(1973,8,20,1499,18,2,1,1,NULL),(1974,8,20,1499,19,2,1,1,NULL),(1975,8,20,1499,20,2,1,1,NULL),(1976,8,20,1499,21,2,1,1,NULL),(1977,8,20,1499,22,2,1,1,NULL),(1978,8,20,1499,23,2,1,1,NULL),(1979,8,20,1499,24,2,1,1,NULL),(1980,8,20,1499,25,2,1,1,NULL),(1981,8,20,1499,26,2,1,1,NULL),(1982,8,20,1499,27,2,1,1,NULL),(1983,8,20,1499,28,2,1,1,NULL),(1984,8,20,1499,29,2,1,1,NULL),(1985,8,20,1499,30,2,1,1,NULL),(1986,8,20,1499,31,2,1,1,NULL),(1987,8,20,1499,32,2,1,1,NULL),(1988,8,20,1499,33,2,1,1,NULL),(1989,8,20,1499,34,2,1,1,NULL),(1990,8,20,1499,35,2,1,1,NULL),(1991,8,20,1499,36,2,1,1,NULL),(1992,8,20,1499,37,2,1,1,NULL),(1993,8,20,1499,38,2,1,1,NULL),(1994,8,20,1499,39,2,1,1,NULL),(1995,8,20,1499,40,2,1,1,NULL),(1996,8,20,1499,41,2,1,1,NULL),(1997,8,20,1499,42,2,1,1,NULL),(1998,8,20,1499,43,2,1,1,NULL),(1999,8,20,1499,44,2,1,1,NULL),(2000,8,20,1499,45,2,1,1,NULL),(2001,8,20,1499,46,2,1,1,NULL),(2002,4,21,1499,47,3,1,1,NULL),(2003,4,21,1499,48,3,1,1,NULL),(2004,4,21,1499,49,3,1,1,NULL),(2005,4,21,1499,50,3,1,1,NULL),(2006,4,21,1499,51,3,1,1,NULL),(2007,4,21,1499,52,3,1,1,NULL),(2008,4,21,1499,53,3,1,1,NULL),(2009,4,21,1499,54,3,1,1,NULL),(2010,4,21,1499,55,3,1,1,NULL),(2011,4,21,1499,56,3,1,1,NULL),(2012,4,21,1499,57,3,1,1,NULL),(2013,4,21,1499,58,3,1,1,NULL),(2014,4,21,1499,59,3,1,1,NULL),(2015,4,21,1499,60,3,1,1,NULL),(2016,4,21,1499,61,3,1,1,NULL),(2017,4,21,1499,62,3,1,1,NULL),(2018,4,21,1499,63,3,1,1,NULL),(2019,4,21,1499,64,3,1,1,NULL),(2020,4,21,1499,65,3,1,1,NULL),(2021,4,21,1499,66,3,1,1,NULL),(2022,4,22,1499,17,2,1,1,NULL),(2023,4,22,1499,18,2,1,1,NULL),(2024,4,22,1499,19,2,1,1,NULL),(2025,4,22,1499,20,2,1,1,NULL),(2026,4,22,1499,21,2,1,1,NULL),(2027,4,22,1499,22,2,1,1,NULL),(2028,4,22,1499,23,2,1,1,NULL),(2029,4,22,1499,24,2,1,1,NULL),(2030,4,22,1499,25,2,1,1,NULL),(2031,4,22,1499,26,2,1,1,NULL),(2032,4,22,1499,27,2,1,1,NULL),(2033,4,22,1499,28,2,1,1,NULL),(2034,4,22,1499,29,2,1,1,NULL),(2035,4,22,1499,30,2,1,1,NULL),(2036,4,22,1499,31,2,1,1,NULL),(2037,4,22,1499,32,2,1,1,NULL),(2038,4,22,1499,33,2,1,1,NULL),(2039,4,22,1499,34,2,1,1,NULL),(2040,4,22,1499,35,2,1,1,NULL),(2041,4,22,1499,36,2,1,1,NULL),(2042,4,22,1499,37,2,1,1,NULL),(2043,4,22,1499,38,2,1,1,NULL),(2044,4,22,1499,39,2,1,1,NULL),(2045,4,22,1499,40,2,1,1,NULL),(2046,4,22,1499,41,2,1,1,NULL),(2047,4,22,1499,42,2,1,1,NULL),(2048,4,22,1499,43,2,1,1,NULL),(2049,4,22,1499,44,2,1,1,NULL),(2050,4,22,1499,45,2,1,1,NULL),(2051,4,22,1499,46,2,1,1,NULL),(2052,7,23,1499,17,2,1,1,NULL),(2053,7,23,1499,18,2,1,1,NULL),(2054,7,23,1499,19,2,1,1,NULL),(2055,7,23,1499,20,2,1,1,NULL),(2056,7,23,1499,21,2,1,1,NULL),(2057,7,23,1499,22,2,1,1,NULL),(2058,7,23,1499,23,2,1,1,NULL),(2059,7,23,1499,24,2,1,1,NULL),(2060,7,23,1499,25,2,1,1,NULL),(2061,7,23,1499,26,2,1,1,NULL),(2062,7,23,1499,27,2,1,1,NULL),(2063,7,23,1499,28,2,1,1,NULL),(2064,7,23,1499,29,2,1,1,NULL),(2065,7,23,1499,30,2,1,1,NULL),(2066,7,23,1499,31,2,1,1,NULL),(2067,7,23,1499,32,2,1,1,NULL),(2068,7,23,1499,33,2,1,1,NULL),(2069,7,23,1499,34,2,1,1,NULL),(2070,7,23,1499,35,2,1,1,NULL),(2071,7,23,1499,36,2,1,1,NULL),(2072,7,23,1499,37,2,1,1,NULL),(2073,7,23,1499,38,2,1,1,NULL),(2074,7,23,1499,39,2,1,1,NULL),(2075,7,23,1499,40,2,1,1,NULL),(2076,7,23,1499,41,2,1,1,NULL),(2077,7,23,1499,42,2,1,1,NULL),(2078,7,23,1499,43,2,1,1,NULL),(2079,7,23,1499,44,2,1,1,NULL),(2080,7,23,1499,45,2,1,1,NULL),(2081,7,23,1499,46,2,1,1,NULL),(2082,6,24,1499,17,2,1,1,NULL),(2083,6,24,1499,18,2,1,1,NULL),(2084,6,24,1499,19,2,1,1,NULL),(2085,6,24,1499,20,2,1,1,NULL),(2086,6,24,1499,21,2,1,1,NULL),(2087,6,24,1499,22,2,1,1,NULL),(2088,6,24,1499,23,2,1,1,NULL),(2089,6,24,1499,24,2,1,1,NULL),(2090,6,24,1499,25,2,1,1,NULL),(2091,6,24,1499,26,2,1,1,NULL),(2092,6,24,1499,27,2,1,1,NULL),(2093,6,24,1499,28,2,1,1,NULL),(2094,6,24,1499,29,2,1,1,NULL),(2095,6,24,1499,30,2,1,1,NULL),(2096,6,24,1499,31,2,1,1,NULL),(2097,6,24,1499,32,2,1,1,NULL),(2098,6,24,1499,33,2,1,1,NULL),(2099,6,24,1499,34,2,1,1,NULL),(2100,6,24,1499,35,2,1,1,NULL),(2101,6,24,1499,36,2,1,1,NULL),(2102,6,24,1499,37,2,1,1,NULL),(2103,6,24,1499,38,2,1,1,NULL),(2104,6,24,1499,39,2,1,1,NULL),(2105,6,24,1499,40,2,1,1,NULL),(2106,6,24,1499,41,2,1,1,NULL),(2107,6,24,1499,42,2,1,1,NULL),(2108,6,24,1499,43,2,1,1,NULL),(2109,6,24,1499,44,2,1,1,NULL),(2110,6,24,1499,45,2,1,1,NULL),(2111,6,24,1499,46,2,1,1,NULL);
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketStatus`
--

DROP TABLE IF EXISTS `TicketStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TicketStatus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `suggested_price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_account_id_idx` (`account_id`),
  KEY `fk_user_status_idx` (`status`),
  CONSTRAINT `fk_user_account_id` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_user_status` FOREIGN KEY (`status`) REFERENCES `UserStatus` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,1,'2024-04-30 02:02:23',2),(2,2,'2024-04-30 02:02:23',2),(3,3,'2024-04-30 02:02:23',2),(4,4,'2024-04-30 02:02:23',2),(5,NULL,'2024-04-30 02:02:23',1),(11,NULL,'2024-05-18 15:54:51',2),(12,NULL,'2024-05-18 15:57:10',2),(13,NULL,'2024-05-18 15:58:50',2),(14,NULL,'2024-05-18 16:03:27',2),(15,NULL,'2024-05-18 16:04:12',2),(16,NULL,'2024-05-18 16:06:20',1),(17,NULL,'2024-05-18 16:06:30',2),(18,NULL,'2024-05-18 18:21:48',1),(19,NULL,'2024-05-18 18:24:48',1),(20,NULL,'2024-05-18 18:30:51',1),(21,NULL,'2024-05-18 18:38:28',1),(22,NULL,'2024-05-18 18:40:17',1);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserExample`
--

DROP TABLE IF EXISTS `UserExample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserExample` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `about_me` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserExample`
--

LOCK TABLES `UserExample` WRITE;
/*!40000 ALTER TABLE `UserExample` DISABLE KEYS */;
INSERT INTO `UserExample` VALUES (1,'yuuko1226','Homework is too hard. That\'s why I like to copy from my classmates!');
/*!40000 ALTER TABLE `UserExample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserStatus`
--

DROP TABLE IF EXISTS `UserStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserStatus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserStatus`
--

LOCK TABLES `UserStatus` WRITE;
/*!40000 ALTER TABLE `UserStatus` DISABLE KEYS */;
INSERT INTO `UserStatus` VALUES (1,'Guest','This user is a guest. Only transaction information is stored for this user.'),(2,'Active','This user is an active member of our system. They have an account associated with them.'),(3,'Deactivated','This user has deactivated their account. Only store the minimal information for safe-keeping.');
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

-- Dump completed on 2024-05-19 18:01:53
