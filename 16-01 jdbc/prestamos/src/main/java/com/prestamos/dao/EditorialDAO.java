package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Editorial;

public class EditorialDAO {
    public List<Editorial> listarEditoriales() {
        String sql =
            "SELECT \"idEditorial\", \"nombre\" " +
            "FROM \"Ejercicio5\".\"Editorial\"";

        List<Editorial> editoriales = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Editorial e = new Editorial(
                    rs.getInt("idEditorial"),
                    rs.getString("nombre")
                );
                editoriales.add(e);
            }

            return editoriales;

        } catch (Exception e) {
            System.out.println("Error listando editoriales: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearEditorial(String nombre) {
        String sql =
                "INSERT INTO \"Ejercicio5\".\"Editorial\" (\"nombre\") VALUES (?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ps.executeUpdate();
            System.out.println("Editorial creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error creando editorial: " + e.getMessage());
        }
    }

}
