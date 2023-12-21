import java.sql.*;

public class database {

    static final String HOST = "jdbc:mysql://localhost/assignment_db";
    static final String NAME = "xUser1000";
    static final String PASSWORD = "xuser1000";

    public static Connection connection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(HOST, NAME, PASSWORD);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static ResultSet result_set (Connection con, String query) {
        Statement stat;
        ResultSet rs = null;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(query);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

}
