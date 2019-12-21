/*
 Navicat Premium Data Transfer

 Source Server         : localmysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : springcloud

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 07/11/2019 16:27:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('12367', '123456', '157875196366160022', '皮蛋粥', 0.01, 2, 'http://xxx.com', '2019-10-26 18:50:01', '2019-10-26 18:50:01');
INSERT INTO `order_detail` VALUES ('1572265264065159067', '1572265249230482564', '157875196366160022', '皮蛋粥', 0.01, 10, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-10-28 20:21:04', '2019-10-28 20:21:04');
INSERT INTO `order_detail` VALUES ('1572422986230763715', '1572422985266920816', '157875196366160022', '皮蛋粥', 0.01, 10, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-10-30 16:09:46', '2019-10-30 16:09:46');
INSERT INTO `order_detail` VALUES ('1572512779604627389', '1572512778632412686', '157875196366160022', '皮蛋粥', 0.01, 10, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-10-31 17:06:19', '2019-10-31 17:06:19');
INSERT INTO `order_detail` VALUES ('1572515825124759977', '1572515806091648987', '157875196366160022', '皮蛋粥', 0.01, 10, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-10-31 17:57:05', '2019-10-31 17:57:05');
INSERT INTO `order_detail` VALUES ('1572765129269206406', '1572765129256929590', '157875196366160022', '皮蛋粥', 0.01, 10, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-11-03 15:12:09', '2019-11-03 15:12:09');
INSERT INTO `order_detail` VALUES ('1572766160158193310', '1572766160143206054', '157875196366160022', '皮蛋粥', 0.01, 10, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-11-03 15:29:20', '2019-11-03 15:29:20');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_buyer_openid`(`buyer_openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1234567', '师兄', '1886131241241', '慕课网总部', '1101110', 2.50, 0, 0, '2019-10-26 18:47:15', '2019-10-26 18:47:15');
INSERT INTO `order_master` VALUES ('1572230177079647461', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 10:36:17', '2019-10-28 10:36:17');
INSERT INTO `order_master` VALUES ('1572230188817835247', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 10:36:28', '2019-10-28 10:36:28');
INSERT INTO `order_master` VALUES ('1572264704845364768', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 20:11:46', '2019-10-28 20:11:46');
INSERT INTO `order_master` VALUES ('1572264778893301261', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 20:12:58', '2019-10-28 20:12:58');
INSERT INTO `order_master` VALUES ('1572264797691194132', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 20:13:17', '2019-10-28 20:13:17');
INSERT INTO `order_master` VALUES ('1572265002884169673', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 20:16:42', '2019-10-28 20:16:42');
INSERT INTO `order_master` VALUES ('1572265034382786927', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-28 20:18:24', '2019-10-28 20:18:24');
INSERT INTO `order_master` VALUES ('1572265249230482564', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 1, 0, '2019-10-28 20:21:04', '2019-11-03 15:29:04');
INSERT INTO `order_master` VALUES ('1572422985266920816', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-30 16:09:46', '2019-10-30 16:09:46');
INSERT INTO `order_master` VALUES ('1572512778632412686', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-10-31 17:06:19', '2019-10-31 17:06:19');
INSERT INTO `order_master` VALUES ('1572765129256929590', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-11-03 15:12:09', '2019-11-03 15:12:09');
INSERT INTO `order_master` VALUES ('1572766160143206054', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5499.00, 0, 0, '2019-11-03 15:29:20', '2019-11-03 15:29:20');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `uqe_category_type`(`category_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '热榜', 1, '2017-03-28 16:40:22', '2019-10-26 16:08:28');
INSERT INTO `product_category` VALUES (2, '好吃的', 2, '2017-03-14 17:38:46', '2019-10-26 16:08:32');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) NULL DEFAULT 0 COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('157875196366160022', '皮蛋粥', 0.01, 12860, '好吃的皮蛋粥', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', 0, 1, '2017-03-28 19:39:15', '2019-10-31 17:56:40');
INSERT INTO `product_info` VALUES ('157875227953464068', '慕斯蛋糕', 10.90, 18300, '美味爽口', '//fuss10.elemecdn.com/9/93/91994e8456818dfe7b0bd95f10a50jpeg.jpeg', 1, 1, '2017-03-28 19:35:54', '2019-10-31 17:56:40');
INSERT INTO `product_info` VALUES ('164103465734242707', '蜜汁鸡翅', 0.02, 98200, '好吃', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', 0, 1, '2017-03-30 17:11:56', '2019-10-31 17:56:40');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '微信openid',
  `role` tinyint(1) NOT NULL COMMENT '1买家2卖家',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '', '', 'abc', 1, '2019-11-03 09:00:52', '2019-11-03 09:00:52');
INSERT INTO `user_info` VALUES ('2', '', '', 'xyz', 2, '2019-11-03 09:00:58', '2019-11-03 09:00:58');

SET FOREIGN_KEY_CHECKS = 1;
