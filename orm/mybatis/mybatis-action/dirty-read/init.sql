CREATE TABLE `user`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `birthday` datetime     DEFAULT NULL COMMENT '生日',
    `sex`      char(1)      DEFAULT NULL COMMENT '性别',
    `address`  varchar(256) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`, `username`, `birthday`, `sex`, `address`) VALUES
('1', 'tomcat1', '2021-03-16 22:37:51', '男', '北京市顺义区1');
