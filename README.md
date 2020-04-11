# sicily-web
小程序后台服务

# 数据库脚本
  ## 食品菜单表
    CREATE TABLE `sicily_food_category` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `name` varchar(128) DEFAULT NULL COMMENT '名称',
      `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
      `created_date` datetime DEFAULT NULL COMMENT '创建时间',
      `deleted_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志。N：未删除，D：已删除，默认N',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='食品菜单表';
  
  ## 食品信息表
    CREATE TABLE `sicily_food_info` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `category_id` bigint(20) DEFAULT NULL COMMENT '所属菜单id',
      `name` varchar(128) DEFAULT NULL COMMENT '名称',
      `image` varchar(1024) DEFAULT NULL COMMENT '图片',
      `today_repository` int(11) DEFAULT NULL COMMENT '今日库存',
      `limit` int(11) DEFAULT NULL COMMENT '限购数量；0：不限购',
      `price` double NOT NULL COMMENT '价格',
      `description` varchar(512) DEFAULT NULL COMMENT '描述',
      `on_sale` char(1) DEFAULT NULL COMMENT '是否上架。1：上架，2：下架',
      `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
      `created_date` datetime DEFAULT NULL COMMENT '创建时间',
      `deleted_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志。N：未删除，D：已删除，默认N',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='食品信息表';
  
  ## 商户信息
    CREATE TABLE `sicily_merchant` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `phone` varchar(20) DEFAULT NULL,
      `password` varchar(128) DEFAULT NULL COMMENT '密码',
      `app_id` varchar(128) DEFAULT NULL,
      `app_secret` varchar(128) DEFAULT NULL,
      `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
      `created_date` datetime DEFAULT NULL COMMENT '创建时间',
      `deleted_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志。N：未删除，D：已删除，默认N',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk COMMENT='商户信息';
    
  ## 订单-食物映射表
    CREATE TABLE `sicily_order_food` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
      `food_id` bigint(20) DEFAULT NULL COMMENT '食物id',
      `amount` int(11) DEFAULT NULL COMMENT '数量',
      `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
      `created_date` datetime DEFAULT NULL COMMENT '创建时间',
      `deleted_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志。N：未删除，D：已删除，默认N',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='订单-食物映射表';
    
  ## 订单信息表
    CREATE TABLE `sicily_order_info` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
      `attain_way` int(11) DEFAULT NULL COMMENT '发货方式：1：自提，2：送货上门',
      `price` double DEFAULT NULL COMMENT '金额',
      `class_num` int(11) DEFAULT NULL COMMENT '种类数量',
      `food_num` int(11) DEFAULT NULL COMMENT '食品件数',
      `address` varchar(20) DEFAULT NULL COMMENT '地址',
      `remark` varchar(256) DEFAULT NULL COMMENT '备注',
      `status` int(11) DEFAULT NULL COMMENT '订单状态：0：加入购物车，1：已下单，2：已接单，3：已取消，4：完成',
      `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
      `created_date` datetime DEFAULT NULL COMMENT '创建时间',
      `deleted_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志。N：未删除，D：已删除，默认N',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk COMMENT='订单信息表';
    
  ## 用户信息表
    CREATE TABLE `sicily_user_info` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `open_id` varchar(128) DEFAULT NULL COMMENT '小程序openId',
      `wechat_name` varchar(128) DEFAULT NULL COMMENT '微信名称',
      `avatar_url` varchar(128) DEFAULT NULL COMMENT '微信头像',
      `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
      `created_by` varchar(128) DEFAULT NULL COMMENT '创建者',
      `created_date` datetime DEFAULT NULL COMMENT '创建时间',
      `deleted_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志。N：未删除，D：已删除，默认N',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk COMMENT='用户信息表';
  
    