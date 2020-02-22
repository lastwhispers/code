-- 表 order 订单结构
CREATE TABLE IF NOT EXISTS `t_order` (
  `id` varchar(128) NOT NULL, -- 订单ID
  `name` varchar(128), -- 订单名称 其他业务熟悉忽略
  `message_id` varchar(128) NOT NULL, -- 消息唯一ID
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 表 broker_message_log 消息记录结构
CREATE TABLE IF NOT EXISTS `broker_message_log` (
  `message_id` varchar(128) NOT NULL, -- 消息唯一ID
  `message` varchar(4000) DEFAULT NULL, -- 消息内容
  `try_count` int(4) DEFAULT '0', -- 重试次数
  `status` varchar(10) DEFAULT '', -- 消息投递状态  0 投递中 1 投递成功   2 投递失败
  `next_retry` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',  --下一次重试时间 或 超时时间
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00', --创建时间
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00', --更新时间
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;