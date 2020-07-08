/*
 Navicat MySQL Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 47.102.159.163:3306
 Source Schema         : spring-boot-learning

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 08/07/2020 09:54:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission_info
-- ----------------------------
DROP TABLE IF EXISTS `permission_info`;
CREATE TABLE `permission_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限表主键',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `permission_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限URI',
  `permission_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限方法类型',
  `active_status` tinyint(4) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '权限创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of permission_info
-- ----------------------------
INSERT INTO `permission_info` VALUES (1, 'USER用户测试', '/api/user', 'GET', 1, '2020-07-05 18:13:47');
INSERT INTO `permission_info` VALUES (2, 'ADMIN测试', '/api/admin', 'GET', 1, '2020-07-05 18:14:11');

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色表主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色备注',
  `active_status` tinyint(4) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '角色创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES (1, 'USER', 'USER', '普通用户级别', 1, '2020-07-02 10:34:37');
INSERT INTO `role_info` VALUES (2, 'ADMIN', 'ADMIN', '超级管理员', 1, '2020-07-05 18:12:57');

-- ----------------------------
-- Table structure for role_permission_link
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_link`;
CREATE TABLE `role_permission_link`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色与权限关联表',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `permission_id` bigint(20) NOT NULL COMMENT '权限表主键',
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role_permission_link
-- ----------------------------
INSERT INTO `role_permission_link` VALUES (1, 1, 1, '2020-07-05 18:14:25');
INSERT INTO `role_permission_link` VALUES (2, 2, 2, '2020-07-05 18:14:31');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `active_status` tinyint(4) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'user', '$2a$10$ms3nmhX6O3J09oQDJlwC7eywfmuLkKiGKadvIno.SIBStlhXtvwGW', 1, '2020-07-02 10:34:07');

-- ----------------------------
-- Table structure for user_role_link
-- ----------------------------
DROP TABLE IF EXISTS `user_role_link`;
CREATE TABLE `user_role_link`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户与角色关联表主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户表主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `create_time` datetime NOT NULL COMMENT '关联创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user_role_link
-- ----------------------------
INSERT INTO `user_role_link` VALUES (1, 1, 1, '2020-07-02 10:34:53');

SET FOREIGN_KEY_CHECKS = 1;
