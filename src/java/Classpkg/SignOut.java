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
/**
 *
 * @author rahul
 */
public class SignOut implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        
        try{
            HttpSession session = request.getSession(false);
            if(session!=null){
            session.invalidate();
            }
            response.sendRedirect("controller");
            //request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);
        }catch(Exception sqle){
            sqle.printStackTrace();
        }finally {            
            //out.close();
        }
    }
}
