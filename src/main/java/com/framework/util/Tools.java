package com.framework.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/** 工具类 */
public class Tools {
	//创建日期格式
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dfhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	
	
	/** 取得随机主文件名 */
	public synchronized static String getRndFilename(){
		return String.valueOf(System.currentTimeMillis());
	}
	
	/** 取得指定文件的文件扩展名 */
	public synchronized static String getFileExtName(String filename){
		int p = filename.indexOf(".");
		return filename.substring(p);
	}
	
	
	/** HTML代码的Escape处理方法,原来的算法是escapeb，因为在php那边不好处理所以先去掉 */
	public static String  escape(String src){
		return src;
	}
	
	/** HTML代码的UnEscape处理方法,原来的算法是unescapeb，因为在php那边不好处理所以先去掉 */
	public static String  unescape(String src){
		return src;
	}
	/** HTML代码的Escape处理方法 */
	public static String  escapeb(String src){
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length()*6);
		for (i=0;i<src.length();i++){
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))//指定字符是否为数字||指定字符是否为小写字母||指定字符是否为大写字母
				tmp.append(j);
			else if(j<256){
				tmp.append( "%" );
				if (j<16)tmp.append("0");
				tmp.append( Integer.toString(j,16));//把转换成16进制表示
			}else{
				tmp.append("%u");
				tmp.append(Integer.toString(j,16));
			}
		}
		return tmp.toString();
	}
	
	public static String stringReplace(String from, String to, String source) {
		if (source == null) {
			return null;
		}
		// String temp1, temp2;

		int i = -1;// shenyg�޸ļ���Ҫ�滻��λ�������0�������˵� �����޸ĵ�ǰ����
		i = source.indexOf(from);
		while (i != -1) {
			source = source.substring(0, i).concat(to).concat(
					source.substring(i + from.length()));
			i = source.indexOf(from);
		}
		return (source);
	}
	
	
	/** HTML代码的UnEscape处理方法 */
	public static String  unescapeb(String src){
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());//设置字符串容量
		int lastPos=0,pos=0;
		char ch;
		while(lastPos<src.length()){
			pos = src.indexOf("%",lastPos);//查找第一次出现%的位置
			if (pos == lastPos){
				if (src.charAt(pos+1)=='u'){//查找第一次出现%的位置+1是否为u
					ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);
					tmp.append(ch);
					lastPos = pos+6;
				}else{
					ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);
					tmp.append(ch);
					lastPos = pos+3;
				}
			}else{
				if (pos == -1){
					tmp.append(src.substring(lastPos));
					lastPos=src.length();
				}else{
					tmp.append(src.substring(lastPos,pos));
					lastPos=pos;
				}
			}
		}
		return tmp.toString();
	}
	/** 为以逗号分隔的字符串的每个单元加入引号,如:aa,bb-->'aa','bb' */
	public static String formatString(String src){
		StringBuffer result = new StringBuffer();
		result.append("");
		if (src!=null){
			String[] tmp = src.split(",");
			result.append("'"+tmp[0]+"'");
			for(int i=1;i<tmp.length;i++){
				result.append(",'"+tmp[i]+"'");
			}
		}		
		return result.toString();
	}	
	
	public static String cutString(String text, int textMaxChar){   
		return cutstr(text, textMaxChar, " ...");  
    }
	public static String cutstr(String text, int length, String dot) {
		int strBLen = strlen(text);
		if (strBLen <= length) {
			return text;
		}
		int temp = 0;
		StringBuffer sb = new StringBuffer(length);
		char[] ch = text.toCharArray();
		for (char c : ch) {
			sb.append(c);
			if (c > 256) {
				temp += 2;
			} else {
				temp += 1;
			}
			if (temp >= length) {
				if (dot != null) {
					sb.append(dot);
				}
				break;
			}
		}
		return sb.toString();
	}
	public static int strlen(String text) {
		return strlen(text, "UTF-8");
	}
	public static int strlen(String text, String charsetName) {
		if (text == null || text.length() == 0) {
			return 0;
		}
		int length = 0;
		try {
			length = text.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return length;
	}
	
	 /**
	  * 去除html标签的java正则表达方法
	  * @param html
	  * @return
	  */
	 public static String replaceHtml(String html){
			String regEx="<.+?>"; //表示标签
		    Pattern p=Pattern.compile(regEx);
		    Matcher m=p.matcher(html);
		    String s=m.replaceAll("");
		    return s;
		}
	/**
	 * ip获取
	 * @param request
	 * @return
	 */
	  public static String getIpAddr(HttpServletRequest request) {       
	       String ip = request.getHeader("x-forwarded-for");       
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	          ip = request.getHeader("Proxy-Client-IP");       
	      }       
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	          ip = request.getHeader("WL-Proxy-Client-IP");       
	       }       
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	           ip = request.getRemoteAddr();       
	      }       
	     return ip;       
	} 
	public static void main(String[] args){
		System.out.println(escape(""));
		
	}

   /**
    * 正则表达式匹配
    * @param var
    * @param pattern
    * @return
    * @author dingyulei
    */
   public   static   boolean regularString(String var,String pattern){
	   Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(var);
		return m.find();
   }
   public   static    List<String> regularpp(String var,String pattern){	
	   Pattern p = Pattern.compile(pattern);		
	   Matcher m = p.matcher(var);
	   List<String> list=new ArrayList<String>();
	   while(m.find()) {			
		   list.add(m.group(1));		
	   }
		return list;
   }
	public static boolean in_array(Object source, Object ext, boolean strict) {
		if (source == null || ext == null) {
			return false;
		}
		if (source instanceof Collection) {
			for (Object s : (Collection) source) {
				if (s.toString().equals(ext.toString())) {
					if (strict) {
						if ((s.getClass().getName().equals(ext.getClass().getName()))) {
							return true;
						}
					} else {
						return true;
					}
				}
			}
		} else {
			for (Object s : (Object[]) source) {
				if (s.toString().equals(ext.toString())) {
					if (strict) {
						if ((s.getClass().getName().equals(ext.getClass().getName()))) {
							return true;
						}
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}
	

  
   
    /**
     * 按长度截取字符串
     * @param s
     * @param i
     * @return
     * @author dingyulei
     */
    public static String subStrLen(String s,int i){
    	int temptitlelen=i;
		if(s.length()>temptitlelen){//标题字符长度超长
			s=cutString(s, temptitlelen*2);
		}
		return s;
    	
    }
	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.equals(""))) {
			return true;
		} else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
			return true;
		} else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}
	/**
	 * springmvc保存图片
	 * @param path
	 * @param filename
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	 public static boolean saveFilemvc(String path,String filename,MultipartFile file) throws IllegalStateException, IOException{
		  File targetFile = new File(path, filename);  
	        if(!targetFile.exists()){  //判断对象file是否存在。
	            targetFile.mkdirs();  
	        }    
	        file.transferTo(targetFile);   //保存	
			return true;
	 }
	public static String getFirstImg(String content) {
		String regex="<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(content);
		String result="";
	    if(m.find()) {			
		    result=m.group(1);		
	    }
		return result;
	}
	public static String getStringRand(int num){
		Random random = new Random();
		String sRand="";
		for (int i=0;i<num;i++){
		    String rand=String.valueOf(random.nextInt(10));
		    sRand+=rand;
	
		}
		return sRand;
	}
    // 在src中查找符合regEx指定的模式或子串，并替换为rep指定的子串
    // 返回替换后的结果
    public static String invokeRegx(String src, String regEx, String rep) {
    Pattern pat = Pattern.compile(regEx);
    Matcher matcher = pat.matcher(src);
    if (matcher.find()) {
            return matcher.replaceAll(rep);
    }

    else {
            return src;
    }

}
    /**
	 * 把字符串的后n位用“*”号代替
	 * @param str  要代替的字符串
	 * @param n   代替的位数
	 * @return
	 */
    public static String replaceSubString(String str){
    	
		try {
			if(str==null||str.equals("")){
				return "匿名";
			}
			String subs= str.substring(0, 3);
			String sube= str.substring(7, 11);
			return subs+"****"+sube;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return str;
		}
    	
    }
    
    
    public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }
    public static String zfmailhead(String fnames,String addtime,String snames,String thema){
    	StringBuffer sb=new StringBuffer();
    	sb.append("<p><br></p><p><br></p><div style=\"font-size:12px;font-family:Arial Narrow;\">------------------&nbsp;原始邮件&nbsp;------------------</div>");
    	sb.append("<div style=\"font-size:12px;background:#efefef;\">");
    	sb.append("<div><b>发件人:</b>"+fnames+"</div>");
    	sb.append("<div><b>发送时间:</b>"+addtime+"</div>");
    	sb.append("<div><b>收件人:</b>"+snames+"</div>");
    	sb.append("<div><b>主题:</b>"+thema+"</div>");
    	sb.append("</div>");
    	  return sb.toString();
    }
    
  		
    /**
	  * 生成订单号
	  */
	 public static String getorderid(){
		 Date d = new Date();   
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmssSS");//其中yyyy-MM-dd是你要表示的格式 //  可以任意组合，不限个数和次序；具体表示为：MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;   
			String str=sdf.format(d);   
			return str;
	 }	
	  /**
	   *获取年、月、日、时、分、秒格式时间
	   * @return
	   */
	 public static Timestamp getTimestamp(){
		 Date date = new Date();       
		 Timestamp nousedate = new Timestamp(date.getTime());
		return nousedate;
		 
	 }
		public static String clob2Str(Clob clob){  
	        String content = "";  
	        try {  
	            Reader is = clob.getCharacterStream();  
	            BufferedReader buff = new BufferedReader(is);// 得到流  
	            String line = buff.readLine();  
	            StringBuffer sb = new StringBuffer();  
	            while (line != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING  
	                sb.append(line);  
	                line = buff.readLine();  
	            }  
	            content = sb.toString();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return content;  
	    }
}
