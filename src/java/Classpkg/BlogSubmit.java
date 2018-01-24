/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classpkg;
import Interfacepkg.MyInterface;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
/**
 *
 * @author rahul
 */

public class BlogSubmit implements MyInterface{
    private String getFileName(final Part part){
        for(String content:part.getHeader("Content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(1+content.indexOf('=')).trim().replace("\"","");
            }
        }
        return null;
    }
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        InputStream is =null;
        int blog_id=0,cat_id=0,sub_cat_id=0,status=1;
        String un1="0";
        try{
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select blog_id from blog order by blog_id desc limit 1";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs;
            rs=m1.toSelect(pst);
            if(rs.next()){
                blog_id=rs.getInt("blog_id");
                blog_id++;
            }
            else{
                blog_id=1;
            }
            String query1="select category_id from category order by category_id desc limit 1";
            pst=con.prepareStatement(query1);
            rs=m1.toSelect(pst);
            if(rs.next()){
                cat_id=rs.getInt("category_id");
                cat_id++;
            }
            else{
                cat_id=1;
            }
            String query2="select sub_cat_id from sub_category order by sub_cat_id desc limit 1";
            pst=con.prepareStatement(query2);
            rs=m1.toSelect(pst);
            if(rs.next()){
                sub_cat_id=rs.getInt("sub_cat_id");
                sub_cat_id++;
            }
            else{
                sub_cat_id=1;
            }
            String title=request.getParameter("title");
            String category=request.getParameter("category");
            String subcategory=request.getParameter("sub_category");
            String content=request.getParameter("content");
            System.out.println(content);
            String s=File.separator;
            String hd=System.getProperty("user.home");
            File file=new File(hd+s+"blogimages");
            if(!file.exists()){
                if(file.mkdir()){
                    System.out.println("Directory is created!");
                } 
                else{
                    System.out.println("Failed to create directory!");
                }
            }
            final String path=hd+s+"blogimages";
            final Part filepart=request.getPart("image");
            final String fileName=blog_id+".jpeg";
            OutputStream os=null;
            String abc=path+s+fileName;
            os=new FileOutputStream(new File(abc));
            is=filepart.getInputStream();
            int read=0;
            final byte[] bytes=new byte[1024];
            while((read=is.read(bytes))!=-1){
                os.write(bytes,0,read);
            }
            query="insert into category(category_id,category_name) values(?,?)";
            pst=con.prepareStatement(query);
            pst.setInt(1,cat_id);
            pst.setString(2,category);
            m1.toInsert(pst);
            query="insert into sub_category(sub_cat_id,sub_cat_name,category_id) values(?,?,?)";
            pst=con.prepareStatement(query);
            pst.setInt(1,sub_cat_id);
            pst.setString(2,subcategory);
            pst.setInt(3,cat_id);
            m1.toInsert(pst);
            //query="insert into blog(blog_id,blog_title,blog_content,blog_date_time,blog_status,sub_cat_id,image_path) values('"+blog_id+"','"+title+"','"+content+"',now(),'"+status+"','"+sub_cat_id+"','"+abc+"')";
            //Class.forName("com.mysql.jdbc.Driver");
            //Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/MyBlog","root","rahul123");
            query="insert into blog(blog_id,blog_title,blog_content,blog_date_time,blog_status,sub_cat_id,image_path) values(?,?,?,now(),?,?,?)";
            pst=con.prepareStatement(query);
            pst.setInt(1,blog_id);
            pst.setString(2,title);
            pst.setString(3,content);
            pst.setInt(4,status);
            pst.setInt(5,sub_cat_id);
            pst.setString(6,abc);
            m1.toInsert(pst);
            //m1.toInsert(st,query);
            query="select email from subscribe";
            pst=con.prepareStatement(query);
            rs=m1.toSelect(pst);
            while(rs.next()){
                String un=rs.getString("email");
                String message="New Blog added\n"+title+"\nClick on the link to read this blog  http://localhost:8080/blog/controller?command=FullBlog&case=2&id="+blog_id;
                String subject="Here Comes a blog"; 
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
                con.commit();
            }
            System.out.println("enable");
            
            request.getRequestDispatcher("controller?command=admin&case=1").forward(request,response);
            
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
