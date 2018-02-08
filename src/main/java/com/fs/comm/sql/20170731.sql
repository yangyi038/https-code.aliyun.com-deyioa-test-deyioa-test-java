

ALTER TABLE `t_stb_log`
MODIFY COLUMN `stbnum`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机顶盒账号' FIRST ,
MODIFY COLUMN `stbtoken`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '机顶盒token' AFTER `stbnum`,
MODIFY COLUMN `stbid`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '机顶盒ID' AFTER `wanip`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`stbnum`);


INSERT INTO sysmenu(id,FUNCTIONCODE,MENULINK,MENUNAME,menuparent,menuorder,VALID,ADDTIME,FUNCTIONTYPE,img,CSSCLASS,path) 
VALUES(104,'104','','系统菜单配置','89',1,1,CURDATE(),2,NULL,NULL,'|99999999||89||104|');