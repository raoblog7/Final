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
import javax.servlet.http.HttpSession;
/**
 *
 * @author rahul
 */
public class UpdateCity implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{ 
            HttpSession ses=request.getSession(false);
            String name=ses.getAttribute("user_name").toString();
            int id=Integer.parseInt(ses.getAttribute("user_id").toString());
            String city=request.getParameter("city");
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="update user_info set city=? where user_id=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,city);
            pst.setInt(2,id);
            m1.toUpdate(pst);
            con.commit();
            request.getRequestDispatcher("controller?command=UserProfile&case=2").forward(request,response);
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

