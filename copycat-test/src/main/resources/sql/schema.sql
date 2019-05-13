DROP TABLE IF EXISTS user2;

CREATE TABLE user2
(
  id         BIGINT(20)  NOT NULL
    COMMENT '主键ID',
  name       VARCHAR(30) NULL DEFAULT NULL
    COMMENT '姓名',
  age        INT(11)     NULL DEFAULT NULL
    COMMENT '年龄',
  email      VARCHAR(50) NULL DEFAULT NULL
    COMMENT '邮箱',
  birth_day datetime    NULL DEFAULT NULL
    COMMENT '生日',
  PRIMARY KEY (id)
);