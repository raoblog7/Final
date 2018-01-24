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
import static jdk.nashorn.internal.objects.Global.getDate;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
/**
 *
 * @author rahul
 */
public class AddComments implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        
        try{   
            int cid=0,i=1;
            HttpSession sess=request.getSession(false);
            int user_id=Integer.parseInt(sess.getAttribute("user_id").toString());
            String name=sess.getAttribute("user_name").toString();
            int id=Integer.parseInt(request.getParameter("id"));
            String s=request.getParameter("msg");
            System.out.println(s);
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select com_id from comments order by com_id desc limit 1";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=m1.toSelect(pst);
            if(rs.next()){
                cid=rs.getInt("com_id");
                cid++;
            }
            else{
                cid=1;
            }
            query="insert into comments(com_id,com_content,user_id,date_time,blog_id) values(?,?,?,now(),?)";
            pst=con.prepareStatement(query);
            pst.setInt(1,cid);
            pst.setString(2,s);
            pst.setInt(3,user_id);
            pst.setInt(4,id);
            m1.toInsert(pst);
            con.commit();
            response.getWriter().println(name);
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(Exception sqle){
            sqle.printStackTrace();
        }finally {            
            //out.close();
        }
    }
}