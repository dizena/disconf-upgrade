/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : disconf

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-02-15 09:45:43
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app';

-- ----------------------------
-- Records of app
-- ----------------------------

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `area_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一的ID（没有啥意义，主键，自增长而已）',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `hostport` varchar(30) DEFAULT NULL COMMENT '127.0.0.1:88这样的地址,ip+port',
  `name` varchar(30) DEFAULT NULL COMMENT '登录账号的管理用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '登录账号对应的密码',
  PRIMARY KEY (`area_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '北京', 'http://127.0.0.1:56789', 'admin', 'admin');
INSERT INTO `area` VALUES ('2', '上海', 'http://192.168.10.130', 'admin', 'admin');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置';

-- ----------------------------
-- Records of config
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `env` VALUES ('1', 'in-dev');
INSERT INTO `env` VALUES ('2', 'in-test');
INSERT INTO `env` VALUES ('3', 'out-test');
INSERT INTO `env` VALUES ('4', 'out-B');
INSERT INTO `env` VALUES ('5', 'out-A');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '99991231235959', '1', '99991231235959', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='用户角色_url访问权限表';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '/api/account/session', '会话', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('2', '1', '/api/account/signin', '登录', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('3', '1', '/api/account/signout', '登出', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('4', '1', '/api/account/password', '修改密码', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('5', '1', '/api/app/add', '生成一个app', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('6', '1', '/api/app/delete', '删除应用', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('7', '1', '/api/app/list', 'app列表', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('8', '1', '/api/app/listapp', '枚举应用', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('9', '1', '/api/area/add', '增加区域', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('10', '1', '/api/area/delete', '删除区域', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('11', '1', '/api/area/list', '枚举区域', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('12', '1', '/api/config/file', '获取配置文件', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('13', '1', '/api/config/item', '获取配置项', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('14', '1', '/api/data/api2Db', '数据导入', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('15', '1', '/api/data/db2Api', '数据导出', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('16', '1', '/api/data/getCount', '获取数据结果', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('17', '1', '/api/data/list', '枚举数据', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('18', '1', '/api/data/sync', '同步接口', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('19', '1', '/api/env/add', '增加环境', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('20', '1', '/api/env/delete', '删除环境', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('21', '1', '/api/env/list', 'env-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('22', '1', '/api/web/config/download/{configId}', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('23', '1', '/api/web/config/downloadfilebatch', 'download', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('24', '1', '/api/web/config/file', '创建file-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('25', '1', '/api/web/config/file/{configId}', 'update/post', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('26', '1', '/api/web/config/filetext', '创建file-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('27', '1', '/api/web/config/filetext/{configId}', 'update', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('28', '1', '/api/web/config/item', '创建item-config', '0010', '99991231235959');
INSERT INTO `role_resource` VALUES ('29', '1', '/api/web/config/item/{configId}', 'update', '0100', '99991231235959');
INSERT INTO `role_resource` VALUES ('30', '1', '/api/web/config/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('31', '1', '/api/web/config/notifyOne', '通知一个', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('32', '1', '/api/web/config/notifySome', '通知多个', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('33', '1', '/api/web/config/simple/list', 'config-list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('34', '1', '/api/web/config/versionlist', '版本list', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('35', '1', '/api/web/config/zk/{configId}', 'get-zk', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('36', '1', '/api/web/config/{configId}', 'get/post', '1001', '99991231235959');
INSERT INTO `role_resource` VALUES ('37', '1', '/api/zoo/hosts', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('38', '1', '/api/zoo/prefix', 'zoo', '1000', '99991231235959');
INSERT INTO `role_resource` VALUES ('39', '1', '/api/zoo/zkdeploy', 'zoo', '1000', '99991231235959');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'f28d164d23291c732f64134e6b7d92be3ff8b1b3', '', '1');
