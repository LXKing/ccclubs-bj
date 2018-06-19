/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : ccclubs_system
Target Host     : localhost:3306
Target Database : ccclubs_system
Date: 2015-03-20 11:25:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cs_chat_message
-- ----------------------------
DROP TABLE IF EXISTS `cs_chat_message`;
CREATE TABLE `cs_chat_message` (
  `cscm_id` bigint(20) NOT NULL auto_increment,
  `cscm_host` bigint(20) NOT NULL,
  `cscm_content` text NOT NULL,
  `cscm_type` smallint(6) NOT NULL,
  `cscm_from` varchar(32) NOT NULL,
  `cscm_target` varchar(32) NOT NULL,
  `cscm_update_time` datetime NOT NULL,
  `cscm_add_time` datetime NOT NULL,
  `cscm_state` smallint(6) NOT NULL,
  `cscm_status` smallint(6) NOT NULL,
  PRIMARY KEY  (`cscm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_chat_message
-- ----------------------------

-- ----------------------------
-- Table structure for cs_chat_talker
-- ----------------------------
DROP TABLE IF EXISTS `cs_chat_talker`;
CREATE TABLE `cs_chat_talker` (
  `csct_id` bigint(20) NOT NULL auto_increment,
  `csct_host` bigint(20) NOT NULL,
  `csct_name` varchar(32) NOT NULL,
  `csct_header` varchar(128) default NULL,
  `csct_sign` varchar(64) default NULL,
  `csct_member` bigint(20) default NULL,
  `csct_flag` varchar(32) default NULL,
  `csct_platform` smallint(6) NOT NULL,
  `csct_count` int(11) NOT NULL,
  `csct_last_ip` varchar(32) default NULL,
  `csct_servicer` bigint(20) default NULL,
  `csct_last_time` datetime default NULL,
  `csct_add_time` datetime NOT NULL,
  `csct_remark` varchar(256) default NULL,
  `csct_state` smallint(6) NOT NULL,
  `csct_status` smallint(6) NOT NULL,
  PRIMARY KEY  (`csct_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_chat_talker
-- ----------------------------

-- ----------------------------
-- Table structure for cs_grow_record
-- ----------------------------
DROP TABLE IF EXISTS `cs_grow_record`;
CREATE TABLE `cs_grow_record` (
  `csgr_id` bigint(20) NOT NULL auto_increment,
  `csgr_host` bigint(20) NOT NULL,
  `csgr_member` bigint(20) NOT NULL,
  `csgr_desc` varchar(256) NOT NULL,
  `csgr_type` bigint(20) NOT NULL,
  `csgr_amount` bigint(20) NOT NULL,
  `csgr_relate` varchar(32) default NULL,
  `csgr_update_time` datetime NOT NULL,
  `csgr_add_time` datetime NOT NULL,
  `csgr_status` smallint(6) NOT NULL,
  PRIMARY KEY  (`csgr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_grow_record
-- ----------------------------

-- ----------------------------
-- Table structure for cs_message
-- ----------------------------
DROP TABLE IF EXISTS `cs_message`;
CREATE TABLE `cs_message` (
  `csm_id` bigint(20) NOT NULL auto_increment,
  `csm_host` bigint(20) NOT NULL,
  `csm_content` varchar(512) NOT NULL,
  `csm_type` smallint(6) NOT NULL,
  `csm_platform` smallint(6) NOT NULL,
  `csm_recver` bigint(20) NOT NULL,
  `csm_sender` bigint(20) default NULL,
  `csm_rcv_time` datetime NOT NULL,
  `csm_add_time` datetime NOT NULL,
  `csm_status` smallint(6) NOT NULL,
  PRIMARY KEY  (`csm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_message
-- ----------------------------

-- ----------------------------
-- Table structure for cs_outside_qr
-- ----------------------------
DROP TABLE IF EXISTS `cs_outside_qr`;
CREATE TABLE `cs_outside_qr` (
  `csoq_id` bigint(20) NOT NULL auto_increment,
  `csoq_host` bigint(20) NOT NULL,
  `csoq_adder` bigint(20) NOT NULL,
  `csoq_car` bigint(20) NOT NULL,
  `csoq_user_pos` varchar(32) NOT NULL,
  `csoq_car_pos` varchar(32) NOT NULL,
  `csoq_distance` double NOT NULL,
  `csoq_remark` varchar(256) default NULL,
  `csoq_add_time` datetime NOT NULL,
  `csoq_status` smallint(6) NOT NULL,
  PRIMARY KEY  (`csoq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_outside_qr
-- ----------------------------

-- ----------------------------
-- Table structure for srv_user_exp
-- ----------------------------
DROP TABLE IF EXISTS `srv_user_exp`;
CREATE TABLE `srv_user_exp` (
  `sue_id` bigint(20) NOT NULL auto_increment,
  `sue_user` bigint(20) NOT NULL,
  `sue_wx_openid` varchar(32) default NULL,
  `sue_is_servicer` tinyint(4) default NULL,
  `sue_chat_nick` varchar(32) default NULL,
  `sue_chat_header` varchar(128) default NULL,
  `sue_chat_sign` varchar(128) default NULL,
  PRIMARY KEY  (`sue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of srv_user_exp
-- ----------------------------
