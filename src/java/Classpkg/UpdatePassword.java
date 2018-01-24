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
public class UpdatePassword implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{
            int status=1;
            HttpSession ses=request.getSession(false);
            String uuid=request.getParameter("uuid");
            PreparedDao m1=new PreparedDao();
            Connection con=m1.toConnect();
            String query="select status from reset_password_info where uuid=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,uuid);
            ResultSet rs=m1.toSelect(pst);
            if(rs.next()){
               status=rs.getInt("status");
            }
            else{
                request.getRequestDispatcher("controller?command=errorpage&case=1").forward(request,response);
            }
            if(status==0){
                int i=1,j=0;
                query="update reset_password_info set status=? where uuid=?";
                pst=con.prepareStatement(query);
                pst.setInt(1,j);
                pst.setString(1,uuid);
                m1.toUpdate(pst);
                query="update login_info set status=? where uuid=?";
                pst=con.prepareStatement(query);
                pst.setInt(1,i);
                pst.setString(2,uuid);
                m1.toUpdate(pst);
                con.commit();
                if(ses!=null){
                    request.getRequestDispatcher("controller?command=UserProfile&case=2").forward(request,response);
                }
                else{
                    con.commit();
                    request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);

                }
            
            }else{
                    request.getRequestDispatcher("controller?command=errorpage&case=1").forward(request,response);

            }
            
            
        }catch(Exception sqle) {
            sqle.printStackTrace();
        }
    }    
}
