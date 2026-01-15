package prestamos.libros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
  public static void main(String[] args) throws Exception {
    String url = "jdbc:postgresql://localhost:5432/ejerciciosNormalizacion";
    String user = "postgres";
    String pass = "12345";

    try (Connection con = DriverManager.getConnection(url, user, pass);
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT 1")) {

      rs.next();
      System.out.println("Conectado ✅ -> " + rs.getInt(1));
    }
  }
}
