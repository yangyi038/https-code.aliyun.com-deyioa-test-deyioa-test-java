//姓名验证
	function isName(v) {    
		var name=v;
		if (name.search(/^[\u0391-\uFFE5\w]+$/) != -1){
		return true;    
		}
		else{
		return false;
		}
	} 
	//邮箱验证
	function isEmail(v) {    
		var email=v;
		if (email.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		return true;    
		}
		else{    
		return false;
		}
	} 
	//验证电话号码
	function fucCheckTelPhone(v)         
	{        
		var telphone=v;

		if (telphone.search(/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/) != -1){    
		return true;
		}
		else{    
		return false;
		}
		
		
	} 
	
	function IsTelephone(obj)// 正则判断
	{ 
	var pattern=/\d{3}-\d{8}|\d{4}-\d{7}/; 
	if(pattern.test(obj)) 
	{ 
	return true; 
	} 
	else 
	{ 
	return false; 
	} 
	} 
	
	/**
	 * 验证电话号码
	 * @param str
	 * @returns {Boolean}
	 */
	function checkLinkPhone(str){
		   var phone = str;
		    if(phone==null||phone==""){}
		    else{
			       if(phone.indexOf("(") != -1 && phone.indexOf(")") != -1 )
		    	   {
		    	      return true;
		           }
			       var rule = /^\d{3}-\d{8}$|^\d{4}-\d{7,8}$|^\d{3}-\d{8}-\d{1,4}$|^\d{4}-\d{7,8}-\d{1,4}$|^\d{3}-\d{3}-\d{4}$|^\d{3}-\d{3}-\d{4}-\d{4}$|00|^\+/;
			       if(rule.test(phone)){      
			          return true;       
			       }else{
			          return false;
			       }
		    }
		}
	//验证手机号码
	function fucCheckTEL(v)         
	{        
		var tel=v;
		if (tel.search(/^(1(([35][0-9])|(47)|[8][01236789]))\d{8}$/) != -1){  
		return true;    
		}
		else{    
		return false;
		}
	} 
	
	
	//检测邮政编码
	function checkZipCode(obj)
	{
	  var flag = false;
	  var str=obj;
	  if(str!=""){
	    for(var i=0;i<str.length;i++)
	    {
	      var item = parseInt(str.charAt(i));
	      if(item<0 ||item>9 ||isNaN(item))
	      {
	        flag = true;
	        break;
	      }
	    }
	    if(flag==true){
	      
	      obj.focus();
	      obj.select();
	      return false;
	    }
	  }
	 return true;
	 
	}
	//验证QQ
	function isQQ(v)         
	{         
		var qq=v;
		if (qq.search(/^[1-9]\d{4,8}$/) != -1){   
		return true;    
		}
		else{    
		return false;
		}
	} 
	//msn验证--(和邮箱的一样)
	function isMsn(v)         
	{         
		var msn=v;
		if (msn.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
		return true;    
		}
		else{    
		return false;
		}
	} 
	//生日验证
	function isBirthDay(v)         
	{         
		var day=v;
		if (day.search(/\d{1,4}\-\d{1,2}\-\d{1,2}/) != -1){  
		return true;    
		}
		else{    
		return false;
		}
	}
	/**
	 * 判断字符是否为数字字母下滑线
	 */
	function notchinese(str){
	if (str.search(/[^A-Za-z0-9_]/g)){
	return (false);
	}else{
	return(true);}
	}
	
	
	function getAge(idCard){

		var myDate = new Date(); 
		var month = myDate.getMonth() + 1; 
		var day = myDate.getDate();
		var age = myDate.getFullYear() - idCard.substring(6, 10) - 1; 
		if (idCard.substring(10, 12) < month || idCard.substring(10, 12) == month && idCard.substring(12, 14) <= day) { 
		age++; 
		} 
		return age;
	}