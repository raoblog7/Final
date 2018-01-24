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
public class EmailSubscription implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{
            System.out.println("no");
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String un=request.getParameter("email");
            String query="select * from subscribe where email=?";
            System.out.println("no");
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,un);
            ResultSet rs=m1.toSelect(pst);
            System.out.println("no");
            if(rs.next()){
                System.out.println("us");
                response.getWriter().println("You have already subscribed");
            }
            else{
                System.out.println("s");
            query="insert into subscribe(email) values(?)";
            pst=con.prepareStatement(query);
            pst.setString(1,un);
            m1.toInsert(pst);
            con.commit();
            response.getWriter().println("Subscription successfuly");
            
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
