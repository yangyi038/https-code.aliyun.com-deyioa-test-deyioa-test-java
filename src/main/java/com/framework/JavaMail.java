package com.framework; 

import java.util.Properties;  

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Multipart;
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.AddressException;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;

public class JavaMail  {  
 
    // 设置服务器   
    private  String KEY_SMTP = GlobalName.key_smtp;  
    private  String VALUE_SMTP =GlobalName.value_smtp;  
    // 服务器验证   
    private  String KEY_PROPS =GlobalName.key_props;  
    private static boolean VALUE_PROPS = true;  
    // 发件人用户名、密码   
    private String SEND_USER =GlobalName.send_user;  
    private String SEND_UNAME =GlobalName.send_uname;  
    private String SEND_PWD =GlobalName.send_pwd;  
    // 建立会话   
    private MimeMessage message;  
    private Session s;  
  
    /* 
     * 初始化方法 
     */  
   public JavaMail() {  
        Properties props = System.getProperties();  
        props.setProperty(KEY_SMTP, VALUE_SMTP);  
        props.put(KEY_PROPS, VALUE_PROPS);  
        s = Session.getInstance(props);  
        /* s.setDebug(true);开启后有调试信息 */  
        message = new MimeMessage(s);  
    }  

    public int doSendHtmlEmail(String headName, String sendHtml,  
            String receiveUser) {  
        try {  
            // 发件人   
            InternetAddress from = new InternetAddress(SEND_USER);  
            message.setFrom(from);  
            // 收件人   
            InternetAddress to = new InternetAddress(receiveUser);  
            message.setRecipient(Message.RecipientType.TO, to);  
            // 邮件标题   
            message.setSubject(headName);  
            String content = sendHtml.toString();  
            // 邮件内容,也可以使纯文本"text/plain"   
            message.setContent(content, "text/html;charset=GBK");  
            message.saveChanges();  
            Transport transport = s.getTransport("smtp");  
            // smtp验证，就是你用来发邮件的邮箱用户名密码   
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);  
            // 发送   
            transport.sendMessage(message, message.getAllRecipients());  
            transport.close();  
            System.out.println("send success!");  
            return 1;
        } catch (AddressException e) {  
           // TODO Auto-generated catch block   
            e.printStackTrace();  
            return 0;
        } catch (MessagingException e) {  
            e.printStackTrace();  
            return 0;
        }  
   }  
  
    public int sendMail(String title,String content,String receiveUser) {  
        JavaMail se = new JavaMail();  
        return se.doSendHtmlEmail(title,content,receiveUser);  
    } 
    
    public int sendFileMail(String title,String content,String receiveUser,String filePath,String fileName) {  
        JavaMail se = new JavaMail();  
        return se.doSendHtmlFileEmail(title,content,receiveUser,filePath,fileName);  
    }
    
    public int doSendHtmlFileEmail(String headName, String sendHtml,  
            String receiveUser,String filePath,String fileName) {  
        try {  
            // 发件人   
            InternetAddress from = new InternetAddress(SEND_USER);  
            message.setFrom(from);  
            // 收件人   
            InternetAddress to = new InternetAddress(receiveUser);  
            message.setRecipient(Message.RecipientType.TO, to);  
            // 邮件标题   
            message.setSubject(headName);  
            String content = sendHtml.toString();  
            

            
            Multipart multipart = new MimeMultipart();
            
            //设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);
            //添加附件
            BodyPart messageBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            //添加附件的内容
            messageBodyPart.setDataHandler(new DataHandler(source));
            //添加附件的标题
            //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            messageBodyPart.setFileName("=?GBK?B?"+enc.encode(fileName.getBytes())+"?=");
            multipart.addBodyPart(messageBodyPart);
         // 邮件内容,也可以使纯文本"text/plain"   
            message.setContent(multipart, "text/html;charset=GBK");

            message.saveChanges();  
            Transport transport = s.getTransport("smtp");  
            // smtp验证，就是你用来发邮件的邮箱用户名密码   
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);  
            // 发送   
            transport.sendMessage(message, message.getAllRecipients());  
            transport.close();  
            System.out.println("send success!");  
            return 1;
        } catch (AddressException e) {  
           // TODO Auto-generated catch block   
            e.printStackTrace();  
            return 0;
        } catch (MessagingException e) {  
            e.printStackTrace();  
            return 0;
        }  
   }
}  
