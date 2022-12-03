create database if not exists `generator`;

use `generator`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
                       `rec_id` int(11) not null auto_increment,
                       `user_name` varchar(32) not null comment '用户名',
                       `password` varchar(32)  not null comment '密码',
                       `role` varchar(256) default 'user' comment '角色：user/admin',
                       `is_deleted` tinyint(1) not null default 0 comment '是否删除',
                       `modified` datetime not null default current_timestamp on update current_timestamp,
                       `created` datetime not null default current_timestamp,
                       PRIMARY KEY (`rec_id`)
)engine = innodb default charset =utf8 comment '用户表';

DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`(
                       `rec_id` int(11) not null auto_increment,
                       `name` varchar(512) not null comment '词库名称',
                       `content` text null comment '词库内容 json数组',
                       `status` tinyint(6) not null default 0 comment '状态 0-待审核 1-通过 2-拒绝',
                       `msg` varchar(512) null comment '审核信息',
                       `user_id` int(11) not null comment '创建的用户id',
                       `is_deleted` tinyint(1) not null default 0 comment '是否删除',
                       `modified` datetime not null default current_timestamp on update current_timestamp,
                       `created` datetime not null default current_timestamp,
                       PRIMARY KEY (`rec_id`),
                       KEY `IX_NAME`(`name`)
)engine = innodb default charset =utf8 comment '词库表';

DROP TABLE IF EXISTS `table_info`;
CREATE TABLE `table_info`(
                             `rec_id` int(11) not null auto_increment,
                             `name` varchar(512) not null comment '名称',
                             `fieldName` varchar(512)  not null comment '字段名称',
                             `content` text null comment '字段信息（json）',
                             `status` tinyint(6) not null default 0 comment '状态 0-待审核 1-通过 2-拒绝',
                             `msg` varchar(512) null comment '审核信息',
                             `user_id` int(11) not null comment '创建的用户id',
                             `is_deleted` tinyint(1) not null default 0 comment '是否删除',
                             `modified` datetime not null default current_timestamp on update current_timestamp,
                             `created` datetime not null default current_timestamp,
                             PRIMARY KEY (`rec_id`),
                             KEY `IX_NAME`(`name`)
)engine = innodb default charset =utf8 comment '表信息';

DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`
(
    `rec_id`         int(11) auto_increment comment 'id' primary key,
    `content`        text                               not null comment '内容',
    `type`          int                                not null comment '举报实体类型（0-词库）',
    `reported_id`     int(11)                             not null comment '被举报对象 id',
    `reported_user_id` int(11)                             not null comment '被举报用户 id',
    `status`         tinyint(1)      default 0          not null comment '状态（0-未处理, 1-已处理）',
    `user_id`        bigint                             not null comment '创建用户 id',
    `created`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `modified`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`      tinyint(1)  default 0                 not null comment '是否删除'
)engine = innodb default charset =utf8 comment '举报';