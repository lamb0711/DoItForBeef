-- MySQL dump 10.13  Distrib 8.0.16, for osx10.13 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `FavoriteList`
--

DROP TABLE IF EXISTS `FavoriteList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `FavoriteList` (
  `music_id` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FavoriteList`
--

LOCK TABLES `FavoriteList` WRITE;
/*!40000 ALTER TABLE `FavoriteList` DISABLE KEYS */;
INSERT INTO `FavoriteList` VALUES (1,12);
/*!40000 ALTER TABLE `FavoriteList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MusicList`
--

DROP TABLE IF EXISTS `MusicList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MusicList` (
  `music_id` int(11) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `album_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `release_date` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `lyrics` text COLLATE utf8mb4_general_ci,
  `title` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `count` int(100) NOT NULL,
  `genre` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`music_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MusicList`
--

LOCK TABLES `MusicList` WRITE;
/*!40000 ALTER TABLE `MusicList` DISABLE KEYS */;
INSERT INTO `MusicList` VALUES (1,'Billie Eilish','WHEN WE ALL FALL ASLEEP, WHERE DO WE GO?','2019-03-29',' My Invisalign has...I have taken out my InvisalignI have taken out my Invisalign and this is the album','bad guy',2,'POP'),(2,'Anne-Marie','Speak Your Mind (Deluxe)','2018-04-17',' I will always remember\nThe day you kissed my lips\nLight as a feather\nAnd it went just like this\nNo, it\'s never been better\nThan the summer of 2002\n','2002',0,'POP'),(3,'Bruno Mars ','Unorthodox Jukebox','2012-12-11',' Baby squirrel, you’s a sexy motherfucker\n\nGive me your give me your give me your attention baby\nI gotta tell you a little something about yourself\n','Treasure',0,'R&B'),(4,'Maroon 5','V','2014-09-01',' I`m hurting baby, I`m broken down\nI need your loving, loving\nI need it now\n','Sugar',0,'ROCK/FOLK'),(5,'Billie Eilish','WHEN WE ALL FALL ASLEEP, WHERE DO WE GO?','2019-03-29',' White shirt now red, my bloody nose\nSleepin\', you\'re on your tippy toes\nCreepin\' around like no one knows\n','!!!!!!!',0,'POP'),(6,'Lil Dicky','Earth','2019-04-19',' We love the Earth, it is our planet\nWe love the Earth, it is our home\nWe love the Earth, it is our planet\n','Earth',0,'HIP-HOP'),(7,'Queen','Platinum Collection (Vol. 1-3)','2001-09-18',' Tonight I\'m gonna have myself a real good time\nI feel alive and the world turning inside out Yeah!\nAnd floating around in ecstasy\n','Don\'t Stop Me Now',0,'ROCK/FOLK'),(8,'Robin Schulz','Sugar','2015-07-17',' She\'s got cherry lips, angel eyes\nShe knows exactly how to tantalize\nShe\'s out to get you danger by design\n','Sugar',0,'DANCE');
/*!40000 ALTER TABLE `MusicList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MusicUser`
--

DROP TABLE IF EXISTS `MusicUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MusicUser` (
  `id` int(11) NOT NULL,
  `myList` text COLLATE utf8mb4_general_ci,
  `count` int(11) NOT NULL,
  `Voucher` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MusicUser`
--

LOCK TABLES `MusicUser` WRITE;
/*!40000 ALTER TABLE `MusicUser` DISABLE KEYS */;
INSERT INTO `MusicUser` VALUES (12,'hello,vacation,susu,sujin',1,'Unlimited listening');
/*!40000 ALTER TABLE `MusicUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MyList`
--

DROP TABLE IF EXISTS `MyList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MyList` (
  `id` int(11) NOT NULL,
  `music_id` int(11) NOT NULL,
  `myList_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MyList`
--

LOCK TABLES `MyList` WRITE;
/*!40000 ALTER TABLE `MyList` DISABLE KEYS */;
INSERT INTO `MyList` VALUES (12,1,'sujin');
/*!40000 ALTER TABLE `MyList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecentMusic`
--

DROP TABLE IF EXISTS `RecentMusic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `RecentMusic` (
  `id` int(11) NOT NULL,
  `music_id` int(11) NOT NULL,
  `count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecentMusic`
--

LOCK TABLES `RecentMusic` WRITE;
/*!40000 ALTER TABLE `RecentMusic` DISABLE KEYS */;
INSERT INTO `RecentMusic` VALUES (12,1,0);
/*!40000 ALTER TABLE `RecentMusic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `pw` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'su','1','1','1'),(12,'su','12','10','122'),(123,'123','123','123','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-19 17:40:20
