// Desarrollado por Jorge Zárate

import java.util.Scanner;

public class ejercicio_5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un numero: ");
        int num1 = sc.nextInt();
        if (num1 % 2 == 0) {
            System.out.println("El numero ES divisible por 2.");
        }else{
            System.out.println("El numero NO es divisible por 2.");
        }
    }
}