/*
 Navicat Premium Data Transfer

 Source Server         : vm-mysql
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 192.168.108.100:30003
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 26/04/2021 23:22:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cb_resource
-- ----------------------------
DROP TABLE IF EXISTS `cb_resource`;
CREATE TABLE `cb_resource`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `resource_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单源',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `resource_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order` int(0) NULL DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客-菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cb_resource
-- ----------------------------
INSERT INTO `cb_resource` VALUES (1, '驾驶舱', 'blog-admin', NULL, NULL, 0, '2021-04-26 13:20:40', '2021-04-26 13:21:07');

-- ----------------------------
-- Table structure for cb_role
-- ----------------------------
DROP TABLE IF EXISTS `cb_role`;
CREATE TABLE `cb_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` int(0) NOT NULL DEFAULT 0 COMMENT '父角色ID',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `order` tinyint(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（0:禁用，1:启用）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客-角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cb_role
-- ----------------------------
INSERT INTO `cb_role` VALUES (1, 0, 'admin', '0000', '管理员', 0, 1, '2021-04-26 13:18:39', '2021-04-26 13:18:53');

-- ----------------------------
-- Table structure for cb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `cb_role_resource`;
CREATE TABLE `cb_role_resource`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(0) NULL DEFAULT NULL COMMENT '资源ID',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客-角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cb_role_resource
-- ----------------------------
INSERT INTO `cb_role_resource` VALUES (1, 1, 1, '2021-04-26 13:19:14', '2021-04-26 13:19:14');

-- ----------------------------
-- Table structure for cb_user
-- ----------------------------
DROP TABLE IF EXISTS `cb_user`;
CREATE TABLE `cb_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `user_email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示名称',
  `user_explain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `login_last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `login_enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用登录（0: 禁用 1：启用）',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客-用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cb_user
-- ----------------------------
INSERT INTO `cb_user` VALUES (1, 'admin', '8279603faf7658fded36ce6a400df107', NULL, NULL, NULL, NULL, NULL, NULL, 1, '2021-04-26 10:22:21', '2021-04-26 10:22:21');

-- ----------------------------
-- Table structure for cb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cb_user_role`;
CREATE TABLE `cb_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modify` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客-角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cb_user_role
-- ----------------------------
INSERT INTO `cb_user_role` VALUES (1, 1, 1, '2021-04-26 13:19:05', '2021-04-26 13:19:05');

SET FOREIGN_KEY_CHECKS = 1;
