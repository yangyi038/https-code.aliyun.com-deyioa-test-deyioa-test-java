

ALTER TABLE `t_picture`
MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `picture_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称' AFTER `id`,
MODIFY COLUMN `old_picture_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片原名称' AFTER `picture_name`,
MODIFY COLUMN `picture_group`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片组' AFTER `old_picture_name`,
MODIFY COLUMN `status`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态' AFTER `picture_group`,
MODIFY COLUMN `upload_time`  datetime NULL DEFAULT NULL COMMENT '上传时间' AFTER `status`,
MODIFY COLUMN `link_type`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接类型' AFTER `upload_time`,
MODIFY COLUMN `link_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接url' AFTER `link_type`,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `is_delete`,
COMMENT='图片管理';


ALTER TABLE `t_title`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称' AFTER `id`,
MODIFY COLUMN `content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本内容' AFTER `name`,
MODIFY COLUMN `status`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态' AFTER `content`,
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `status`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `create_time`,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `is_delete`,
COMMENT='字幕管理表';


ALTER TABLE `t_image_lable`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称' AFTER `id`,
MODIFY COLUMN `lable_describe`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板描述' AFTER `name`,
MODIFY COLUMN `status`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态' AFTER `lable_describe`,
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `status`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `create_time`,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `is_delete`,
COMMENT='图文模板';


ALTER TABLE `t_image_text`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题' AFTER `id`,
MODIFY COLUMN `status`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态' AFTER `name`,
MODIFY COLUMN `show_type`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示格式' AFTER `line_status`,
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `content`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `create_time`,
MODIFY COLUMN `templet`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图文模板' AFTER `is_delete`,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `lable_attribute`,
COMMENT='图文信息表';

ALTER TABLE `t_image_text`
MODIFY COLUMN `status`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态' AFTER `name`;

ALTER TABLE `t_application_manager`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称' AFTER `id`,
MODIFY COLUMN `grader`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用评分' AFTER `developer_name`,
MODIFY COLUMN `synopsis`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用简介' AFTER `apply_parameter`,
MODIFY COLUMN `status`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上线状态' AFTER `synopsis`,
MODIFY COLUMN `online_time`  datetime NULL DEFAULT NULL COMMENT '上线时间' AFTER `status`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `online_time`,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `is_delete`,
COMMENT='应用列表';

ALTER TABLE `t_channel`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `language`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言' AFTER `protective_emblem`,
MODIFY COLUMN `province`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省/州' AFTER `country`,
MODIFY COLUMN `city`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市' AFTER `province`,
MODIFY COLUMN `email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' AFTER `city`,
MODIFY COLUMN `supplier`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容供应商' AFTER `email`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `create_date`,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `is_delete`,
COMMENT='直播频道表';


ALTER TABLE `t_column`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `companyid`  bigint(20) NULL DEFAULT NULL COMMENT '运营商ID' AFTER `id`,
MODIFY COLUMN `classify`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级菜单' AFTER `companyid`,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '栏目名称' AFTER `classify`,
MODIFY COLUMN `number`  int(10) NULL DEFAULT NULL COMMENT '栏目编号' AFTER `name`,
MODIFY COLUMN `type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '栏目类型' AFTER `number`,
MODIFY COLUMN `show_picture`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片显示方式' AFTER `status`,
MODIFY COLUMN `play`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表播放' AFTER `show_picture`,
MODIFY COLUMN `identifying`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '栏目唯一标识' AFTER `operation`,
MODIFY COLUMN `describe_context`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '栏目描述' AFTER `label`,
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `describe_context`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `create_time`,
MODIFY COLUMN `parent_id`  int(11) NULL DEFAULT NULL COMMENT '上级ID' AFTER `is_delete`;



