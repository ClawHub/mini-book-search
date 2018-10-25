/*
 Navicat Premium Data Transfer

 Source Server         : 192.144.192.190
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 192.144.192.190:53306
 Source Schema         : mini-book-search

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/10/2018 23:07:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book_info
-- ----------------------------
DROP TABLE IF EXISTS `t_book_info`;
CREATE TABLE `t_book_info`  (
  `id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''书籍id'',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''书名'',
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''作者'',
  `site` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''站点 0:女 1:男  '',
  `classify` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''分类 0：玄幻 1：奇幻 2：武侠 3：仙侠 4：都市 。。。。'',
  `state` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''状态 0：连载 1：完本'',
  `attribute` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''属性 0：免费 1:vip'',
  `number` int(11) NULL DEFAULT NULL COMMENT ''字数'',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''简介'',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''图片链接'',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ''书籍信息'' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_book_shelf
-- ----------------------------
DROP TABLE IF EXISTS `t_book_shelf`;
CREATE TABLE `t_book_shelf`  (
  `id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''id'',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''openId'',
  `book_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''bookId'',
  `date_time` bigint(20) NULL DEFAULT NULL COMMENT ''新增时间''
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ''书架表'' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_book_source
-- ----------------------------
DROP TABLE IF EXISTS `t_book_source`;
CREATE TABLE `t_book_source`  (
  `source_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''书籍源id'',
  `book_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''书籍id'',
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''小说链接'',
  `update_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''更新时间'',
  `catalog_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''目录链接'',
  `web_site` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''web站点'',
  PRIMARY KEY (`source_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ''书籍来源表'' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_chapter
-- ----------------------------
DROP TABLE IF EXISTS `t_chapter`;
CREATE TABLE `t_chapter`  (
  `id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''章节id'',
  `source_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''书籍源id'',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''章节名称'',
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''章节链接'',
  `date_time` bigint(20) NULL DEFAULT NULL COMMENT ''首发时间'',
  `sort` bigint(20) NULL DEFAULT NULL COMMENT ''序列'',
  `number` int(11) NULL DEFAULT NULL COMMENT ''章节字数'',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ''章节表'' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person`  (
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''openId'',
  `nick_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''昵称'',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''性别 1：男 0：女'',
  `language` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''语言'',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''城市'',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''省份'',
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''国家'',
  `avatar_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''头像url''
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ''人员信息表'' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_recommend
-- ----------------------------
DROP TABLE IF EXISTS `t_recommend`;
CREATE TABLE `t_recommend`  (
  `id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''id'',
  `book` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ''书名'',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT ''创建时间'',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ''推荐表'' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
