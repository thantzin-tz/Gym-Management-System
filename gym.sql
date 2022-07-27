-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: gym
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `equipment_id` int NOT NULL AUTO_INCREMENT,
  `equip_name` varchar(45) NOT NULL,
  `equip_condition` varchar(45) NOT NULL,
  `last_maintainDate` date NOT NULL,
  `next_maintainDate` date NOT NULL,
  `staff_id` int NOT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `FK_staff_equipment_idx` (`staff_id`),
  CONSTRAINT `FK_staff_equipment` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'Leg Press Machine','good','2022-07-23','2023-07-08',1),(2,'Leg Press Machine','good','2022-07-27','2023-07-29',1),(3,'Space Machine','Good','2022-07-25','2022-12-31',7);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `gender` varchar(45) NOT NULL,
  `weight` double NOT NULL,
  `height` double NOT NULL,
  `bmi` double NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'Yu Ya',18,'N0.93,MaHarMyaing Street,Sanchaung,Yangon.','09789396294','2022-07-23','Female',50,162,19.05),(2,'Nay Chi',23,'No.26,Shangone Street,Sanchaung Township Yangon.','09456249635','2022-07-23','Female',56,156,23.01),(3,'Chit Su',26,'No.45,Pathein Street,Sanchaung','09455999546','2022-07-23','Female',65,159,25.71),(4,'Sai Nyan Lin',28,'No.595,Pyay Road, Sanchaung,Yangon','09265999256','2022-07-23','Male',60,170,20.76),(5,'Nyi Nyi',20,'No.2,ChanThar Street, Sanchaung,Yangon','09444595369','2022-07-23','Male',45,165,16.53),(6,'Thu Kha Linn',35,'No.428,Ma Po Street, Sanchaung,Yangon.','09262493659','2022-07-23','Male',62,175,20.24),(7,'Su Thadar',32,'No.789,Pyay Road, Kamaryut,Yangon','09962369242','2022-07-23','Female',60,145,28.54),(8,'Linn Myat Htoo',32,'No.46,Myint Mo Street,Sanchaung,Yangon.','09456987429','2022-07-23','Male',54,170,18.69),(9,'Ye Yint Maung',19,'No.6,Sanchaung Street,Sanchaung ,Yangon.','09429596269','2022-07-23','Male',50,170,17.3),(10,'Min Min',35,'Yangon','09854578444','2022-07-25','Male',55,160,21.48);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `attendance` varchar(45) NOT NULL,
  `reportDate` date NOT NULL,
  `reportWeight` double NOT NULL DEFAULT '0',
  `reportHeight` double NOT NULL DEFAULT '0',
  `reportBMI` double NOT NULL DEFAULT '0',
  `remark` varchar(45) NOT NULL DEFAULT 'Absent',
  `scheduledMember_id` int NOT NULL,
  `workoutPlan_id` int NOT NULL,
  `playHour` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`report_id`),
  KEY `fk_scheduledmember_report_idx` (`scheduledMember_id`),
  KEY `fk_workoutplan_report_idx` (`workoutPlan_id`),
  CONSTRAINT `fk_scheduledmember_report` FOREIGN KEY (`scheduledMember_id`) REFERENCES `scheduledmember` (`scheduledMember_id`),
  CONSTRAINT `fk_workoutplan_report` FOREIGN KEY (`workoutPlan_id`) REFERENCES `workoutplan` (`workoutPlan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'0','2022-07-24',45,165,16.53,'Attend',3,1,3),(2,'0','2022-07-25',0,0,0,'Absent',4,2,0),(4,'0','2022-07-25',50,140,25.51,'Attend',1,2,4),(5,'0','2022-07-25',55,160,21.48,'Attend',43,1,3);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `schedule_name` varchar(255) NOT NULL,
  `time` varchar(45) NOT NULL,
  `typeOfBody` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `fees` double NOT NULL,
  `staff_id` int NOT NULL,
  `schedule_type` varchar(45) NOT NULL,
  `availableMember` int NOT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `fk_staff_schedule_idx` (`staff_id`),
  CONSTRAINT `FK_staff_schedule` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'Transform Your Body Shape','3 Hours','Over Weight','Female',45000,5,'Ordinary',1),(2,'FIRM BUTT','3 Hours','Normal','Female',60000,6,'Special',15),(3,' ABS Challenge','3 Hours','Normal','Female',90000,3,'Special',15),(4,'Flat Tummpy Exercise','3 Hours','Over Weight','Female',70000,6,'Special',1),(5,'Level One Workout For Beginner For Male','2 Hours','Over Weight','Male',45000,5,'Ordinary',50),(6,'Level One Workout for Beginner for Female','2 Hours','Over Weight','Female',45000,6,'Ordinary',50),(7,'Get Skinny in a Week','3 Hours','Normal','Female',70000,6,'Special',0),(8,'Thirty Day Bubble Butt Squat Challenge','3 Hours','Normal','Female',100000,3,'Special',12),(9,'circuit program','3 Hours','Normal','Male',70000,5,'Special',14);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduledmember`
--

DROP TABLE IF EXISTS `scheduledmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scheduledmember` (
  `scheduledMember_id` int NOT NULL AUTO_INCREMENT,
  `schedule_id` int NOT NULL,
  `member_id` int NOT NULL,
  `joinDate` date NOT NULL,
  `expireDate` date NOT NULL,
  `dayLeft` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`scheduledMember_id`),
  KEY `FK_schedule_scheduledMember_idx` (`schedule_id`),
  KEY `FK_member_scheduledMember_idx` (`member_id`),
  CONSTRAINT `FK_member_scheduledMember` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK_schedule_scheduledMember` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduledmember`
--

LOCK TABLES `scheduledmember` WRITE;
/*!40000 ALTER TABLE `scheduledmember` DISABLE KEYS */;
INSERT INTO `scheduledmember` VALUES (1,3,1,'2022-07-23','2022-08-23',26),(3,5,5,'2022-07-23','2022-07-30',2),(4,2,2,'2022-07-23','2022-08-20',23),(43,7,7,'2022-07-25','2022-07-31',3),(45,3,6,'2022-07-25','2022-07-30',2);
/*!40000 ALTER TABLE `scheduledmember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salary` double NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_id_UNIQUE` (`staff_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Yin Yin',23,'09429394959','No.42,Kamaryut,Yangon','yinyin26@gmail.com','Female','Admin','yinyin','yin123456',500000,1),(2,'Kyi Le Khaing',29,'09264959697','No.95,Dagon,Yangon','kyile29@gmail.com','Female','Admin','kyi','kyi12345',500000,1),(3,'Yone Lay',25,'09789456239','No.74,Shwe Gone Daing, Yangon','yonelay@gmail.com','Male','Trainer',NULL,NULL,650000,1),(4,'Tin Tun Aung',28,'09456393624','No.6,Myaung Mya Street ,Sanchaung','tintun28@gmail.com','Male','Trainer',NULL,NULL,700000,1),(5,'Thun Thet Maung',22,'09696239452','No.39,Yankin Street,Yankin,Yangon.','thun22@gmail.com','Male','Trainer',NULL,NULL,400000,1),(6,'Barani Aung',29,'09747178964','No.9,TarMwe Street,TarMwe,Yangon','barani29@gmail.com','Female','Trainer',NULL,NULL,350000,1),(7,'Thant Zin',25,'0987347367','Yangon','thant@gmail.com','Male','Admin','thant','123',500000,1),(8,'Thuzar Lwin',27,'09839489384','Yangon','tzl@gmail.com','Female','Admin','thuzar','123',500000,1),(9,'Thuzar ',34,'09049384938','Yangon','thua@gmail.com','Female','Trainer','','',500000,0),(10,'Cho Cho',34,'0983483843','Mdy','cho@gmail.com','Female','Admin','cho','123',4500000,1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout`
--

DROP TABLE IF EXISTS `workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout` (
  `workout_id` int NOT NULL AUTO_INCREMENT,
  `workout_name` varchar(255) NOT NULL,
  `bodypart_name` varchar(75) NOT NULL,
  PRIMARY KEY (`workout_id`),
  UNIQUE KEY `workout_name_UNIQUE` (`workout_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout`
--

LOCK TABLES `workout` WRITE;
/*!40000 ALTER TABLE `workout` DISABLE KEYS */;
INSERT INTO `workout` VALUES (1,'Parallel Bar Dips','Chest'),(2,'Dumbbel Seated Shoulder Press','Shoulder'),(3,'Renegade Rows','Knees'),(4,'Leg Raises','Leg'),(5,'Alternatating Side Plank','Arms'),(7,'Plank','Arms'),(9,'Planks','Arms');
/*!40000 ALTER TABLE `workout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workoutplan`
--

DROP TABLE IF EXISTS `workoutplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workoutplan` (
  `workoutPlan_id` int NOT NULL AUTO_INCREMENT,
  `workout_id` int NOT NULL,
  `setTime` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `level` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`workoutPlan_id`),
  KEY `workout_id_idx` (`workout_id`),
  CONSTRAINT `workout_id` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`workout_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workoutplan`
--

LOCK TABLES `workoutplan` WRITE;
/*!40000 ALTER TABLE `workoutplan` DISABLE KEYS */;
INSERT INTO `workoutplan` VALUES (1,1,'6reps x 4sets','Male','Beginner','To Cover Wide Chest'),(2,2,'10reps x 3sets','Female','Intermediate','To Beauty'),(3,3,'5reps x 5sets','Male','Beginner','To Strength'),(4,9,'55','Male','Beginner','For Strength for arms');
/*!40000 ALTER TABLE `workoutplan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-27 19:59:25
