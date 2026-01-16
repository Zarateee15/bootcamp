// Desarrollado por Jorge Zárate
import java.util.Scanner;

public class ejercicio_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa el precio del producto: ");

        float num1 = sc.nextFloat();
        System.out.println("El costo final conm IVA del 10% es: " + (num1*1.1));
    }
}