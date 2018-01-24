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
import java.util.*;
/**
 *
 * @author rahul
 */
public class ADUser implements MyInterface {
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{
            int i=0,j=1;
            String check="activate",decheck="deactivate";
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String email=request.getParameter("action");
            String perform=request.getParameter("perform");
            if(check.equals(perform)){
                String query="update login_info set status=? where email=?";
                PreparedStatement pst=con.prepareStatement(query);
                pst.setInt(1,j);
                pst.setString(2,email);
                m1.toUpdate(pst);
                con.commit();
            }
            if(decheck.equals(perform)){
                String query="update login_info set status=? where email=?";
                PreparedStatement pst=con.prepareStatement(query);
                pst.setInt(1,i);
                pst.setString(2,email);
                m1.toUpdate(pst);
                con.commit();
            }
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(Exception sqle){
            sqle.printStackTrace();
        }
        finally {            
            //out.close();
        }
    } 
}
