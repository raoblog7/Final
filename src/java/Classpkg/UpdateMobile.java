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
import static java.lang.Math.random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
/**
 *
 * @author rahul
 */
public class UpdateMobile implements MyInterface {
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{ 
            System.out.println("1");
            HttpSession ses=request.getSession(false);
            String name=ses.getAttribute("user_name").toString();
            int id=Integer.parseInt(ses.getAttribute("user_id").toString());
            String mobile=request.getParameter("mobile");
            System.out.println(mobile);
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="update user_info set mobile_no=? where user_id=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,mobile);
            pst.setInt(2,id);
            m1.toUpdate(pst);
            con.commit();
            request.getRequestDispatcher("controller?command=UserProfile&case=2").forward(request,response);
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
