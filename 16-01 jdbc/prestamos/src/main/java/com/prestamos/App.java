package com.prestamos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.prestamos.dao.AsignaturaDAO;
import com.prestamos.dao.AulaDAO;
import com.prestamos.dao.PrestamoDAO;
import com.prestamos.dao.ProfesorDAO;
import com.prestamos.dao.LibroDAO;
import com.prestamos.dao.EditorialDAO;
import com.prestamos.dao.CursoDAO;
import com.prestamos.dao.ColegioDAO;
import com.prestamos.dao.DetallePrestamoDAO;

import com.prestamos.model.Asignatura;
import com.prestamos.model.Prestamo;
import com.prestamos.model.Profesor;
import com.prestamos.model.Aula;
import com.prestamos.model.Libro;
import com.prestamos.model.Editorial;
import com.prestamos.model.Curso;
import com.prestamos.model.Colegio;
import com.prestamos.model.DetallePrestamo;

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
    AulaDAO aulas = new AulaDAO();
    ColegioDAO colegios = new ColegioDAO();
    CursoDAO cursos = new CursoDAO();
    LibroDAO libros = new LibroDAO();
    EditorialDAO editoriales = new EditorialDAO();
    DetallePrestamoDAO detalles = new DetallePrestamoDAO();

    Scanner sc = new Scanner(System.in);

    limpiarPantalla();
    System.out.println("----------- Menu Principal ----------");
    System.out.println("| 1) Realizar un Prestamo           |");
    System.out.println("| 2) Registrar un Profesor          |");
    System.out.println("| 3) Registrar una Asignatura       |");
    System.out.println("| 4) Registrar un Aula              |");
    System.out.println("| 5) Realizar un Colegio            |");
    System.out.println("| 6) Registrar un Curso             |");
    System.out.println("| 7) Registrar un Libro             |");
    System.out.println("| 8) Registrar una Editorial        |");
    System.out.println("| 9) Ver datos registrados          |");
    System.out.println("-------------------------------------");
    System.out.print("Ingrese una opcion: ");

    int opc = sc.nextInt(); sc.nextLine(); // limpia el Enter
    //limpiarPantalla();

    switch (opc){
      case 1:
        List<Integer> idProfes = new ArrayList<>();
        List<Integer> idLibros = new ArrayList<>();
        String fechaPrestamo = (LocalDate.now()).toString();

        System.out.println("Fecha del prestamo: " + fechaPrestamo);

        // Muestra los profes disponibles
        System.out.println("--------------------------------");
        System.out.println("ID\t\tPROFESOR\t\tCEDULA ");
        System.out.println("--------------------------------");
        for (Profesor p : profesores.listarProfesores()) {
          System.out.println(p);
          idProfes.add(p.getIdProfesor());
        }
        System.out.println("--------------------------------");

        System.out.print("Elija el ID del profe: ");
        int profe = sc.nextInt();

        if (!idProfes.contains(profe)){
          System.out.println("Profesor NO existe :(");
          break;
        }
        int prestamoCreado = prestamos.crearPrestamo(fechaPrestamo,profe); // Se crea el prestamo. FUNCIONA

        // Muestra los libros disponibles
        System.out.println("----------------------------------");
        System.out.println("ID  COPIAS\t\t\tLIBRO");
        System.out.println("----------------------------------");
        for (Libro lb : libros.listarLibros()) {
          System.out.println(lb);
          idLibros.add(lb.getIdLibro());
        }
        System.out.println("----------------------------------");

        System.out.print("Elija el ID del libro: ");
        int libro = sc.nextInt(); sc.nextLine(); // limpia el Enter

        if (!idLibros.contains(libro)){
          System.out.println("Libro NO existe :(");
          break;
        }

        System.out.print("Cantidad a alquilar: ");
        int cantidad = sc.nextInt(); sc.nextLine(); // limpia el Enter

        int detalleCreado = detalles.crearDetallePrestamo(prestamoCreado, cantidad);

        detalles.crearPrestamoLibro(detalleCreado,libro);

        break;

      case 2:

        System.out.print("Ingrese el nombre completo del Profesor: ");
        String nombreProfesor = sc.nextLine();

        System.out.print("Ingrese la cedula del Profesor: ");
        long cedulaProfesor = sc.nextLong();
        sc.nextLine();

        profesores.crearProfesor(nombreProfesor, cedulaProfesor);
        break;

      case 3: 

        System.out.println("Ingrese el nombre de la Asignatura: ");
        String nombreAsignatura = sc.nextLine();
        
        asignaturas.crearAsignatura(nombreAsignatura);
        break;

      case 4: 

        System.out.println("Ingrese el nombre del Aula: ");
        String nombreAula = sc.nextLine();
        
        aulas.crearAula(nombreAula);
        break;

      case 5: 

        System.out.println("Ingrese el nombre del Colegio: ");
        String nombreColegio = sc.nextLine();
        
        colegios.crearColegio(nombreColegio);
        break;

      case 6: 

        System.out.println("Ingrese el nombre del Curso: ");
        String nombreCurso = sc.nextLine();
        
        cursos.crearCurso(nombreCurso);
        break;

      case 7:
        List<Integer> idEditoriales = new ArrayList<>();

        System.out.print("Ingrese el nombre del Libro: ");
        String nombreLibro = sc.nextLine();

        System.out.print("Cantidad de copias disponibles del libro: ");
        int cantCopias = sc.nextInt(); sc.nextLine(); // consumir el Enter

        System.out.println("-----------------");
        System.out.println("ID   EDITORIAL ");
        System.out.println("-----------------");
        for (Editorial ed : editoriales.listarEditoriales()) {
          System.out.println(ed);
          idEditoriales.add(ed.getIdEditorial()); // Se guardan las ids de las editoriales existentes
        }
        System.out.println("-----------------");

        System.out.print("Elija el ID de la editorial: ");
        int Editorial = sc.nextInt();

        if (idEditoriales.contains(Editorial)){
          libros.crearLibro(nombreLibro, cantCopias, Editorial);
        }else{
          System.out.print("Editorial NO existe :(");
        }

        break;

      case 8: 

        System.out.println("Ingrese el nombre de la Editorial: ");
        String nombreEditorial = sc.nextLine();
        
        editoriales.crearEditorial(nombreEditorial);
        break;

      case 9:

        //limpiarPantalla();
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
        //limpiarPantalla();

        switch (opc) {
          case 1: // Prestamos

            for (Prestamo pre : prestamos.listarPrestamos()) {
              System.out.println("--------------------------------");
              System.out.println("ID: "+pre.getIdPrestamo()+"\tFecha Prestamo: "+pre.getFechaPrestamo());
              System.out.println("--------------------------------");
              for (DetallePrestamo dt : detalles.listarDetalles(pre.getIdPrestamo())) {
                System.out.println(dt);
              }
              System.out.println("--------------------------------");
            }
            break;
          
          case 2: // Profesores

            System.out.println("--------------------------------");
            System.out.println("ID\t\tPROFESOR\t\tCEDULA ");
            System.out.println("--------------------------------");
            for (Profesor p : profesores.listarProfesores()) {
              System.out.println(p);
            }
            System.out.println("--------------------------------");
            break;

          case 3: // Asignaturas

            for (Asignatura a : asignaturas.listarAsignatura()) {
              System.out.println(a);
            }
            break;

          case 4: // Aulas

            for (Aula au : aulas.listarAulas()) {
              System.out.println(au);
            }
            break;
          case 5: // Colegios

            for (Colegio co : colegios.listarColegios()) {
              System.out.println(co);
            }
            break;
          case 6: // Cursos

            for (Curso cur : cursos.listarCursos()) {
              System.out.println(cur);
            }
            break;

          case 7: // Libros

            for (Libro li : libros.listarLibros()) {
              System.out.println(li);
            }
            break;
          case 8: // Editoriales

            System.out.println("-----------------");
            System.out.println("ID   EDITORIAL ");
            System.out.println("-----------------");
            for (Editorial ed : editoriales.listarEditoriales()) {
              System.out.println(ed);
            }
            System.out.println("-----------------");
            break;

          default:
            System.out.println("ERROR.");
            break;
        }

        break;

      default:
        System.out.println("ERROR.");
        break;
    }

    sc.close();
  }
}