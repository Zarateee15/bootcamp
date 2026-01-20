package py.bootcamp.editorial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import py.bootcamp.editorial.asignatura.AsignaturaController;
import py.bootcamp.editorial.aula.AulaController;
import py.bootcamp.editorial.colegio.ColegioController;
import py.bootcamp.editorial.curso.CursoController;
import py.bootcamp.editorial.editorial.EditorialController;
import py.bootcamp.editorial.libro.LibroController;
import py.bootcamp.editorial.profesor.ProfesorController;
import py.bootcamp.editorial.prestamo.PrestamoController;

import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {

    private final ColegioController colegioController;
    private final ProfesorController profesorController;
    private final AsignaturaController asignaturaController;
    private final AulaController aulaController;
    private final CursoController cursoController;
    private final EditorialController editorialController;
    private final LibroController libroController;
    private final PrestamoController prestamoController;

    public MenuConsola(ColegioController colegioController, ProfesorController profesorController, AsignaturaController asignaturaController, AulaController aulaController, CursoController cursoController, EditorialController editorialController, LibroController libroController, PrestamoController prestamoController) {
        this.colegioController = colegioController;
        this.profesorController = profesorController;
        this.asignaturaController = asignaturaController;
        this.aulaController = aulaController;
        this.cursoController = cursoController;
        this.editorialController = editorialController;
        this.libroController = libroController;
        this.prestamoController = prestamoController;
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int opc = menuPrincipal(sc);
            switch (opc) {
                case 0 -> { return; }
                case 1 -> registrarPrestamo(sc);
                case 2 -> listarPrestamos();
                case 3 -> submenuDatos(sc);
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private int menuPrincipal(Scanner sc) {
        System.out.println("\n---------- MENU ----------");
        System.out.println("1) Registrar un Prestamo");
        System.out.println("2) Listar Prestamos");
        System.out.println("3) Ver/Registrar datos");
        System.out.println("0) Salir");
        System.out.println("--------------------------");
        System.out.print("Ingrese una opcion: ");

        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void registrarPrestamo(Scanner sc) {
        System.out.println("\n[Registrar prestamo] (aca llamas a PrestamoController cuando lo tengas)");
        // ejemplo futuro:
        // prestamoController.registrar(...);

        // por ahora podes ir guiando el flujo:
        System.out.println("Primero elegi colegio/profesor/libro, etc...");
    }

    private void listarPrestamos() {
        System.out.println("\n[Listar prestamos]");
        // prestamoController.listar().forEach(System.out::println);
    }

    private int ingresarOpcion(Scanner sc) {
        int op;
        try { op = Integer.parseInt(sc.nextLine()); return op;}
        catch (NumberFormatException e) { return -1; }

    }

    private void submenuDatos(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Datos --------");
            System.out.println("1) Colegios");
            System.out.println("2) Profesores");
            System.out.println("3) Asignaturas");
            System.out.println("4) Aulas");
            System.out.println("5) Cursos");
            System.out.println("6) Editorial");
            System.out.println("7) Libros");
            System.out.println("0) Volver");
            System.out.println("\n-----------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> menuColegios(sc);
                case 2 -> menuProfes(sc);
                case 3 -> menuAsignaturas(sc);
                case 4 -> menuAulas(sc);
                case 5 -> menuCursos(sc);
                case 6 -> menuEditoriales(sc);
                case 7 -> menuLibros(sc);
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private void menuColegios(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Colegios --------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n--------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> colegioController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Colegio: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + colegioController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private void menuProfes(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Profesores --------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n----------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> profesorController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Profesor: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + profesorController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private void menuAsignaturas(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Asignaturas --------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n-----------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> asignaturaController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Colegio: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + asignaturaController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private void menuAulas(Scanner sc) {
        while (true) {
            System.out.println("\n----------- Aulas -----------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n-----------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> aulaController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Colegio: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + aulaController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }



    private void menuCursos(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Cursos ---------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n--------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> cursoController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Curso: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + cursoController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private void menuEditoriales(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Editoriales ---------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n------------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> editorialController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Curso: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + editorialController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    private void menuLibros(Scanner sc) {
        while (true) {
            System.out.println("\n-------- Libros ---------");
            System.out.println("1) Listar");
            System.out.println("2) Crear");
            System.out.println("0) Volver");
            System.out.println("\n-------------------------");
            System.out.print("Opcion: ");

            int op = ingresarOpcion(sc);

            if (op == 0) break;

            switch (op) {
                case 1 -> libroController.listar().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nombre del Curso: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + libroController.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
}
