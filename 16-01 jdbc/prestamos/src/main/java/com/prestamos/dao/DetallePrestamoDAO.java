package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.DetallePrestamo;

public class DetallePrestamoDAO {
    public List<DetallePrestamo> listarDetalles(int idPrestamo) {
        String sql =
                "SELECT \"idDetalle\",  \"cantidad\" " +
                        "FROM \"Ejercicio5\".\"DetallePrestamo\" " +
                        "WHERE \"idPrestamo\" = ?";

        List<DetallePrestamo> detPrestamos = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,idPrestamo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetallePrestamo dp = new DetallePrestamo(
                            rs.getInt("idDetalle"),
                            idPrestamo,
                            rs.getInt("cantidad")
                    );
                    detPrestamos.add(dp);
                }
            }

            return detPrestamos;

        } catch (Exception e) {
            System.out.println("Error listando detalles: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public int crearDetallePrestamo(int idPrestamo, int cantidad) {
        String sql =
                "INSERT INTO \"Ejercicio5\".\"DetallePrestamo\" (\"idPrestamo\", \"cantidad\") " +
                        "VALUES (?, ?) RETURNING \"idDetalle\" ";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);
            ps.setInt(2, cantidad);

            try(ResultSet rs =ps.executeQuery()){
                rs.next();
                return rs.getInt(1); // Retorna el id del detalle  creado
            }

        } catch (Exception e) {
            System.out.println("Error creando detalle prestamo: " + e.getMessage());
            return -1;
        }
    }

    public void crearPrestamoLibro(int idDetalle, int idLibro) {
        String sql2 =
                "INSERT INTO \"Ejercicio5\".\"PrestamoLibro\" (\"idDetalle\", \"idLibro\") " +
                        "VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql2)) {

            ps.setInt(1, idDetalle);
            ps.setInt(2, idLibro);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
