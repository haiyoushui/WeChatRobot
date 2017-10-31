/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : isss

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-10-31 10:31:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_face_pepleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_face_pepleinfo`;
CREATE TABLE `tbl_face_pepleinfo` (
  `uid` char(30) NOT NULL,
  `groupid` varchar(255) DEFAULT NULL,
  `uname` char(25) NOT NULL,
  `gender` char(10) DEFAULT NULL,
  `birthday` char(30) DEFAULT NULL,
  `uinfo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `idcardaddress` varchar(255) DEFAULT NULL,
  `ethnic` varchar(30) DEFAULT NULL,
  `idcardid` char(30) DEFAULT NULL,
  `time` char(30) DEFAULT NULL,
  `IMEI` varchar(30) DEFAULT NULL,
  `faceliveness` varchar(30) DEFAULT NULL,
  `picPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_face_pepleinfo
-- ----------------------------
INSERT INTO `tbl_face_pepleinfo` VALUES ('371427199206110430', 'ISSS_2017_T', '刘昊', '男', '1992年06月11日', '你好', '山东省德州市夏津县', '山东省德州市夏津县', 'null', '371427199206110430', '20171031094730', '862979038887230', null, 'H:\\ISSS\\FaceAdd\\2017-10-31\\371427199206110430_862979038887230_20171031094725.jpeg');

-- ----------------------------
-- Table structure for `tbl_phone_allowlicense`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_phone_allowlicense`;
CREATE TABLE `tbl_phone_allowlicense` (
  `ID` int(99) NOT NULL AUTO_INCREMENT,
  `IMEI` char(99) NOT NULL,
  `isallow` int(11) NOT NULL DEFAULT '1',
  `function1` int(11) DEFAULT '1',
  `function2` int(11) DEFAULT '1',
  `function3` int(11) DEFAULT '1',
  `function4` int(11) DEFAULT '1',
  `function5` int(11) DEFAULT '1',
  `function6` int(11) DEFAULT '1',
  `function7` int(11) DEFAULT '1',
  `function8` int(11) DEFAULT '1',
  `function9` int(11) DEFAULT '1',
  `starttime` char(25) DEFAULT NULL,
  `endtime` char(25) DEFAULT NULL,
  `remark` char(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_phone_allowlicense
-- ----------------------------
INSERT INTO `tbl_phone_allowlicense` VALUES ('12', '862979038887230', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2017-10-31 10:26:18', '2017-11-01 10:26:18', '2017-10-31 10:26:18');

-- ----------------------------
-- Table structure for `tbl_phone_message`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_phone_message`;
CREATE TABLE `tbl_phone_message` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `IMEI` char(99) NOT NULL,
  `logintime` varchar(25) NOT NULL,
  `version` char(25) DEFAULT NULL,
  `remark` char(255) DEFAULT NULL,
  `sysVersion` varchar(25) DEFAULT NULL,
  `phoneModel` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_phone_message
-- ----------------------------
INSERT INTO `tbl_phone_message` VALUES ('14', '862979038887230', '2017-10-31 10:26:18', '1.0', '5.1.1', null, 'vivo X7', null);
