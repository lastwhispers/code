/*
 Navicat Premium Data Transfer

 Source Server         : localmysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : orm

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 07/11/2019 16:32:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `ID` int(11) NOT NULL COMMENT '编号',
  `UID` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `MONEY` double NULL DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_Reference_8`(`UID`) USING BTREE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`UID`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 41, 1000);
INSERT INTO `account` VALUES (2, 45, 1000);
INSERT INTO `account` VALUES (3, 41, 2000);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` int(11) NOT NULL COMMENT '编号',
  `ROLE_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '院长', '管理整个学院');
INSERT INTO `role` VALUES (2, '总裁', '管理整个公司');
INSERT INTO `role` VALUES (3, '校长', '管理整个学校');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (41, '张三', '2018-02-27 17:47:08', '男', '北京市海淀区');
INSERT INTO `user` VALUES (42, '小二王', '2018-03-02 15:09:37', '女', '北京金燕龙');
INSERT INTO `user` VALUES (43, '小二王', '2018-03-04 11:34:34', '女', '北京金燕龙');
INSERT INTO `user` VALUES (45, '45', '2019-10-18 16:07:30', '女', '45');
INSERT INTO `user` VALUES (46, '老王', '2018-03-07 17:37:26', '男', '北京');
INSERT INTO `user` VALUES (48, '小马宝莉', '2018-03-08 11:44:00', '女', '北京修正');
INSERT INTO `user` VALUES (49, '二王', '2019-10-17 16:26:26', '男', '北京市顺义区');
INSERT INTO `user` VALUES (50, 'modify User property', '2019-10-17 16:33:01', '男', '北京市顺义区');
INSERT INTO `user` VALUES (51, 'modify User property', '2019-10-17 16:36:36', '男', '北京市顺义区');
INSERT INTO `user` VALUES (53, 'modify User property', '2019-10-17 16:38:46', '男', '北京市顺义区');
INSERT INTO `user` VALUES (54, 'modify User property', '2019-10-17 16:39:20', '男', '北京市顺义区');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `UID` int(11) NOT NULL COMMENT '用户编号',
  `RID` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`UID`, `RID`) USING BTREE,
  INDEX `FK_Reference_10`(`RID`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (41, 1);
INSERT INTO `user_role` VALUES (45, 1);
INSERT INTO `user_role` VALUES (41, 2);

SET FOREIGN_KEY_CHECKS = 1;
