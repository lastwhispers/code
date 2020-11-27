use xxx_project_db;

CREATE TABLE `log` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `project_name` VARCHAR(255) DEFAULT NULL COMMENT '目项名',
    `create_date` VARCHAR(255) DEFAULT NULL COMMENT '创建时间',
    `level` VARCHAR(255) DEFAULT NULL COMMENT '优先级',
    `category` VARCHAR(255) DEFAULT NULL COMMENT '所在类的全名',
    `file_name` VARCHAR(255) DEFAULT NULL COMMENT '输出日志消息产生时所在的文件名称 ',
    `thread_name` VARCHAR(255) DEFAULT NULL COMMENT '日志事件的线程名',
    `line` VARCHAR(255) DEFAULT NULL COMMENT '号行',
    `all_category` VARCHAR(255) DEFAULT NULL COMMENT '日志事件的发生位置',
    `message` VARCHAR(4000) DEFAULT NULL COMMENT '输出代码中指定的消息',
    PRIMARY KEY (`id`)
);


