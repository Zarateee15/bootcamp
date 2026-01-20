package py.bootcamp.editorial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import py.bootcamp.editorial.colegio.ColegioService;

import java.util.Scanner;

@SpringBootApplication
public class EditorialApplication implements CommandLineRunner {

    private final ColegioService colegioService;

    public EditorialApplication(ColegioService colegioService) {
        this.colegioService = colegioService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EditorialApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n---------- MENU ----------");
            System.out.println("1) Listar colegios");
            System.out.println("2) Crear colegio");
            System.out.println("0) Salir");
            System.out.println("\n--------------------------");
            System.out.print("Ingrese una opcion: ");

            int opc = Integer.parseInt(sc.nextLine());

            if (opc == 0) break;

            switch (opc) {
                case 1 -> {
                    System.out.println("\n---------- Colegios ----------");
                    colegioService.listar().forEach(System.out::println);
                    System.out.println("\n------------------------------");
                }
                case 2 -> {
                    System.out.print("Nombre del colegio: ");
                    String nombre = sc.nextLine();
                    try {
                        System.out.println("Creado: " + colegioService.crear(nombre));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
}
