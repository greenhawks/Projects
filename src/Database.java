import java.sql.*;
import javax.sql.*;

public class Database {
    String url = "jdbc:mysql://localhost:3306/nikhil";
    String username = "root";
    String password = "Nikhil@2003";
    Connection con;
    Statement st;
    PreparedStatement ps;
    public  Database()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
            st = con.createStatement();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    void create(String username ,String fullname ,String mobnum , String email, String password) throws java.lang.Exception
    {
        String query = "INSERT INTO users VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,fullname);
        ps.setString(3,email);
        ps.setString(4,mobnum);
        ps.setString(5,password);
        ps.executeUpdate();
    }
    public boolean check(String username , String password) throws java.lang.Exception
    {
        String query = "SELECT * FROM users WHERE username = ? and password=?";
        ps=con.prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        int rows = 0;
        while(rs.next())
        {
            rows+=1;
        }
        if(rows==1)
        {
            return true;
        }
        return false;
    }
}
