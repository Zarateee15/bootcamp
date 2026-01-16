package com.prestamos;

import com.prestamos.dao.ProfesorDAO;
import com.prestamos.model.Profesor;

public class App {
  public static void main(String[] args) throws Exception {
    ProfesorDAO profesores = new ProfesorDAO();

    System.out.println("=== Profesores ===");
    for (Profesor p : profesores.listarProfesores()) {
      System.out.println(p);
    }
  }
}