CREATE DATABASE orm;
USE orm;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`       int(11) NOT NULL auto_increment,
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `birthday` datetime     default NULL COMMENT '生日',
    `sex`      char(1)      default NULL COMMENT '性别',
    `address`  varchar(256) default NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;