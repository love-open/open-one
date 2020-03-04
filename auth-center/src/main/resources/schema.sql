/*
 Navicat Premium Data Transfer

 Source Server         : 本地测试库
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1:3306
 Source Schema         : auth

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 25/02/2020 23:27:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级类型Id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对照码',
  `value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对照值',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sys字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (90, NULL, 'JJRLX', 'holiday_type', '节假日类型');
INSERT INTO `sys_dict_type` VALUES (91, '90', 'JJRLX', '0', '周末');
INSERT INTO `sys_dict_type` VALUES (92, '90', 'JJRLX', '1', '调休');
INSERT INTO `sys_dict_type` VALUES (93, '90', 'JJRLX', '2', '元旦');
INSERT INTO `sys_dict_type` VALUES (94, '90', 'JJRLX', '3', '除夕');
INSERT INTO `sys_dict_type` VALUES (95, '90', 'JJRLX', '4', '春节');
INSERT INTO `sys_dict_type` VALUES (96, '90', 'JJRLX', '5', '清明节');
INSERT INTO `sys_dict_type` VALUES (97, '90', 'JJRLX', '6', '劳动节');
INSERT INTO `sys_dict_type` VALUES (98, '90', 'JJRLX', '7', '端午节');
INSERT INTO `sys_dict_type` VALUES (99, '90', 'JJRLX', '8', '中秋节');
INSERT INTO `sys_dict_type` VALUES (100, '90', 'JJRLX', '9', '国庆节');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `parent_menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点主键',
  `display_order` int(4) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `icon` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标名称',
  `leaf_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0253426d418a42cbb5dc4b5df428f37c', '角色管理', '/role/manage/page', '8d09a7c69405474795cd6ae312fe6b27', 102, '角色管理', NULL, 1);
INSERT INTO `sys_menu` VALUES ('0dca8191e3c84e8cb5553e6ab5dd13bc', '菜单管理', '/menu/manage/page', '8d09a7c69405474795cd6ae312fe6b27', 103, '菜单管理', NULL, 1);
INSERT INTO `sys_menu` VALUES ('1f2bcda2713144cb8c9f451c1bd1b33f', '字典管理', '/dictType/manage/page', '8d09a7c69405474795cd6ae312fe6b27', 104, '字典管理', NULL, 1);
INSERT INTO `sys_menu` VALUES ('82d0f840e03f4fc9b00d8d55a7de05b8', '用户管理', '/user/manage/page', '8d09a7c69405474795cd6ae312fe6b27', 101, '基础模块', NULL, 1);
INSERT INTO `sys_menu` VALUES ('8d09a7c69405474795cd6ae312fe6b27', '系统设置', '', 'root', 10, '', '', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `unique_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `remark` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('a22ae2f87c034677b6f894bc315e4b32', '普通用户', 'USER', '');
INSERT INTO `sys_role` VALUES ('efabbbb72fb647b0af5347f8d692ccc3', '超级管理员', 'ADMIN', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色表主键',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单表主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('258952f44bf144a2a0a5c7871026b3b2', 'efabbbb72fb647b0af5347f8d692ccc3', '0253426d418a42cbb5dc4b5df428f37c');
INSERT INTO `sys_role_menu` VALUES ('432e9d66da584ed2be0d3c0065754965', 'efabbbb72fb647b0af5347f8d692ccc3', '0dca8191e3c84e8cb5553e6ab5dd13bc');
INSERT INTO `sys_role_menu` VALUES ('7e585a4128c845b49cc21f7a7f283165', 'efabbbb72fb647b0af5347f8d692ccc3', '82d0f840e03f4fc9b00d8d55a7de05b8');
INSERT INTO `sys_role_menu` VALUES ('90ec0a8c2077474e877562ba8576aa84', 'efabbbb72fb647b0af5347f8d692ccc3', '8d09a7c69405474795cd6ae312fe6b27');
INSERT INTO `sys_role_menu` VALUES ('a734fe0ef6b54a1282fcd03fbb400fd8', 'efabbbb72fb647b0af5347f8d692ccc3', '1f2bcda2713144cb8c9f451c1bd1b33f');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `team_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编码',
  `team_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '邮箱',
  `last_login_time` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录ip',
  `online_flag` tinyint(1) NULL DEFAULT NULL COMMENT '在线标志',
  `lock_flag` tinyint(1) NULL DEFAULT NULL COMMENT '锁定标志',
  `wechat_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信昵称',
  `wechat_unionid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信唯一识别号',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `competent_department_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管的部门的编码',
  `competent_department_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管的部门的名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('8245e2ee0f2d4aa99af3cf6867ba23aa', '刘彦伯', 'liuyb', '$2a$10$JcgleKuPwSaSdkkWFAa8cOnqOFWSQAdjXqDDJEjg1seiTEKFkg6zq', '137732151', '开发二部', '18660611271', '', '2020-02-20 10:58:43', '127.0.0.1', 1, 0, NULL, NULL, '', '', '');
INSERT INTO `sys_user` VALUES ('f2c2d8e2a2654c9a90361dcc6e6aab00', '超级管理员', 'admin', '$2a$10$YzerxbhhA9qT/7Wwe7.UWObq7cNPN5sTqGyll.qT/JAIpECd5C7P2', '', '', '18660611271', '', '2020-02-25 23:25:58', '127.0.0.1', 1, NULL, NULL, NULL, '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户表主键',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色表主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1111111111111111111111111111111', 'f2c2d8e2a2654c9a90361dcc6e6aab00', 'efabbbb72fb647b0af5347f8d692ccc3');
INSERT INTO `sys_user_role` VALUES ('88f513276b454710a422495d5bbd3ff8', '8245e2ee0f2d4aa99af3cf6867ba23aa', 'c700d7b11c46426cad3e7016064484f1');

SET FOREIGN_KEY_CHECKS = 1;
