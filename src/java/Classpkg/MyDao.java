package Classpkg;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahul
 */
import java.sql.*;

public class MyDao {

    public Statement toConnect(Connection con) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/MyBlog?verifyServerCertificate=false&useSSL=true";
        String uname = "root";
        String password = "rahul123";
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
        //con.setAutoCommit(false);
        return st;
    }
    public void toInsert(Statement st, String query) throws SQLException {
        st.execute(query);
    }
    public ResultSet toSelect(Statement st,String query) throws SQLException{
            ResultSet rs=st.executeQuery(query);
            return rs;
    }
    public void toUpdate(Statement st,String query) throws SQLException{
        st.executeUpdate(query);
    }
    public void toDelete(Statement st,String query) throws SQLException{
        st.execute(query);
    }
}    
