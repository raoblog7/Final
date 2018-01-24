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
public class EmailVerify implements MyInterface {
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        int j=1;
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="update login_info set status=? where user_uuid=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1, j);
            pst.setInt(2,id);
            m1.toUpdate(pst);
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
