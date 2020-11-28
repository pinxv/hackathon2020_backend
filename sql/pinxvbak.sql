-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: pinxv
-- ------------------------------------------------------
-- Server version	5.7.31-log

create database if not exists pinxv;
use pinxv;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `HighRiskArea`
--

DROP TABLE IF EXISTS `HighRiskArea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HighRiskArea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `riskLevelId` int(11) DEFAULT NULL,
  `area` int(11) DEFAULT NULL COMMENT '风险地区名称',
  `latitude` float DEFAULT NULL COMMENT '纬度',
  `longitude` float DEFAULT NULL COMMENT '经度',
  `riskLevel` int(11) DEFAULT NULL COMMENT '风险等级',
  PRIMARY KEY (`id`),
  KEY `riskLevelId_fk` (`riskLevelId`),
  CONSTRAINT `riskLevelId_fk` FOREIGN KEY (`riskLevelId`) REFERENCES `RiskLevel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HighRiskArea`
--

LOCK TABLES `HighRiskArea` WRITE;
/*!40000 ALTER TABLE `HighRiskArea` DISABLE KEYS */;
/*!40000 ALTER TABLE `HighRiskArea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RiskLevel`
--

DROP TABLE IF EXISTS `RiskLevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RiskLevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(60) NOT NULL,
  `city` varchar(60) NOT NULL,
  `block` varchar(60) NOT NULL,
  `latitude` float DEFAULT NULL COMMENT '纬度',
  `longitude` float DEFAULT NULL COMMENT '经度',
  `riskLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RiskLevel`
--

LOCK TABLES `RiskLevel` WRITE;
/*!40000 ALTER TABLE `RiskLevel` DISABLE KEYS */;
/*!40000 ALTER TABLE `RiskLevel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-28 11:02:29
