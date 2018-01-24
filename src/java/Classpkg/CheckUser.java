/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classpkg;
import Interfacepkg.MyInterface;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
/**
 *
 * @author rahul
 */
public class CheckUser implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{
            String un=request.getParameter("email");
            PreparedDao m1=new PreparedDao();
            Connection con=m1.toConnect();
            String query="select email from login_info where email=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,un);
            ResultSet rs =m1.toSelect(pst);
            if(rs.next()){
                response.getWriter().println("1");
            }
            else{
               response.getWriter().println("2");
            }
            
        }catch(Exception sqle) {
            sqle.printStackTrace();
        }
    }
}