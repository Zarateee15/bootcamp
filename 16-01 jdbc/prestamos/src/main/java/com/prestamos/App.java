package com.prestamos;

import java.util.Scanner;
import com.prestamos.dao.AsignaturaDAO;
import com.prestamos.dao.PrestamoDAO;
import com.prestamos.dao.ProfesorDAO;
import com.prestamos.model.Asignatura;
import com.prestamos.model.Prestamo;
import com.prestamos.model.Profesor;

public class App {
  public static void limpiarPantalla() {
    try {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        }
    } catch (Exception e) {
        // fallback: imprimir muchas líneas
        for (int i = 0; i < 50; i++) System.out.println();
    }

  }

  public static void main(String[] args) throws Exception {
    ProfesorDAO profesores = new ProfesorDAO();
    AsignaturaDAO asignaturas = new AsignaturaDAO();
    PrestamoDAO prestamos = new PrestamoDAO();
    Scanner sc = new Scanner(System.in);

    limpiarPantalla();
    System.out.println("----------- Menu Principal ----------");
    System.out.println("| 1) Realizar un prestamo           |");
    System.out.println("| 2) Registrar un profesor          |");
    System.out.println("| 3) Registrar una asignatura       |");
    System.out.println("| 4) Ver datos registrados          |");
    System.out.println("-------------------------------------");
    System.out.println("Ingrese una opcion:");

    int opc = sc.nextInt();
    limpiarPantalla();

    switch (opc){
      case 1: 

        System.out.println("Realizandoooo");
        break;

      case 2:

        System.out.println("Ingrese el nombre completo del profesor:");
        String nombreProfesor = sc.nextLine();

        System.out.println("Ingrese la cedula del profesor:");
        long cedulaProfesor = sc.nextLong();

        profesores.crearProfesor(nombreProfesor, cedulaProfesor);
        break;

      case 3: 

        System.out.println("Ingrese el nombre de la asignatura:");
        String nombreAsignatura = sc.nextLine();
        
        asignaturas.crearAsignatura(nombreAsignatura);
        break;

      case 4:

        limpiarPantalla();
        System.out.println("------- Ver Datos Registrados -------");
        System.out.println("| 1) Prestamos                      |");
        System.out.println("| 2) Profesores                     |");
        System.out.println("| 3) Asignaturas                    |");
        System.out.println("| 4) Aulas                          |");
        System.out.println("| 5) Colegios                       |");
        System.out.println("| 6) Cursos                         |");
        System.out.println("| 7) Libros                         |");
        System.out.println("| 8) Editoriales                    |");
        System.out.println("-------------------------------------");
        System.out.println("Ingrese una opcion:");
        opc = sc.nextInt();
        limpiarPantalla();

        switch (opc) {
          case 1:
            for (Prestamo pre : prestamos.listarPrestamos()) {
              System.out.println(pre);
            }
            break;
          
          case 2:
            for (Profesor p : profesores.listarProfesores()) {
              System.out.println(p);
            }
            break;
          case 3:
            for (Asignatura a : asignaturas.listarAsignatura()) {
              System.out.println(a);
            }
          default:
            break;
        }
        break;
    }

    sc.close();
  }
}