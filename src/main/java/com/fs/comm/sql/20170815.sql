


ALTER TABLE `t_stb_log`
ADD COLUMN `companyid`  bigint NULL COMMENT '运营商ID' AFTER `stbnum`;


ALTER TABLE `t_hotel`
ADD COLUMN `txturl`  varchar(255) NULL COMMENT '接口文档中的通知公告管理地址，现用于生成二维码的地址' AFTER `welcome`;

ALTER TABLE `t_hotel`
ADD COLUMN `validdate`  datetime NULL COMMENT '该酒店下机顶盒到期时间' AFTER `txturl`;



