
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


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
  `create_time` datetime(0) NOT NULL COMMENT '角色创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES (1, 'USER', 'USER', '普通用户级别', 1, '2020-07-02 10:34:37');


-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `active_status` tinyint(4) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

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
  `create_time` datetime(0) NOT NULL COMMENT '关联创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role_link
-- ----------------------------
INSERT INTO `user_role_link` VALUES (1, 1, 1, '2020-07-02 10:34:53');

SET FOREIGN_KEY_CHECKS = 1;
