package com.blueshit.joke.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 邮件工具类
 *
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/11/18 21:10
 * @description
 */
public class MailUtils {
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    /**
     * 邮件发送工具类
     * @param sender 发送方用户名
     * @param password 发送方密码
     * @param emailTitle 邮件标题
     * @param emailContent 邮件内容
     * @param receiver 邮件接收人
     */
    public static void sendEmail(final String sender, final String password,String emailTitle,String emailContent,String receiver){
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        sendEmail(sender,password,emailTitle,emailContent,receivers);
    }
    /**
     * 邮件发送工具类
     * @param sender 发送方用户名
     * @param password 发送方密码
     * @param emailTitle 邮件标题
     * @param emailContent 邮件内容
     * @param receivers 邮件接收人列表
     */
	public static void sendEmail(final String sender, final String password,String emailTitle,String emailContent,List<String> receivers){
	   Properties props = new Properties();
	   props.put("mail.smtp.host", "smtp.qq.com"); //腾讯smtp服务器地址
	   props.put("mail.smtp.auth", true);  //是否需要认证
	   props.put("mail.smtp.port", "587"); //端口
       props.put("mail.transport.protocol", "smtp"); //邮件协议
	   
	   //获得一个带有authenticator的session实例
	   Session session = Session.getInstance(props,new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(sender,password);
           }
       });
	   session.setDebug(false);//是否打开debug模式，会打印发送细节到console
	   //实例化一个MimeMessage集成自abstract Message 。参数为session
	   Message message = new MimeMessage(session); 
	   try{
		   message.setFrom(new InternetAddress(sender+"@qq.com", "山东中医药大学校友网")); //设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]
		   message.setSubject(emailTitle); //设置标题
		   message.setContent(emailContent,"text/html;charset=utf8");//设置文本内容 单一文本使用setText,Multipart复杂对象使用setContent

		   //message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com")); //设置单个接收方

           //设置多个接收方
           InternetAddress[] ias = new InternetAddress[receivers.size()];
           for(int i=0;i<receivers.size();i++){
               InternetAddress ia = new InternetAddress(receivers.get(i));
               ias[i] = ia;
           }
           message.addRecipients(Message.RecipientType.TO,ias);
		   message.setRecipients(Message.RecipientType.TO, ias);
		   
	       Transport.send(message); //使用Transport静态方法发送邮件
	   }catch(Exception e){
		   logger.error("send email errror:",e);
	   }  
	}

    public static void main(String[] args) {
        List<String> receivers = new ArrayList<String>();
        receivers.add("1036661027@qq.com");
        receivers.add("721680641@qq.com");
        sendEmail("2112947929","itboys","精品推荐","嘻嘻哈哈",receivers);
    }
}
