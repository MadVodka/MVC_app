-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cars_rent
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price_per_day` double DEFAULT NULL,
  `reg_number` varchar(45) NOT NULL,
  `status` enum('IDLE','IN_USE','IN_MAINTAINANCE','WRITTEN_OFF') NOT NULL DEFAULT 'IDLE',
  `cars_spec_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`cars_spec_id`),
  UNIQUE KEY `regNumber_UNIQUE` (`reg_number`),
  KEY `fk_cars_cars_info1_idx` (`cars_spec_id`),
  CONSTRAINT `fk_cars_cars_info1` FOREIGN KEY (`cars_spec_id`) REFERENCES `cars_specification` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,69,'ла231з','IDLE',3),(2,100,'щц923ц','IDLE',4),(3,50,'со195ч','WRITTEN_OFF',2),(4,80,'кс710у','IDLE',7),(5,62,'ог198п','IDLE',2),(8,55,'лу045с','IN_MAINTAINANCE',15),(9,130,'сс777с','IDLE',1),(10,90,'уи428ц','IN_MAINTAINANCE',5),(11,100,'зч264а','IDLE',4),(12,140,'им423е','IDLE',13);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars_specification`
--

DROP TABLE IF EXISTS `cars_specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars_specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `year_made` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars_specification`
--

LOCK TABLES `cars_specification` WRITE;
/*!40000 ALTER TABLE `cars_specification` DISABLE KEYS */;
INSERT INTO `cars_specification` VALUES (1,'Ford','Mustang',2015),(2,'Hyundai','Solaris',2013),(3,'Hyundai','Solaris',2011),(4,'Audi','A4',2018),(5,'BMW','X1',2015),(7,'Audi','Q3',2014),(9,'Kia','Rio',2018),(11,'Fiat','Albea',2013),(12,'Toyota','Land Cruiser L200',2019),(13,'Mercedes','C-class',2017),(14,'Seat','Leon',2012),(15,'Skoda','Yeti',2010),(16,'Ford','Kuga',2017),(17,'Renault','Duster',2015),(18,'Nissan','Juke',2011);
/*!40000 ALTER TABLE `cars_specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `users_id` int(11) NOT NULL,
  `cars_id` int(11) NOT NULL,
  `status` enum('IN_WAITING','APPROVED','DENIED','CANCELED_BY_USER') NOT NULL DEFAULT 'IN_WAITING',
  `price_per_day` double NOT NULL,
  `total_price` double NOT NULL,
  `add_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_users_idx` (`users_id`),
  KEY `fk_orders_car_fleet1_idx` (`cars_id`),
  CONSTRAINT `fk_orders_car_fleet1` FOREIGN KEY (`cars_id`) REFERENCES `cars` (`id`),
  CONSTRAINT `fk_orders_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2019-08-29','2019-09-01',8,4,'DENIED',80,320,NULL),(3,'2019-08-30','2019-09-06',14,11,'DENIED',100,800,NULL),(6,'2019-09-04','2019-09-10',20,2,'IN_WAITING',100,700,NULL),(7,'2019-09-04','2019-09-10',20,2,'IN_WAITING',100,700,NULL),(8,'2019-09-02','2019-09-07',20,2,'IN_WAITING',72,504,NULL),(9,'2019-11-05','2019-11-19',30,9,'IN_WAITING',0,0,NULL),(10,'2019-11-12','2019-11-24',30,3,'IN_WAITING',0,0,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `second_name` varchar(45) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `status` enum('ACTIVE','BLOCKED','DELETED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'ivan','','Иван','Нифедов','USER','ACTIVE'),(9,'andrew','','Андрей','Леонов','USER','ACTIVE'),(11,'george1','','Георгий','Титов','USER','ACTIVE'),(12,'kill_bill','','Квентин','Тарантино','USER','ACTIVE'),(13,'super_god','',NULL,NULL,'ADMIN','ACTIVE'),(14,'perq777','','Петр','Фишер','USER','ACTIVE'),(15,'rabbit_me','','Роман','Хлебов','USER','BLOCKED'),(16,'real_thug69','','Сергей','Полежайкин','USER','BLOCKED'),(19,'second_manager','',NULL,NULL,'ADMIN','ACTIVE'),(20,'igorIgor','','Игорь','Великий','USER','ACTIVE'),(29,'admin','$2a$10$5jurca86wQlQxQHYDc/kkuqND6NmWWzdK3TWyQhC7ESCLlcxQmUQm','','','ADMIN','ACTIVE'),(30,'first','$2a$10$JLWgD.swgRiV2YG9asGG/.4Rg/Y9ZoaN6/wWrcjQK6lqbngc4lBdG','ÐÐ»ÐµÐ³','ÐÐ¾ÑÐ±Ð°ÑÐµÐ½ÐºÐ¾','USER','ACTIVE'),(31,'second','$2a$10$I0xZYt/O.zB8jgcds.aV3O/6OL2oNEhjQ2noOctjbsSnvK9g2pQZK','','','USER','ACTIVE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cars_rent'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-14  1:58:21
