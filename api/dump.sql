CREATE DATABASE  IF NOT EXISTS `rewards` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rewards`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: ec2-13-233-134-142.ap-south-1.compute.amazonaws.com    Database: rewards
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `apikey`
--

DROP TABLE IF EXISTS `apikey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apikey` (
  `_id` int NOT NULL AUTO_INCREMENT,
  `client` varchar(45) NOT NULL,
  `apikey` varchar(500) NOT NULL,
  `isDisabled` int DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apikey`
--

LOCK TABLES `apikey` WRITE;
/*!40000 ALTER TABLE `apikey` DISABLE KEYS */;
INSERT INTO `apikey` VALUES (2,'rewards-pwa-next-server','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZXdhcmRzLXB3YS1uZXh0LXNlcnZlcj09PT09V2VkIEZlYiAyOCAxMzozODoyOSBJU1QgMjAyNCIsImlhdCI6MTcwOTEwNzcwOSwiZXhwIjoyNDk3NTA3NzA5fQ.CwyTLFKehZSzQRVeZQlFrg4u8lZE8eluGNqELUdmmOjT374MNrzepNjceQedqzsgXxP4WObsHhzprw_1EvJfqw',0);
/*!40000 ALTER TABLE `apikey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `shopId` bigint unsigned NOT NULL,
  `userId` bigint NOT NULL,
  `disable` bigint NOT NULL,
  `disableDate` date NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `favorites_shopid_foreign` (`shopId`),
  CONSTRAINT `favorites_shopid_foreign` FOREIGN KEY (`shopId`) REFERENCES `store` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `shopId` bigint unsigned NOT NULL,
  `userId` bigint NOT NULL,
  `reviewTime` timestamp NOT NULL,
  `description` text NOT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `review_shopid_foreign` (`shopId`),
  CONSTRAINT `review_shopid_foreign` FOREIGN KEY (`shopId`) REFERENCES `store` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `totalReviews` int NOT NULL,
  `latitude` decimal(8,2) NOT NULL,
  `longitude` decimal(8,2) NOT NULL,
  `openStatus` enum('OPEN','CLOSED') NOT NULL,
  `storeTimeId` bigint unsigned DEFAULT NULL,
  `storeName` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `wheelchairAccessible` int DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `store_storetimeid_foreign` (`storeTimeId`),
  CONSTRAINT `store_storetimeid_foreign` FOREIGN KEY (`storeTimeId`) REFERENCES `storetime` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (6,50,37.77,-122.42,'OPEN',NULL,'Super Mart',NULL,NULL,NULL,NULL),(7,30,40.71,-74.01,'CLOSED',NULL,'City Grocers',NULL,NULL,NULL,NULL),(8,20,34.05,-118.24,'OPEN',NULL,'Downtown Market',NULL,NULL,NULL,NULL),(9,45,41.88,-87.63,'OPEN',NULL,'Metro Foods',NULL,NULL,NULL,NULL),(10,15,51.51,-0.13,'CLOSED',NULL,'Central Superstore',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeholiday`
--

DROP TABLE IF EXISTS `storeholiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storeholiday` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `storeId` bigint unsigned NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `storeholiday_storeid_foreign` (`storeId`),
  CONSTRAINT `storeholiday_storeid_foreign` FOREIGN KEY (`storeId`) REFERENCES `store` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeholiday`
--

LOCK TABLES `storeholiday` WRITE;
/*!40000 ALTER TABLE `storeholiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeholiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeimagelink`
--

DROP TABLE IF EXISTS `storeimagelink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storeimagelink` (
  `_id` int NOT NULL AUTO_INCREMENT,
  `linktoimage` varchar(200) NOT NULL,
  `imagetype` varchar(45) DEFAULT NULL,
  `storeId` int NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeimagelink`
--

LOCK TABLES `storeimagelink` WRITE;
/*!40000 ALTER TABLE `storeimagelink` DISABLE KEYS */;
INSERT INTO `storeimagelink` VALUES (1,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/6/images/restaurant_688988_restaurant020230608110544.jpg','DISPLAY_PHOTO',6),(2,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/6/images/retro-cafe.jpg','DISPLAY_PHOTO',6),(3,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/6/images/tony-lee-8IKf54pc3qk-unsplash-1.jpg','DISPLAY_PHOTO',6),(4,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/6/logo/coffee-and-cafe-logo-design-inspiration-vector-31020950.jpg','LOGO',6),(5,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/7/images/restaurant_688988_restaurant020230608110544.jpg','DISPLAY_PHOTO',7),(6,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/7/images/retro-cafe.jpg','DISPLAY_PHOTO',7),(7,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/7/images/tony-lee-8IKf54pc3qk-unsplash-1.jpg','DISPLAY_PHOTO',7),(8,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/7/logo/coffee-and-cafe-logo-design-inspiration-vector-31020950.jpg','LOGO',7),(9,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/8/images/restaurant_688988_restaurant020230608110544.jpg','DISPLAY_PHOTO',8),(10,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/8/images/retro-cafe.jpg','DISPLAY_PHOTO',8),(11,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/8/images/tony-lee-8IKf54pc3qk-unsplash-1.jpg','DISPLAY_PHOTO',8),(12,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/8/logo/coffee-and-cafe-logo-design-inspiration-vector-31020950.jpg','LOGO',8),(13,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/9/images/restaurant_688988_restaurant020230608110544.jpg','DISPLAY_PHOTO',9),(14,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/9/images/retro-cafe.jpg','DISPLAY_PHOTO',9),(15,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/9/images/tony-lee-8IKf54pc3qk-unsplash-1.jpg','DISPLAY_PHOTO',9),(16,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/9/logo/coffee-and-cafe-logo-design-inspiration-vector-31020950.jpg','LOGO',9),(17,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/10/images/restaurant_688988_restaurant020230608110544.jpg','DISPLAY_PHOTO',10),(18,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/10/images/retro-cafe.jpg','DISPLAY_PHOTO',10),(19,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/10/images/tony-lee-8IKf54pc3qk-unsplash-1.jpg','DISPLAY_PHOTO',10),(20,'https://rewardsbucket.s3.ap-south-1.amazonaws.com/10/logo/coffee-and-cafe-logo-design-inspiration-vector-31020950.jpg','LOGO',10);
/*!40000 ALTER TABLE `storeimagelink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storetime`
--

DROP TABLE IF EXISTS `storetime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storetime` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `openTime` varchar(255) NOT NULL,
  `closeTime` varchar(255) NOT NULL,
  `offDays` varchar(255) DEFAULT NULL,
  `shopId` bigint DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storetime`
--

LOCK TABLES `storetime` WRITE;
/*!40000 ALTER TABLE `storetime` DISABLE KEYS */;
INSERT INTO `storetime` VALUES (11,'08:00 AM','06:00 PM','Saturday,Sunday',6),(12,'09:30 AM','08:00 PM','Monday',7),(13,'10:00 AM','07:30 PM','Wednesday',8),(14,'07:00 AM','05:30 PM','Thursday',9),(15,'11:00 AM','09:00 PM','Tuesday',10);
/*!40000 ALTER TABLE `storetime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `_id` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `userTypeId` varchar(20) DEFAULT 'CONSUMER',
  `hashedValue` varchar(100) DEFAULT NULL,
  `points` bigint DEFAULT '0',
  PRIMARY KEY (`_id`),
  UNIQUE KEY `user_userid_unique` (`userId`),
  UNIQUE KEY `user_username_unique` (`username`),
  KEY `user_usertypeid_foreign` (`userTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('user1','john_doe','john.doe@example.com',6,'John','Doe','1','JFNDKJSNBCHUWOUAHL',10),('user2','jane_smith','jane.smith@example.com',7,'Jane','Smith','2',NULL,0),('user3','alice_jones','alice.jones@example.com',8,'Alice','Jones','1',NULL,0),('user4','bob_miller','bob.miller@example.com',9,'Bob','Miller','2',NULL,0),('user5','emma_white','emma.white@example.com',10,'Emma','White','1',NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'VENDOR'),(2,'CONSUMER');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendorshoplink`
--

DROP TABLE IF EXISTS `vendorshoplink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendorshoplink` (
  `_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `shopId` bigint unsigned NOT NULL,
  `vendorId` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `vendorshoplink_vendorid_foreign` (`vendorId`),
  KEY `vendorshoplink_shopid_foreign` (`shopId`),
  CONSTRAINT `vendorshoplink_shopid_foreign` FOREIGN KEY (`shopId`) REFERENCES `store` (`_id`),
  CONSTRAINT `vendorshoplink_vendorid_foreign` FOREIGN KEY (`vendorId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendorshoplink`
--

LOCK TABLES `vendorshoplink` WRITE;
/*!40000 ALTER TABLE `vendorshoplink` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendorshoplink` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-01 13:32:45
