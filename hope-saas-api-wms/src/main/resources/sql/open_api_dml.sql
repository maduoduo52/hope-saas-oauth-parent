DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型  MENU 菜单  BUTTON 按钮（按钮包括权限）',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态  1 启用 0禁用',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `parent` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parents` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '父节点集合',
  `root_flag` tinyint(1) DEFAULT 0 COMMENT '是否为根节点',
  `order_key` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url 访问地址  主要为前端地址',
  `authority_url` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限地址  后台java接口访问地址',
  `authority_button` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限按钮  此按钮IDS来控制 界面按钮或者字段显示与否',
  `add_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT 0 COMMENT '是否删除0:未删除 1:已删除',
  `add_emp_id` bigint(20) DEFAULT NULL COMMENT '添加人',
  `update_emp_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ba_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '菜单管理', 'MENU', 1, 'fa fa-user', NULL, NULL, 1, NULL, '6', '/index1.htm,/logOut.htm', '3', NULL, '2018-10-29 15:42:23', 0, 0, NULL, 11, NULL);
INSERT INTO `sys_menu` VALUES (2, '系统管理', 'MENU', 1, 'fa fa-gears', 1, NULL, 0, NULL, NULL, NULL, NULL, '2018-10-29 13:59:50', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, '组织机构管理', 'MENU', 1, 'fa fa-users', 2, NULL, 0, NULL, './org/list.htm', '/org/list.htm,/org/getAll.htm,/org/savePage.htm,/org/save.htm,/org/delete.htm', NULL, '2018-10-29 14:00:18', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, '用户管理', 'MENU', 1, 'fa fa-user', 2, NULL, 0, NULL, './user/list.htm', '/user/list.htm,/user/selectUserList.htm', NULL, '2018-10-29 14:00:55', '2020-05-25 09:38:23', 0, 0, 11, NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, '角色管理', 'MENU', 1, 'fa fa-coffee', 2, NULL, 0, NULL, './post/list.htm', '/post/list.htm,/post/selectList.htm,/post/savePage.htm,/post/delete.htm,/post/authority.htm,/post/save.htm,/menu/getAll.htm,/post/saveAuthority.htm,/post/saveUserPage.htm,/post/saveUser.htm', NULL, '2018-10-29 14:01:15', '2020-05-25 11:34:05', 0, 0, 11, NULL, NULL);
INSERT INTO `sys_menu` VALUES (6, '菜单管理', 'MENU', 1, 'fa fa-navicon', 2, NULL, 0, NULL, './menu/list.htm', '/menu/list.htm,/menu/savePage.htm,/menu/save.htm,/menu/delete.htm', NULL, '2018-10-29 14:01:36', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_menu` VALUES (7, '1', 'MENU', 1, '1', 2, NULL, 0, NULL, '1', '1', '1', '2018-10-29 15:42:34', '2018-10-29 15:42:26', 0, 1, 11, 11, NULL);
INSERT INTO `sys_menu` VALUES (8, '保存', 'BUTTON', 1, NULL, 4, NULL, 0, NULL, '/user/savePage.htm', '/user/savePage.htm,/user/save.htm', NULL, '2020-05-25 09:39:35', '2020-05-25 13:19:20', 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (9, '删除', 'BUTTON', 1, NULL, 4, NULL, 0, NULL, '/user/delete.htm', '/user/delete.htm', NULL, '2020-05-25 09:43:07', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (10, '权限分配', 'BUTTON', 1, NULL, 4, NULL, 0, NULL, '/user/authority.htm', '/user/authority.htm,/menu/getAll.htm,/user/saveAuthority.htm', NULL, '2020-05-25 09:43:32', '2020-05-25 13:19:35', 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11, '列表', 'MENU', 1, NULL, 4, NULL, 0, NULL, '/user/list.htm', '/user/list.htm,/user/selectUserList.htm,/org/getAll.htm', NULL, '2020-05-25 09:47:17', '2020-05-25 10:00:09', 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'company 公司 department 部门',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态  1 启用 0禁用',
  `parent` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parents` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '父节点集合',
  `root_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为根节点',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份编码',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市 编码 ',
  `area` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区编码',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `add_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT 0 COMMENT '是否删除0:未删除 1:已删除',
  `add_emp_id` bigint(20) DEFAULT NULL COMMENT '添加人',
  `update_emp_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ba_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, '成都运荔枝', 'company', 1, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2020-05-22 23:29:39', 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_org` VALUES (4, '9999', 'company', 1, 1, NULL, 0, NULL, NULL, NULL, NULL, '2018-10-25 14:56:29', NULL, 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_org` VALUES (6, '科技中心-WMS项目组', 'department', 1, 1, NULL, 0, NULL, NULL, NULL, NULL, '2020-05-22 23:29:57', '2020-05-25 09:08:24', 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态  1 启用 0禁用',
  `org_id` bigint(50) DEFAULT NULL COMMENT '机构ID',
  `add_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT 0 COMMENT '是否删除0:未删除 1:已删除',
  `add_emp_id` bigint(20) DEFAULT NULL COMMENT '添加人',
  `update_emp_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ba_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, '测试', 1, 1, '2018-10-25 17:21:53', '2018-10-29 15:40:35', 0, 0, NULL, 11, NULL);
INSERT INTO `sys_post` VALUES (2, '测试23', 1, 1, '2018-10-25 17:21:50', '2018-10-25 17:25:40', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_post` VALUES (3, '测试78', 1, 1, '2018-10-25 17:21:55', '2018-10-29 15:40:26', 0, 1, NULL, 11, NULL);
INSERT INTO `sys_post` VALUES (4, '124124', 1, 1, '2018-10-25 17:27:19', '2018-10-25 17:27:09', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_post` VALUES (5, '后台开发', 1, 6, '2020-05-25 09:08:48', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post` VALUES (6, '超级管理员', 1, 1, '2020-05-25 09:22:45', '2020-05-25 09:35:05', 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_post_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_post_menu`;
CREATE TABLE `sys_post_menu`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(50) NOT NULL,
  `post_id` bigint(50) NOT NULL,
  `add_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT 0 COMMENT '是否删除0:未删除 1:已删除',
  `add_emp_id` bigint(20) DEFAULT NULL COMMENT '添加人',
  `update_emp_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ba_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职位 - 菜单 关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post_menu
-- ----------------------------
INSERT INTO `sys_post_menu` VALUES (2, 1, 3, '2018-10-29 14:49:42', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (3, 3, 3, '2018-10-29 14:49:42', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (4, 4, 3, '2018-10-29 14:49:42', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (11, 1, 5, '2020-05-25 09:10:58', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (12, 2, 5, '2020-05-25 09:10:58', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (13, 3, 5, '2020-05-25 09:10:58', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (14, 4, 5, '2020-05-25 09:10:58', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (15, 5, 5, '2020-05-25 09:10:58', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (16, 6, 5, '2020-05-25 09:10:59', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (23, 1, 6, '2020-05-25 09:23:00', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (24, 2, 6, '2020-05-25 09:23:00', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (25, 3, 6, '2020-05-25 09:23:00', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (26, 4, 6, '2020-05-25 09:23:00', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (27, 5, 6, '2020-05-25 09:23:00', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (28, 6, 6, '2020-05-25 09:23:00', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (29, 1, 1, '2020-05-25 09:48:46', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (30, 2, 1, '2020-05-25 09:48:46', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (31, 4, 1, '2020-05-25 09:48:46', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_post_menu` VALUES (32, 11, 1, '2020-05-25 09:48:46', NULL, 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(50) NOT NULL COMMENT '用户ID',
  `menu_id` bigint(50) NOT NULL COMMENT '菜单ID',
  `add_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT 0 COMMENT '是否删除0:未删除 1:已删除',
  `add_emp_id` bigint(20) DEFAULT NULL COMMENT '添加人',
  `update_emp_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `BA_REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户 - 菜单 关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------
INSERT INTO `sys_user_menu` VALUES (1, 26, 1, '2018-10-29 14:44:54', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (2, 26, 2, '2018-10-29 14:44:54', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (3, 26, 3, '2018-10-29 14:44:54', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (4, 26, 4, '2018-10-29 14:44:54', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (5, 26, 5, '2018-10-29 14:44:54', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (6, 26, 6, '2018-10-29 14:44:54', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (7, 26, 6, '2018-10-29 15:20:11', NULL, 0, 0, 11, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (13, 20, 1, '2020-05-25 09:47:39', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (14, 20, 2, '2020-05-25 09:47:39', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (15, 20, 4, '2020-05-25 09:47:39', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user_menu` VALUES (16, 20, 11, '2020-05-25 09:47:40', NULL, 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL COMMENT '用户ID',
  `post_id` bigint(32) NOT NULL COMMENT '职位ID',
  `add_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT 0 COMMENT '是否删除0:未删除 1:已删除',
  `add_emp_id` bigint(20) DEFAULT NULL COMMENT '添加人',
  `update_emp_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ba_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户 - 职位 关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (5, 26, 6, '2020-05-25 09:34:48', NULL, 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user_post` VALUES (13, 20, 1, '2020-05-25 13:10:21', NULL, 0, 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `customer_info` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tenant_id` varchar(50) DEFAULT NULL COMMENT '租户id',
  `shipper_code` varchar(50) DEFAULT NULL COMMENT '货主编码',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐值',
  `source_code` varchar(50) NOT NULL COMMENT '来源编码',
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `company_address` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `person_name` varchar(20) DEFAULT NULL COMMENT '联系人名称',
  `person_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `user_key` varchar(50) DEFAULT NULL COMMENT '用户加密解密key',
  `store_url` varchar(255) DEFAULT NULL COMMENT '入库反馈推送地址',
  `deliver_url` varchar(255) DEFAULT NULL COMMENT '出库反馈推送地址',
  `adjust_url` varchar(255) DEFAULT NULL COMMENT '库存调整推送地址（主动发起）',
  `inventory_url` varchar(255) DEFAULT NULL COMMENT '库存盘点推送地址（主动发起）',
  `move_url` varchar(255) DEFAULT NULL COMMENT '移库移位推送地址（主动发起）',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(5) DEFAULT NULL COMMENT '版本号',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `ba_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `org_id` bigint(50) DEFAULT NULL COMMENT '机构ID',
  `admin` tinyint(1) DEFAULT NULL COMMENT '是否为超级管理员',
  `status` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `tenant_id_idx` (`tenant_id`) USING BTREE,
  KEY `shipper_code_idx` (`shipper_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

INSERT INTO `customer_info` VALUES (1, 'T10000045', 'testShipper', 'admin', '8b24832e94513fbcceb1f052ddd9e4b9', 'KQL1IGW8RZ', 'SL', NULL, NULL, NULL, NULL, 'rO12UIivyaJIIHOt', 'NOT_NEED_SEND', 'NOT_NEED_SEND', 'NOT_NEED_SEND', 'NOT_NEED_SEND', 'NOT_NEED_SEND', '2020-05-22 17:26:57', '2020-05-25 11:31:37', 0, 0, NULL, 6, 1, 1);