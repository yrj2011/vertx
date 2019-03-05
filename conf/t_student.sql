
DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` char(36) NOT NULL,
  `stu_name` varchar(40) DEFAULT NULL,
  `stu_addr` varchar(40) DEFAULT NULL COMMENT '居住地址',
  `tel` char(11) NOT NULL,
  `gender` char(2) DEFAULT '男' COMMENT '性别',
  `birth` CHAR(8) DEFAULT NULL,
  `id_card` char(18) DEFAULT NULL,
  `id_card_addr` varchar(40) DEFAULT NULL COMMENT '身份证地址',
  `register_time` CHAR(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
