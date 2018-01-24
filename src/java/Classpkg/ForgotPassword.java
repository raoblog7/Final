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
import javax.servlet.http.HttpSession;
/**
 *
 * @author rahul
 */
public class ForgotPassword implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{int i=0,j=1;
            String un=request.getParameter("email");
            String pass=request.getParameter("pass");
            PreparedDao m1=new PreparedDao();
            Connection con=m1.toConnect();
            String uuid=UUID.randomUUID().toString();
            String query="select user_id from login_info where email=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,un);
            ResultSet rs=m1.toSelect(pst);
            int user_id=0;
            if(rs.next()){
                user_id=rs.getInt("user_id");
            }
            else{
            request.getRequestDispatcher("controller?command=error&case=1").forward(request,response);
            }
            query="update login_info set password=SHA(?), user_uuid=?, status=? where(user_id=?)";
            pst=con.prepareStatement(query);
            pst.setString(1,pass);
            pst.setString(2,uuid);
            pst.setInt(3,i);
            pst.setInt(4,user_id);
            m1.toUpdate(pst);
            query="insert into reset_password_info(user_id,uuid,status,date_time) values(?,?,?,now())";
            pst=con.prepareStatement(query);
            pst.setInt(1,user_id);
            pst.setString(2,uuid);
            pst.setInt(3,j);
            m1.toUpdate(pst);
            String message="Click on the link to resert you password and login into your account  http://localhost:8080/blog/controller?command=UpdatePassword&case=2&id="+uuid;
            String subject="Reset Password"; 
            final String username=context.getInitParameter("email");
            final String password=context.getInitParameter("email_pass");
            String to=un;
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
            HttpSession ses=request.getSession(false);
            con.commit();
            if(ses!=null){
                    request.getRequestDispatcher("controller?command=UserProfile&case=2").forward(request,response);
                }
                else{
                    request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);

                }
            
            
        }catch(Exception sqle){
            sqle.printStackTrace();
        }
    }
}
