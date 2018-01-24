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
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
/**
 *
 * @author rahul
 */
public class DynamicBlogDisplay implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        
        boolean b;
        String pass1="0";
        int status=0;
        int utype=0;
        try{
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select blog_id,blog_title,blog_content,image_path from blog where blog_status=1 order by blog_id desc limit 10";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs;
            rs=m1.toSelect(pst);
            JSONObject jobj=new JSONObject();
            JSONArray arr=new JSONArray();
            while(rs.next()){
                JSONObject obj=new JSONObject();
                String sub=rs.getString("blog_content");
                if(sub.length()>=300){
                    sub=sub.substring(0,300);
                }
                obj.put("content",sub);
                obj.put("id",rs.getString("blog_id"));
                obj.put("path",rs.getString("image_path"));
                obj.put("title",rs.getString("blog_title"));
                arr.add(obj);
            }
            jobj.put("blog",arr);
            String res=jobj.toJSONString();
            con.commit();
            response.getWriter().println(res);
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
