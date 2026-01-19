package com.prestamos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.prestamos.db.Conexion;
import com.prestamos.model.Asignatura;

public class AsignaturaDAO {
    public List<Asignatura> listarAsignatura() {
        String sql = "SELECT \"idAsignatura\" , \"nombre\" " +
                    "FROM \"Ejercicio5\".\"Asignatura\"";

        List <Asignatura> asignaturas = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while (rs.next()) {
                Asignatura a = new Asignatura(
                    rs.getInt("idAsignatura"),
                    rs.getString("nombre")
                );
                asignaturas.add(a);
            }

            return asignaturas;   

        } catch (Exception e){
            System.out.println("Error listando asignaturas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void crearAsignatura(String nombre) {
        String sql =
            "INSERT INTO \"Ejercicio5\".\"Asignatura\" (\"nombre\") VALUES (?)";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1,nombre);
            ps.executeUpdate();

            System.out.println("Asignatura creada correctamente.");

        } catch  (Exception e){
            System.out.println("Error creando asignatura: " + e.getMessage());
        }
    }
}
