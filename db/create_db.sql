CREATE DATABASE `oa` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `test` (
  `dd` int(10) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `age` int(10) default NULL,
  PRIMARY KEY  (`dd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

Create TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

Create TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

Insert INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (100, 'yihaomen', '123456', TRUE);

Insert INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 100, 'ROLE_USER');


-- user & role & resource
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `resource` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '', 'URL', '/app/admin', '');
INSERT INTO `sys_resource` VALUES ('2', '', 'URL', '/app/welcome', '');

-- ----------------------------
-- Table structure for `sys_resource_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_role`;
CREATE TABLE `sys_resource_role` (
  `resc_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  KEY `resc_id` (`resc_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_resource_role_ibfk_1` FOREIGN KEY (`resc_id`) REFERENCES `sys_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_resource_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource_role
-- ----------------------------
INSERT INTO `sys_resource_role` VALUES ('1', '1');
INSERT INTO `sys_resource_role` VALUES ('2', '1');
INSERT INTO `sys_resource_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '管理员角色');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '用户角色');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '1', '管理员');
INSERT INTO `sys_user` VALUES ('2', 'user', 'user', '1', '用户');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '2');