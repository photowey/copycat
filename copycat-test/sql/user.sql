-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birth_day` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', '2002-09-19 17:28:29');
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', '2000-09-19 17:28:33');
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', '1992-09-19 17:28:35');
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', '1999-09-19 17:28:38');
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', '1996-09-19 17:28:41');