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
public class ADBlog implements MyInterface {
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        boolean b;
        String check="activate";
        String decheck="deactivate";
        int j=0;
        int i=1;
        try{ 
            PrintWriter out = response.getWriter();   
            String name=request.getParameter("action");
            String perform=request.getParameter("perform");
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            if(check.equals(perform)){
                String query="update blog set blog_status=? where blog_title=?";
                PreparedStatement pst=con.prepareStatement(query);
                pst.setInt(1,i);
                pst.setString(2, name);
                m1.toUpdate(pst);
                con.commit();
            }
            if(decheck.equals(perform)){
                String query="update blog set blog_status=? where blog_title=?";
                PreparedStatement pst=con.prepareStatement(query);
                pst.setInt(1,j);
                pst.setString(2, name);
                m1.toUpdate(pst);
                con.commit();
            }
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch (IOException sqle) {
            sqle.printStackTrace();
        }catch(Exception sqle){
            sqle.printStackTrace();
        }
        finally {            
            //out.close();
        }
    } 
}
