package com.framework.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* ���֤��ز���
* @author Lvrl
*
*/
public class IDCard {
/**
* ���췽��
*/
public IDCard(){

}
/*********************************** ���֤��֤��ʼ ****************************************/	
/**
 * ���֤������֤ 
 * 1������Ľṹ
 * ������ݺ�������������룬��ʮ��λ���ֱ������һλУ������ɡ�����˳�������������Ϊ����λ���ֵ�ַ�룬
 * ��λ���ֳ��������룬��λ����˳�����һλ����У���롣
 * 2����ַ��(ǰ��λ���� 
 * ��ʾ�������ס����������(�С��졢��)�������������룬��GB/T2260�Ĺ涨ִ�С� 
 * 3�����������루����λ��ʮ��λ��
 * ��ʾ�������������ꡢ�¡��գ���GB/T7408�Ĺ涨ִ�У��ꡢ�¡��մ���֮�䲻�÷ָ����� 
 * 4��˳���루��ʮ��λ��ʮ��λ��
 * ��ʾ��ͬһ��ַ������ʶ������Χ�ڣ���ͬ�ꡢͬ�¡�ͬ�ճ������˱ඨ��˳��ţ�
 * ˳�����������������ԣ�ż�������Ů�ԡ� 
 * 5��У���루��ʮ��λ����
 * ��1��ʮ��λ���ֱ������Ȩ��͹�ʽ S = Sum(Ai * Wi), i = 0, ... , 16 ���ȶ�ǰ17λ���ֵ�Ȩ���
 * Ai:��ʾ��iλ���ϵ����֤��������ֵ Wi:��ʾ��iλ���ϵļ�Ȩ���� Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
 * 2 ��2������ģ Y = mod(S, 11) ��3��ͨ��ģ�õ���Ӧ��У���� Y: 0 1 2 3 4 5 6 7 8 9 10 У����: 1 0
 * X 9 8 7 6 5 4 3 2
 */

/**
 * ���ܣ����֤����Ч��֤
 * @param IDStr ���֤��
 * @return ��Ч������"" ��Ч������String��Ϣ
 * @throws ParseException
 */
@SuppressWarnings("unchecked")
public static String IDCardValidate(String IDStr) throws ParseException {
	String errorInfo = "";// ��¼������Ϣ
	String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
			"3", "2" };
	String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
			"9", "10", "5", "8", "4", "2" };
	String Ai = "";
	// ================ ����ĳ��� 15λ��18λ ================
	if (IDStr.length() != 15 && IDStr.length() != 18) {
		errorInfo = "���֤���볤��Ӧ��Ϊ15λ��18λ��";
		return errorInfo;
	}
	// =======================(end)========================

	// ================ ���� �������Ϊ��Ϊ���� ================
	if (IDStr.length() == 18) {
		Ai = IDStr.substring(0, 17);
	} else if (IDStr.length() == 15) {
		Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
	}
	if (isNumeric(Ai) == false) {
		errorInfo = "���֤15λ���붼ӦΪ���� ; 18λ��������һλ�⣬��ӦΪ���֡�";
		return errorInfo;
	}
	// =======================(end)========================

	// ================ ���������Ƿ���Ч ================
	String strYear = Ai.substring(6, 10);// ���
	String strMonth = Ai.substring(10, 12);// �·�
	String strDay = Ai.substring(12, 14);// �·�
	if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
		errorInfo = "���֤������Ч��";
		return errorInfo;
	}
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
			|| (gc.getTime().getTime() - s.parse(
					strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
		errorInfo = "���֤���ղ�����Ч��Χ��";
		return errorInfo;
	}
	if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
		errorInfo = "���֤�·���Ч";
		return errorInfo;
	}
	if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
		errorInfo = "���֤������Ч";
		return errorInfo;
	}
	// =====================(end)=====================

	// ================ ������ʱ����Ч ================
	Hashtable h = GetAreaCode();
	if (h.get(Ai.substring(0, 2)) == null) {
		errorInfo = "���֤�����������";
		return errorInfo;
	}
	// ==============================================

	// ================ �ж����һλ��ֵ ================
//	int TotalmulAiWi = 0;
//	for (int i = 0; i < 17; i++) {
//		TotalmulAiWi = TotalmulAiWi
//				+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
//				* Integer.parseInt(Wi[i]);
//	}
//	int modValue = TotalmulAiWi % 11;
//	String strVerifyCode = ValCodeArr[modValue];
//	Ai = Ai + strVerifyCode;
//
//	if (IDStr.length() == 18) {
//		if (Ai.equalsIgnoreCase(IDStr) == false) {
//			errorInfo = "���֤��Ч�����ǺϷ������֤����";
//			return errorInfo;
//		}
//	} else {
//		return "";
//	}
	// =====================(end)=====================
	return "";
}

/**
 * ���ܣ����õ�������
 * @return Hashtable ����
 */
