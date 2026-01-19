package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Aula;

public class AulaDAO {

    public List<Aula> listarAulas() {
        String sql =
            "SELECT \"idAula\", \"nombre\" " +
            "FROM \"Ejercicio5\".\"Aula\"";

        List<Aula> aulas = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Aula a = new Aula(
                    rs.getInt("idAula"),
                    rs.getString("nombre")
                );
                aulas.add(a);
            }

            return aulas;

        } catch (Exception e) {
            System.out.println("Error listando aulas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearAula(String nombre) {
        String sql =
            "INSERT INTO \"Ejercicio5\".\"Aula\" (\"nombre\") VALUES (?)";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ps.executeUpdate();
            System.out.println("Aula creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error creando aula: " + e.getMessage());
        }
    }
}
