jQuery(function() {
    // 给文本框加个keypress，即键盘按下的时候判断
jQuery(".number").keypress(function(event) {
        if (!$.browser.mozilla) {
            if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 46) {
            	  // ie6,7,8,opera,chrome管用
                event.preventDefault();
            }
        } else {
        if (event.charCode && (event.charCode < 48 || event.charCode > 57) && event.keyCode != 46) {
        	 // firefox管用
                event.preventDefault();
            }
        }
    });
jQuery(".number").blur(function(event) {
	var txt=this.value;
	  if(txt.search("^[0-9]+(.[0-9]{1,2})?$")!=0){
		  modalDialogAlert("请输入至多两位小数的数字！");
	        this.value='';
	        return false;
	    }
});

jQuery(".znumber").keypress(function(event) {
    if (!$.browser.mozilla) {
        if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57)) {
        	  // ie6,7,8,opera,chrome管用
            event.preventDefault();
        }
    } else {
    if (event.charCode && (event.charCode < 48 || event.charCode > 57)) {
    	 // firefox管用
            event.preventDefault();
        }
    }
});
jQuery(".znumber").blur(function(event) {
var txt=this.value;
  if(txt.search("^[0-9]*[0-9][0-9]*$")!=0){
	  modalDialogAlert("请输入整数！");
        this.value='';
        return false;
    }
});

});

function setExportParam(cl){
	var headermate="";
	for(var i=0;i<cl.length;i++){
		if(i==cl.length-1){
			headermate=headermate+cl[i].name+"@"+cl[i].hidden;
			}else{
				headermate=headermate+cl[i].name+"@"+cl[i].hidden+",";
			}
		}
	return headermate;
}
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
} 

//身份证验证   
function checkIdcard(idcard1,showMsg){   
   var idcard=this.trim(idcard1);// 对身份证号码做处理。去除头尾空格。
      
   var Errors=new Array(   
   "验证通过!",   
   "身份证号码位数不对!",   
   "身份证号码出生日期超出范围或含有非法字符!",   
   "身份证号码校验错误!",   
   "身份证地区非法!"  
   );   
   var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}    
      
   var idcard,Y,JYM;   
   var S,M;   
   var idcard_array = new Array();   
   idcard_array = idcard.split(""); 
   /*基本校验*/  
   if(idcard == "" || idcard == null || idcard.length == 0)    
   {   
    if(showMsg==null||showMsg=="") alert("身份证号为空，请输入您的身份证号！");     
       
    return false;   
   }   
   /*地区检验*/  
   if(area[parseInt(idcard.substr(0,2))]==null)    
   {   
    if(showMsg==null||showMsg=="") alert(Errors[4]);    
       
    return false;   
   }   
   /*身份号码位数及格式检验*/  
   switch(idcard.length){   
    case 15:   
    if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){   
     ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性   
    } else {   
     ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性   
    }   
    if(ereg.test(idcard)){   
       
    	
      return true; //15位验证通过   
     }   
    else {   
      if(showMsg==null||showMsg=="") alert(Errors[2]);   
       return false;   
      }   
    break;   
       
    case 18:   
    //18位身份号码检测   
    //出生日期的合法性检查    
    //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))   
    //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))   
    if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){   
    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式   
    } else {   
    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式   
    }   
    if(ereg.test(idcard)){//测试出生日期的合法性   
     //计算校验位   
     S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7   
     + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9   
     + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10   
     + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5   
     + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8   
     + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4   
     + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2   
     + parseInt(idcard_array[7]) * 1    
     + parseInt(idcard_array[8]) * 6   
     + parseInt(idcard_array[9]) * 3 ;   
     Y = S % 11;   
     M = "F";   
     JYM = "10X98765432";   
     M = JYM.substr(Y,1);/*判断校验位*/  
     if(M == idcard_array[17]){   
      //alert(Errors[0]+"18");  
      return true; /*检测ID的校验位false;*/  
     }   
     else {   
      if(showMsg==null||showMsg=="") alert(Errors[3]);    
      return false;   
     }   
    }   
    else {   
     if(showMsg==null||showMsg=="") alert(Errors[2]);    
     return false;   
    }   
    break;   
       
    default:   
     if(showMsg==null||showMsg=="") alert(Errors[1]);    
     return false;   
   }   
 }
 
 //计算字符串长度；
function strLen(str){
  var len = 0;   
  for(i=0;i<str.length;i++)   
  {   
 if(str.charCodeAt(i)>256)   
 {   
  len += 2;   
 }   
 else   
 {   
  len++;   
 }   
  }   
 return len;
}
/**  
 * 通过身份证判断是男是女  
 * @param idCard 15/18位身份证号码   
 * @return '25'-女、'24'-男  
 */  
function maleOrFemalByIdCard(idCard){   
    var IdCard = this.trim(idCard);// 对身份证号码做处理。去除头尾空格。 
    if(IdCard.length==15){   
        if(IdCard.substring(14,15)%2==0){   
            return '2';   
        }else{   
            return '1';   
        }   
    }else if(IdCard.length ==18){   
        if(IdCard.substring(14,17)%2==0){   
            return '2';   
        }else{   
            return '1';   
        }   
    }
}
/* 身份证号确定出生日期 */
function birthdate(idCard){
   var bir = null;
   var IdCard = this.trim(idCard);   
   if(IdCard.length==18){
     bir=IdCard.substr(6,4)+"-"+IdCard.substr(10,2)+"-"+IdCard.substr(12,2);
     }
   else if(IdCard.length==15){
     bir="19"+IdCard.substr(6,2)+"-"+IdCard.substr(8,2)+"-"+IdCard.substr(10,2);
     }
     return bir;
}
function checkIp(ip){
	 var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
     if(ip.match(reg))
     {
        return true;
     }
     else 
     {
       return false;
     }

	
}
/**
 * return IE,IE6,IE7,IE8,IE9,Chrome,Firefox,Opera,WebKit,Safari,Others
*/
function getBrowserName()
{
  var browserName;
  var isOpera = check(/opera/);
  var isChrome = check(/chrome/);
  var isFirefox = check(/firefox/);
  var isWebKit = check(/webkit/);
  var isSafari = !isChrome && check(/safari/);
  var isIE = !isOpera && check(/msie/);
  var isIE7 = isIE && check(/msie 7/);
  var isIE8 = isIE && check(/msie 8/);
  if(isIE8)
  {
    browserName = "IE";  
  }else if(isIE7)
  {
    browserName = "IE";
  }else if(isIE)
  {
    browserName = "IE";
  }else if(isChrome)
  {
    browserName = "Chrome";
  }else if(isFirefox)
  {
    browserName = "Firefox";
  }else if(isOpera)
  {
    browserName = "Opera";
  }else if(isWebKit)
  {
    browserName = "WebKit";
  }else if(isSafari)
  {
    browserName = "Safari";
  }else
  {
    browserName = "Others";
  }
  return browserName;
} 
