package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Curso;

public class CursoDAO {
    public List<Curso> listarCursos() {
        String sql =
            "SELECT \"idCurso\", \"nombre\" " +
            "FROM \"Ejercicio5\".\"Curso\"";

        List<Curso> cursos = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Curso c = new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre")
                );
                cursos.add(c);
            }

            return cursos;

        } catch (Exception e) {
            System.out.println("Error listando cursos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearCurso(String nombre) {
        String sql =
                "INSERT INTO \"Ejercicio5\".\"Curso\" (\"nombre\") VALUES (?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ps.executeUpdate();
            System.out.println("Curso creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error creando curso: " + e.getMessage());
        }
    }

}