@SuppressWarnings("unchecked")
private static Hashtable GetAreaCode() {
	Hashtable hashtable = new Hashtable();
	hashtable.put("11", "����");
	hashtable.put("12", "���");
	hashtable.put("13", "�ӱ�");
	hashtable.put("14", "ɽ��");
	hashtable.put("15", "���ɹ�");
	hashtable.put("21", "����");
	hashtable.put("22", "����");
	hashtable.put("23", "������");
	hashtable.put("31", "�Ϻ�");
	hashtable.put("32", "����");
	hashtable.put("33", "�㽭");
	hashtable.put("34", "����");
	hashtable.put("35", "����");
	hashtable.put("36", "����");
	hashtable.put("37", "ɽ��");
	hashtable.put("41", "����");
	hashtable.put("42", "����");
	hashtable.put("43", "����");
	hashtable.put("44", "�㶫");
	hashtable.put("45", "����");
	hashtable.put("46", "����");
	hashtable.put("50", "����");
	hashtable.put("51", "�Ĵ�");
	hashtable.put("52", "����");
	hashtable.put("53", "����");
	hashtable.put("54", "����");
	hashtable.put("61", "����");
	hashtable.put("62", "����");
	hashtable.put("63", "�ຣ");
	hashtable.put("64", "����");
	hashtable.put("65", "�½�");
	hashtable.put("71", "̨��");
	hashtable.put("81", "���");
	hashtable.put("82", "����");
	hashtable.put("91", "����");
	return hashtable;
}

/**
 * ���ܣ��ж��ַ����Ƿ�Ϊ����
 * @param str
 * @return
 */
private static boolean isNumeric(String str) {
	Pattern pattern = Pattern.compile("[0-9]*");
	Matcher isNum = pattern.matcher(str);
	if (isNum.matches()) {
		return true;
	} else {
		return false;
	}
}

/**
 * ���ܣ��ж��ַ����Ƿ�Ϊ���ڸ�ʽ
 * @param str
 * @return
 */
public static boolean isDate(String strDate) {
	Pattern pattern = Pattern
			.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	Matcher m = pattern.matcher(strDate);
	if (m.matches()) {
		return true;
	} else {
		return false;
	}
}
/*********************************** ���֤��֤���� ****************************************/

/**
* ���ܣ���15λ���֤ת����18λ
* @param  id
* @return newid or id
*/
public static final String getIDCard_18bit(String id)
{
  //����15λ����ת����18λ������ֱ�ӷ���ID
  if(15 == id.length()){
  final int [] W =
        {
        7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
   final String [] A =
   {
        "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
  int i,j,s=0;
  String newid;
   newid = id;
   newid =newid.substring(0,6)+"19"+newid.substring(6,id.length());
   for ( i=0;i<newid.length() ;i++ )
   {

   j= Integer.parseInt(newid.substring(i,i+1))*W[i];
   s=s+j;
   }
   s = s%11;
   newid=newid+A[s];
 
   return newid;
  }else{
  return id;
  } 

}

/**
* �����֤��ȡ��������
* @param cardNumber �Ѿ�У��Ϸ������֤��
* @return Strig YYYY-MM-DD ��������
*/
public static String getBirthDateFromCard(String cardNumber){
String card = cardNumber.trim();
    String year;
    String month;
    String day;
    if (card.length()==18){ //����18λ���֤
        year=card.substring(6,10);
        month=card.substring(10,12);
        day=card.substring(12,14);
    }else{ //�����18λ���֤
    year=card.substring(6,8);
        month=card.substring(8,10);
        day=card.substring(10,12);
    year="19"+year;        
    }
    if (month.length()==1){
        month="0"+month; //������λ
    }
    if (day.length()==1){
        day="0"+day; //������λ
    }
    return year+"-"+month+"-"+day;
}

/**
* �����֤��ȡ�Ա�
* @param cardNumber �Ѿ�У��Ϸ������֤��
* @return String Sex �Ա�
*/
public static String getSexFromCard(String cardNumber){
String inputStr=cardNumber.toString();
    int sex;
    if (inputStr.length()==18)
    {
        sex=inputStr.charAt(16);
        if (sex%2==0)
        {
            return "1";
        }else{
            return "2";
        }
    }else{
        sex=inputStr.charAt(14);
        if (sex%2==0)
        {
        	 return "1";
        }else{
        	return "2";
        }
    }
}

/**
* �����֤��ȡ��������
* @param cardNumber �Ѿ�У��Ϸ������֤��
* @return Strig YYYY-MM-DD ��������
*/
public static java.sql.Date getBirthFromCard(String cardNumber){
String birthString=getBirthDateFromCard(cardNumber);
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
java.sql.Date birthDate= new java.sql.Date(new Date().getTime());
//���ַ���ת��ΪDate
try {
Date date = df.parse(birthString);
birthDate=new java.sql.Date(date.getTime());
} catch (ParseException e) {
// TODO �Զ����� catch ��
e.printStackTrace();
}
return  birthDate;

}

/**
* �������֤��������
*/
public static   int   getAge(String   IDCardNum)
{  
        Calendar   cal1   =   Calendar.getInstance();  
        Calendar   today   =   Calendar.getInstance();
        //������֤15λ����ת��Ϊ18λ
        if(IDCardNum.length()==15)
        IDCardNum=getIDCard_18bit(IDCardNum);
        cal1.set(Integer.parseInt(IDCardNum.substring(6,10)),  
        Integer.parseInt(IDCardNum.substring(10,12)),  
        Integer.parseInt(IDCardNum.substring(12,14)));  
        return   getYearDiff(today, cal1);  
    }  
 
   public static  int   getYearDiff(Calendar   cal,   Calendar   cal1)
   {  
       int   m   =   (cal.get(cal.MONTH))   -   (cal1.get(cal1.MONTH));  
       int   y   =   (cal.get(cal.YEAR))   -   (cal1.get(cal1.YEAR));  
       return   (y   *   12   +   m)/12;  
   }  

} 

