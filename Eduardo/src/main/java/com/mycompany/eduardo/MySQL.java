import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

    public static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "54628");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                System.out.println(rs.getString("ProductName"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}