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
            "VALUES (?, ?) RETURNING \"idPrestamo\" ";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(fechaPrestamo)); // formato: yyyy-MM-dd
            ps.setInt(2, idProfesor);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1); // Retorna el id del prestamo creado
            }


        } catch (Exception e) {
            System.out.println("Error creando prestamo: " + e.getMessage());
            return -1;
        }
    }


}
