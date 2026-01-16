// Desarrollado por Jorge Zárate
import java.util.Scanner;

public class ejercicio_2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el primer numero: ");
        int num1 = sc.nextInt();
        System.out.println("Ingresa el segundo numero: ");
        int num2 = sc.nextInt();

        if (num1 > num2) {
            System.out.println("El primer numero es mayor que el segundo.");
        }else if(num2 > num1 ){
            System.out.println("El segundo numero es mayor que el primero.");
        }else{
            System.out.println("Los numeros son iguales.");
        }
    }
}