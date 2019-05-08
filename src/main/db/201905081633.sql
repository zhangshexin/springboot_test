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
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci NOT NULL COMMENT '评论内容',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `statues` int(11) NOT NULL DEFAULT '1' COMMENT '状态：0：不可见，1：可见',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_id` (`u_id`) USING HASH,
  UNIQUE KEY `compay_id` (`company_id`) USING HASH,
  CONSTRAINT `compay_id` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_company`
--

DROP TABLE IF EXISTS `t_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL COMMENT '企业名称',
  `tags` text CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci COMMENT '给企业打标签，例如：好，流氓',
  `info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci NOT NULL COMMENT '公司的简介',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `statues` int(11) NOT NULL DEFAULT '1' COMMENT '状态，0：不可见，1：正常，2：置顶',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '显示的优先级，与statues=2一起使用，优先级越高越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_company`
--

LOCK TABLES `t_company` WRITE;
/*!40000 ALTER TABLE `t_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_company` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_grade`
--

LOCK TABLES `t_grade` WRITE;
/*!40000 ALTER TABLE `t_grade` DISABLE KEYS */;
INSERT INTO `t_grade` VALUES (1,5,1111,'2019-04-17 17:48:57',NULL,1,0),(2,7,34,'2019-04-17 17:49:18',NULL,1,0),(3,8,3434,'2019-04-17 17:49:27',NULL,1,0),(4,7,0,'2019-05-07 15:47:00',NULL,1,5),(5,7,2,'2019-05-08 10:43:54',NULL,1,2),(6,7,5,'2019-05-08 11:04:47',NULL,1,1);
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
  `answer` varchar(255) NOT NULL COMMENT '答案，json形式,例：{“all”:”a,bbbb,ddd,ddd”,”right”:”a”},all对应所有给出的答案，right对应正确答案（可有多个），都以逗号隔开,填空题all无效',
  `status` int(11) NOT NULL DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '题型 ：1选择，2填空',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_questions`
--

LOCK TABLES `t_questions` WRITE;
/*!40000 ALTER TABLE `t_questions` DISABLE KEYS */;
INSERT INTO `t_questions` VALUES (1,1,'lslsls【skskks】lls','{\n	\"all\": \"a,bbbb,ddd,ddd\",\n	\"right\": \"a\"\n}',1,NULL,NULL,1),(5,5,'wewewe【sksk】we','{\n	\"all\": \"a,bbbb,ddd,ddd\",\n	\"right\": \"a\"\n}',1,NULL,NULL,2),(6,1,'fsdsdfsfs【sfsdfs】df','{\n	\"all\": \"a,bbbb,ddd,ddd\",\n	\"right\": \"a\"\n}',1,NULL,NULL,1),(7,2,'中国[sfsfs]hghgh','{\n	\"all\": \"a,bbbb,ddd,ddd\",\n	\"right\": \"a\"\n}',0,'2019-04-28 14:40:44','2019-04-28 14:50:43',1),(8,2,'开始开始开始[sdfsfs]','{\n	\"all\": \"a,bbbb,ddd,ddd\",\n	\"right\": \"a\"\n}',1,'2019-04-28 14:51:26','2019-04-28 14:51:26',2),(9,2,'哈哈哈哈哈哈哈哈【sfsdf】哈哈和','{ 	\"all\": \"a,bbbb,ddd,ddd\", 	\"right\": \"a\" }',1,'2019-04-28 14:52:25','2019-04-28 14:52:25',2),(10,5,'爱与皮与咕咕中【sds】kjl【sfds】民意测验','{ 	\"all\": \"a,bbbb,ddd,ddd\", 	\"right\": \"a\" }',1,'2019-04-28 14:52:25','2019-04-28 14:52:25',1),(11,5,'爱与皮与咕咕中【sds】k2323jl【sfds】民意测2323验','{ 	\"all\": \"a,bbbb,ddd,ddd\", 	\"right\": \"a\" }',1,'2019-04-28 14:52:25','2019-04-28 14:52:25',1),(13,5,'爱与皮与咕咕中【sds】k2rrrr323jl【sfds】民意rrrr测2323验','{ 	\"all\": \"a,bbbb,ddd,ddd\", 	\"right\": \"a\" }',1,'2019-04-28 14:52:25','2019-04-28 14:52:25',1),(14,5,'爱与皮与咕咕fjlsdkfjls中【sds】k2rrrr323jl【sfds】民意rrrr测2323验','{ 	\"all\": \"a,bbbb,ddd,ddd\", 	\"right\": \"a\" }',1,'2019-04-28 14:52:25','2019-04-28 14:52:25',1);
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
  `questions` varchar(255) DEFAULT NULL COMMENT '归类到此专题的考题，只有在type为auto时才起效,格式为考题的id以逗号分隔开',
  `type` int(11) DEFAULT '0' COMMENT '是否为自动（随机）选题组，当设为1时为自动，此时questions不能为空，否则不起效；默为0为随机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_special`
--

LOCK TABLES `t_special` WRITE;
/*!40000 ALTER TABLE `t_special` DISABLE KEYS */;
INSERT INTO `t_special` VALUES (1,'专题1','专题1描述',10,20,1,NULL,NULL,'1，5，9,6，7，8,10,11,13,14',1),(2,'sfsdfdsfsfsdfs','lsjflksjfkldsfjkls',10,10,1,'2019-04-26 15:07:24','2019-04-28 15:53:45','9,',0),(4,'??????????','???????',23,32,0,'2019-04-26 16:38:32','2019-04-26 16:38:32',NULL,0),(5,'沃尔沃热污染物而温柔','认为而沃尔沃而',23,32,1,'2019-04-26 16:40:15','2019-04-26 16:40:15',NULL,0);
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

-- Dump completed on 2019-05-08 16:33:23
