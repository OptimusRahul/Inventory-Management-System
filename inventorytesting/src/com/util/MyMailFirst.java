package com.util;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import java.sql.Array;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MyMailFirst {

	private static final Object[] TO_ADDRESS = null;	
	
	public static void main(ArrayList email) throws UnsupportedEncodingException {
		try{
		  String SMTP_HOST = "smtp.gmail.com";
		  String FROM_ADDRESS = "inventorysystemegain@gmail.com";
		  String PASSWORD = "egaininventory";
		  String FROM_NAME = "E-gain";
		  String TO_NAME = "Customers";
		  String TO_ADDRESS[] = getList(email);	  
		  
		  Properties props = new Properties();  
		    String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        Session session = Session.getInstance(props, new SocialAuth());
          Message msg = new MimeMessage(session);

          InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
          msg.setFrom(from);

          InternetAddress[] to = new InternetAddress[TO_ADDRESS.length];
          for(int i=0;i<TO_ADDRESS.length;i++)
          {
        	  to[i]=new InternetAddress(TO_ADDRESS[i]);
          }
          msg.setRecipients(Message.RecipientType.TO,to);

          msg.setSubject("This is the Subject Line!");
          msg.setContent("JAVA PROGRAMMING LANGUAGE.....","text/plain");	          
	      
         // Send message
         Transport.send(msg);
         System.out.println("Sent message successfully....");
	      }
			catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	public static String[] getList(ArrayList al) {
		if(al.size()!=0){
			String str[]=new String[al.size()];
			for(int i=0;i<al.size();i++){
				str[i]=(String) al.get(i);
			} 
			return str;
		}
		return null;
	}
}
	
class SocialAuth extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("inventorysystemegain@gmail.com","egaininventory");
    	}
}

