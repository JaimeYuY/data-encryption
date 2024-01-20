-- t_user_info definition

CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) DEFAULT '' COMMENT '用户名',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(128) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(128) DEFAULT NULL COMMENT '身份证号',
  `nick` varchar(128) DEFAULT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息';
