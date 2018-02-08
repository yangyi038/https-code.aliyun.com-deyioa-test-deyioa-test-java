

-- 用户类型    add pzj
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'hoteltype','酒店用户',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'hoteltype','普通用户',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'hoteltype','手机用户',1,3,3);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'hoteltype','公寓用户',1,4,4);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'hoteltype','医院用户',1,5,5);

-- 证件类型 add pzj
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'cardtype','身份证',1,1,1);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'cardtype','护照',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'cardtype','驾驶证',1,3,3);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'cardtype','军官证',1,4,4);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'cardtype','营业执照',1,5,5);

-- 付款方式  add pzj
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'paytype','后付款',1,1,1);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'paytype','先付款',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'paytype','外部代收款',1,3,3);

-- 机顶盒状态    add pzj
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'stbstatus','未激活',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'stbstatus','已激活',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'stbstatus','用户停机',1,3,3);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'stbstatus','欠费停机',1,4,4);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'stbstatus','回收',1,5,5);

-- 账户类型       add pzj
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'accounttype','普通账户',1,1,1);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'accounttype','网关设备',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'accounttype','小Q设备',1,3,3);


--  pzj add at 20170606
-- 流播放状态
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'streamstatus','空闲',1,1,1);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'streamstatus','播放',1,2,2);
-- 心跳状态
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'onlinestatus','中断',1,1,1);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'onlinestatus','正常',1,2,2);
-- 终端类型
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','IPTV',1,1,1);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','iPhone',1,2,2);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','iPad',1,3,3);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','AndroidMobil',1,4,4);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','AndroidStb',1,5,5);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','AndroidPad',1,6,6);
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','PC',1,7,7);

-- pzj add at 20170711 
insert INTO parameter( ptype ,pname,isshow,porder,pcode) 
VALUES ( 'terminaltype','eCosSTB',1,8,8);






