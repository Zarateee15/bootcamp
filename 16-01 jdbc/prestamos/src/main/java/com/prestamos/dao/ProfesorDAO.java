package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Profesor;

public class ProfesorDAO {

    public List<Profesor> listarProfesores() {
        String sql =
            "SELECT \"idProfesor\", \"nombre\", \"cedula\" " +
            "FROM \"Ejercicio5\".\"Profesor\"";

        List<Profesor> profes = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Profesor p = new Profesor(
                    rs.getInt("idProfesor"),
                    rs.getString("nombre"),
                    rs.getLong("cedula")
                );
                profes.add(p);
            }

            return profes;

        } catch (Exception e) {
            System.out.println("Error listando profesores: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearProfesor(String nombre, long cedula) {
        String sql =
            "INSERT INTO \"Ejercicio5\".\"Profesor\" (\"nombre\", \"cedula\") " +
            "VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setLong(2, cedula);

            ps.executeUpdate();
            System.out.println("Profesor creado correctamente.");    

        } catch (Exception e) {
            System.out.println("Error creando profesor: " + e.getMessage());
        }
    }


    public boolean actualizarProfesor(int idProfesor, String nombre, Long cedula) {
        // Usamos Long para permitir null (si no querés actualizar cedula)
        String sql =
            "UPDATE \"Ejercicio5\".\"Profesor\" " +
            "SET \"nombre\" = COALESCE(?, \"nombre\"), " +
            "    \"cedula\" = COALESCE(?, \"cedula\") " +
            "WHERE \"idProfesor\" = ?";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, nombre);  // puede ser null
            ps.setObject(2, cedula);  // puede ser null
            ps.setInt(3, idProfesor);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error actualizando profesor: " + e.getMessage());
            return false;
        }
    }
}
