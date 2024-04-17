/*
 Navicat Premium Data Transfer

 Source Server         : 8.130.33.86
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 8.130.33.86:3306
 Source Schema         : msblog

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 04/03/2024 17:50:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(10) unsigned NOT NULL COMMENT '主键',
  `permission_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '权限',
  `module_id` int(10) unsigned NOT NULL COMMENT '模块ID',
  `action_id` int(10) unsigned NOT NULL COMMENT '行为ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_permission` (`permission_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (0, 'ROOT', 0, 0);
INSERT INTO `permission` VALUES (1, 'ROLE:INSERT', 1, 1);
INSERT INTO `permission` VALUES (2, 'ROLE:DELETE', 1, 2);
INSERT INTO `permission` VALUES (3, 'ROLE:UPDATE', 1, 3);
INSERT INTO `permission` VALUES (4, 'ROLE:SELECT', 1, 4);
INSERT INTO `permission` VALUES (5, 'USER:INSERT', 2, 1);
INSERT INTO `permission` VALUES (6, 'USER:DELETE', 2, 2);
INSERT INTO `permission` VALUES (7, 'USER:UPDATE', 2, 3);
INSERT INTO `permission` VALUES (8, 'USER:SELECT', 2, 4);
INSERT INTO `permission` VALUES (9, 'DEPT:INSERT', 3, 1);
INSERT INTO `permission` VALUES (10, 'DEPT:DELETE', 3, 2);
INSERT INTO `permission` VALUES (11, 'DEPT:UPDATE', 3, 3);
INSERT INTO `permission` VALUES (12, 'DEPT:SELECT', 3, 4);
INSERT INTO `permission` VALUES (13, 'GOODS:INSERT', 4, 1);
INSERT INTO `permission` VALUES (14, 'GOODS:DELETE', 4, 2);
INSERT INTO `permission` VALUES (15, 'GOODS:UPDATE', 4, 3);
INSERT INTO `permission` VALUES (16, 'GOODS:SELECT', 4, 4);
INSERT INTO `permission` VALUES (17, 'RULE:INSERT', 5, 1);
INSERT INTO `permission` VALUES (18, 'RULE:DELETE', 5, 2);
INSERT INTO `permission` VALUES (19, 'RULE:UPDATE', 5, 3);
INSERT INTO `permission` VALUES (20, 'RULE:SELECT', 5, 4);
INSERT INTO `permission` VALUES (21, 'CUSTOMER:INSERT', 6, 1);
INSERT INTO `permission` VALUES (22, 'CUSTOMER:DELETE', 6, 2);
INSERT INTO `permission` VALUES (23, 'CUSTOMER:UPDATE', 6, 3);
INSERT INTO `permission` VALUES (24, 'CUSTOMER:SELECT', 6, 4);
INSERT INTO `permission` VALUES (25, 'ORDER:INSERT', 7, 1);
INSERT INTO `permission` VALUES (26, 'ORDER:DELETE', 7, 2);
INSERT INTO `permission` VALUES (27, 'ORDER:UPDATE', 7, 3);
INSERT INTO `permission` VALUES (28, 'ORDER:SELECT', 7, 4);
INSERT INTO `permission` VALUES (29, 'CUSTOMER_IM:INSERT', 8, 1);
INSERT INTO `permission` VALUES (30, 'CUSTOMER_IM:DELETE', 8, 2);
INSERT INTO `permission` VALUES (31, 'CUSTOMER_IM:UPDATE', 8, 3);
INSERT INTO `permission` VALUES (32, 'CUSTOMER_IM:SELECT', 8, 4);
INSERT INTO `permission` VALUES (33, 'APPOINTMENT:INSERT', 9, 1);
INSERT INTO `permission` VALUES (34, 'APPOINTMENT:DELETE', 9, 2);
INSERT INTO `permission` VALUES (35, 'APPOINTMENT:UPDATE', 9, 3);
INSERT INTO `permission` VALUES (36, 'APPOINTMENT:SELECT', 9, 4);
INSERT INTO `permission` VALUES (37, 'CUSTOMER_CHECKIN:INSERT', 10, 1);
INSERT INTO `permission` VALUES (38, 'CUSTOMER_CHECKIN:DELETE', 10, 2);
INSERT INTO `permission` VALUES (39, 'CUSTOMER_CHECKIN:UPDATE', 10, 3);
INSERT INTO `permission` VALUES (40, 'CUSTOMER_CHECKIN:SELECT', 10, 4);
INSERT INTO `permission` VALUES (41, 'APPOINTMENT_RESTRICTION:INSERT', 11, 1);
INSERT INTO `permission` VALUES (42, 'APPOINTMENT_RESTRICTION:DELETE', 11, 2);
INSERT INTO `permission` VALUES (43, 'APPOINTMENT_RESTRICTION:UPDATE', 11, 3);
INSERT INTO `permission` VALUES (44, 'APPOINTMENT_RESTRICTION:SELECT', 11, 4);
INSERT INTO `permission` VALUES (45, 'CHECKUP:INSERT', 12, 1);
INSERT INTO `permission` VALUES (46, 'CHECKUP:DELETE', 12, 2);
INSERT INTO `permission` VALUES (47, 'CHECKUP:UPDATE', 12, 3);
INSERT INTO `permission` VALUES (48, 'CHECKUP:SELECT', 12, 4);
INSERT INTO `permission` VALUES (49, 'CHECKUP_REPORT:INSERT', 13, 1);
INSERT INTO `permission` VALUES (50, 'CHECKUP_REPORT:DELETE', 13, 2);
INSERT INTO `permission` VALUES (51, 'CHECKUP_REPORT:UPDATE', 13, 3);
INSERT INTO `permission` VALUES (52, 'CHECKUP_REPORT:SELECT', 13, 4);
INSERT INTO `permission` VALUES (53, 'FLOW_REGULATION:INSERT', 14, 1);
INSERT INTO `permission` VALUES (54, 'FLOW_REGULATION:DELETE', 14, 2);
INSERT INTO `permission` VALUES (55, 'FLOW_REGULATION:UPDATE', 14, 3);
INSERT INTO `permission` VALUES (56, 'FLOW_REGULATION:SELECT', 14, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
