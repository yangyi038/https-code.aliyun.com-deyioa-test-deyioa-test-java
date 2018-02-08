

ALTER TABLE `t_sys_parameter`
MODIFY COLUMN `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID' FIRST ,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名称' AFTER `id`,
MODIFY COLUMN `parameter_value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值' AFTER `name`,
MODIFY COLUMN `parameter_type`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数类型' AFTER `parameter_value`,
MODIFY COLUMN `sys_describe`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述' AFTER `parameter_type`,
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `sys_describe`,
MODIFY COLUMN `is_delete`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `create_time`;

ALTER TABLE `t_sys_parameter`
ADD COLUMN `companyid`  bigint NULL COMMENT '运营商ID' AFTER `id`;

ALTER TABLE `t_sys_parameter`
COMMENT='系统参数配置表';

ALTER TABLE `t_streaming_configuration`
MODIFY COLUMN `software_version`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件版本号' AFTER `firmware`,
MODIFY COLUMN `company_id`  bigint(20) NULL DEFAULT NULL COMMENT '运营商id' AFTER `lats_ip`,
MODIFY COLUMN `is_delete`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除' AFTER `company_id`,
COMMENT='流媒体配置';

ALTER TABLE `t_syslog`
MODIFY COLUMN `id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' FIRST ,
MODIFY COLUMN `username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名' AFTER `dotime`,
ADD COLUMN `companyid`  bigint NULL COMMENT '运营商ID' AFTER `id`,
COMMENT='用户操作日志';






