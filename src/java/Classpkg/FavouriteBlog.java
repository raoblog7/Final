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
public class FavouriteBlog implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        
        int i=1;
        int status=0;
        int utype=0;
        try{
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            int user_id=Integer.parseInt((request.getParameter("id")));
            String query="select DISTINCT(blog_id) from comments where user_id=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,user_id);
            ResultSet rs,rs1;
            rs=m1.toSelect(pst);
            JSONObject jobj=new JSONObject();
            JSONArray arr=new JSONArray();
            int c=0;
            ab: while(rs.next()){
                    JSONObject obj=new JSONObject();
                    int id=rs.getInt("blog_id");
                    query="select blog_title from blog where blog_status=? and blog_id=?";
                    pst=con.prepareStatement(query);
                    pst.setInt(1,i);
                    pst.setInt(2,id);
                    rs1=m1.toSelect(pst);
                    while(rs1.next()){
                        obj.put("id",id);
                        obj.put("title",rs1.getString("blog_title"));
                        arr.add(obj);
                        c++;
                        if(c==5){
                            break ab;
                        }
                    }
                }
            jobj.put("blog",arr);
            con.commit();
            String res=jobj.toJSONString();
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
