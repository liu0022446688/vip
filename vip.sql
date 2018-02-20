/*
MySQL Data Transfer
Source Host: localhost
Source Database: vip
Target Host: localhost
Target Database: vip
Date: 2017/3/20 ����һ ���� 9:17:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE `sys_user` (
  `id` char(32) NOT NULL DEFAULT '',
  `account` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vip_consume
-- ----------------------------
CREATE TABLE `vip_consume` (
  `id` char(32) NOT NULL DEFAULT '',
  `vip_id` char(32) DEFAULT NULL,
  `whens` datetime DEFAULT NULL,
  `orderno` varchar(32) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL COMMENT '消费金额，单元分',
  `operator_id` char(32) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vip_id` (`vip_id`),
  KEY `operator_id` (`operator_id`),
  CONSTRAINT `vip_consume_ibfk_1` FOREIGN KEY (`vip_id`) REFERENCES `vip_detail` (`id`),
  CONSTRAINT `vip_consume_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vip_detail
-- ----------------------------
CREATE TABLE `vip_detail` (
  `id` char(32) NOT NULL DEFAULT '',
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `qq` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `zip` varchar(16) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `rank` tinyint(1) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL COMMENT '消费金额，单位分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vip_rank
-- ----------------------------
CREATE TABLE `vip_rank` (
  `code` tinyint(1) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `needAmount` int(11) DEFAULT NULL,
  `discount` tinyint(4) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '0001', '系统管理员', '1ef523c6b645a65441a91fa80df077c2');
INSERT INTO `vip_consume` VALUES ('1', '1', '2016-02-10 20:42:36', '2016021000002', '5000', '1', '客人很友好', '0');
INSERT INTO `vip_detail` VALUES ('1', '13888888888', '王老五', '1', '40', '10000', '10000@qq.com', '北京中关村xxx路xx号', '2000000', null, '1', '15000');
INSERT INTO `vip_detail` VALUES ('76ada7de690b4188b9b4ceb622b226e4', '13888888887', '张飞飞', '0', '18', '321', '2', '354', '43', '543', '0', '0');
INSERT INTO `vip_rank` VALUES ('1', '普通VIP', '100', '98', '消费满100即可成为普通会员');
INSERT INTO `vip_rank` VALUES ('2', '黄金VIP', '1000', '95', null);
INSERT INTO `vip_rank` VALUES ('3', '白金VIP', '5000', '90', null);
INSERT INTO `vip_rank` VALUES ('4', '钻石VIP', '10000', '85', null);
INSERT INTO `vip_rank` VALUES ('5', '终身VIP', '20000', '80', null);
