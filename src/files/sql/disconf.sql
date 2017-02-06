/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : disconf

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-02-06 17:00:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `app_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一的ID（没有啥意义，主键，自增长而已）',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT 'APP名(一般是产品线+服务名)',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '介绍',
  `create_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '生成时间',
  `update_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '修改时',
  `emails` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱列表逗号分隔',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='app';

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', 'test', 'test', '20170117165554', '20170117165554', '');
INSERT INTO `app` VALUES ('2', 'demo', 'demo', '99991231235959', '99991231235959', '');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一的ID（没有啥意义，主键，自增长而已）',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '配置文件/配置项',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：1是正常 0是删除',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '配置文件名/配置项KeY名',
  `value` text NOT NULL COMMENT '0 配置文件：文件的内容，1 配置项：配置值',
  `app_id` bigint(20) NOT NULL COMMENT 'appid',
  `version` varchar(255) NOT NULL DEFAULT 'DEFAULT_VERSION' COMMENT '版本',
  `env_id` bigint(20) NOT NULL COMMENT 'envid',
  `create_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '生成时间',
  `update_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '修改时间',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 COMMENT='配置';

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', '0', '1', 'coefficients.properties', 'coe.baiFaCoe=1.3\ncoe.yuErBaoCoe=1.3\n', '1', '1_0_0_0', '1', '99991231235959', '20141205154137');
INSERT INTO `config` VALUES ('2', '1', '1', 'moneyInvest', '10000', '2', '1_0_0_0', '1', '99991231235959', '20140902183514');

-- ----------------------------
-- Table structure for config_history
-- ----------------------------
DROP TABLE IF EXISTS `config_history`;
CREATE TABLE `config_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_id` bigint(20) NOT NULL,
  `old_value` longtext NOT NULL,
  `new_value` longtext NOT NULL,
  `create_time` varchar(14) NOT NULL DEFAULT '99991231235959',
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config_history
-- ----------------------------

-- ----------------------------
-- Table structure for env
-- ----------------------------
DROP TABLE IF EXISTS `env`;
CREATE TABLE `env` (
  `env_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '环境ID（主键，自增长）',
  `name` varchar(255) NOT NULL DEFAULT 'DEFAULT_ENV' COMMENT '环境名字',
  PRIMARY KEY (`env_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='rd/qa/local可以自定义，默认为 DEFAULT_ENV';

-- ----------------------------
-- Records of env
-- ----------------------------
INSERT INTO `env` VALUES ('1', 'rd');
INSERT INTO `env` VALUES ('2', 'qa');
INSERT INTO `env` VALUES ('3', 'local');
INSERT INTO `env` VALUES ('4', 'online');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名',
  `create_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通人', '99991231235959', '2', '99991231235959', '2');
INSERT INTO `role` VALUES ('2', '管理员', '99991231235959', '2', '99991231235959', '2');
INSERT INTO `role` VALUES ('3', '测试管理员', '99991231235959', '2', '99991231235959', '2');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `role_res_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'role-resource id',
  `role_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户角色id',
  `url_pattern` varchar(200) NOT NULL DEFAULT '' COMMENT 'controller_requestMapping_value + method_requestMapping_value',
  `url_description` varchar(200) NOT NULL DEFAULT '' COMMENT 'url功能描述',
  `method_mask` varchar(4) NOT NULL DEFAULT '' COMMENT 'GET, PUT, POST, DELETE, 1: accessible',
  `update_time` varchar(14) NOT NULL DEFAULT '99991231235959' COMMENT '更新时间',
  PRIMARY KEY (`role_res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='用户角色_url访问权限表';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '/api/app/list', 'app列表', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('2', '2', '/api/app/list', 'app列表', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('3', '3', '/api/app/list', 'app列表', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('4', '1', '/api/app', '生成一个app', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('5', '2', '/api/app', '生成一个app', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('6', '3', '/api/app', '生成一个app', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('7', '1', '/api/env/list', 'env-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('8', '2', '/api/env/list', 'env-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('9', '3', '/api/env/list', 'env-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('10', '1', '/api/account/session', '会话', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('11', '2', '/api/account/session', '会话', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('12', '3', '/api/account/session', '会话', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('13', '1', '/api/account/signin', '登录', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('14', '2', '/api/account/signin', '登录', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('15', '3', '/api/account/signin', '登录', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('16', '1', '/api/account/signout', '登出', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('17', '2', '/api/account/signout', '登出', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('18', '3', '/api/account/signout', '登出', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('19', '1', '/api/config/item', '获取配置项', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('20', '2', '/api/config/item', '获取配置项', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('21', '3', '/api/config/item', '获取配置项', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('22', '1', '/api/config/file', '获取配置文件', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('23', '2', '/api/config/file', '获取配置文件', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('24', '3', '/api/config/file', '获取配置文件', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('25', '1', '/api/zoo/hosts', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('26', '2', '/api/zoo/hosts', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('27', '3', '/api/zoo/hosts', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('28', '1', '/api/zoo/prefix', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('29', '2', '/api/zoo/prefix', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('30', '3', '/api/zoo/prefix', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('31', '1', '/api/zoo/zkdeploy', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('32', '2', '/api/zoo/zkdeploy', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('33', '3', '/api/zoo/zkdeploy', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('34', '1', '/api/web/config/item', '创建item-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('35', '2', '/api/web/config/item', '创建item-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('36', '3', '/api/web/config/item', '创建item-config', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('37', '1', '/api/web/config/file', '创建file-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('38', '2', '/api/web/config/file', '创建file-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('39', '3', '/api/web/config/file', '创建file-config', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('40', '1', '/api/web/config/filetext', '创建file-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('41', '2', '/api/web/config/filetext', '创建file-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('42', '3', '/api/web/config/filetext', '创建file-config', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('43', '1', '/api/web/config/versionlist', '版本list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('44', '2', '/api/web/config/versionlist', '版本list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('45', '3', '/api/web/config/versionlist', '版本list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('46', '1', '/api/web/config/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('47', '2', '/api/web/config/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('48', '3', '/api/web/config/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('49', '1', '/api/web/config/simple/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('50', '2', '/api/web/config/simple/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('51', '3', '/api/web/config/simple/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('52', '1', '/api/web/config/{configId}', 'get/post', '1001', '99991231235959');
INSERT INTO `role_resource` VALUES ('53', '2', '/api/web/config/{configId}', 'get/post', '1001', '99991231235959');
INSERT INTO `role_resource` VALUES ('54', '3', '/api/web/config/{configId}', 'get/post', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('55', '1', '/api/web/config/zk/{configId}', 'get-zk', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('56', '2', '/api/web/config/zk/{configId}', 'get-zk', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('57', '3', '/api/web/config/zk/{configId}', 'get-zk', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('58', '1', '/api/web/config/download/{configId}', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('59', '2', '/api/web/config/download/{configId}', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('60', '3', '/api/web/config/download/{configId}', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('61', '1', '/api/web/config/downloadfilebatch', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('62', '2', '/api/web/config/downloadfilebatch', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('63', '3', '/api/web/config/downloadfilebatch', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('64', '1', '/api/web/config/item/{configId}', 'update', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('65', '2', '/api/web/config/item/{configId}', 'update', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('66', '3', '/api/web/config/item/{configId}', 'update', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('67', '1', '/api/web/config/file/{configId}', 'update/post', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('68', '2', '/api/web/config/file/{configId}', 'update/post', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('69', '3', '/api/web/config/file/{configId}', 'update/post', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('70', '1', '/api/web/config/filetext/{configId}', 'update', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('71', '2', '/api/web/config/filetext/{configId}', 'update', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('72', '3', '/api/web/config/filetext/{configId}', 'update', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('73', '1', '/api/account/password', '修改密码', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('74', '2', '/api/account/password', '修改密码', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('75', '3', '/api/account/password', '修改密码', '0000', '99991231235959');
INSERT INTO `role_resource` VALUES ('76', '2', '/api/app/delete', '删除应用', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('77', '2', '/api/env/add', '增加环境', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('78', '2', '/api/env/delete', '删除环境', '1000', '99991231235959');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `token` varchar(255) NOT NULL COMMENT 'token',
  `ownapps` varchar(255) NOT NULL DEFAULT '' COMMENT '能操作的APPID,逗号分隔',
  `role_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '角色ID',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'f28d164d23291c732f64134e6b7d92be3ff8b1b3', '', '2');
INSERT INTO `user` VALUES ('2', 'admin_read', 'b76f3e20d1c8d0bc17d40158e44097d5eeee8640', '2022ab9c2754d62f9ddba5fded91e4238247ebaf', '2', '3');
