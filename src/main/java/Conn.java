import java.sql.*;


public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        secConnection sec = new secConnection();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = sec.connect();
            s = c.createStatement();
            System.out.println("connection is done");      

        } catch (Exception e) {
            System.out.println(e);
        }
    }    
}
