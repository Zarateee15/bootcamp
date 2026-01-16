package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Prestamo;

public class PrestamoDAO {

    public List<Prestamo> listarPrestamos() {
        String sql =
            "SELECT \"idPrestamo\", \"fechaPrestamo\", \"idProfesor\" " +
            "FROM \"Ejercicio5\".\"Prestamo\"";

        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Prestamo p = new Prestamo(
                    rs.getInt("idPrestamo"),
                    rs.getDate("fechaPrestamo").toString(),
                    rs.getInt("idProfesor")
                );
                prestamos.add(p);
            }

            return prestamos;

        } catch (Exception e) {
            System.out.println("Error listando prestamos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public int crearPrestamo(String fechaPrestamo, int idProfesor) {
        String sql =
            "INSERT INTO \"Ejercicio5\".\"Prestamo\" (\"fechaPrestamo\", \"idProfesor\") " +
            "VALUES (?, ?) RETURNING \"idPrestamo\"";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Si querés, esto se puede hacer mejor con LocalDate y ps.setDate(...)
            ps.setDate(1, java.sql.Date.valueOf(fechaPrestamo)); // formato: yyyy-MM-dd
            ps.setInt(2, idProfesor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("idPrestamo");
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Error creando prestamo: " + e.getMessage());
            return -1;
        }
    }

    public boolean eliminarPrestamo(int idPrestamo) {
        String sql =
            "DELETE FROM \"Ejercicio5\".\"Prestamo\" WHERE \"idPrestamo\" = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error eliminando prestamo: " + e.getMessage());
            return false;
        }
    }

    // UPDATE PARCIAL: si un parámetro es null, NO se cambia ese campo
    public boolean actualizarPrestamoParcial(
            int idPrestamo,
            String fechaPrestamo,  // null => no cambia
            Integer idProfesor     // null => no cambia
    ) {
        String sql =
            "UPDATE \"Ejercicio5\".\"Prestamo\" " +
            "SET \"fechaPrestamo\" = COALESCE(?, \"fechaPrestamo\"), " +
            "    \"idProfesor\" = COALESCE(?, \"idProfesor\") " +
            "WHERE \"idPrestamo\" = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // fechaPrestamo: si viene null, dejamos null y COALESCE mantiene el valor
            if (fechaPrestamo == null) {
                ps.setObject(1, null);
            } else {
                ps.setDate(1, java.sql.Date.valueOf(fechaPrestamo)); // yyyy-MM-dd
            }

            ps.setObject(2, idProfesor); // Integer permite null
            ps.setInt(3, idPrestamo);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error actualizando prestamo: " + e.getMessage());
            return false;
        }
    }
}
