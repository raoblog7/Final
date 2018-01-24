/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classpkg;
import Interfacepkg.MyInterface;
import javax.servlet.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author rahul
 */
public class FootConnect implements MyInterface {
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        int j=1;
        try{
            String email=request.getParameter("email");
            String name=request.getParameter("name");
            String text=request.getParameter("message");
            String message="Email: "+email+"\nName: "+name+"\nMessage: "+text;
            String subject="From :"+email; 
            final String username=context.getInitParameter("email");
            final String password=context.getInitParameter("email_pass");
            String to="raosahabrahul1@gmail.com";
            String from=username;
            String host="smtp.gmail.com";
            Properties props=new Properties();
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host",host);
            props.put("mail.smtp.port","587");
            Session session=Session.getInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(username,password);
                }
            });
            Message msg=new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            response.getWriter().println("Thankx for your feedback");
            //request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);
        }catch(Exception sqle){
            sqle.printStackTrace();
        }finally{            
            //out.close();
        }
    }
}
