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
public class AddLogin implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        boolean b;
        String pass1="0";
        int status=0;
        int utype=0;
        try {
            //String un=request.getParameter("email");
            //String pass=request.getParameter("pass");
            String pass="rahul123";
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select status,user_type,user_id from login_info where (email=? and password=SHA('"+pass+"'))";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,"raosahabrahul1@gmail.com");
            ResultSet rs;
            rs=m1.toSelect(pst);
            int id=0;
            String full_name=null;
            System.out.println("llkjjjl");
            if(rs.next()){
                System.out.println("llkjjjl");
                //pass1=(rs.getString("password"));
                status=rs.getInt("status");
                utype=rs.getInt("user_type");
                id=rs.getInt("user_id");
                String query1="select first_name,last_name from user_info where user_id=?";
                pst=con.prepareStatement(query1);
                pst.setInt(1, id);
                rs=m1.toSelect(pst);
                
                if(rs.next()){
                    String fn=rs.getString("first_name");
                    String ln=rs.getString("last_name");
                    full_name=fn+" "+ln;
                }
            System.out.println(utype+" "+status);
            if( utype==1){
                if( status==1){
                //System.out.println(b);
                //boolean b1=pass1.equals(pass1);
                //System.out.println(b1);
                System.out.println("hiilkl");
                    query1="select blog_status,blog_title from blog";
                    pst=con.prepareStatement(query1);
                    rs=m1.toSelect(pst);
                    HashMap hs=new HashMap();
                    HashMap hs1=new HashMap();
                    HashMap hs2=new HashMap();
                    String s1,s2;
                    while(rs.next()){      
                        status=rs.getInt("blog_status");
                        s1=rs.getString("blog_title");
                        hs.put(s1,status);
                    }
                request.setAttribute("blog",hs);
                String query2=" select a.email,a.status,b.first_name from login_info a inner join user_info b on(a.user_id=b.user_id)";
                pst=con.prepareStatement(query2);
                rs=m1.toSelect(pst);
                while(rs.next()){      
                    status=rs.getInt("status");
                    s1=rs.getString("email");
                    s2=rs.getString("first_name");
                    hs1.put(s1,s2);
                    hs2.put(s1,status);
                }
                request.setAttribute("login",hs1);
                request.setAttribute("user",hs2);
                HttpSession session = request.getSession(true);
                session.setAttribute("user_name",full_name);
                session.setAttribute("user_id",id);
                System.out.println("hsdcasd");
                request.getRequestDispatcher("/WEB-INF/Pages/admin.jsp").forward(request,response);
                System.out.println("nhi ja rha");
            }
            else{
                    System.out.println("hiinb");
                 response.getWriter().println("Invalid login details");
                //request.getRequestDispatcher("controller?command=errorpage&case=1").forward(request,response);
            }
            }
            }
            else{
                System.out.println("hii12345");
                response.getWriter().println("Invalid login details");
                //request.getRequestDispatcher("controller?command=errorpage&case=1").forward(request,response);
            }
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

