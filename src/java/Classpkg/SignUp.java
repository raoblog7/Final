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
public class SignUp implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        
        boolean b;
        String pass1="0";
        int status=0,user_id;
        int utype=0;
        try{
            String fn=request.getParameter("fn");
            String ln=request.getParameter("ln");
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            String city=request.getParameter("city");
            String mobile=request.getParameter("mobile");
            System.out.println(fn);
            String uuid=UUID.randomUUID().toString();
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select user_id from user_info order by user_id desc limit 1";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=m1.toSelect(pst);
            if(rs.next()){
                user_id=rs.getInt("user_id");
                user_id++;
            }
            else{
                user_id=1;
            }
            query="insert into user_info(user_id,first_name,last_name,mobile_no,city) values(?,?,?,?,?)";
            pst=con.prepareStatement(query);
            pst.setInt(1,user_id);
            pst.setString(2,fn);
            pst.setString(3,ln);
            pst.setString(4,mobile);
            pst.setString(5,city);
            m1.toInsert(pst);
            query="insert into login_info(email,password,status,user_id,user_type,user_uuid) values(?,SHA('"+pass+"'),?,?,?,?)";
            pst=con.prepareStatement(query);
            pst.setString(1,email);
            pst.setInt(2,status);
            pst.setInt(3,user_id);
            pst.setInt(4,utype);
            pst.setString(5,uuid);
            m1.toInsert(pst);
            String message="Click on the link to verify and login your account http://localhost:8080/blog/controller?command=EmailVerify&case=2&id="+uuid;
            String subject="Verification of email"; 
            final String username=context.getInitParameter("email");
            final String password=context.getInitParameter("email_pass");
            String to=email;
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
            con.commit();
            request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(Exception sqle){
            sqle.printStackTrace();
        }finally{            
                //out.close();
        }
    }
}