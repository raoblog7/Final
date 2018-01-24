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
import static jdk.nashorn.internal.objects.Global.getDate;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
/**
 *
 * @author rahul
 */
public class DisplayComments implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        
        try{   
            int cid=0,i=1;
            String content=null,fn=null,ln=null,name=null;
            int id=Integer.parseInt(request.getParameter("id"));
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select a.com_content,b.first_name,b.last_name from comments a inner join user_info b on(a.user_id=b.user_id) where (a.blog_id=?) order by a.date_time desc";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,id);
            ResultSet rs=m1.toSelect(pst);
            JSONArray arr=new JSONArray();
            JSONObject obj=new JSONObject();
            while(rs.next()){
                JSONObject jobj=new JSONObject();
                content=rs.getString("a.com_content");
                fn=rs.getString("b.first_name");
                ln=rs.getString("b.last_name");
                name=fn+" "+ln;
                jobj.put("name",name);
                jobj.put("content",content);
                arr.add(jobj);
            }
            obj.put("comments", arr);
            String res=obj.toJSONString();
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
