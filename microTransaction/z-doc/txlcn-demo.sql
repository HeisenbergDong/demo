/*
Navicat MySQL Data Transfer

Source Server         : 81
Source Server Version : 50505
Source Host           : 192.168.1.81:3306
Source Database       : txlcn-demo

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-05-07 13:22:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_demo
-- ----------------------------
DROP TABLE IF EXISTS `t_demo`;
CREATE TABLE `t_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kid` varchar(45) DEFAULT NULL,
  `group_id` varchar(64) DEFAULT NULL,
  `demo_field` varchar(255) DEFAULT NULL,
  `app_name` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=744 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_demo
-- ----------------------------
INSERT INTO `t_demo` VALUES ('717', 'e905d7e29537', 'e905d796e537', 'the-value', 'txlcn-demo-spring-service-b:12002', '2019-03-18 09:45:10');
INSERT INTO `t_demo` VALUES ('720', 'e905d62ef537', 'e905d796e537', 'the-value', 'txlcn-demo-spring-service-c:12003', '2019-03-18 09:45:10');
INSERT INTO `t_demo` VALUES ('723', 'e905d675e537', 'e905d796e537', 'the-value', 'txlcn-demo-spring-service-a:12011', '2019-03-18 17:45:10');

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) NOT NULL,
  `unit_id` varchar(32) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `create_time` varchar(30) NOT NULL,
  `app_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logger
-- ----------------------------
INSERT INTO `t_logger` VALUES ('187', 'd4b79406a537', '2185a14f8734b5b1e64116714c04e3c4', 'Transaction', 'business code error', '2019-03-14 19:07:18 026', 'txlcn-demo-spring-service-a:12011');
INSERT INTO `t_logger` VALUES ('190', 'd4b7ce85a537', '2185a14f8734b5b1e64116714c04e3c4', 'Transaction', 'business code error', '2019-03-14 19:07:32 959', 'txlcn-demo-spring-service-a:12011');
INSERT INTO `t_logger` VALUES ('193', 'd7ca8bf2a537', '2185a14f8734b5b1e64116714c04e3c4', 'Transaction', 'business code error', '2019-03-15 09:26:54 055', 'txlcn-demo-spring-service-a:12011');
INSERT INTO `t_logger` VALUES ('198', 'e90601abe537', '2185a14f8734b5b1e64116714c04e3c4', 'Transaction', 'business code error', '2019-03-18 17:45:23 432', 'txlcn-demo-spring-service-a:12011');
INSERT INTO `t_logger` VALUES ('201', 'e909198ee537', '2185a14f8734b5b1e64116714c04e3c4', 'Transaction', 'business code error', '2019-03-18 17:48:56 257', 'txlcn-demo-spring-service-a:12011');
