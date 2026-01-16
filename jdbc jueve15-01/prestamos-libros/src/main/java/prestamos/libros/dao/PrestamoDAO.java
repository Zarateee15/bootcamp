package prestamos.libros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import prestamos.libros.db.Conexion;
import prestamos.libros.model.Prestamo;

public class PrestamoDAO {

    public List<Prestamo> listarPrestamos() {
        String sql = "SELECT \"idPrestamo\", \"idColegio\", \"idProfesor\", \"fechaPrestamo\", \"fechaDevolucion\", \"estado\" " + "FROM \"Ejercicio5\".\"Prestamo\"";

        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Prestamo p = new Prestamo(
            rs.getInt("idPrestamo"),
            rs.getInt("idColegio"),
            rs.getInt("idProfesor"),
            rs.getString("fechaPrestamo").toString(),
            rs.getString("fechaDevolucion") == null ? null : rs.getString("fechaDevolucion").toString(),
            rs.getString("estado")
        );
        prestamos.add(p);
        }

        return prestamos;

        } catch (Exception e) {
        System.out.println("Error listando prestamos: " + e.getMessage());
        return Collections.emptyList();
        }
    }

    public int crearProfesor(String nombreCompleto, long cedula, String direccion, int idColegio) {
        String sql =
            "INSERT INTO \"Ejercicio5\".\"Profesor\" (\"nombreCompleto\",\"cedula\",\"direccion\",\"idColegio\") " +
            "VALUES (?,?,?,?) RETURNING \"idProfesor\"";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombreCompleto);
        ps.setLong(2, cedula);
        ps.setString(3, direccion);
        ps.setInt(4, idColegio);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
            return rs.getInt("idProfesor");
            }
            return -1; // Por si no devuelve nada
        }

        } catch (Exception e) {
        System.out.println("Error creando profesor: " + e.getMessage());
        return -1;
        }
    }

    public boolean eliminarProfesor(int idProfesor) {
        String sql =
            "DELETE FROM \"Ejercicio5\".\"Profesor\" WHERE \"idProfesor\" = ?";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idProfesor);
        int filas = ps.executeUpdate();
        return filas > 0;

        } catch (Exception e) {
        System.out.println("Error eliminando profesor: " + e.getMessage());
        return false;
        }
    }

    // UPDATE PARCIAL: si un parámetro es null, NO se cambia ese campo
    public boolean actualizarProfesorParcial(
            int idProfesor,
            String nombreCompleto,   // null => no cambia
            Long cedula,             // null => no cambia
            String direccion,        // null => no cambia
            Integer idColegio        // null => no cambia
    ) {
        String sql =
            "UPDATE \"Ejercicio5\".\"Profesor\" " +
            "SET \"nombreCompleto\" = COALESCE(?, \"nombreCompleto\"), " +
            "    \"cedula\" = COALESCE(?, \"cedula\"), " +
            "    \"direccion\" = COALESCE(?, \"direccion\"), " +
            "    \"idColegio\" = COALESCE(?, \"idColegio\") " +
            "WHERE \"idProfesor\" = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, nombreCompleto);
            ps.setObject(2, cedula);
            ps.setObject(3, direccion);
            ps.setObject(4, idColegio);
            ps.setInt(5, idProfesor);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error actualizando profesor: " + e.getMessage());
            return false;
        }
    }
}
