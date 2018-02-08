package com.framework;

public class PreventInfusion { 
private static final String inj_str = "'@and@exec@insert@select@delete@update@count@*@%@chr@mid@master@truncate@char@declare@;@or@lock table@grant@drop@ascii@,"; 
    private static String strReplace(String str, String restr) { 
	return str.replace(restr, ""); 
	}  
      
    private static String dealNull(String str) {
	String returnstr = null;
	if (str == null)
	returnstr = "";
	else
	returnstr = str;  
        return returnstr;
		}  
    
    public static String sqlInfusion(String str) { 
	String inj_stra[] = inj_str.split("@");
	str = dealNull(str);  
       for (int i = 0; i < inj_stra.length; i++) {
	   if (str.indexOf(inj_stra[i]) >= 0) {
	   str = strReplace(str, inj_stra[i]);  
            } 
	   if (str.indexOf(inj_stra[i].toLowerCase()) >= 0) {
		   str = strReplace(str, inj_stra[i].toLowerCase());  
	    } 
        }  
        return str;
		}  
      
    public static void main(String[] args) { 
	System.out.println(sqlInfusion(""));
	System.out.println(sqlInfusion("null"));
	System.out.println(sqlInfusion(null)); 
	System.out.println(sqlInfusion("'adm'in,SELEct;")); 
	}  
}