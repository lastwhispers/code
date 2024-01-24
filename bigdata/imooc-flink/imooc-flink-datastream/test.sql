CREATE TABLE pk_flink_imooc.student
(
    id   int          NOT NULL DEFAULT '0',
    name varchar(255) NOT NULL DEFAULT '',
    age  int          NOT NULL DEFAULT '0',
    PRIMARY KEY (id)
);

CREATE TABLE `pk_flink_imooc`.`pk_traffic`
(
    `domain`  varchar(255) NOT NULL DEFAULT '',
    `traffic` double       NOT NULL DEFAULT '0.0',
    PRIMARY KEY (domain)
);


