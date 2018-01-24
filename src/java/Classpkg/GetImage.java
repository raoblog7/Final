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
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
/**
 *
 * @author rahul
 */
public class GetImage implements MyInterface{
    public void entry(HttpServletRequest request,HttpServletResponse response,ServletContext context){
        try{
            String s=request.getParameter("path");
            System.out.println("value="+s);
            if(s.equals("")){
                String se=File.separator;
                String hd=System.getProperty("user.home");
                File f=new File(hd+se+"blogimages"+se+"dummy.jpeg");
                byte[] b=Files.readAllBytes(f.toPath());
                response.reset();
                response.setContentType("image/jpeg");
                response.getOutputStream().write(b);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
            else{
                System.out.println("getimage");
                File f=new File(s);
                byte[] b=Files.readAllBytes(f.toPath());
                response.reset();
                response.setContentType("image/jpeg");
                response.getOutputStream().write(b);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        }catch (Exception sqle) {
            sqle.printStackTrace();
        }finally {            
            //out.close();
        }
    }
}
