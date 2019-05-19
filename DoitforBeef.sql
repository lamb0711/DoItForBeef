-- MySQL dump 10.13  Distrib 8.0.15, for osx10.14 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `dibs`
--

DROP TABLE IF EXISTS `dibs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dibs` (
  `id` int(10) NOT NULL,
  `productId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dibs`
--

LOCK TABLES `dibs` WRITE;
/*!40000 ALTER TABLE `dibs` DISABLE KEYS */;
/*!40000 ALTER TABLE `dibs` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!40000 ALTER TABLE `FavoriteList` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `MyList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordering`
--

DROP TABLE IF EXISTS `ordering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ordering` (
  `UserId` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `number` int(10) NOT NULL,
  `address` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordering`
--

LOCK TABLES `ordering` WRITE;
/*!40000 ALTER TABLE `ordering` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payment` (
  `id` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `numbers` int(10) NOT NULL,
  `cardNumber` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `cardPassword` varchar(10) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `productId` int(10) NOT NULL,
  `productName` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `price` int(20) NOT NULL,
  `type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `brand` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `inventory` int(10) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mall` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'NIKE AIR MAX',87300,'SHOES','NIKE',12,'2019-04-12 03:12:12','ShoeMarker'),(2,'ZX 500',135900,'SHOES','ADIDAS',10,'2018-06-18 16:12:14','PRIMIUM'),(3,'NITE JOGGER W D',99180,'SHOES','ADIDAS',2,'2019-04-07 17:18:12','GMARKET'),(4,'NIKE POLO',75000,'T-Shirt','NIKE',101,'2019-02-11 20:15:10','GMARKET'),(5,'GIORDANO-PIKE',14000,'T-Shirt','GIORDANO',34,'2014-10-05 04:04:20','TMON'),(6,'MAGNUM',5000,'ICE_Cream','BINGRE',24,'2019-05-12 02:02:32','TMON'),(7,'Haggen-Dazs ,minicup',3900,'ICE_Cream','HAAGEN_DAZS',54,'2017-01-11 16:23:21','COUPANG');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `context` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `likes` int(11) NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (21300126,1,'1','What is DB?',0),(21200111,2,'3','I love beef, do you love beef?',1),(21300126,3,'2','I HEARD THAT HANDONG GLOBAL UNIVERSITY is very good school!!!',4),(21300126,4,'2','I HEARD THAT HANDONG GLOBAL UNIVERSITY is very good school!!!',0),(21300126,5,'EDUCATION','I HEARD THAT HANDONG GLOBAL UNIVERSITY is very good school!!!',1),(21300126,6,'GAME','I love LOL what do you like?',1),(21300126,7,'LIFE','What is the advantage of coffee?',2),(21300126,8,'ENTERTAINMENT','I love soccer',0);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `RecentMusic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reply` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `reply` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `likereply` int(11) DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (21300126,7,1,'Coffee contains lots of caffeine',0);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShoppingCart`
--

DROP TABLE IF EXISTS `ShoppingCart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ShoppingCart` (
  `id` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `number` int(10) NOT NULL,
  `validity` int(10) NOT NULL DEFAULT (1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShoppingCart`
--

LOCK TABLES `ShoppingCart` WRITE;
/*!40000 ALTER TABLE `ShoppingCart` DISABLE KEYS */;
/*!40000 ALTER TABLE `ShoppingCart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `user_pw` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `user_phone` int(11) NOT NULL,
  `user_email` varchar(30) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21300126,'seungwoo','1234',1031011797,'21300126@handong.edu'),(21300129,'kimkim','12345678',1031112222,'hi@naver.com'),(21300111,'lol','12341234',1011112222,'hi@gmail.com'),(21300121,'sun','1111',1011112345,'hi@gmail.com');
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

-- Dump completed on 2019-05-19 19:33:56
