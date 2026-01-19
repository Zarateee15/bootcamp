package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Libro;

public class LibroDAO {
    public List<Libro> listarLibros() {
        String sql =
            "SELECT \"idLibro\", \"nombre\", \"cantidadCopias\", \"idEditorial\" " +
            "FROM \"Ejercicio5\".\"Libro\"";

        List<Libro> libros = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Libro l = new Libro(
                    rs.getInt("idLibro"),
                    rs.getString("nombre"),
                    rs.getInt("cantidadCopias"),
                    rs.getInt("idEditorial")
                );
                libros.add(l);
            }

            return libros;

        } catch (Exception e) {
            System.out.println("Error listando libros: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearLibro(String nombre, int cantidadCopias, int idEditorial) {
        String sql =
                "INSERT INTO \"Ejercicio5\".\"Libro\" (\"nombre\", \"cantidadCopias\", \"idEditorial\") " +
                        "VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, cantidadCopias);
            ps.setInt(3, idEditorial);

            ps.executeUpdate();
            System.out.println("Libro creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error creando libro: " + e.getMessage());
        }
    }

}
