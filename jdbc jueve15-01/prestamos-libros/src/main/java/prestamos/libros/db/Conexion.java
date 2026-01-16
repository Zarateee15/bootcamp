package prestamos.libros.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Url con la base de datos EjerciciosNormalizacion y el esquma Ejercicio5
    private static final String URL = "jdbc:postgresql://localhost:5432/ejerciciosNormalizacion";
    private static final String USER = "postgres";
    private static final String PASS = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
