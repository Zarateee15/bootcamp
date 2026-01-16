package prestamos.libros;

import prestamos.libros.dao.ProfesorDAO;
import prestamos.libros.model.Profesor;

public class App {
  public static void main(String[] args) throws Exception {
    ProfesorDAO profesores = new ProfesorDAO();

    System.out.println("=== Profesores ===");
    for (Profesor p : profesores.listarProfesores()) {
      System.out.println(p);
    }
  }
}
