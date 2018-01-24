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
public class UpdateImage implements MyInterface{
    @Override
    public void entry(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        InputStream is =null;
        try{
            System.out.println("begin update image");
            HttpSession ses=request.getSession(false);
            String name=ses.getAttribute("user_name").toString();
            int id=Integer.parseInt(ses.getAttribute("user_id").toString());
            PreparedDao m1=new PreparedDao();
            Connection con=m1.toConnect();
            File file=new File("/home/rahul/"+name);
            if(!file.exists()){
                if(file.mkdir()){
                    System.out.println("Directory is created!");
                } 
                else{
                    System.out.println("Failed to create directory!");
                }
            }
            String s=File.separator;
            String hd=System.getProperty("user.home");
            final String path=hd+s+name;
            final Part filepart=request.getPart("image");
            final String fileName="profile.jpeg";
            OutputStream os=null;
            String abc=path+s+ fileName;
            os=new FileOutputStream(new File(abc));
            is=filepart.getInputStream();
            int read=0;
            final byte[] bytes=new byte[1024];
            while((read=is.read(bytes))!=-1){
                os.write(bytes,0,read);
            }
            System.out.println(abc+""+id);
            String query="update user_info set image_path=? where user_id=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,abc);
            pst.setInt(2,id);
            m1.toUpdate(pst);
            con.commit();
            System.out.println("end u image");
            request.getRequestDispatcher("controller?command=UserProfile&case=2").forward(request,response);

            
            
        }catch(Exception sqle){
            sqle.printStackTrace();
        }finally{            
            //out.close();
        }
    
    
    }
}
