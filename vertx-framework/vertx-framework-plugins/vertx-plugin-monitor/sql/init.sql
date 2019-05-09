CREATE TABLE `vertx_ms_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL COMMENT '地址',
  `body` text,
  `log_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ms_type` varchar(20) DEFAULT '0' COMMENT '00 普通消息  01 流程消息',
  `headers` text,
  `fid` varchar(40) DEFAULT NULL COMMENT '流程执行的ID',
  `verticle` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;