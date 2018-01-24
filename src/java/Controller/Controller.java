/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interfacepkg.MyInterface;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rahul
 */
@MultipartConfig
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
        
                try  {
                    String s=request.getParameter("command");
                    System.out.println(s);
                    if(s==null){
                        request.getRequestDispatcher("controller?command=index&case=1").forward(request,response);
                    }
                    String n=request.getParameter("case");
                    System.out.println(n);
                    int a;
                    a = Integer.parseInt(n);
                    System.out.println(a);
                    //System.out.println(s);
                    // request.getRequestDispatcher("WEB-INF/pages/signin.jsp").forward(request,response);
                    switch(a){
                        case 1:System.out.println("hii");
                            request.getRequestDispatcher("/WEB-INF/Pages/"+s+".jsp").forward(request,response);
                            System.out.println("hii");
                            break;
                        case 2: //System.out.println("hii");
                            String classname="Classpkg."+s;
                            MyInterface m=(MyInterface)Class.forName(classname).newInstance();
                            //System.out.println("hii");
                            m.entry(request, response, getServletContext());
                            //System.out.println("hii");
                            break;
                           
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{            
                    // out.close();
                }
            }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
