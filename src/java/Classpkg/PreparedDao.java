/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classpkg;
import java.sql.*;
/**
 *
 * @author rahul
 */
public class PreparedDao {
    public Connection toConnect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/MyBlog?verifyServerCertificate=false&useSSL=true";
        String uname = "root";
        String password = "rahul123";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, password);
        //PreparedStatement pst=con.prepareStatement(query);
        con.setAutoCommit(false);
        return con;
    }
    public void toInsert(PreparedStatement pst) throws SQLException {
        pst.execute();
    }
    public ResultSet toSelect(PreparedStatement pst) throws SQLException{
            ResultSet rs=pst.executeQuery();
            return rs;
    }
    public void toUpdate(PreparedStatement pst) throws SQLException{
        pst.execute();
    }
    public void toDelete(PreparedStatement pst) throws SQLException{
        pst.execute();
    }
    
}
