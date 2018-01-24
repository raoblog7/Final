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
public class UserProfile implements MyInterface {
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{ 
            HttpSession sess=request.getSession(false);
            int id=Integer.parseInt(sess.getAttribute("user_id").toString());
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String query="select a.image_path,a.mobile_no,a.city,b.email from user_info a inner join login_info b on(a.user_id=b.user_id) where a.user_id=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs;
            rs=m1.toSelect(pst);
            ArrayList hm=new ArrayList();
            while(rs.next()){
                hm.add(rs.getString("image_path"));
                hm.add(rs.getString("email"));
                hm.add(rs.getString("mobile_no"));
                hm.add(rs.getString("city"));
            }
            request.setAttribute("blog", hm);
            con.commit();
            request.getRequestDispatcher("controller?command=profile&case=1").forward(request,response);
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
