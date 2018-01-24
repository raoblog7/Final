/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classpkg;
import Interfacepkg.MyInterface;
import javax.servlet.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author rahul
 */
public class FullBlog implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{ 
            int id=Integer.parseInt(request.getParameter("id"));
            //System.out.println("blogid="+id);
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select blog_id,blog_title,blog_content,image_path,blog_date_time from blog where blog_id=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,id);
            ResultSet rs;
            rs=m1.toSelect(pst);
            //System.out.println("blogid="+id);
            ArrayList hm=new ArrayList();
            while(rs.next()){
                hm.add(rs.getString("image_path"));
                hm.add(rs.getString("blog_title"));
                hm.add(rs.getString("blog_content"));
                hm.add(rs.getString("blog_date_time"));
                hm.add(rs.getString("blog_id"));
            }
            request.setAttribute("blog", hm);
            System.out.println("blogid="+id);
            con.commit();
            request.getRequestDispatcher("controller?command=blog&case=1").include(request,response);
        }catch(ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }catch(SQLException sqle) {
            sqle.printStackTrace();
        }catch(Exception sqle) {
            sqle.printStackTrace();
        }finally {            
            //out.close();
        }
    }
}
