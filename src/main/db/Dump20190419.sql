CREATE DATABASE  IF NOT EXISTS `mytest` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `mytest`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: mytest
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `t_grade`
--

DROP TABLE IF EXISTS `t_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_grade` (
  `idt` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `grade` int(11) NOT NULL COMMENT '成绩',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updata_date` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT '1' COMMENT '用户帐号状态，0：注销，1：正常，2：冻结',
  `special_id` int(11) NOT NULL,
  PRIMARY KEY (`idt`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_grade`
--

LOCK TABLES `t_grade` WRITE;
/*!40000 ALTER TABLE `t_grade` DISABLE KEYS */;
INSERT INTO `t_grade` VALUES (1,5,1111,'2019-04-17 17:48:57',NULL,1,0),(2,7,34,'2019-04-17 17:49:18',NULL,1,0),(3,8,3434,'2019-04-17 17:49:27',NULL,1,0);
/*!40000 ALTER TABLE `t_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_questions`
--

DROP TABLE IF EXISTS `t_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `special_id` int(11) NOT NULL COMMENT '专题id',
  `question` varchar(255) NOT NULL COMMENT '问题，以【】表示需填内容，例：选 择题：中国最冷莫的城市是和最大的城市是【】；填空题：中国最冷莫的城市是【】，最大的城市是【】',
  `answer` varchar(255) NOT NULL COMMENT '答案，json形式,例：选择题：[“a”,”c”]填空题[“中国”,”上海”]',
  `status` int(11) NOT NULL DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '题型 ：1选择，2填空',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_questions`
--

LOCK TABLES `t_questions` WRITE;
/*!40000 ALTER TABLE `t_questions` DISABLE KEYS */;
INSERT INTO `t_questions` VALUES (1,1,'lslslslls','lslslsl',1,NULL,NULL,1),(5,1,'wewewewe','wewewewewe',1,NULL,NULL,2),(6,1,'fsdsdfsfsdf','sdfsdfsfs',1,NULL,NULL,1),(7,2,'ghghghghgh','ghghghg',0,NULL,NULL,1);
/*!40000 ALTER TABLE `t_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_special`
--

DROP TABLE IF EXISTS `t_special`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_special` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `special_name` varchar(255) NOT NULL COMMENT '专题名',
  `special_des` varchar(255) DEFAULT NULL COMMENT '专题介绍',
  `test_time` int(11) NOT NULL DEFAULT '600' COMMENT '考试时间，单位秒',
  `count` int(11) NOT NULL DEFAULT '10' COMMENT '专题里有多少道考题',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用，默认为可用：1，不可用：0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `questions` varchar(255) DEFAULT NULL COMMENT '归类到此专题的考题，只有在type为auto时才起效',
  `type` int(11) DEFAULT '0' COMMENT '是否为自动（随机）选题组，当设为1时为自动，此时questions不能为空，否则不起效；默为0为随机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_special`
--

LOCK TABLES `t_special` WRITE;
/*!40000 ALTER TABLE `t_special` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_special` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '用户帐号状态，0：注销，1：正常，2：冻结',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `phone_number` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '手机号',
  `role` varchar(45) NOT NULL COMMENT '用户角色，只有admin才可以管理数据，其他为consumer',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (5,'123','123','123',1,'2018-05-28 16:55:22','2019-04-18 13:37:42','123','admin'),(7,'12344','123','777777',1,'2018-05-28 18:12:42','2019-04-18 13:37:42','1234','consumer'),(8,NULL,'11111',NULL,1,'2019-04-17 16:52:51','2019-04-18 13:37:42','11111','consumer');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mytest'
--

--
-- Dumping routines for database 'mytest'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-19 10:57:18
