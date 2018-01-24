/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classpkg;
import Interfacepkg.MyInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rahul
 */
public class EmailUnsubscription implements MyInterface{
     public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{
            PreparedDao m1 = new PreparedDao();
            Connection con=m1.toConnect();
            String un=request.getParameter("email");
            String query="delete from subscribe where(email=?)";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,un);
            m1.toDelete(pst);
            con.commit();
            request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);

            
            
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
