/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 80026
Source Host           : 127.0.0.1:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2023-01-27 12:04:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dorm
-- ----------------------------
DROP TABLE IF EXISTS `dorm`;
CREATE TABLE `dorm` (
  `id` int NOT NULL AUTO_INCREMENT,
  `louyu_id` int DEFAULT NULL,
  `room_name` int DEFAULT NULL,
  `room_number` int DEFAULT NULL,
  `room_spare` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `drom_louyu_id` (`louyu_id`),
  KEY `room_name` (`room_name`),
  CONSTRAINT `drom_louyu_id` FOREIGN KEY (`louyu_id`) REFERENCES `louyu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of dorm
-- ----------------------------
INSERT INTO `dorm` VALUES ('1', '2', '101', '4', '1');
INSERT INTO `dorm` VALUES ('2', '3', '101', '4', '1');

-- ----------------------------
-- Table structure for louyu
-- ----------------------------
DROP TABLE IF EXISTS `louyu`;
CREATE TABLE `louyu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `brief` varchar(255) DEFAULT NULL,
  `gender` char(2) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `louyu_user_id` (`user_id`),
  CONSTRAINT `louyu_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of louyu
-- ----------------------------
INSERT INTO `louyu` VALUES ('2', '18栋', '政法学院宿舍楼', '女', '2');
INSERT INTO `louyu` VALUES ('3', '23栋', '政法学院宿舍楼', '男', '4');
INSERT INTO `louyu` VALUES ('4', '7栋', '林学院宿舍楼', '男', '17');
INSERT INTO `louyu` VALUES ('5', '16栋', '体育学院宿舍楼', '男', '17');
INSERT INTO `louyu` VALUES ('6', '29栋', '商学院宿舍楼', '女', '18');
INSERT INTO `louyu` VALUES ('7', '5栋', '林学院宿舍楼', '女', '26');
INSERT INTO `louyu` VALUES ('8', '9栋', '体育学院宿舍楼', '女', '22');
INSERT INTO `louyu` VALUES ('9', '14栋', '商学院宿舍楼', '男', '25');
INSERT INTO `louyu` VALUES ('11', '98栋', '土木学院宿舍楼', '女', '23');
INSERT INTO `louyu` VALUES ('12', '51栋', '航天学院宿舍楼', '男', '18');

-- ----------------------------
-- Table structure for queqing
-- ----------------------------
DROP TABLE IF EXISTS `queqing`;
CREATE TABLE `queqing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `louyu_id` int DEFAULT NULL,
  `dorm_id` int DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of queqing
-- ----------------------------
INSERT INTO `queqing` VALUES ('1', '1', '101', '王伟', '回家', '2', '2022-10-01 00:00:00');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_number` varchar(30) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `gender` char(2) DEFAULT NULL,
  `dorm_id` int DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_number` (`student_number`),
  KEY `student_dorm_id` (`dorm_id`),
  CONSTRAINT `student_dorm_id` FOREIGN KEY (`dorm_id`) REFERENCES `dorm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '0001', '王伟', '男', '1', '1', '2022-09-01 00:00:00');
INSERT INTO `student` VALUES ('2', '0002', '江轻怡', '女', '2', '1', '2022-09-01 00:00:00');
INSERT INTO `student` VALUES ('3', '0003', '黄化', '男', '1', '1', '2022-09-01 00:00:00');
INSERT INTO `student` VALUES ('4', '0004', '李丽', '女', '2', '1', '2022-09-01 00:00:00');
INSERT INTO `student` VALUES ('5', '0005', '张启', '男', '1', '1', '2022-09-01 00:00:00');
INSERT INTO `student` VALUES ('6', '0006', '叶飘缪', '女', '2', '1', '2022-09-01 00:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'abc', '123', '张三', '男', '13576579065', '2');
INSERT INTO `user` VALUES ('2', 'ad32', '777', '紫菀', '女', '17947385938', '1');
INSERT INTO `user` VALUES ('4', 'aaa', 'a123', '玉美丽', '女', '13487046392', '1');
INSERT INTO `user` VALUES ('5', '123', '123', '江凡', '男', '18746938093', '1');
INSERT INTO `user` VALUES ('15', '520', '123', '夏晴', '女', '17823836035', '1');
INSERT INTO `user` VALUES ('17', '857', 'a125', '林九', '男', '13475935013', '1');
INSERT INTO `user` VALUES ('18', 'ufc', '123', '泰森', '男', '15849647835', '1');
INSERT INTO `user` VALUES ('19', 'jhg', 'ad23', '常媚', '女', '15873597026', '1');
INSERT INTO `user` VALUES ('22', '13sdj', '900', '君梓偌', '女', '17834822303', '1');
INSERT INTO `user` VALUES ('23', '23423', '123', '傲娇', '女', '15434534506', '1');
INSERT INTO `user` VALUES ('25', 'jb323', '123', '吊炸天', '男', '16849038901', '1');
INSERT INTO `user` VALUES ('26', '1314', '213', '宫晴雪', '男', '15689037589', '1');
