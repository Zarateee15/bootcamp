package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Colegio;

public class ColegioDAO {
    public List<Colegio> listarColegios() {
        String sql =
            "SELECT \"idColegio\", \"nombre\" " +
            "FROM \"Ejercicio5\".\"Colegio\"";

        List<Colegio> colegios = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Colegio c = new Colegio(
                    rs.getInt("idColegio"),
                    rs.getString("nombre")
                );
                colegios.add(c);
            }

            return colegios;

        } catch (Exception e) {
            System.out.println("Error listando colegios: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearColegio(String nombre) {
        String sql =
            "INSERT INTO \"Ejercicio5\".\"Colegio\" (\"nombre\") VALUES (?)";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ps.executeUpdate();
            System.out.println("Colegio creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error creando colegio: " + e.getMessage());
        }
    }
}
